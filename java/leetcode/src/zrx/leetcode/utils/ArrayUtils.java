package zrx.leetcode.utils;

import java.util.Arrays;

public class ArrayUtils {
    public static int[] createIntArray(int... arr) {
        return arr;
    }

    public static void printArray(int[][] arr) {
        Arrays.stream(arr).forEach(ArrayUtils::printArray);
    }

    public static void printArray(int[] arr) {
        StringBuilder str = Arrays.stream(arr)
                .mapToObj(e -> e + " ")
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                );
        str.deleteCharAt(str.length() - 1);
        System.out.println(str);
    }
}
