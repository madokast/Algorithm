package zrx.leetcode.a0300.a0320;

/**
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * <p>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 * <p>
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 * <p>
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0321CreateMaximumNumber {
    public static void main(String[] args) {

    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        Info[] nums1Max = new Info[m];
        Info[] nums2Max = new Info[n];


        return null;
    }

    static class Info {
        final int val;
        final int maxLeft;
        final int maxIndex;

        public Info(int val, int maxLeft, int maxIndex) {
            this.val = val;
            this.maxLeft = maxLeft;
            this.maxIndex = maxIndex;
        }

        static Info[] create(int[] nums) {
            int length = nums.length;
            Info[] infos = new Info[length];

            int maxLeft = nums[length - 1];
            int maxIndex = length - 1;
            infos[length - 1] = new Info(nums[length - 1], maxLeft, maxIndex);

            for (int i = length - 2; i >= 0; i--) {
                int val = nums[i];
                if (val >= maxLeft) {
                    maxLeft = val;
                    maxIndex = i;
                }

                infos[i] = new Info(val, maxLeft, maxIndex);
            }

            return infos;
        }
    }
}
