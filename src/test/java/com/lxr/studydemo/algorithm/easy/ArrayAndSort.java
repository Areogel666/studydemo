package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ArrayAndSort
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 16:49
 * @Version 1.0
 */
public class ArrayAndSort {
    /**
     * 26. 删除排序数组中的重复项
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return int
     * @author Areogel
     * @date 2021/3/4 15:23
     */
    public int removeDuplicates(int[] nums) {
        //有序数组，考虑双指针法：
        //思路1：考虑快指针，当取值与慢指针对应的值相等时，快指针++，直到不等时慢指针++
        //思路2：考虑慢指针，当取值不等时，慢指针++
        if (nums.length == 0) return 0;
        int preIndex = 0;
        for (int curIndex = 1; curIndex < nums.length; curIndex++) {
            while (nums[preIndex] == nums[curIndex]) {
                if (++curIndex == nums.length) { //如果最后一个数字重复，判断快指针是否小于长度，如果等于长度，直接返回当前慢指针
                    return preIndex + 1;
                }
            }
            nums[++preIndex] = nums[curIndex];
        }
        return preIndex + 1;
    }

    @Test
    public void testRemoveDuplicates() {
        System.out.println(removeDuplicates(new int[]{0,4,4}));
    }


    /**
     * 88. 合并两个有序数组
     *
     * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
     *
     * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //双指针比较 需要一个额外数组 O(n) O(m+n)
        int[] array = Arrays.copyOf(nums1, m);
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (num1 == m) {
                nums1[i] = nums2[num2++];
                continue;
            }
            if (num2 == n) {
                nums1[i] = array[num1++];
                continue;
            }
            if (array[num1] < nums2[num2]) {
                nums1[i] = array[num1++];
            } else {
                nums1[i] = nums2[num2++];
            }
        }
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        // 考虑直接将值存到nums1最后 三指针  O(1) O(m+n)
        int p1 = m - 1, p2 = n - 1, p3 = m + n - 1;
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p3--] = nums1[p1--];
            } else {
                nums1[p3--] = nums2[p2--];
            }
        }

    }

    @Test
    public void testMerge() {
        AtomicInteger i = new AtomicInteger();
        int[] array1 = new int[]{4,5,6,0,0,0};
        int[] array2 = new int[]{1,2,3};
//        int[] array2 = IntStream.generate(() -> (int)(Math.random() * 100)).limit(8).sorted().toArray();
//        merge(array1, 3, array2, 3);
        merge1(array1, 3, array2, 3);
        Arrays.stream(array1).forEach(System.out::println);
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
     *
     * @param nums
	 * @return int
     */
    public int missingNumber(int[] nums) {
        //有序数组查找问题——>考虑二分法
        if (nums == null || nums.length < 1) return 0;
        int middle;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] == middle) { //如果第middle个元素取值等于middle，说明[left, middle]没有缺少数字，则继续搜寻后半
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        //当循环结束时，left就是缺失数字
        return left;
    }

    public int missingNumber1(int[] nums) {
        //取巧方式，由于是递增数列，因此可以利用等差数列前n项和-数组之和
        if (nums == null || nums.length < 1) return 0;
        int length = nums.length;
        int total = (0 + length) * (length + 1) / 2; // (首项 + 尾项) * 项数 / 2
        int res = total;
        for (int i = 0; i < nums.length; i++) {
            res -= nums[i];
        }
        return res;
    }

    /**
     * 215. 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     *
     * @param nums
     * @param k
     * @return int
     */
    public int findKthLargest(int[] nums, int k) {
        //借用快速排序思想
        //每次分区后可以确定切分点的最终位置，查找第k大元素相当于返回切分点等于nums.length - k（倒数第k）时，nums[lt]的值
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int lt = partition(nums, left, right);
            if (lt == nums.length - k) { //倒数第k个位置的切分点元素就是结果
                return nums[lt];
            }
            if (lt < nums.length - k) { //切分点小，则继续对大于切分点元素做分区
                left = lt + 1;
            } else { //切分点大，则继续对小于切分点元素做分区
                right = lt - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        //获取随机基准数
        int randomIndex = left + (int) Math.random() * (right - left + 1);
        //交换基准数至最左
        swap(nums, left, randomIndex);
        int pivot = nums[left];
        int lt = left;
        for (int i = left + 1; i <= right; i++) {
            //大放过，小交换
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        //最后确定pivot位置在lt
        swap(nums, left, lt);
        return lt;
    }

    private void swap(int[] nums, int index1, int index2) {
        /*//无临时变量交换
        nums[index1] = nums[index1] + nums[index2];
        nums[index2] = nums[index1] - nums[index2];
        nums[index1] = nums[index1] - nums[index2];*/
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

}
