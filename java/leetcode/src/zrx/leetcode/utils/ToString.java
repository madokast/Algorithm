package zrx.leetcode.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * ToString
 *
 * @author ZhaoRX
 * @since 2021/9/10 20:25
 */
public class ToString {
    public static String from(Object a) {
        if (a.getClass().isArray()) return fromArray(a);
        else return Objects.toString(a);
    }

    private static String fromArray(Object array) {
        Class<?> clazz = array.getClass();
        if (clazz == byte[].class)
            return Arrays.toString((byte[]) array);
        else if (clazz == short[].class)
            return Arrays.toString((short[]) array);
        else if (clazz == int[].class)
            return Arrays.toString((int[]) array);
        else if (clazz == long[].class)
            return Arrays.toString((long[]) array);
        else if (clazz == char[].class)
            return Arrays.toString((char[]) array);
        else if (clazz == float[].class)
            return Arrays.toString((float[]) array);
        else if (clazz == double[].class)
            return Arrays.toString((double[]) array);
        else if (clazz == boolean[].class)
            return Arrays.toString((boolean[]) array);
        else if (array instanceof Object[])
            return Arrays.deepToString((Object[]) array);
        else
            throw new RuntimeException("unknown type " + array);
    }
}

