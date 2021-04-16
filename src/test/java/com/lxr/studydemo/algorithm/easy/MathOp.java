package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     * @param n
     * @return int
     */
    public int climbStairs(int n) {
        //动态规划 爬到楼顶前可能存在两种情况：差一步，差两步
        //因此得出规律dp[n] = dp[n - 1] + dp[n - 2]
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs1(int n) {
        //由方法一通项可知，此题等价于求解斐波那契数列，因此很容易想到递归解法，但此种解法时间复杂度高O(n^2)
        if (n == 0 || n == 1) return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
        /*我们来总结一下斐波那契数列第 nn 项的求解方法：
        1.n比较小的时候，可以直接使用过递归法求解，不做任何记忆化操作，时间复杂度是 O(2^n)，存在很多冗余计算。
        2.一般情况下，我们使用「记忆化搜索」或者「迭代」的方法，实现这个转移方程，时间复杂度和空间复杂度都可以做到 O(n)O(n)。
        3.为了优化空间复杂度，我们可以不用保存 f(x - 2)f(x−2) 之前的项，我们只用三个变量来维护 f(x)、f(x - 1) 和 f(x - 2)，你可以理解成是把「滚动数组思想」应用在了动态规划中，也可以理解成是一种递推，这样把空间复杂度优化到了O(1)。
        4.随着 n 的不断增大 O(n)可能已经不能满足我们的需要了，我们可以用「矩阵快速幂」的方法把算法加速到 O(\log n)。
        5.我们也可以把 n 代入斐波那契数列的通项公式计算结果，但是如果我们用浮点数计算来实现，可能会产生精度误差。
        */
    }

    public int climbStairs2(int n) {
        //滚动数组优化空间复杂度
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 69. x 的平方根
     * 实现int sqrt(int x)函数。
     * 计算并返回x的平方根，其中x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     * 由于返回类型是整数，小数部分将被舍去。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        //二分查找：搜索mid^2 == x
        int low = 0, high = x, res = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if ((long)mid * mid <= x) { //(long)保证mid平方不溢出
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public String mySqrt1(int x) {
        // 牛顿迭代法 此处附加要求保留4位小数
        if (x == 0) {
            return "0.0000";
        }
        double C = x, res = x;
        while (true) {
            double xi = 0.5 * (res + C / res); // xi = (xi+C/xi)/2
            if (Math.abs(res - xi) < 1e-7) { //如果两次迭代差值很小，说明非常接近根的值，则可以返回根的近似值
                break;
            }
            res = xi; //设置当前结果
        }
        // 返回值保留4位小数
        return new BigDecimal(res).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }
}
