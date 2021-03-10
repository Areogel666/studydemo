package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @ClassName Math
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 16:48
 * @Version 1.0
 */
public class MathOp {
    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 空间复杂度O(n) 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //考虑采用哈希map查询，时间复杂度为O(1)，循环n次
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(target - nums[i])) {
                return new int[]{numMap.get(target - nums[i]), i};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{0};
    }

    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Arrays.stream(twoSum(nums, target)).forEach(System.out::println);
    }

    /**
     * 7. 整数反转
     * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x = -x;
        }
        String resStr = "";
        String numStr = String.valueOf(x);
        Stack<Character> numStack = new Stack<>();
        for (int i = 0; i < numStr.length(); i++) {
            numStack.push(numStr.charAt(i));
        }
        while (!numStack.empty()) {
            Character c = numStack.pop();
            resStr += c;
        }
        int resNum;
        try {
            resNum = Integer.parseInt(resStr);
        } catch (NumberFormatException e) {
            resNum = 0;
        }
        if (negative) {
            return -resNum;
        }
        return resNum;
    }

    public int reverseBetter(int x) {
        //考虑用数学方式，循环求余。同时每次判断res * 10 + x % 10是否会超限
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return res;
    }

    @Test
    public void testReverse() {
        System.out.println(reverse(1534236469));
        System.out.println(reverseBetter(1534236469));
    }

    /**
     * 9. 回文数
     * <p>
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     *
     * @param x
     * @return boolean
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        while (x != 0) {
            list.add(x % 10);
            x /= 10;
        }
        int right;
        int left;
        for (int j = 0; j <= list.size() / 2; j++) {
            right = list.get(j);
            left = list.get(list.size() - j - 1);
            if (right != left) return false;
        }
        return true;
    }

    public boolean isPalindrome1(int x) {
        //数学方式
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) div *= 10; //求出最大除数
        while (x > 0) {
            int left = x / div;//左起
            int right = x % 10;//右起
            if (left != right) return false;
            x = (x % div) / 10; //去除左右已比较数字
            div /= 100; //由于去除两位，除数减少两位 100
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10; //反转数
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }


    @Test
    public void testIsPalindrome() {
        System.out.println(isPalindrome(1432341));
        System.out.println(isPalindrome1(1432341));
        System.out.println(isPalindrome2(1432341));
    }
}
