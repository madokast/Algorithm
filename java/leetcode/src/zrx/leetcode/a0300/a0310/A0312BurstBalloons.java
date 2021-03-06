package zrx.leetcode.a0300.a0310;

import zrx.leetcode.utils.ArrayUtils;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 * <p>
 * 输入：nums = [1,5]
 * 输出：10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0312BurstBalloons {
    public static void main(String[] args) {
        A0312BurstBalloons a = new A0312BurstBalloons();
        System.out.println(a.maxCoins(ArrayUtils.createIntArray(3, 1, 5, 8)));
        System.out.println(a.maxCoins(ArrayUtils.createIntArray(1)));
        System.out.println(a.maxCoins(ArrayUtils.createIntArray(5, 1)));
    }

    public int maxCoins(int[] nums) {
        int size = nums.length;
        int[][] dp = new int[size + 1][size + 1];

        // 子问题大小
        for (int subQuestionLength = 1; subQuestionLength <= size; subQuestionLength++) {
            // 子数组开头为 start，即问题为 [start, start+subQuestionLength)
            for (int start = 0; start < size - subQuestionLength + 1; start++) {
                // 最后戳破的气球
                for (int lastBalloonIndex = start; lastBalloonIndex < start + subQuestionLength; lastBalloonIndex++) {
                    dp[start][start + subQuestionLength] = Math.max(
                            dp[start][start + subQuestionLength],
                            dp[start][lastBalloonIndex] + dp[lastBalloonIndex + 1][start + subQuestionLength]
                                    + (nums[lastBalloonIndex]
                                    * ((start - 1 == -1) ? 1 : nums[start - 1])
                                    * ((start + subQuestionLength == size) ? 1 : nums[start + subQuestionLength]))
                    );
                }
            }
        }
        return dp[0][size];
    }
}
