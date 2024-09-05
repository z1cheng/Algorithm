package algorithm;

import java.util.Arrays;

/**
 * 数位 DP
 * 参数解释：
 * i，表示下标 i 的数位
 * limitLow/limitHigh，表示是否受到上下界的约束
 * isNum（可选）考虑前导 0 情况，表示 i 之前是否填了数字，false则当前也可以跳过否则至少填 1；true 则可以从 0 开始
 * mask（可选）表示选过的数字集合，用二进制表示
 */
public class DigitalDP {

    /**
     * low 表示下界，以字符数组表示
     */
    private char[] low;
    /**
     * high 表示上界，以字符数组表示
     */
    private char[] high;
    private long[] memo;

    /**
     * 二维状态数组
     */
    private long[][] memo2;

    public void call(int n) {
        call("0", String.valueOf(n));
    }

    public void call(String low, String high) {
        this.high = high.toCharArray();
        int len = this.high.length;
        //补上前导 0，和 high 对齐
        this.low = ("0".repeat(len - low.length()) + low).toCharArray();
        this.memo = new long[len];
        Arrays.fill(memo, -1);
        this.memo2 = new long[len][1 << 10];// mask最大是 0~9 都选了，即 (1 << 10)-1
        for (var m : memo2) {
            Arrays.fill(m, -1);
        }
        // call dfs...
    }

    /**
     * 不考虑前导 0
     * dfs(0, true, true)
     *
     * @param i         当前位下标
     * @param limitLow  前面数位是否达到下界，true 表示达到
     * @param limitHigh 前面数位是否达到上界，true 表示达到
     * @return
     */
    private long dfs(int i, boolean limitLow, boolean limitHigh) {
        if (i == high.length) {
            // 判断是否 valid
            return 1;
        }
        if (!limitLow && !limitHigh && memo[i] != -1) {
            return memo[i]; // 之前计算过
        }
        // 第 i 个数位可以从 lowVal 枚举到 highVal
        // 如果对数位还有其它约束，应当只在下面的 for 循环做限制，不应修改 lowVal 或 highVal
        int lowVal = limitLow ? low[i] - '0' : 0;
        int highVal = limitHigh ? high[i] - '0' : 9;
        long res = 0;
        for (int cur = lowVal; cur <= highVal; cur++) {
            res += dfs(i + 1, limitLow && cur == lowVal, limitHigh && cur == highVal);
        }
        if (!limitLow && !limitHigh) {
            memo[i] = res;
        }
        return res;
    }

    /**
     * 前导 0
     * dfs(0, true, true, false)
     *
     * @param i         当前位下标
     * @param limitLow  前面数位是否达到下界
     * @param limitHigh 前面数位是否达到上界
     * @param isNum     前面数位是否填了非 0 数字，true 填了，false 没填
     * @return
     */
    private long dfs(int i, boolean limitLow, boolean limitHigh, boolean isNum) {
        if (i == high.length) {
            // 判断是否 valid
            return isNum ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i] != -1) {
            return memo[i]; // 之前计算过
        }
        long res = 0;
        if (low[i] == '0' && !isNum) { //前面填的都是前导 0，非数字，limitLow 肯定是 true
            //当前也可以跳过
            res += dfs(i + 1, true, false, false);
        }
        // 第 i 个数位可以从 lowVal 枚举到 highVal
        // 如果对数位还有其它约束，应当只在下面的 for 循环做限制，不应修改 lowVal 或 highVal
        int lowVal = limitLow ? low[i] - '0' : 0;
        int highVal = limitHigh ? high[i] - '0' : 9;
        // 如果前面填了数字，当前数位可以从 0 开始；如果前面还是没填（跳过）那么只能从 1 开始
        for (int cur = Math.max(isNum ? 0 : 1, lowVal); cur <= highVal; cur++) {
            res += dfs(i + 1, limitLow && cur == lowVal, limitHigh && cur == highVal, true);
        }
        if (!limitLow && !limitHigh) {
            memo[i] = res;
        }
        return res;
    }

    /**
     * 前导 0，mask
     * 此时需要二维状态数组
     * dfs(0, 0, true, true, false)
     *
     * @param i         当前位下标
     * @param mask      选过的数字集合
     * @param limitLow  前面数位是否达到下界
     * @param limitHigh 前面数位是否达到上界
     * @param isNum     前面数位是否填了非 0 数字，true 填了，false 没填
     * @return
     */
    private long dfs(int i, int mask, boolean limitLow, boolean limitHigh, boolean isNum) {
        if (i == high.length) {
            // 判断是否 valid
            return isNum ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo2[i][mask] != -1) {
            return memo2[i][mask]; // 之前计算过
        }
        long res = 0;
        if (low[i] == '0' && !isNum) { //前面填的都是前导 0，非数字，limitLow 肯定是 true
            //当前也可以跳过
            res += dfs(i + 1, 0, true, false, false);
        }
        // 第 i 个数位可以从 lowVal 枚举到 highVal
        // 如果对数位还有其它约束，应当只在下面的 for 循环做限制，不应修改 lowVal 或 highVal
        int lowVal = limitLow ? low[i] - '0' : 0;
        int highVal = limitHigh ? high[i] - '0' : 9;
        // 如果前面填了数字，当前数位可以从 0 开始；如果前面还是没填（跳过）那么只能从 1 开始
        for (int cur = Math.max(isNum ? 0 : 1, lowVal); cur <= highVal; cur++) {
            if (((mask >> cur) & 1) == 0) { // 没选过
                res += dfs(i + 1, mask | (1 << cur), limitLow && cur == lowVal, limitHigh && cur == highVal, true);
            }
        }
        if (!limitLow && !limitHigh) {
            memo2[i][mask] = res;
        }
        return res;
    }
}
