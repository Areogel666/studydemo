package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

/**
 * @ClassName StringOp
 * @Author Areogel
 * @Date 2021/4/28 14:18
 * @Version 1.0
 */
public class StringOp {

    /**
     * 28. 实现 strStr()
     * 实现strStr()函数。
     *
     * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
     *
     * 说明：
     *
     * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
     *
     * 示例 1：
     *
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 示例 2：
     *
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * 示例 3：
     *
     * 输入：haystack = "", needle = ""
     * 输出：0
     *
     * @param haystack
     * @param needle
     * @return int
     */
    public int strStr(String haystack, String needle) {
        //O(m*n)
        // 特判
        if (needle == null || needle.isEmpty()) return 0;
        // 循环hystack，判断needle是否是子串
        // 三指针，一个指针指向子串起始位置，一个指针指向needle中当前判定位置，另一个指针指向haystack遍历位置
        int res = -1, nIndex = 0;
        for (int i = 0; i < haystack.length(); i++) {
            char h = haystack.charAt(i);
            char n = needle.charAt(nIndex);
            if (h == n) {
                res = res < 0 ? i : res; //更新子串起始位置res
                if (++nIndex == needle.length()) { //如果needle全部遍历完成，则说明包含，返回res
                    return res;
                }
            } else {
                nIndex = 0; //needle指针归零
                i = res < 0 ? i : res; //遍历指针回退至子串初始位置（下次for循环会继续右移一位遍历）
                res = -1; //重置res位置
            }
        }
        return -1;
    }

    public int strStr1(String haystack, String needle) {
        //KMP算法
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;  // 因为next数组里记录的起始位置为-1（next[0] = -1），j代表遍历needle指针位置
        for (int i = 0; i < haystack.length(); i++) { // 注意i就从0开始
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {  // 不匹配
                j = next[j]; // j 寻找之前匹配的位置
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) { // 匹配，j和i同时向后移动
                j++; // i的增加在for循环里
            }
            if (j == needle.length() - 1) { // 文本串s里出现了模式串t
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }

    private void getNext(int[] next, String s){
        // 创建前缀表（prefix table）。前缀表是用来回退的，它记录了模式串与主串(文本串)不匹配的时候，模式串应该从哪里开始重新匹配。
        // next数组即可以就是前缀表，也可以是前缀表统一减一（右移一位，初始位置为-1）
        int j = -1; // j就是前缀长度-1
        next[0] = j;
        for (int i = 1; i < s.length(); i++){ // 注意i从1开始
            while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) { // 前后缀不相同了
                j = next[j]; // 向前回溯
            }
            if (s.charAt(i) == s.charAt(j + 1)) { // 找到相同的前后缀
                j++;
            }
            next[i] = j; // 将j（前缀的长度）赋给next[i]
        }
    }

    @Test
    public void test() {
        String a = "aabaabaafa";
        String b = "aabaaf";
        System.out.println(strStr1(a, b));
    }

}
