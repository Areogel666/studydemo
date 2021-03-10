package com.lxr.studydemo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Easy {

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 空间复杂度O(n) 时间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //考虑采用哈希map查询，时间复杂度为O(1)，循环n次
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(target - nums[i])) {
                return new int[]{numMap.get(target - nums[i]), i};
            }
            numMap.put(nums[i], i);
        }
        return new int[]{0};
    }

    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Arrays.stream(twoSum(nums, target)).forEach(System.out::println);
    }

    /**
     * 7. 整数反转
     * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x = -x;
        }
        String resStr = "";
        String numStr = String.valueOf(x);
        Stack<Character> numStack = new Stack<>();
        for (int i = 0; i < numStr.length(); i++) {
            numStack.push(numStr.charAt(i));
        }
        while (!numStack.empty()) {
            Character c = numStack.pop();
            resStr += c;
        }
        int resNum;
        try {
            resNum = Integer.parseInt(resStr);
        } catch (NumberFormatException e) {
            resNum = 0;
        }
        if (negative) {
            return -resNum;
        }
        return resNum;
    }

    public int reverseBetter(int x) {
        //考虑用数学方式，循环求余。同时每次判断res * 10 + x % 10是否会超限
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return res;
    }

    @Test
    public void testReverse() {
        System.out.println(reverse(1534236469));
        System.out.println(reverseBetter(1534236469));
    }

    /**
     * 9. 回文数
     * <p>
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     *
     * @param x
     * @return boolean
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        while (x != 0) {
            list.add(x % 10);
            x /= 10;
        }
        int right;
        int left;
        for (int j = 0; j <= list.size() / 2; j++) {
            right = list.get(j);
            left = list.get(list.size() - j - 1);
            if (right != left) return false;
        }
        return true;
    }

    public boolean isPalindrome1(int x) {
        //数学方式
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) div *= 10; //求出最大除数
        while (x > 0) {
            int left = x / div;//左起
            int right = x % 10;//右起
            if (left != right) return false;
            x = (x % div) / 10; //去除左右已比较数字
            div /= 100; //由于去除两位，除数减少两位 100
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10; //反转数
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }


    @Test
    public void testIsPalindrome() {
        System.out.println(isPalindrome(1432341));
        System.out.println(isPalindrome1(1432341));
        System.out.println(isPalindrome2(1432341));
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * @param root
	 * @return boolean
     */
    public boolean isSymmetric(TreeNode root) {
        //考虑递归的方法
        //初始条件   左子树与右子树镜像
        //递归条件   左左子树与右右子树 && 左右子树与右左子树镜像
        //结束条件   左右节点都为null || 不等
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
        //考虑把初始条件转换为递归条件
//        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        //迭代  递归——>迭代：考虑使用队列
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return true;
        queue.offer(root.right);
        queue.offer(root.left);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.offer(right.right);
            queue.offer(left.left);
            queue.offer(right.left);
            queue.offer(left.right);
        }
        return true;
    }

    @Test
    public void testIsSymmetric() {
        TreeNode leftLeaf = new TreeNode(3);
        TreeNode rightLeaf = new TreeNode(2);
        TreeNode left = new TreeNode(4, leftLeaf, rightLeaf);
        TreeNode right = new TreeNode(4, rightLeaf, leftLeaf);
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(isSymmetric(root));
        System.out.println(isSymmetric2(root));
    }

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
