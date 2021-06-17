package class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaoxuan
 * @description: 将多叉树转化为二叉树
 * @date 2021/6/13 18:20
 */
public class Code03_EncodeNaryTreeToBinaryTree {

//    Leetcode
//
//431. Encode N-ary Tree to Binary Tree


    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(root.val);
        treeNode.left = en(root.children);
        return treeNode;
    }

    public TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode tNode = new TreeNode(child.val);
            if (head == null) {
                head = tNode;
            } else {
                head.right = tNode;
            }
            cur = tNode;
            cur.left = en(child.children);
        }
        return head;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, de(root.left));
    }

    public List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();
        while (root != null) {
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }


}
