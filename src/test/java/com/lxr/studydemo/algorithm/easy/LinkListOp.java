package com.lxr.studydemo.algorithm.easy;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LinkList
 * @Description TODO
 * @Author Areogel
 * @Date 2021/3/10 16:45
 * @Version 1.0
 */
public class LinkListOp {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 206. 反转链表
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //递归操作 ——> 下个节点next指向本节点, 本节点next指向null
        //head == null主要是判断第一个节点是空的情况，head.next == null是递归条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    public ListNode reverseList1(ListNode head) {
        //迭代
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    @Test
    public void testReverseList() {
        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
//        ListNode listNode = reverseList(node1);
        ListNode listNode = reverseList1(node1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * 92. 反转链表 II
     * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     *
     * @param head
     * @param left
     * @param right
     * @return com.lxr.studydemo.algorithm.easy.LinkListOp.ListNode
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    public ListNode reverseBetween1(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    /**
     * 141. 环形链表
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     *
     * 进阶：
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     *
     * @param head
     * @return boolean
     */
    public boolean hasCycle(ListNode head) {
        // 首先考虑新建哈希表存储节点，判断next节点是否已存在于哈希表中，如果已存在则说明出现循环
        // 空间复杂度O(n)
        if (head == null) return false;
        Set<ListNode> nodeSet = new HashSet<>();
        while (!nodeSet.contains(head)) {
            nodeSet.add(head);
            if (head.next == null) { // 如果下一节点是null，则说明链表中没有循环，返回false
                return false;
            }
            head = head.next;
        }
        // 如果跳出循环，说明存在环
        return true;
    }

    public boolean hasCycle1(ListNode head) {
        // 优化方法一写法
        if (head == null) return false;
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            if (!nodeSet.add(head)) { // nodeSet.add(head) return true if not exists 所以当哈希表中存在节点时返回false终止循环
                return true;
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        // 空间复杂度O(1)的方法 ——> 双指针（快慢指针）
        // 快指针步长2，慢指针步长1，慢指针从head开始搜寻节点，快指针从head.next开始。
        // 如果直链，快指针搜寻到null，结束搜索
        // 当快指针等于慢指针时，退出循环，确认为环
        // 说明：快指针步长是慢指针2倍，即使是最大环，慢指针走一圈的时候，快指针必走两圈，因而快指针必可以在O(n)内追上慢指针
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode quick = head.next;
        while (quick != null && quick.next != null) {
            if (slow == (quick = quick.next) || slow == (quick = quick.next)) {
                return true;
            }
            slow = slow.next;
        }
        return false;
    }

    @Test
    public void testHasCycle() {
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        node4.next = node2;
        System.out.println(hasCycle2(node1));
    }
}
