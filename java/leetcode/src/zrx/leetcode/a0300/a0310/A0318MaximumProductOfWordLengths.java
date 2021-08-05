package zrx.leetcode.a0300.a0310;

import zrx.leetcode.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 * <p>
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * <p>
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-word-lengths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 执行结果：
 * 通过
 * 显示详情
 * 添加备注
 * <p>
 * 执行用时：
 * 8 ms
 * , 在所有 Java 提交中击败了
 * 89.24%
 * 的用户
 * 内存消耗：
 * 38.5 MB
 * , 在所有 Java 提交中击败了
 * 65.56%
 * 的用户
 */
public class A0318MaximumProductOfWordLengths {

    public static void main(String[] args) {
        A0318MaximumProductOfWordLengths a = new A0318MaximumProductOfWordLengths();
        System.out.println(a.maxProduct(ArrayUtils.createArray("abcw", "baz", "foo", "bar", "xtfn", "abcdef")));//16
        System.out.println(a.maxProduct(ArrayUtils.createArray("a", "aa", "aaa", "aaaa")));//0
        System.out.println(a.maxProduct(ArrayUtils.createArray("a", "ab", "abc", "d", "cd", "bcd", "abcd")));//4

    }


    public int maxProduct(String[] words) {
        int length = words.length;
        int[][] data = new int[length][];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            data[i] = new int[]{encode(word), word.length()};
        }
        int ret = 0;
        for (int i = 0; i < length; i++) {
            int[] left = data[i];
            for (int j = i + 1; j < length; j++) {
                int[] right = data[j];
                if ((left[0] & right[0]) == 0) {
                    ret = Math.max(ret, left[1] * right[1]);
                }
            }
        }
        return ret;
    }

    private static int encode(String word) {
        int code = 0;
        for (char c : word.toCharArray()) {
            code |= BIT_MAP[c]; // or
        }
        return code;
    }

    private static final int[] BIT_MAP = new int['z' + 1];

    static {
        for (int i = 'a'; i <= 'z'; i++) {
            BIT_MAP[i] = 1 << (i - 'a');
        }
    }
}
