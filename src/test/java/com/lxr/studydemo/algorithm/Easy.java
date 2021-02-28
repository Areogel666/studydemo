package com.lxr.studydemo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Easy {

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 你可以按任意顺序返回答案。
     *
     * 空间复杂度O(n) 时间复杂度O(n)
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
        int[] nums = {2,7,11,15};
        int target = 9;
        Arrays.stream(twoSum(nums, target)).forEach(System.out::println);
    }

    /**
     * 7. 整数反转
     * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
     *
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
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)){
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)){
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


}
