package zrx.leetcode.a0300.a0340;

import zrx.leetcode.utils.EqualCal;
import zrx.leetcode.utils.Ints;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/10 20:37
 */
public class A0349IntersectionOfTwoArrays {

    public static void main(String[] args) {
        A0349IntersectionOfTwoArrays a = new A0349IntersectionOfTwoArrays();
        int[] ans = a.intersection(Ints.of(1, 2, 2, 1), Ints.of(2, 2));
        EqualCal.assertEqual(Ints.of(2), ans);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> s2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        s1.retainAll(s2);
        return s1.stream().mapToInt(Integer::valueOf).toArray();
    }

}
