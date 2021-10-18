package zrx.leetcode.utils;

import java.util.Objects;

/**
 * EqualCal 相等计算
 *
 * @author ZhaoRX
 * @since 2021/9/10 20:21
 */
public class EqualCal {
    public static void assertEqual(Object a, Object b) {
        if (!compare(a, b)) {
            throw new RuntimeException("不相等:" + ToString.from(a) + " ≠ " + ToString.from(b));
        }
    }

    public static boolean compare(Object a, Object b) {
        return Objects.deepEquals(a, b);
    }
}
