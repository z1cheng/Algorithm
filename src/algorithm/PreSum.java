package algorithm;

/**
 * 前缀和
 * See <a href="https://leetcode.cn/circle/discuss/UUuRex/">二维前缀和</a>
 */
public class PreSum {

    /**
     * 一维前缀和
     *
     * @param nums
     * @return
     */
    public int[] preSum(int[] nums) {
        int n = nums.length;
        // preSum[i] 表示[0:i-1]的和
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        return preSum;
    }

    /**
     * 一维区间和，[left, right] 区间和
     *
     * @param preSum
     * @param left
     * @param right
     * @return
     */
    public int rangeSum(int[] preSum, int left, int right) {
        return preSum[right + 1] - preSum[left];
    }

    /**
     * 二维前缀和
     *
     * @param matrix
     * @return
     */
    public int[][] preSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // preSum[i][j] 表示[0:i-1][0:j-1]的和
        int[][] preSum = new int[m + 1][n + 1];
        // 初始化
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + matrix[i][j];
            }
        }
        return preSum;
    }

    /**
     * 二维区域和, [x1, y1] 到 [x2, y2] 的矩形的区域和
     *
     * @param preSum
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public int regionSum(int[][] preSum, int x1, int y1, int x2, int y2) {
        return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
    }
}
