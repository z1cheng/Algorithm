package algorithm;

import java.math.BigInteger;

public class QuickPow {

    //快速幂，不取模
    public static long qpow_nomod(long x, int n) {
        long res = 1L;
        while (n > 0) {
            if (n % 2 > 0) {
                res = res * x;
            }
            x = x * x;
            n /= 2;
        }
        return res;
    }

    // 快速幂，取模
    // int MOD = (int)1e9 + 7;
    // 注意调用时, (qpow(2, n, mod) + mod) % mod 避免负数
    public static long qpow(long x, int n, long mod) {
        long res = 1L;
        while (n > 0) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n /= 2;
        }
        return res;
    }

    // 同理，(qpow(2, n, mod) + mod) % mod 避免负数
    public static long qpow_java(long x, int n, long mod) {
        return (BigInteger.valueOf(x).modPow(BigInteger.valueOf(n), BigInteger.valueOf(mod)).longValue() + mod) % mod;
    }
}
