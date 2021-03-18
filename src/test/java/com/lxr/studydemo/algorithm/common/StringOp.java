package com.lxr.studydemo.algorithm.common;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @ClassName String
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 16:51
 * @Version 1.0
 */
public class StringOp {
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
        //方法一：动态规划 + 哈希表  时间复杂度 O(N)  空间复杂度 O(1)
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
        //方法二：滑动窗口 时间复杂度 O(N)  空间复杂度 O(1)
        Map<Character, Integer> map = new HashMap<>();
        int j = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { //包含字符
                j = Math.max(map.get(c), j);//获得左窗口位置
            }
            map.put(c, ++i);//右窗口右移一位
            maxLength = Math.max(i - j, maxLength);//右窗-左窗获得最大长度
        }
        return maxLength;
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

    public int lengthOfLongestSubstring4(String s) {
        //方法三：滑动窗口思想 双指针 考虑用Set存储当前不重复子串
        //指针i循环遍历s，指针j指向当前不重复子串开始位置
        Set<Character> set = new HashSet<>();
        int j = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (set.contains(c)) { //如果子串有此字符，则删除j指针指向的元素，并右移j指针，直到set不含此字符
                set.remove(s.charAt(j++));
            }
            set.add(c);
            maxLength = Math.max(maxLength, set.size());
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring5(String s) {
        //滑动窗口 [j,i]的范围是窗口范围
        Map<Character, Integer> map = new HashMap<>();
        int j = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i);
            if (map.containsKey(c)) { //包含字符
                j = Math.max(map.get(c), j);//获得左窗口位置
            }
            map.put(c, ++i);//右窗口右移一位
            maxLength = Math.max(i - j, maxLength);//右窗-左窗获得最大长度
        }
        return maxLength;
    }


    @Test
    public void testLengthOfLongestSubstring() {
        System.out.println(lengthOfLongestSubstring3("abcabcdbb"));
    }

}
