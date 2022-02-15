package zrx.leetcode.a0300.a0360;


import zrx.leetcode.utils.EqualCal;

import java.util.*;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * <p>
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * <p>
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 * <p>
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0365WaterAndJugProblem {

    public static void main(String[] args) {
        A0365WaterAndJugProblem q = new A0365WaterAndJugProblem();
        EqualCal.assertEqual(true, q.canMeasureWater(3, 5, 4));
        EqualCal.assertEqual(false, q.canMeasureWater(2, 6, 5));
        EqualCal.assertEqual(true, q.canMeasureWater(1, 2, 3));
        // EqualCal.assertEqual(true, q.canMeasureWater(104659,104677,142424));


        EqualCal.assertEqual(true, q.canMeasureWater2(3, 5, 4));
        EqualCal.assertEqual(false, q.canMeasureWater2(2, 6, 5));
        EqualCal.assertEqual(true, q.canMeasureWater2(1, 2, 3));
        EqualCal.assertEqual(true, q.canMeasureWater2(104659, 104677, 142424));
    }

    public boolean canMeasureWater2(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (targetCapacity > jug1Capacity + jug2Capacity) return false;
        if (jug1Capacity == 0) return targetCapacity == jug2Capacity;
        if (jug2Capacity == 0) return targetCapacity == jug1Capacity;
        int mcd = maxCommonDiv(jug1Capacity, jug2Capacity);
        return targetCapacity % mcd == 0;
    }

    // 求最大公约数
    private static int maxCommonDiv(int a, int b) {
        if (a == b) return a;
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int mod = max % min;
        if (mod == 0) return min;
        return maxCommonDiv(min, mod);
    }


    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // 已经出现过的状态
        Set<State> happen = new HashSet<>();

        // 初始状态
        State init = new State(0, 0);

        // 深度搜索栈
        Deque<State> stack = new LinkedList<>();

        stack.push(init);
        happen.add(init);

        // 是否发现目标，用于搜索时传递消息，如果发现，立即退出
        boolean[] found = new boolean[1];
        found[0] = false;

        dfs(stack, jug1Capacity, jug2Capacity, targetCapacity, happen, found);

        return found[0];
    }

    private void dfs(Deque<State> stack, int jug1Capacity, int jug2Capacity, int targetCapacity, Set<State> happen, boolean[] found) {
        State current = stack.peek();
        if (current.jug1 == targetCapacity || current.jug2 == targetCapacity || current.jug1 + current.jug2 == targetCapacity) {
            found[0] = true;
            return;
        }

        for (State next : current.operateAll(jug1Capacity, jug2Capacity)) {
            if (!happen.contains(next)) {
                happen.add(next);
                stack.push(next);
                dfs(stack, jug1Capacity, jug2Capacity, targetCapacity, happen, found);
                stack.pop();
            }
        }
    }

    // 水壶状态
    static class State {
        // 两个水壶当前水量
        int jug1;
        int jug2;


        public State(int jug1, int jug2) {
            this.jug1 = jug1;
            this.jug2 = jug2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return jug1 == state.jug1 &&
                    jug2 == state.jug2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(jug1, jug2);
        }

        State[] operateAll(int jug1Capacity, int jug2Capacity) {
            State[] res = new State[6];
            res[0] = full1(jug1Capacity, jug2Capacity);
            res[1] = full2(jug1Capacity, jug2Capacity);
            res[2] = clear1(jug1Capacity, jug2Capacity);
            res[3] = clear2(jug1Capacity, jug2Capacity);
            res[4] = pour12(jug1Capacity, jug2Capacity);
            res[5] = pour21(jug1Capacity, jug2Capacity);
            return res;
        }

        State full1(int jug1Capacity, int jug2Capacity) {
            return new State(jug1Capacity, jug2);
        }

        State full2(int jug1Capacity, int jug2Capacity) {
            return new State(jug1, jug2Capacity);
        }

        State clear1(int jug1Capacity, int jug2Capacity) {
            return new State(0, jug2);
        }

        State clear2(int jug1Capacity, int jug2Capacity) {
            return new State(jug1, 0);
        }

        State pour12(int jug1Capacity, int jug2Capacity) {
            if (jug1 + jug2 < jug2Capacity) {
                return new State(0, jug1 + jug2);
            } else {
                return new State(jug1 + jug2 - jug2Capacity, jug2Capacity);
            }
        }

        State pour21(int jug1Capacity, int jug2Capacity) {
            if (jug1 + jug2 < jug1Capacity) {
                return new State(jug1 + jug2, 0);
            } else {
                return new State(jug1Capacity, jug1 + jug2 - jug1Capacity);
            }
        }

        @Override
        public String toString() {
            return jug1 + "-" + jug2;
        }
    }
}
