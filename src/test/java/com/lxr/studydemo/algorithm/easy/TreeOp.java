package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

import java.util.*;

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

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉树: root =[3,5,1,6,2,0,8,null,null,7,4]
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //考虑递归:
        // 1.递归左右子树，查找节点，查找到返回上一层，查找不到返回null
        // 2.如果此节点等于p或q，说明最近公共祖先是此节点或其祖先节点，因此直接返回递归
        // 3.如果返回的左右子节点都有值，说明两个节点在此节点左右两边，则此节点为最近公共祖先
        // 4.如果一边返回结果，说明要么返回节点是最近公共祖先，要么最近公共祖先还在祖先节点，因此继续返回
        // 5.如果两边都返回null，说明父节点下未找到给定节点，继续向上递归
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /**
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * 例如:
     *      给定二叉树:[3,9,20,null,null,15,7],
     *      3
     *      / \
     *      9  20
     *      /  \
     *      15   7
     *      返回：
     *      [3,9,20,15,7]
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        //BFS
        if (root == null) return new int[0];
        List<Integer> resList = new ArrayList<>(); // 由于后面遍历打印，考虑存在ArrayList
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            resList.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] array = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            array[i] = resList.get(i);
        }
        return array;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 例如：
     * 给定二叉树[3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回锯齿形层序遍历如下：
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderReverse(TreeNode root) {
        //BFS
        List<List<Integer>> resList = new LinkedList<>();
        if (root == null) return resList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int treeHigh = 0;
        while (!queue.isEmpty()) {
            List<Integer> curLevelList = new LinkedList<>();//也可以考虑使用Deque
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //还是从队列中取值，但是放入结果链表的时候分别头插尾插
                if (treeHigh % 2 == 1) {
                    curLevelList.add(0, node.val);
                } else {
                    curLevelList.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            resList.add(curLevelList);
            treeHigh++;
        }
        return resList;
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层序遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderEveryLevel(TreeNode root) {
        // BFS 广度优先搜索
        // 使用队列，将待遍历节点放入队列，完成遍历出队列，直到队列为空遍历完成
        // 增加一个内层循环，存储本层遍历的所有结果，遍历queue.size()次
        List<List<Integer>> resList = new LinkedList<>();
        if (root == null) return resList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevelList = new ArrayList<>();
            int size = queue.size(); //本层节点个数
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curLevelList.add(node.val); //存入本层节点
                if (node.left != null) {
                    queue.add(node.left); //向队列中加入下一层节点
                }
                if (node.right != null) {
                    queue.add(node.right); //向队列中加入下一层节点
                }
            }
            resList.add(curLevelList);
        }
        return resList;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层序遍历为：
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderEveryLevelBottom(TreeNode root) {
        //考虑使用普通BFS
        List<List<Integer>> resList = new LinkedList<>(); //使用链表尾插
        if (root == null) return resList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curLevelList.add(node.val); //存入本层节点
                if (node.left != null) {
                    queue.add(node.left); //向队列中加入下一层节点
                }
                if (node.right != null) {
                    queue.add(node.right); //向队列中加入下一层节点
                }
            }
            resList.add(0, curLevelList);
        }
        return resList;
    }

    /**
     * 145. 二叉树的后序遍历
     * 给定一个二叉树，返回它的 后序遍历。
     * 示例:
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [3,2,1]
     * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
     * @param root
     * @return
     */
    public List<Integer> allOrderTraversal(TreeNode root) {
        //dfs 深度优先搜索 ——> 利用栈 ——> 递归
        List<Integer> resList = new LinkedList<>();
        if (root == null) {
            return resList;
        }
        allOrderTraversalDfs(root, resList);
        return resList;
    }

    private void allOrderTraversalDfs(TreeNode root, List<Integer> resList) {
        if (root == null) return;
        // 前序遍历：先遍历根节点，然后左子树，最后右子树
//        resList.add(root.val);
//        postorderTraversalDfs(root.left, resList);
//        postorderTraversalDfs(root.right, resList);
        // 中序遍历：先遍历左子树，然后根节点，最后右子树
//        postorderTraversalDfs(root.left, resList);
//        resList.add(root.val);
//        postorderTraversalDfs(root.right, resList);
        // 后序遍历：先遍历左子树，然后右子树，最后根节点
        allOrderTraversalDfs(root.left, resList);
        allOrderTraversalDfs(root.right, resList);
        resList.add(root.val);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        //前序遍历 根左右
        List<Integer> resList = new LinkedList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) { //循环至左下没有元素为止
                resList.add(root.val); //先放入元素
                stack.push(root);
                root = root.left;
            }
            root = stack.pop(); //压栈
            root = root.right; //此时循环上一层右子树
        }
        return resList;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        //中序遍历 左根右
        List<Integer> resList = new LinkedList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left; //仍然是向左下搜索，与前序的区别是只搜索不存入resList
            }
            //直到搜到最左下节点，再存入resList
            root = stack.pop();
            resList.add(root.val);
            root = root.right; //最后搜寻右侧
        }
        return resList;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        //后序遍历 左右根
        //考虑翻转，根右左，与前序相似，只不过先搜索右下
        List<Integer> resList = new LinkedList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                resList.add(root.val);
                stack.push(root);
                root = root.right; // 先搜索右下节点
            }
            root = stack.pop(); //压栈
            root = root.left;
        }
        Collections.reverse(resList);
        return resList;
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        // 后序 左右根 dfs 深度优先搜索 ——> 利用栈 ——> 如果使用迭代，则利用显式栈
        // 不考虑取巧的方式
        List<Integer> resList = new LinkedList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) { //循环结束保证左节点为null
                stack.push(root);
                root = root.left;
            }
            root = stack.peek(); //由于最后打印根，所以只有当节点所有左右子树都搜索完成后才能pop。因此此处使用peek获得栈首元素。
            //保证右节点为null，此时可存值压栈
            //注：root.right == prev这个条件，保证右子节点已存储的情况下，直接存储根节点
            if (root.right == null || root.right == prev) {
                resList.add(root.val);
                stack.pop(); //此时压栈后，栈中首元素是上一节点
                prev = root;
                root = null; //必须将root置为null，这样才能继续压栈找到上一节点
            } else {
                root = root.right;
            }
        }
        return resList;
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     *
     * 示例：
     * 输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeDfs(root);
        return root;
    }

    private void invertTreeDfs(TreeNode node) {
        if (node == null) return;
        //左右子节点交换
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        invertTreeDfs(node.left);
        invertTreeDfs(node.right);
    }

    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     * @param root
	 * @return boolean
     */
    public boolean isBalanced(TreeNode root) {
        //递归比较左右子树高度差 时间复杂度 O(N*N) 空间复杂度 O(N)
        //自顶向下的递归，每次查询树高，存在重复递归
        if (root == null) return true;
        int leftHigh = getTreeHigh(root.left); //左子树高
        int rightHigh = getTreeHigh(root.right); //右子树高
        if (leftHigh - rightHigh > 1 || leftHigh - rightHigh < -1) return false; //如果子树高度相差1以上，直接返回false
        return isBalanced(root.left) && isBalanced(root.right); //分别递归左右子树
    }

    private int getTreeHigh(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getTreeHigh(node.left), getTreeHigh(node.right)) + 1;
    }

    public boolean isBalanced1(TreeNode root) {
        //自底向上的递归 时间复杂度 O(N) 空间复杂度 O(N)
        //后序遍历到底，每次记录树高，如果子树已不平衡返回false，平衡则比较左右子树高度差
        //由于每次递归返回树高，不会重复查询树高
        return isChildBalance(root) != -1;
    }

    //如平衡获得节点树高，不平衡返回-1
    private int isChildBalance(TreeNode node) {
        if (node == null) return 0; //如无子节点，返回树高0
        int left = isChildBalance(node.left); //后序遍历到最左下节点，此时返回left = 0
        if (left == -1) return -1;
        int right = isChildBalance(node.right); //后序遍历右节点，获得右子树树高
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : Math.abs(left - right) + 1; //如果右子树与左子树高度差大于1，返回-1表示不平衡
    }
}
