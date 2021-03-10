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
     * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
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
            if (num2 ==  n) {
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
}
