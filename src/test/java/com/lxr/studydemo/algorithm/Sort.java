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

    /**
     * 快速排序
     * O(nlog(n))
     * @param nums
     * @return
     */
    public void quickSort(int[] nums, int left, int right) {
        //分而治之，分区
        if (left >= right) return; //分区点在左/右数第0，第1个元素的时候，完成左/右半区的分区，之后只进行另外半边的分区操作
        int lt = partition(nums, left, right); //返回切分元素
        quickSort(nums, left, lt - 1);
        quickSort(nums, lt + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        //left最左元素（pivot） right最右元素  i循环指针（比较元素） lt分区指针（分区边界元素，最终与pivot交换）
        //「大放过，小操作」，也就是说：遇到大于等于的元素就什么都不做，继续遍历，而遇到小的元素，就把它们依次交换到数组的前面去。
        //如果 i 指向的元素大于等于基准元素 pivot 的时候，什么都不用做；
        //如果 i 指向的元素小于基准元素 pivot 的时候，lt 先向后移动一位，再与 i 交换，然后 i 再向前移动，这就能保持在循环的过程中，i 和 j 的定义不变。
        int randomIndex = (int) (left + Math.random() * (right - left + 1)); //基准数取随机数
        swap(nums, left, randomIndex);
        int pivot = nums[left];
        int lt = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {//小于分区切分点，需要移动到分区左侧
                lt++;//分区指针右移一位（指向原来右分区头元素）
                swap(nums, i, lt);//交换当前遍历元素和原来右分区的头元素
            }
        }
        //最后交换基准元素到分区切分点
        swap(nums, lt, left);
        return lt;
    }
}
