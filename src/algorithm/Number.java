package algorithm;

public class Number {

    //阶乘
    private int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    //带取模的阶乘
    private int factorial_mod(int n, int mod) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            res %= mod;
        }
        return res % mod;
    }

    // 求不同的质因数个数
    // 预处理(10 ^ 5 +1)
    // 时间复杂度 o(n * log log n)
    public static int[] omega() {
        var res = new int[100001];
        for (int i = 2; i < res.length; i++) {
            if (res[i] == 0) { // i 是质数
                for (int j = i; j < res.length; j += i) {
                    res[j]++; // i 是 j 的质因数
                }
            }
        }
        return res;
    }

    // 质数数组，false 是质数，true 合数
    public static boolean[] isNotPrime() {
        boolean[] res = new boolean[100001];
        for (int i = 2; i * i <= res.length; i++) {
            if (!res[i]) {// 如果是质数
                // i+i=2i包括3i,4i,...(i-1)i都在之前被更小的素数筛过了，所以i*i
                for (int j = i * i; j <= res.length; j += i) {
                    res[j] = true; // 把合数置为 true
                }
            }
        }
        return res;
    }

    // 最大公约数
    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
