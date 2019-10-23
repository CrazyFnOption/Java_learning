package com.algorithm_learning.seach;

public class binary_tree {
    private node root;

    public class node {
        double val;
        node left;
        node right;
    }

    private node insert(node root,double val) {
        if (root == null) {
            root = new node();
            root.left = root.right = null;
            root.val = val;
            return root;
        }
        if (val < root.val) root.left = insert(root.left,val);
        else root.right = insert(root.right,val);
        return root;
    }

    private boolean search(node root, double val) {
        if (root == null) return false;
        if (root.val == val) return true;
        return search(root.left, val) || search(root.right, val);
    }

    public boolean search(double val) {
        return search(root,val);
    }

    public binary_tree(double[] data) {
        for (int i = 0; i < data.length; i++) {
            root = insert(root, data[i]);
        }

    }
}
