package algorithm;

import java.util.*;

public class Number {

    /**
     * 求 n 的阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * 求 n 的阶乘，取模 mod
     *
     * @param n
     * @param mod
     * @return
     */
    public static int factorial_mod(int n, int mod) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            res %= mod;
        }
        return res % mod;
    }

    /**
     * 求 n 的因子个数
     *
     * @param n 正整数
     * @return n 的因子个数
     */
    public static int countFactors(int n) {
        int res = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res++;
                if (i * i != n) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 质因数分解，将 n 分解成若干个质数的乘积
     *
     * @param n 正整数
     * @return n 的质因数列表
     */
    public static List<Integer> primeFactorization(int n) {
        var res = new ArrayList<Integer>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                n /= i;
                res.add(i);
            }
        }
        if (n != 1) {
            res.add(n);
        }
        return res;
    }

    /**
     * 求不同的质因数个数
     * 预处理(10 ^ 5 +1)
     * 时间复杂度 o(n * log log n)
     *
     * @return
     */
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

    /**
     * 质数数组，false 是质数，true 合数
     *
     * @return
     */
    public static boolean[] notPrime() {
        boolean[] res = new boolean[100001];
        for (int i = 2; i * i <= res.length; i++) {
            if (!res[i]) {// 如果是质数
                // i+i=2i包括3i,4i,...(i-1)i都在之前被更小的素数筛过了
                // 所以从i*i继续筛 (i+1)i,(i+2)i...
                for (int j = i * i; j <= res.length; j += i) {
                    res[j] = true; // 把合数置为 true
                }
            }
        }
        return res;
    }

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    /**
     * 求排列数 A(n, m) = n! / (n - m)!
     * A(3, 2) = 3! / (3 - 2)! = 6 / 1 = 6
     *
     * @param a
     * @param b
     * @return
     */
    public static int permutation(int a, int b) {
        return factorial(a) / factorial(a - b);
    }

    /**
     * 求组合数 C(n, m) = n! / (m! * (n - m)!)
     * C(3, 2) = 3! / (2! * (3 - 2)!) = 6 / 2 = 3
     * Python: comb(3, 2)
     * 
     * @param a
     * @param b
     * @return
     */
    public static int combination(int a, int b) {
        return factorial(a) / (factorial(b) * factorial(a - b));
    }
}
