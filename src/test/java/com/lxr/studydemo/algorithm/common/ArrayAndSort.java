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

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 有序数组查找，考虑二分法
        if (nums == null) return new int[]{-1, -1};
        int left = 0, right = nums.length - 1;
        int leftIdx = nums.length, rightIdx = nums.length; //解决target位于最小最大的问题
        // 开始位置：第一个大于等于target位置
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
                leftIdx = mid;
            } else {
                left = mid + 1;
            }
        }
        // 结束位置：第一个大于target位置 - 1
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
                rightIdx = mid;
            } else {
                left = mid + 1;
            }
        }
        rightIdx--; //注意
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * 33. 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
     *
     * 示例 1：
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例2：
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * @param nums
     * @param target
     * @return int
     */
//    public int search(int[] nums, int target) {
        // 有序数组搜索，首先考虑二分法
        // 两种情况：target大于nums[0]则
        //           target小于nums[0]
//    }
}
