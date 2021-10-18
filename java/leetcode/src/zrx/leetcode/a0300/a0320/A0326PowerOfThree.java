package zrx.leetcode.a0300.a0320;

/**
 * @Description 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-three
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 2021/9/5 20:02
 * @Created by ZhaoRX
 */
public class A0326PowerOfThree {
    public static void main(String[] args) {
        A0326PowerOfThree a = new A0326PowerOfThree();
        System.out.println(a.isPowerOfThree(3));
        System.out.println(a.isPowerOfThree(81));
        System.out.println(a.isPowerOfThree(82));
    }

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
