package zrx.leetcode.a0300.a0340;

import zrx.leetcode.utils.EqualCal;

import java.util.Arrays;

/**
 * reverse-string
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/9/10 20:37
 */

public class A0344ReverseString {
    public static void main(String[] args) {
        A0344ReverseString a = new A0344ReverseString();
        char[] hello = "hello".toCharArray();
        a.reverseString(hello);
        EqualCal.assertEqual("olleh".toCharArray(), hello);
    }

    public void reverseString(char[] s) {
        int length = s.length;
        if (length < 2) return;
        for (int i = 0; i < length / 2; i++) {
            if (s[i] != s[length - i - 1]) {
                char temp = s[i];
                s[i] = s[length - i - 1];
                s[length - i - 1] = temp;
            }
        }
    }
}
