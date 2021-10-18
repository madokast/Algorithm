package zrx.leetcode.a0300.a0350;

import zrx.leetcode.utils.EqualCal;
import zrx.leetcode.utils.Ints;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * <p>
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 注意：不允许旋转信封。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 示例 2：
 * <p>
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= envelopes.length <= 5000
 * envelopes[i].length == 2
 * 1 <= wi, hi <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/10/11 21:57
 */
public class A0354RussianDollEnvelopes {
    public static void main(String[] args) {
        A0354RussianDollEnvelopes a = new A0354RussianDollEnvelopes();
        int i = a.maxEnvelopes(new int[][]{
                new int[]{5, 4},
                new int[]{6, 4},
                new int[]{6, 7},
                new int[]{2, 3}
        });
        EqualCal.assertEqual(i, 3);
    }

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1, e2) -> {
            int c = Integer.compare(e1[0], e2[0]);
            if (c == 0) {
                return Integer.compare(e1[1], e2[1]);
            } else return c;
        });
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp,1);
        for (int i = 1; i < envelopes.length; i++) {
            int[] cur = envelopes[i];
            for (int j = 0; j < i; j++) {
                int[] pre = envelopes[j];
                if (pre[1] < cur[1] && pre[0] < cur[0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().orElse(1);
    }
}
