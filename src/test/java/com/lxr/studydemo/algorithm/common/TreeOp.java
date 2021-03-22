package com.lxr.studydemo.algorithm.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
     * 199. 二叉树的右视图
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     * 输入:[1,2,3,null,5,null,4]
     * 输出:[1, 3, 4]
     * 解释:
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     * @param
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        //由于每层输出一个节点，首先考虑层序遍历BFS
        List<Integer> resList = new LinkedList<>();
        if (root == null) return resList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) { //循环遍历每层
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    resList.add(node.val); //保存每层最后一个元素
                }
            }
        }
        return resList;
    }


}
