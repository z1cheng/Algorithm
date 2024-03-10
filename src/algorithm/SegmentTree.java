package algorithm;

/**
 * 线段树
 * todo: 目前只支持单点修改和区间总和
 */
public class SegmentTree {

    //维护最小值
    private final int[] min;
    //维护总和
    private final long[] sum;

    public SegmentTree(int n) {
        min = new int[n * 4];
        sum = new long[n * 4];
    }

    // 将 idx 上的元素值增加 val
    public void add(int cur, int left, int right, int idx, int val) {
        if (left == right) {
            min[cur] += val;
            sum[cur] += val;
            return;
        }
        var mid = (left + right) / 2;
        if (idx <= mid) {
            add(cur * 2, left, mid, idx, val);
        } else {
            add(cur * 2 + 1, mid + 1, right, idx, val);
        }
        min[cur] = Math.min(min[cur * 2], min[cur * 2 + 1]);
        sum[cur] = sum[cur * 2] + sum[cur * 2 + 1];
    }

    // 返回区间 [L,R] 内的元素和
    public long rangeSum(int cur, int left, int right, final int L, final int R) { // L 和 R 在整个递归过程中均不变，将其大写，视作常量
        if (L <= left && right <= R) {
            return sum[cur];
        }
        var sum = 0L;
        var mid = (left + right) / 2;
        if (L <= mid) {
            sum += rangeSum(cur * 2, left, mid, L, R);
        }
        if (R > mid) {
            sum += rangeSum(cur * 2 + 1, mid + 1, right, L, R);
        }
        return sum;
    }

    // 返回区间 [1,R] 中 <= val 的最靠左的位置，不存在时返回 0
    public int index(int cur, int left, int right, final int R, int val) { // R 在整个递归过程中均不变，将其大写，视作常量
        if (min[cur] > val) {
            return 0; // 说明整个区间的元素值都大于 val
        }
        if (left == right) {
            return left;
        }
        var mid = (left + right) / 2;
        if (min[cur * 2] <= val) {
            return index(cur * 2, left, mid, R, val); // 看看左半部分
        }
        if (mid < R) {
            return index(cur * 2 + 1, mid + 1, right, R, val); // 看看右半部分
        }
        return 0;
    }
}
