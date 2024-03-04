package algorithm;

import java.math.BigInteger;

public class QuickPow {

    public static long qpow_nomod(long x, int n) {
        var res = 1L;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x;
            }
            x = x * x;
        }
        return res;
    }

    // 快速幂，取模
    // int MOD = (int)1e9 + 7;
    // 注意调用时, (qpow(2, n, mod) + mod) % mod 避免负数
    public static long qpow(long x, int n, long mod) {
        var res = 1L;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return res;
    }

    // 同理，+ mod ) % mod 避免负数
    public static long qpow_java(long x, int n, long mod) {
        return (BigInteger.valueOf(x).modPow(BigInteger.valueOf(n), BigInteger.valueOf(mod)).longValue() + mod) % mod;
    }
}
