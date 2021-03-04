package com.lxr.studydemo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Common {

    /**
     * 剑指 Offer 04. 二维数组中的查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //暴力；二分法；binaryTree(左下或右上做顶点)
        //以右上做顶点，大于15下移，小于15左移，以此类推 时间复杂度O(m+n)
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0;
        int left = m - 1;
        while (top >= n || left < 0) {
            int current = matrix[top][left];
            if (target == current) {
                return true;
            } else if (target > current) {
                top++;
            } else if (target < current) {
                left--;
            }
        }
        return false;
    }

    @Test
    public void testFindNumberIn2DArray() {
        int[][] matrix = {{1,   4,  7, 11, 15}
                         ,{2,   5,  8, 12, 19}
                         ,{3,   6,  9, 16, 22}
                         ,{10, 13, 14, 17, 24}
                         ,{18, 21, 23, 26, 30}};
        boolean numberIn2DArray = findNumberIn2DArray(matrix, 87);
        System.out.println(numberIn2DArray);
    }


    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        //动态规划 + 哈希表  时间复杂度 O(N)  空间复杂度 O(1)
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 查找当前字符索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            //注：(j - i) 代表当前字符距上一次出现的长度, tmp为当前不重复子串长度, 即dp[j - 1]
            //如果(j - i) > tmp, 说明当前子串不包含此字符, 则temp++; 反之, 说明包含, 则取(j - i) 为新的子串
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        //双指针 + 哈希表 时间复杂度 O(N)  空间复杂度 O(1)
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for(int j = 0; j < s.length(); j++) {
            if(dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 当前字符上一次出现的位置 与 子串左指针比较  更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }

    public int lengthOfLongestSubstring3(String s) {
        //根据方法一思路手写
        Map<Character, Integer> dic = new HashMap<>();
        int temp = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            //查找当前字符位置
            int i = dic.getOrDefault(s.charAt(j), -1);
            //将该字符存入map
            dic.put(s.charAt(j), j);
            //判断当前子串是否包含此字符
            if (temp < j - i) {
                temp++;
            } else {
                temp = j - i;
            }
            res = Math.max(res, temp);
        }
        return res;
    }

    @Test
    public void testLengthOfLongestSubstring() {
        System.out.println(lengthOfLongestSubstring3("abcabcbb"));
    }
}
