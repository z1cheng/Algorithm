package algorithm;

public class ZFunc {
    //z function，返回一个数组存储 s[i:] 与 s 的最长公共前缀长度
    //z-box 指的是上次匹配的最长前缀
    //那么 z-box 区间和 s[i:] 其实就是最开始的 s[0:] 和 s[1:] 的关系，所以可以直接复用 z[i - left] 值，但同时不超过 z-box 区间
    public static int[] zFunc(String s) {
        int n = s.length();
        int[] z = new int[n];
        int left = 0, right = 0;
        for (int i = 1; i < n; i++) {
            if (i <= right) {// 此时 i 在 z-box 中
                //right - i + 1 即 i 到 zbox 右边界的长度
                z[i] = Math.min(right - i + 1, z[i - left]);
            }
            //暴力匹配
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                left = i;
                right = i + z[i];
                z[i]++;
            }
        }
        return z;
    }
}
