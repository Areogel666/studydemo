package com.lxr.studydemo.algorithm.common;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LinkList
 * @Description
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
        //头插法：固定当前节点，每次将下一节点插入头部，直到当前节点指向right + 1节点
        //设置虚拟头结点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        //首先找到left前一节点
        ListNode prev = dummyNode;
        ListNode cur = null;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        //开始头插处理反转链表
        cur = prev.next; //获得当前节点
        //循环到right节点完成指针变更
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            //将cur指向下下个节点（处理当前节点指向）
            cur.next = next.next;
            //将next指向prev.next（处理头插节点指向）
            next.next = prev.next;
            //将prev指向next（处理cur上一节点指向）
            prev.next = next;
        }
        return dummyNode.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
     *
     * 进阶：你能尝试使用一趟扫描实现吗？
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     *
     * @param head
     * @param n
     * @return com.lxr.studydemo.algorithm.common.LinkListOp.ListNode
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 一趟实现，考虑递归，返回后一个节点的倒数位置
        // 由于此处可能存在单节点链表，为简化分类，引入哨兵节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        removeNthFromEndRecursion(dummyNode, n);
        return dummyNode.next;
    }

    private int removeNthFromEndRecursion(ListNode curr, int n) {
        //终止条件
        if (curr == null || curr.next == null) return 1;
        int end = removeNthFromEndRecursion(curr.next, n);//记录是倒数第n个几点
        if (end != n) return ++end; //不是待删除节点的前一节点，跳过
        //是待删除节点的前一节点，则此节点指向下下一节点
        curr.next = curr.next.next; //此处由于
        return ++end;
    }
}
