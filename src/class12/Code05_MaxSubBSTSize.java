package class12;

import java.util.ArrayList;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/13 18:26
 */
public class Code05_MaxSubBSTSize {
//    给定一棵二叉树的头节点head，
//    返回这颗二叉树中最大的二叉搜索子树的大小

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 中序遍历
    public static int getBSTSize(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    private static void in(Node node, ArrayList<Node> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static int maxSubBSTSize2(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }

    private static Info process(Node node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value;
        int min = node.value;
        boolean isBST = true;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        if (leftInfo != null && leftInfo.max >= node.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= node.value) {
            isBST = false;
        }
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        int maxSubBSTSize = 0;
        if (isBST) {
            maxSubBSTSize = (leftInfo != null ? leftInfo.maxSubBSTSize : 0) + (rightInfo != null ? rightInfo.maxSubBSTSize : 0) + 1;
        } else {
            if (leftInfo != null) {
                maxSubBSTSize = Math.max(leftInfo.maxSubBSTSize, maxSubBSTSize);
            }
            if (rightInfo != null) {
                maxSubBSTSize = Math.max(rightInfo.maxSubBSTSize, maxSubBSTSize);
            }
        }


        return new Info(isBST, max, min, maxSubBSTSize);
    }

    public static class Info {
        boolean isBST;
        int max;
        int min;
        int maxSubBSTSize;

        public Info(boolean isBST, int max, int min, int maxSubBSTSize) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
