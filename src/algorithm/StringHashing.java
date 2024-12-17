package algorithm;

/**
 * 字符串哈希
 * 可以快速的判断两个字符串是否相等，相比于直接使用 HashMap 会更快（避免了同bucket中字符串的按位比较）
 */
public class StringHashing {

    /**
     * usage
     */
    public static void main(String[] args) {
        System.out.println(new StringHashing("abcdef").hashing(0, 2));
        System.out.println(new StringHashing("abc").hashing());
    }

    public static final int BASE = 499;

    public static final int MAX_LENGTH = 100005;

    /**
     * pow[i] 表示 BASE 的 i 次方
     */
    public static long[] pow = new long[MAX_LENGTH];

    static {
        pow[0] = 1;
        for (int i = 1; i < MAX_LENGTH; i++) {
            pow[i] = pow[i - 1] * BASE;
        }
    }

    /**
     * hash[i] 表示字符串的前 i+1 个字符的 hash 值
     * 比如字符串 "abcdef"
     * hash[0] = mapping('a')
     * hash[1] = hash[0] * BASE + mapping('b')
     * hash[2] = hash[1] * BASE + mapping('c')
     */
    public long[] hash;

    public char[] chars;

    /**
     * O(N) 构造字符串哈希
     */
    public StringHashing(String s) {
        chars = s.toCharArray();
        hash = new long[chars.length];
        hash[0] = mapping(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            hash[i] = hash[i - 1] * BASE + mapping(chars[i]);
        }
    }

    /**
     * O(1) 获取任意子串 s[l...r] 的 hash 值
     * hash[l...r] = hash[r] - hash[l-1] * pow[r-l+1]
     */
    public long hashing(int l, int r) {
        long res = hash[r];
        if (l > 0) {
            res -= hash[l - 1] * pow[r - l + 1];
        }
        return res;
    }

    /**
     * 获得整个 string 的 hashCode
     */
    public long hashing() {
//        long res = 0;
//        for (char c : chars) {
//            res = res * BASE + mapping(c);
//        }
//        return res;
        return hashing(0, chars.length - 1);
    }

    /**
     * 得到单个字符对应的 mapping 值
     * '0' -> 1
     * '1' -> 2
     * '9' -> 10
     * 'A' -> 11
     * 'Z' -> 36
     * 'a' -> 37
     * 'z' -> 62
     */
    public static int mapping(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0' + 1;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 11;
        } else {
            return c - 'a' + 37;
        }
    }
}
