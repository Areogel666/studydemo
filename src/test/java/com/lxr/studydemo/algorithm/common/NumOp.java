package com.lxr.studydemo.algorithm.common;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NumOp {

    @Test
    public void testNum() {
        int dividend = 82, divisor = 4;
        assertEquals(20, divide(dividend, divisor));
        String a = "10011", b = "1101";
        assertEquals("100000", addBinary(a, b));
    }

    /**
     * 29. 两数相除
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     *
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     *
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        //  除法的本质是多次减法，因此可以拆分为循环做减法
        // 首先处理溢出的情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 统一转成负数（如果转成正数，Integer.MIN_VALUE会溢出）
        int neg = 2;
        if (dividend > 0) {
            dividend = -dividend;
            neg--;
        }
        if (divisor > 0) {
            divisor = -divisor;
            neg--;
        }
        int result = 0;
        while (dividend <= divisor) { // 负数比较
            // 优化：1个1个减 ——> 翻倍减
            int multiDivisor = divisor;
            int cnt = 1;
            // 考虑避免multiDivisor + multiDivisor溢出
            while (multiDivisor >= Integer.MIN_VALUE >> 1 && dividend <= multiDivisor + multiDivisor) {
                cnt += cnt;
                multiDivisor += multiDivisor;
            }
            dividend -= multiDivisor;
            result += cnt;
        }
        // 根据neg判断result正负
        return neg == 1 ? -result : result;
    }

    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0。
     *
     * 示例 1:
     *
     * 输入: a = "11", b = "1"
     * 输出: "100"
     *
     * 示例 2:
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     *
     */
    public String addBinary(String a, String b) {
        // 仿照十进制进位的思想
        int alen = a.length() - 1;
        int blen = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        while (alen >= 0 || blen >= 0) {
            // 数字字符减去'0'得到int
            int aTmp = alen >= 0 ? a.charAt(alen--) - '0' : 0;
            int bTmp = blen >= 0 ? b.charAt(blen--) - '0' : 0;
            int total = aTmp + bTmp + carry;
            if (total > 1) {
                total -= 2;
                carry = 1;
            } else {
                carry = 0;
            }
            result.append(total);
        }
        if (carry == 1) {
            result.append(carry);
        }
        return result.reverse().toString();
    }
}
