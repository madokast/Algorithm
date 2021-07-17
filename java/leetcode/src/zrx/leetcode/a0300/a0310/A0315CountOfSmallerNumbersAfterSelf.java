package zrx.leetcode.a0300.a0310;

import zrx.leetcode.utils.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 315. 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class A0315CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        A0315CountOfSmallerNumbersAfterSelf a = new A0315CountOfSmallerNumbersAfterSelf();
        System.out.println(a.countSmaller2(ArrayUtils.createIntArray(5, 2, 6, 1)));
        System.out.println(a.countSmaller(ArrayUtils.createIntArray(5, 2, 6, 1)));
        System.out.println(a.countSmaller(ArrayUtils.createIntArray(
                26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98,
                69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41
        )));
        System.out.println(a.countSmaller2(ArrayUtils.createIntArray(
                26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98,
                69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41
        )));

    }

    // 歸并排序法
    private static class RichNumber {
        private final int val;
        private final int index;
        private int numberSmaller = 0;

        public RichNumber(int val, int index) {
            this.val = val;
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public int getNumberSmaller() {
            return numberSmaller;
        }

        @Override
        public String toString() {
            return Integer.valueOf(val).toString();
        }
    }

    public List<Integer> countSmaller2(int[] nums) {
        int length = nums.length;
        RichNumber[] richNumbers = new RichNumber[length];
        for (int i = 0; i < nums.length; i++) {
            richNumbers[i] = new RichNumber(nums[i], i);
        }
        margeSort(richNumbers, 0, length, new RichNumber[length]);
        return Arrays.stream(richNumbers)
                .sorted(Comparator.comparing(RichNumber::getIndex))
                .map(RichNumber::getNumberSmaller)
                .collect(Collectors.toList());
    }

    private void margeSort(RichNumber[] richNumbers, int startIn, int endEx, RichNumber[] margeHelper) {
        int length = endEx - startIn;
        if (length > 1) { // 無序
            int middle = (endEx + startIn) / 2;
            margeSort(richNumbers, startIn, middle, margeHelper);
            margeSort(richNumbers, middle, endEx, margeHelper);

            int leftIndex = startIn;
            int rightIndex = middle;
            int margeHelperIndex = 0;
            int smaller = 0;

            while (leftIndex < middle && rightIndex < endEx) {
                int left = richNumbers[leftIndex].val;
                int right = richNumbers[rightIndex].val;

                if (left < right) {
                    richNumbers[leftIndex].numberSmaller += smaller;
                    margeHelper[margeHelperIndex++] = richNumbers[leftIndex];
                    leftIndex++;
                } else if (left > right) {
                    smaller++;
                    margeHelper[margeHelperIndex++] = richNumbers[rightIndex];
                    rightIndex++;
                } else {
                    while (leftIndex < middle && richNumbers[leftIndex].val == left) {
                        richNumbers[leftIndex].numberSmaller += smaller;
                        margeHelper[margeHelperIndex++] = richNumbers[leftIndex];
                        leftIndex++;
                    }

                    while (rightIndex < endEx && richNumbers[rightIndex].val == right) {
                        smaller++;
                        margeHelper[margeHelperIndex++] = richNumbers[rightIndex];
                        rightIndex++;
                    }

                }
            }

            while (leftIndex < middle) {
                richNumbers[leftIndex].numberSmaller += smaller;
                margeHelper[margeHelperIndex++] = richNumbers[leftIndex];
                leftIndex++;
            }

            while (rightIndex < endEx) {
                margeHelper[margeHelperIndex++] = richNumbers[rightIndex];
                rightIndex++;
            }

            System.arraycopy(margeHelper, 0, richNumbers, startIn, length);
        }
    }

    //------------------------------------------ 超時 ---------------------------
    // 二叉樹法

    private static class TreeNode {
        int val;
        int numberInLeft = 0;
        int repeatTime = 1;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int length = nums.length;
        LinkedList<Integer> ret = new LinkedList<>();
        if (length <= 0) return ret;

        int last = nums[length - 1];
        ret.addFirst(0);

        TreeNode head = new TreeNode(last);

        for (int i = nums.length - 2; i >= 0; i--) {
            int num = nums[i];
            TreeNode newNode = new TreeNode(num);
            int numberSmaller = 0;

            TreeNode cur = head;
            while (true) {
                int curVal = cur.val;
                if (curVal < num) {
                    // 插入右邊
                    numberSmaller += cur.numberInLeft;
                    numberSmaller += cur.repeatTime;
                    if (cur.right == null) {
                        cur.right = newNode;
                        break;
                    } else {
                        cur = cur.right;
                    }
                } else if (curVal > num) {
                    // 插入左側
                    cur.numberInLeft++;
                    if (cur.left == null) {
                        cur.left = newNode;
                        break;
                    } else {
                        cur = cur.left;
                    }
                } else { // curVal == num
                    cur.repeatTime++;
                    numberSmaller += cur.numberInLeft;
                    break;
                }
            }

            ret.addFirst(numberSmaller);
        }
        return ret;
    }
}
