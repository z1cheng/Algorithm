package algorithm;

/**
 * 树状数组，支持单点修改，区间查询
 */
public class FenwickTree {

    private final int[] tree;

    //tree idx从 1 开始，所以初始化 n+1
    public FenwickTree(int n) {
        tree = new int[n];
    }

    private int lowbit(int i) {
        return i & -i;
    }

    // a[i] 增加 val
    // 1<=i<=n
    public void add(int i, int val) {
        while (i < tree.length) {
            tree[i] += val;
            i += lowbit(i);
        }
    }

    // 求前缀和 a[1] + ... + a[i]
    // 1<=i<=n
    public int preSum(int i) {
        int res = 0;
        while (i > 0) {
            res += tree[i];
            i &= i - 1;
        }
        return res;
    }

    // 求区间和 a[l] + ... + a[r]
    // 1<=l<=r<=n
    public int rangeSum(int left, int right) {
        return preSum(right) - preSum(left - 1);
    }
}
