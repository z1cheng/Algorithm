package algorithm;

public class ManhattanDistance {

    /**
     * Given a points array, return the maximum Manhattan distance.
     * 曼哈顿距离 = |x1 - x2| + |y1 - y2|
     * 设置 t = x + y, k = x - y，我们需要计算 max(maxT - minT, maxK - minK)
     * 也就是最大曼哈顿距离取自与「最大的 x+y 与最小的 x+y 之差或者和最大的 x-y 与最小的 x-y 之差」
     *
     * @param points the points array (the point is (x, y), x >= 0, y >= 0)
     * @return the maximum Manhattan distance
     */
    public static int maximumManhattanDistance(int[][] points) {
        int n = points.length;
        // t[i] = x + y, k[i] = x - y
        int[] t = new int[n];
        int[] k = new int[n];
        // 计算 x+y 和 x-y
        for (int i = 0; i < n; i++) {
            t[i] = points[i][0] + points[i][1];
            k[i] = points[i][0] - points[i][1];
        }
        int maxT = 0, minT = Integer.MAX_VALUE, maxK = 0, minK = Integer.MAX_VALUE;
        // 找到最大最小的 x+y 和 x-y
        for (int i = 0; i < n; i++) {
            maxT = Math.max(maxT, t[i]);
            minT = Math.min(minT, t[i]);
            maxK = Math.max(maxK, k[i]);
            minK = Math.min(minK, k[i]);
        }
        return Math.max(maxT - minT, maxK - minK);
    }
}
