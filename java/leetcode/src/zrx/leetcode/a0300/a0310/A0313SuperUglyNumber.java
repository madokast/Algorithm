package zrx.leetcode.a0300.a0310;

import zrx.leetcode.utils.ArrayUtils;

/**
 * 编写一段程序来查找第 n 个超级丑数。
 * <p>
 * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 12, primes = [2,7,13,19]
 * 输出: 32
 * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 * 说明:
 * <p>
 * 1 是任何给定 primes 的超级丑数。
 *  给定 primes 中的数字以升序排列。
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
 * 第 n 个超级丑数确保在 32 位有符整数范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0313SuperUglyNumber {
    public static void main(String[] args) {
        A0313SuperUglyNumber a = new A0313SuperUglyNumber();
        System.out.println(a.nthSuperUglyNumber(12, ArrayUtils.createIntArray(2, 7, 13, 19))); // 32
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int numberLength = primes.length;
        int[] candidateIndexInPrimesMultiplyToResultsIndex = new int[numberLength];
        int[] results = new int[n];
        results[0] = 1;

        for (int i = 1; i < n; i++) {

            int candidateResult = Integer.MAX_VALUE;

            for (int primesIndex = 0; primesIndex < numberLength; primesIndex++) {
                int candidate = results[candidateIndexInPrimesMultiplyToResultsIndex[primesIndex]] * primes[primesIndex];
                if (candidate < candidateResult) {
                    candidateResult = candidate;
                }
            }

            for (int primesIndex = 0; primesIndex < numberLength; primesIndex++) {
                int candidate = results[candidateIndexInPrimesMultiplyToResultsIndex[primesIndex]] * primes[primesIndex];
                if (candidate == candidateResult) {
                    candidateIndexInPrimesMultiplyToResultsIndex[primesIndex]++;
                }
            }

            results[i] = candidateResult;
        }

        ArrayUtils.printArray(results);
        ArrayUtils.printArray(candidateIndexInPrimesMultiplyToResultsIndex);

        return results[n - 1];
    }


}
