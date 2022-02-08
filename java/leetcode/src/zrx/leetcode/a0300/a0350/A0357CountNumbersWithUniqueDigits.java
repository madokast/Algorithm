package zrx.leetcode.a0300.a0350;

import zrx.leetcode.utils.EqualCal;

import java.util.Arrays;

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
//        test1(e);
        int[] dp0 = new int[8];
        int[] dp1 = new int[8];

        for (int i = 0; i < 10000000; i++) {
            if (isUnique(i)) {
                int length = length(i);
                if (contain0(i)) {
                    dp0[length]++;
                } else {
                    dp1[length]++;
                }
            }
        }

        System.out.println(Arrays.toString(dp0));
        System.out.println(Arrays.toString(dp1));

        test1(e);

        e.countNumbersWithUniqueDigits(5);
        e.countNumbersWithUniqueDigits(6);
        e.countNumbersWithUniqueDigits(7);
    }

    private static void test1(A0357CountNumbersWithUniqueDigits e) {
        EqualCal.assertEqual(91, e.countNumbersWithUniqueDigits(2));

        int ans2 = 0;
        for (int i = 0; i < 100; i++) {
            if (isUnique(i)) ans2++;
        }

        EqualCal.assertEqual(ans2, e.countNumbersWithUniqueDigits(2));

        int ans3 = 0;
        for (int i = 0; i < 1000; i++) {
            if (isUnique(i)) ans3++;
        }

        EqualCal.assertEqual(ans3, e.countNumbersWithUniqueDigits(3));

        int ans4 = 0;
        for (int i = 0; i < 10000; i++) {
            if (isUnique(i)) ans4++;
        }

        EqualCal.assertEqual(ans4, e.countNumbersWithUniqueDigits(4));
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        if (n == 2) return 91;

        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        dp0[1] = 1;
        dp1[1] = 9;

        for (int i = 2; i <= n; i++) {
            int use = 9 - i + 1;
            dp1[i] = use * dp1[i - 1];
            dp0[i] = (use + 1) * dp0[i - 1] + (i > 2 ? dp1[i - 1] : 0);
        }

        System.out.println(Arrays.toString(dp0));
        System.out.println(Arrays.toString(dp1));

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dp0[i] + dp1[i];
        }

        return ans;
    }

    private static int[] tab = new int[10];

    private static boolean isUnique(int n) {
        Arrays.fill(tab, 0);
        while (n > 0) {
            int d = n % 10;
            tab[d]++;
            if (tab[d] > 1) return false;
            n = n / 10;
        }
        return true;
    }

    private static boolean contain0(int n) {
        if (n == 0) return true;

        while (n > 0) {
            int d = n % 10;
            if (d == 0) return true;
            n = n / 10;
        }
        return false;
    }

    private static int length(int n) {
        if (n == 0) return 1;
        int len = 0;
        while (n > 0) {
            len++;
            n = n / 10;
        }
        return len;
    }

    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) return 1;
        int r = 10;
        for (int i = 2; i <= n; i++) {
            r += p(9, i) + (i - 1) * p(9, i - 1);
        }
        return r;
    }

    private static int p(int a, int b) {
        if (b == 0) return 0;
        int r = 1;
        for (int i = 0; i < b; i++) {
            r *= a - i;
        }
        return r;
    }
}
