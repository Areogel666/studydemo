package com.lxr.studydemo.algorithm.common;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathOp {

    @Test
    public void testNum() {
//        int dividend = 82, divisor = 4;
//        assertEquals(20, divide(dividend, divisor));
//        String a = "10011", b = "1101";
//        assertEquals("100000", addBinary(a, b));
//        int[] nums = new int[]{1, 3, 4, 3, 1, 8, 6, 8, 6};
//        assertEquals(4, singleNumberI(nums));
//        int[] nums = new int[]{0,1,0,1,0,1,97};
//        assertEquals(97, singleNumberII(nums));
        int[] nums = new int[]{1,2,1,3,2,5};
        assertArrayEquals(new int[]{3,5}, singleNumberIII(nums));
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
        // 除法的本质是多次减法，因此可以拆分为循环做减法
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

    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * @param nums
     * @return
     */
    public int singleNumberI(int[] nums) {
        // 思路1，利用哈希表，时间复杂度O(n)，空间复杂度O(n)
        // 思路2，利用位运算亦或^ （a^a=0, a^0=a）
        if (nums == null) return 0;
        return Arrays.stream(nums).reduce((a, b) -> a ^ b).getAsInt();
    }

    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * 你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [2,2,3,2]
     * 输出：3
     *
     * 示例 2：
     *
     * 输入：nums = [0,1,0,1,0,1,99]
     * 输出：99
     *
     * @param nums
     * @return
     */
    public int singleNumberII(int[] nums) {
        // 思路1：哈希表
        // 思路2：可以将数组中数字转为2进制数，此时对于所有2进制数的每一位数字遍历求和，然后与3求余，如果余数是0，说明出现1次的元素在这个数字位的值是0，反之则是1
        int result = 0;
        // int长32位
        int[] bits = new int[32];
        for (int i = 0; i < nums.length; i++) {
            // 通过位运算，循环求出各个位置的数字
            for (int j = 0; j < bits.length; j++) {
                // 任意数&1可以截去高位，保留最低位，通过这个性质，我们可以把十进制数转换成二进制数组
                bits[j] += (nums[i] >> (31 - j)) & 1;
            }
        }
        for (int i = 0; i < bits.length; i++) {
            // 通过 << 运算，求出结果
            // 每次左移1位，直到归位
            result = (result << 1) + bits[i] % 3;
            // 每次累加结果位
//            result += (bits[i] % 3) << (31 - i);
        }
        return result;
    }

    /**
     * 260. 只出现一次的数字 III
     * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
     *
     * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     *
     * 示例 2：
     *
     * 输入：nums = [-1,0]
     * 输出：[-1,0]
     *
     * 示例 3：
     *
     * 输入：nums = [0,1]
     * 输出：[1,0]
     *
     * @param nums
     * @return
     */
    public int[] singleNumberIII(int[] nums) {
        // 思路1：哈希表
        Map<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hash.put(nums[i], hash.getOrDefault(nums[i], 0) + 1);
        }
        int[] res = new int[2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            if (entry.getValue() == 1) {
                res[i++] = entry.getKey();
            }
            if (i == 2) {
                break;
            }
        }
        return res;
    }

    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * 示例 1：
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     *
     * 示例 2：
     *
     * 输入：nums = [0,1,1]
     * 输出：[]
     * 解释：唯一可能的三元组和不为 0 。
     *
     * 示例 3：
     *
     * 输入：nums = [0,0,0]
     * 输出：[[0,0,0]]
     * 解释：唯一可能的三元组和为 0 。
     *
     * 提示：
     *     3 <= nums.length <= 3000
     *     -105 <= nums[i] <= 105
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {

    }
}
