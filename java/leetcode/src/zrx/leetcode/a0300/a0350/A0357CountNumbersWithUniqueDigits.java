package zrx.leetcode.a0300.a0350;

import zrx.leetcode.utils.EqualCal;

/**
 * count-numbers-with-unique-digits
 * <p>
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * <p>
 * 示例:
 * <p>
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/18 23:08
 */
public class A0357CountNumbersWithUniqueDigits {

    public static void main(String[] args) {
        A0357CountNumbersWithUniqueDigits e = new A0357CountNumbersWithUniqueDigits();
        EqualCal.assertEqual(91, e.countNumbersWithUniqueDigits(2));
    }

    public int countNumbersWithUniqueDigits(int n) {
        return -1;
    }
}
