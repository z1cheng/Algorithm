package algorithm;

import java.util.Random;

/**
 * 蓄水池采样算法，用于从未知大小的数据流中随机选取 k 个元素
 */
public class ReservoirSampling {

    private final int[] reservoir;
    private int curIdx;
    private final Random rand;

    public ReservoirSampling(int k) {
        this.reservoir = new int[k];
        this.curIdx = 0;
        this.rand = new Random();
    }

    public void add(int num) {
        if (curIdx < reservoir.length) {
            reservoir[curIdx] = num;
        } else {
            // j 范围是 [0, curIdx]
            // 当前添加的概率 k / (curIdx+1)
            int j = rand.nextInt(curIdx + 1);
            if (j < reservoir.length) {
                reservoir[j] = num;
            }
        }
        curIdx++;
    }

    public int[] getReservoir() {
        return reservoir;
    }

    public static void main(String[] args) {
        int n = 42;
        int k = 7;
        int times = 100000;
        int[] count = new int[n + 1];
        for (int i = 0; i < times; i++) {
            ReservoirSampling rs = new ReservoirSampling(k);
            for (int j = 1; j <= n; j++) {
                rs.add(j);
            }
            for (int j : rs.getReservoir()) {
                count[j]++;
            }
        }
        System.out.println("每个球被选中的概率: " + (double) k / n);
        for (int i = 1; i <= n; i++) {
            System.out.println(i + "选中次数: " + count[i] + ", 选中概率: " + (double) count[i] / times);
        }
    }
}
