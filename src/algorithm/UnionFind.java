package algorithm;

/**
 * 并查集，是一种树形结构，可以快速判断两个结点是否连通
 */
public class UnionFind {
    // 连通块个数，optional
    private int count;
    // 存储每个节点的父节点
    // 根节点的父节点是它自己，即 parent[x] = x
    private final int[] parent;

    // n 为图中节点的个数
    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) {
            return;
        }

        parent[rootQ] = rootP;
        // 两个连通分量合并成一个连通分量
        count--;
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 获取节点 x 的根节点
    // 彻底的路径压缩，递归实现
    public int find(int x) {
        if (parent[x] != x) { // 如果 x 不是根节点
            parent[x] = find(parent[x]); // 递归向上找根节点，然后把 x 的父节点指向根节点
        }
        return parent[x];
    }

    //路径压缩迭代实现
//    public int find(int x) {
//        // 先找到根节点
//        int root = x;
//        while (parent[root] != root) {
//            root = parent[root];
//        }
//        // 然后把 x 到根节点之间的所有节点直接接到根节点下面
//        int oldParent = parent[x];
//        while (x != root) {
//            parent[x] = root;
//            x = oldParent;
//            oldParent = parent[oldParent];
//        }
//        return root;
//    }

    // 返回图中的连通块个数
    public int count() {
        return count;
    }
}
