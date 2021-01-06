package com.wish.commonparent;

import com.wish.common.TreeNode;

/**
 *  二叉树公共父节点
 *
 * @author wish
 * @date 2021/1/3 22:06
 */
public class CommonParent {

    public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if(root == null) {
            return null;
        }
        if(root == p || root == q ) {
            return root;
        }
        TreeNode<Integer> left = lowestCommonAncestor(root.getLeft(), p ,q);
        TreeNode<Integer> right = lowestCommonAncestor(root.getRight(), p ,q);
        if(left != null && right != null) {
            return root;
        }
        if(left != null && right == null) {
            return left;
        }
        if(left == null && right != null) {
            return right;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode<Integer> node0 = new TreeNode<>(7, null, null);
        TreeNode<Integer> node1 = new TreeNode<>(4, null, null);
        TreeNode<Integer> node2 = new TreeNode<>(2, node0, node1);
        TreeNode<Integer> node3 = new TreeNode<>(6, null, null);
        TreeNode<Integer> node4 = new TreeNode<>(5, node3, node2);
        TreeNode<Integer> node5 = new TreeNode<>(0, null, null);
        TreeNode<Integer> node6 = new TreeNode<>(8, null, null);
        TreeNode<Integer> node7 = new TreeNode<>(1, node5, node6);
        TreeNode<Integer> node8 = new TreeNode<>(3, node4, node7);

        TreeNode<Integer> lowestCommonAncestor = lowestCommonAncestor(node8, node1, node6);
        System.out.println(lowestCommonAncestor == null ? "null" : lowestCommonAncestor.getValue());
    }
}
