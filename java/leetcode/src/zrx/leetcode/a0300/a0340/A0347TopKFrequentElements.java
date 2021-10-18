package zrx.leetcode.a0300.a0340;

import zrx.leetcode.utils.ArrayUtils;
import zrx.leetcode.utils.EqualCal;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/10 20:26
 */
public class A0347TopKFrequentElements {

    public static void main(String[] args) {
        A0347TopKFrequentElements a = new A0347TopKFrequentElements();
        int[] ints = a.topKFrequent(ArrayUtils.createIntArray(1, 1, 1, 2, 2, 3), 2);
        Arrays.sort(ints);
        EqualCal.assertEqual(ArrayUtils.createIntArray(1,2), ints);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> times = new HashMap<>();
        for (int num : nums) {
            Integer t = times.getOrDefault(num, 0);
            times.put(num,t+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : times.entrySet()) {
            if(queue.size()<k) {
                queue.add(entry);
            }else {
                if (queue.peek().getValue()<entry.getValue()) {
                    queue.poll();
                    queue.add(entry);
                }
            }
        }

        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }



}
