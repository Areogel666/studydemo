package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

import java.util.*;
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

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 示例 1：
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2：
     *
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
	 * @return int
     */
    public int maxProfit(int[] prices) {
        //双指针，两个指针分别指向买入节点和卖出节点
        if (prices == null || prices.length < 2) return 0;
        int res = 0;
        int i = 0; //买入指针
        int j = 1; //卖出指针
        while (j < prices.length) {
            if (prices[j] <= prices[i]) {
                //如果卖出节点小，则设为新的买入节点，卖出节点右移一位
                i = j;
                j++;
                continue;
            }
            //如果卖出节点大，则判断是否比当前利润高，是则更新利润，否则跳过
            int temp = prices[j] - prices[i];
            res = temp > res ? temp : res;
            j++;
        }
        return res;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * @param prices
     * @return int
     */
    public int maxProfitII(int[] prices) {
        //双指针，分别指向买入节点和卖出节点。只要出现一次卖出节点比上次卖出节点更小，就完成一次买卖交易
        if (prices == null || prices.length < 2) return 0;
        int res = 0;
        int i = 0, j = 1; //买卖指针
        while (j < prices.length) {
            if (prices[j] < prices[j - 1]) {
                //卖出节点比上次卖出节点更小，则完成一次交易
                res += prices[j - 1] - prices[i];
                i = j++;
            } else {
                //卖出节点比上次更大，则更新卖出节点
                j++;
            }
        }
        if (prices[j - 1] - prices[i] > 0) { //循环结束最后更新结果
            res += prices[j - 1] - prices[i];
        }
        return res;
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * @param s
     * @return
     */
    public void reverseString(char[] s) {
        //题意即反转数组，那么考虑二分交换
        int mid = s.length / 2;
        int i = 0;
        char temp;
        while (i++ <= mid) {
            temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    /**
     * 496. 下一个更大元素 I
     * 给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
     *
     * 请你找出 nums1中每个元素在nums2中的下一个比其大的值。
     *
     * nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。
     *
     * 示例 1:
     *
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     *     对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
     *     对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
     *     对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 使用单调栈（栈内的元素都保持有序）
        // 首先遍历nums2，建立单调栈，每次弹出栈得到下一个更大数字后存入哈希集合，最后根据num1取出结果
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            // 建立单调栈
            while (!stack.isEmpty() && stack.peek() < nums2[i]) { //栈中上一个元素比当前元素小，则记录上一个元素的下一更大元素为nums2[i]
                map.put(stack.pop(), nums2[i]); //pop掉栈中更小元素，因此栈是有序的（后入栈元素总是更大）
            }
            stack.push(nums2[i]);
        }
        //根据map集合查出结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     *
     * 示例 1：
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     *
     * 输入：matrix =[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
	 * @return int[]
     * @author Areogel
     * @date 2021/4/22 17:09
     */
    public int[] spiralOrder(int[][] matrix) {
        // 模拟 设定边界
        if (matrix == null || matrix.length == 0) return new int[0];
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        int x = 0;
        int[] res = new int[matrix[0].length * matrix.length];
        // 1.首先从matrix[top][left]遍历到matrix[top][right]，然后top++
        // 2.从matrix[top][right]遍历到matrix[bottom][right]，然后right--
        // 3.从matrix[bottom][right]遍历到matrix[bottom][left]，然后bottom--
        // 4.从matrix[bottom][left]遍历到matrix[top][left]，然后left++
        // 边界：如果left和right、top和bottom相交，则说明遍历完毕
        while (true) {
            for (int i = left; i <= right; i++) {
                res[x++] = matrix[top][i];
            }
            if (++top > bottom) break;
            for (int i = top; i <= bottom; i++) {
                res[x++] = matrix[i][right];
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                res[x++] = matrix[bottom][i];
            }
            if (--bottom < top) break;
            for (int i = bottom; i >= top; i--) {
                res[x++] = matrix[i][left];
            }
            if (++left > right) break;
        }
        return res;
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * @param nums
     * @return
     */
    public void moveZeroes(int[] nums) {
        // 双指针，一个指针寻找0，一个指针遍历数组
        // 当遍历到不为0的数字时，与指向0位置交换。通过这样的操作把0逐渐移到末尾
        int j = -1; // 开始未找到0的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && j >= 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++; //交换后，j++位置的数字一定是0，所以将指针右移一位
            } else if (nums[i] == 0 && j < 0) {
                j = i;
            }
        }
    }

    public void moveZeroes1(int[] nums) {
        if(nums==null) {
            return;
        }
        //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        //非0元素统计完了，剩下的都是0了
        //所以第二次遍历把末尾的元素都赋为0即可
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * 找出数组中重复的数字。
     *
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     *
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * @param nums
	 * @return int
     */
    public int findRepeatNumber(int[] nums) {
        // 通常思路会创建一个hash集合存储比较，空间复杂度O(n)
        // 由于题目中nums里所有数字在 0～n-1 的范围内，
        // 所以可以把数字nums[i]交换到对应的i位置，如果i位置已存在i==nums[i]，则找到重复数字
        if (nums == null) return -1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) { //当前i放置的数字不匹配时
                if (nums[i] == nums[nums[i]]) { //如果此数字已被放置到正确位置，说明是重复数字
                    return nums[i];
                }
                // 把nums[i]放到它对应的位置上去
                int temp = nums[i];
                nums[i] = nums[nums[i]];
                nums[temp] = temp; //注意此处写法
            }
        }
        return -1;
    }

}
