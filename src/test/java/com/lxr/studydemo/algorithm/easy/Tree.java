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
public class Tree {
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

        return 0;
    }

    @Test
    public void testMaxDepth() {

    }
}
