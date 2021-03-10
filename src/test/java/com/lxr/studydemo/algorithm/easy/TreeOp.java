package com.lxr.studydemo.algorithm.easy;

import com.lxr.studydemo.algorithm.Easy;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName Tree
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 13:13
 * @Version 1.0
 */
public class TreeOp {
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
     * 104. 二叉树的最大深度
     *
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明:叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度3 。
     *
     */
    public int maxDepth(TreeNode root) {
        // 深度优先搜索 递归
        // 最大深度 = max(左子树深度, 右子树深度) + 1
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth1(TreeNode root) {
        // 广度优先搜索 队列
        if (root == null) return 0;
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {// 队列不为空时，循环
            int size = queue.size();
            while (size-- > 0) {// 当本层循环父节点数大于0时，继续查找非空子节点，并放入队列中
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            res++;// 完成一层循环，树高加1
        }
        return res;
    }

    @Test
    public void testMaxDepth() {
        TreeNode leftLeaf = new TreeNode(15);
        TreeNode rightLeaf = new TreeNode(7);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, leftLeaf, rightLeaf);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(maxDepth1(root));
    }
}
