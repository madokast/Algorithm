package zrx.leetcode.utils;

import java.util.Arrays;

/**
 * int[]
 *
 * @author ZhaoRX
 * @since 2021/10/10 20:39
 */
public class Ints {
    public static int[] of(int... ints) {
        return ArrayUtils.createIntArray(ints);
    }

    public static int[] repeat(int e,int times){
        int[] r = new int[times];
        Arrays.fill(r,e);
        return r;
    }
}
