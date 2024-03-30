package algorithm;

import java.util.Random;

public class ReservoirSampling {

    private int[] reservoir;
    private int n;
    private Random rand;

    public ReservoirSampling(int k) {
        this.reservoir = new int[k];
        this.n = 0;
        this.rand = new Random();
    }

    public void add(int num) {
        if (n < reservoir.length) {
            reservoir[n] = num;
        } else {
            // j 范围是 [0, n - 1]
            // 当前添加的概率 k / n
            int j = rand.nextInt(n + 1);
            if (j < reservoir.length) {
                reservoir[j] = num;
            }
        }
        n++;
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
