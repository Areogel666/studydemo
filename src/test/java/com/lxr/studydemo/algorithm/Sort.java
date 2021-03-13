package com.lxr.studydemo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class Sort {

    @Test
    public void testSort() {
        int[] ints = IntStream.generate(() -> (int) (Math.random() * 10)).limit(10).toArray();
        insertionSort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 冒泡排序
     * O(n*n)
     * @param nums
     * @return
     */
    public int[] bubbleSort(int[] nums) {
        // 想象冒泡的过程，每次最大值冒泡到队尾，队尾元素不再参与下次冒泡。
        // 除了最后一个，每个泡泡都要经过冒泡的过程，所以外层循环nums.length - 1次
        // 由于每轮冒泡确认一个队尾元素位置，因此内层循环从0开始，需要循环nums.length - i - 1次
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * O(n*n)
     * @param nums
     * @return
     */
    public int[] choiceSort(int[] nums) {
        // 每次选择最小值，交换到队首，队首元素不再参与下次循环。
        // 除了最后一个，每个元素都要有选择的过程，所以外层循环nums.length - 1次
        // 由于每轮选择确认一个队首元素位置，所以内层循环从i开始，循环nums.length - i - 1次
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i; //记录最小值
            for (int j = i; j < nums.length - 1; j++) {
                if (nums[j + 1] < nums[min]) {
                    min = j + 1;
                }
            }
            swap(nums, i, min); //交换最小值到队首
        }
        return nums;
    }

    /**
     * 插入排序
     * O(n*n)
     * @param nums
     * @return
     */
    public int[] insertionSort(int[] nums) {
        // 每次将元素插入到已排序好部分的合适位置
        // 除第一个元素，都需要插入，所以外层循环nums.length - 1次
        // 每次插入，依次与上一个元素比较，上一元素较大，则将其向后移一位，直到上一元素值更小，此时位置即为插入位置
        // 内层循环最差进行n - 1次比较，最佳1次，平均n / 2次
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int j = i - 1;
            while (j >= 0 && cur < nums[j]) {
                nums[j + 1] = nums[j]; //后移一位
                j--;
            }
            nums[j + 1] = cur; //最后确认插入位置
        }
        return nums;
    }
}
