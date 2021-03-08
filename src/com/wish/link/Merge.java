package com.wish.link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wish
 * @ClassName Merge.java
 * @Description 合并\ k k 个已排序的链表并将其作为一个已排序的链表返回
 * @createTime 2021年03月08日 19:04:00
 */

public class Merge {
    public static void main(String[] args) {
        int[][] array = new int[][]{{1,2,3},{4,5,6,7},{-1,6,9,10},{1,2,3,4}};
        List<ListNode> list = new ArrayList<>();
        for (int[] ints : array) {
            ListNode root = null;
            ListNode prev = null;
            for (int i : ints) {
                ListNode listNode = new ListNode(i);
                if(root == null) {
                    root = listNode;
                }
                if(prev != null) {
                    prev.next = listNode;
                }
                prev = listNode;
            }
            list.add(root);
        }
        ListNode listNode = mergeKLists(list);
        print(listNode);
    }

    public static void print(ListNode listNode) {
        while(listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println("");
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

        public static ListNode mergeKLists(List<ListNode> lists) {
            if(lists.size() > 2) {
                int sep = lists.size()/2;
                ListNode left = mergeKLists(lists.subList(0, sep));
                ListNode right = mergeKLists(lists.subList(sep, lists.size()));
                List<ListNode> newList = Arrays.asList(left, right);
                return mergeKLists(newList);
            }else if(lists.size() == 1){
                return lists.get(0);
            }else {
                ListNode left = lists.get(0);
                ListNode right = lists.get(1);
                ListNode listNode = mergeList(left, right);
                print(listNode);
                return listNode;
            }
    }

    public static ListNode mergeList(ListNode left, ListNode right) {
        ListNode current = new ListNode(0);
        ListNode root = current;
        while(left != null || right != null) {
            if(right != null && (left == null  || right.val < left.val)) {
                current.next = right;
                current = right;
                right = right.next;
            }else {
                current.next = left;
                current = left;
                left = left.next;
            }
        }
        return root.next;
    }
}
