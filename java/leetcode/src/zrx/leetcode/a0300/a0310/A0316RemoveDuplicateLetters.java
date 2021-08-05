package zrx.leetcode.a0300.a0310;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class A0316RemoveDuplicateLetters {
    public static void main(String[] args) {
        A0316RemoveDuplicateLetters t = new A0316RemoveDuplicateLetters();
        System.out.println(t.removeDuplicateLetters("bcabc"));// abc
        System.out.println(t.removeDuplicateLetters("cccca"));// ca
        System.out.println(t.removeDuplicateLetters("cbacdcbc"));//
        System.out.println(t.removeDuplicateLetters("aaaa"));// a
        System.out.println(t.removeDuplicateLetters("abacb"));// abc
    }

    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            int times = map.getOrDefault(c, 0) + 1;
            map.put(c, times);
        }

        Deque<Character> stack = new LinkedList<>();
        for (char c : chars) {
            if (stack.contains(c)) {
                map.put(c, map.get(c) - 1);
                continue;
            }
            while (!stack.isEmpty()) {
                Character top = stack.peek();
                if (top > c) {
                    Integer timesTop = map.get(top);
                    if (timesTop > 1) {
                        stack.pop();
                        map.put(top, timesTop - 1);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
