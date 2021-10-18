package zrx.leetcode.a0300.a0350;

import zrx.leetcode.utils.ArrayUtils;
import zrx.leetcode.utils.EqualCal;

import java.util.ArrayList;
import java.util.List;

/**
 *  给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 * <p>
 * 实现 SummaryRanges 类：
 * <p>
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * 输出：
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 * <p>
 * 解释：
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= val <= 104
 * 最多调用 addNum 和 getIntervals 方法 3 * 104 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/10 21:06
 */
public class A0352DataStreamAsDisjointIntervals {

    public static void main(String[] args) {
        A0352DataStreamAsDisjointIntervals a = new A0352DataStreamAsDisjointIntervals();
        SummaryRanges summaryRanges = a.new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(2);
        summaryRanges.addNum(3);
        summaryRanges.addNum(6);
        summaryRanges.addNum(7);

        int[][] intervals = summaryRanges.getIntervals();
        EqualCal.assertEqual(ArrayUtils.createArray(
                ArrayUtils.createIntArray(1, 3),
                ArrayUtils.createIntArray(6, 7)
        ), intervals);

        summaryRanges = a.new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(3);
        summaryRanges.addNum(2);

        intervals = summaryRanges.getIntervals();
        EqualCal.assertEqual(ArrayUtils.createArray(
                ArrayUtils.createIntArray(1, 3)
        ), intervals);

        summaryRanges = a.new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(3);
        summaryRanges.addNum(3);
        summaryRanges.addNum(7);
        summaryRanges.addNum(2);
        summaryRanges.addNum(2);
        summaryRanges.addNum(6);

        intervals = summaryRanges.getIntervals();
        EqualCal.assertEqual(ArrayUtils.createArray(
                ArrayUtils.createIntArray(1, 3),
                ArrayUtils.createIntArray(6, 7)
        ), intervals);


        summaryRanges = a.new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(9);
        summaryRanges.addNum(2);

        intervals = summaryRanges.getIntervals();
        EqualCal.assertEqual(ArrayUtils.createArray(
                ArrayUtils.createIntArray(1, 2),
                ArrayUtils.createIntArray(9, 9)
        ), intervals);

        summaryRanges = a.new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(0);

        intervals = summaryRanges.getIntervals();
        EqualCal.assertEqual(ArrayUtils.createArray(
                ArrayUtils.createIntArray(0, 1)
        ), intervals);
    }


    class SummaryRanges {

        private List<int[]> intervals;

        public SummaryRanges() {
            intervals = new ArrayList<>();
        }

        public void addNum(int val) {
            if (intervals.isEmpty()) {
                intervals.add(new int[]{val, val});
                return;
            }

            int leftIntervalIndex = -1, rightIntervalIndex = -1;

            for (int i = 0; i < intervals.size(); i++) {
                int[] cur = intervals.get(i);
                int left = cur[0];
                int right = cur[1];
                if (val < left) {
                    rightIntervalIndex = i;
                    leftIntervalIndex = i - 1;
                    break;
                } else if (val <= right) {
                    return;
                } else {
                    leftIntervalIndex = i;
                    rightIntervalIndex = i + 1;
                }
            }

            if (leftIntervalIndex == -1) {
                int[] ints = intervals.get(rightIntervalIndex);
                int left = ints[0];
                if (val == left - 1) {
                    ints[0] = val;
                } else {
                    intervals.add(0, new int[]{val, val});
                }
            }else {
                int[] lefts = intervals.get(leftIntervalIndex);
                if(rightIntervalIndex==intervals.size()){
                    int right = lefts[1];
                    if(val==right+1){
                        lefts[1] = val;
                    }else {
                        intervals.add(new int[]{val, val});
                    }
                }else {
                    int[] rights = intervals.get(rightIntervalIndex);
                    if(lefts[1]==rights[0]-2){
                        intervals.remove(rightIntervalIndex);
                        lefts[1] = rights[1];
                    }else if(lefts[1]==val-1){
                        lefts[1] = val;
                    }else if(rights[0]==val+1){
                        rights[0]=val;
                    }else {
                        intervals.add(rightIntervalIndex,new int[]{val, val});
                    }
                }
            }
        }


        public int[][] getIntervals() {
            return intervals.toArray(new int[intervals.size()][]);
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
}
