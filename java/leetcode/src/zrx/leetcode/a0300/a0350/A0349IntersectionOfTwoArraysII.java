package zrx.leetcode.a0300.a0350;

import zrx.leetcode.utils.EqualCal;
import zrx.leetcode.utils.Ints;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/10 20:37
 */
public class A0349IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        Map<Integer, Integer> integerIntegerMap = timesMap(Ints.of(1, 1, 2, 2));
        System.out.println(integerIntegerMap);

        A0349IntersectionOfTwoArraysII a = new A0349IntersectionOfTwoArraysII();
        int[] ans = a.intersect(Ints.of(1, 2, 2, 1), Ints.of(2, 2));
        EqualCal.assertEqual(Ints.of(2, 2), ans);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> m1 = timesMap(nums1);
        Map<Integer, Integer> m2 = timesMap(nums2);
        return m1.entrySet().stream().flatMap(e -> {
            if (m2.containsKey(e.getKey())) {
                int times = Math.min(e.getValue(), m2.get(e.getKey()));
                int[] ints = new int[times];
                Arrays.fill(ints, e.getKey());
                return Arrays.stream(ints).boxed();
            } else {
                return IntStream.empty().boxed();
            }
        }).mapToInt(Integer::valueOf).toArray();
    }

    public static Map<Integer, Integer> timesMap(int[] es) {
        return Arrays.stream(es).collect(
                HashMap::new,
                (map, e) -> {
                    int time = (int) map.getOrDefault(e, 0);
                    map.put(e, time + 1);
                },
                (m1, m2) -> m2.forEach((k, v) -> {
                    int time = (int) m1.getOrDefault(k, 0);
                    m1.put(k, time + (int) v);
                })
        );
    }

}
