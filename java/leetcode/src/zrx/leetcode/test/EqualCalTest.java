package zrx.leetcode.test;

import zrx.leetcode.utils.ArrayUtils;
import zrx.leetcode.utils.EqualCal;
import zrx.leetcode.utils.Ints;

/**
 *
 * @author ZhaoRX
 * @since 2021/10/11 21:12
 */
public class EqualCalTest {
    public static void main(String[] args) {
        EqualCal.assertEqual(
                ArrayUtils.createArray(Ints.of(1,2),Ints.of(3,4)),
                ArrayUtils.createArray(Ints.of(1,2),Ints.of(3,4))
        );
    }
}