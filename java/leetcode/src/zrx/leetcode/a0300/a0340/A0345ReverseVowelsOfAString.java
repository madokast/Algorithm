package zrx.leetcode.a0300.a0340;

import zrx.leetcode.utils.EqualCal;

import java.util.HashSet;
import java.util.Set;

/**
 * reverse-vowels-of-a-string
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * <p>
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "hello"
 * 输出："holle"
 * 示例 2：
 * <p>
 * 输入：s = "leetcode"
 * 输出："leotcede"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由 可打印的 ASCII 字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoRX
 * @since 2021/9/10 20:50
 */
public class A0345ReverseVowelsOfAString {
    public static void main(String[] args) {
        A0345ReverseVowelsOfAString a = new A0345ReverseVowelsOfAString();
        EqualCal.assertEqual("holle", a.reverseVowels("hello"));
        EqualCal.assertEqual("leotcede", a.reverseVowels("leetcode"));
        EqualCal.assertEqual("aA", a.reverseVowels("Aa"));
    }

    private static final Set<Character> SET = new HashSet<>();

    static {
        SET.add('a');
        SET.add('e');
        SET.add('i');
        SET.add('o');
        SET.add('u');

        SET.add('A');
        SET.add('E');
        SET.add('I');
        SET.add('O');
        SET.add('U');
    }


    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = -1;
        int right = chars.length;
        while (true) {
            left = nextVowels(chars, left, false);
            right = nextVowels(chars, right, true);
            if (left >= chars.length) break;
            if (right < 0) break;
            if (left >= right) break;
            swap(chars, left, right);
        }
        return new String(chars);
    }

    private void swap(char[] array, int i, int j) {
        if (array[i] != array[j]) {
            array[i] = (char) (array[i] ^ array[j]);
            array[j] = (char) (array[i] ^ array[j]);
            array[i] = (char) (array[i] ^ array[j]);
        }
    }

    private int nextVowels(char[] str, int current, boolean back) {
        if (back) {
            current--;
            while (current >= 0) {
                if (SET.contains(str[current])) {
                    return current;
                } else {
                    current--;
                }
            }
        } else {
            current++;
            while (current < str.length) {
                if (SET.contains(str[current])) {
                    return current;
                } else {
                    current++;
                }
            }
        }

        return current;
    }
}
