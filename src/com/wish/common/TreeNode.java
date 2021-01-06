package com.wish.common;

/**
 * 二叉树节点
 * @param <T>
 */
public class TreeNode<T> {
    private T value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(T value, TreeNode left ,TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
