package com.lxr.studydemo.algorithm.common;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @ClassName ArrayAndSort
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 16:50
 * @Version 1.0
 */
public class ArrayAndSort {
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
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[]
     *
     * @param nums
	 * @return List<List<Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //注意题目要求不可以包含重复的三元组，此处有两种方式：1.利用哈希表，2.利用排序，跳过重复数字项
        //此题考虑使用排序，减少循环及空间使用
        //一个数做循环指针，另外两个数为左右双指针。
        List<List<Integer>> resList = new ArrayList<>();
        //特判
        if (nums == null || nums.length < 3) return resList;
        //排序
        Arrays.sort(nums);
        int left;
        int right;
        //循环第一个数，只要保证第一个数不重复
        for (int i = 0; i < nums.length; i++) {
            //如果第一个数大于0，则三数和必大于0，直接结束循环
            if (nums[i] > 0) {
                break;
            }
            //如果第一个数的值重复了，直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                //如果满足要求，则left++, right--
                if (nums[i] + nums[left] + nums[right] == 0) {
                    resList.add(Arrays.asList(new Integer[]{nums[i], nums[left], nums[right]}));
                    while (left < right && nums[left] == nums[left + 1]) left++; // 去重
                    while (left < right && nums[right] == nums[right - 1]) right--; // 去重
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] > 0) { //如果大于0，右指针左移
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0){ //如果小于0，左指针右移
                    left++;
                }
            }
        }
        return resList;
    }
}
