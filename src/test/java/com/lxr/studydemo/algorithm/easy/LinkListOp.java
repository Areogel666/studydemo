package com.lxr.studydemo.algorithm.easy;

import com.lxr.studydemo.algorithm.Easy;
import org.junit.jupiter.api.Test;

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
}
