package class12;

import java.util.ArrayList;

/**
 * @author lixiaoxuan
 * @description: 是否是搜索二叉树
 * @date 2021/6/13 18:24
 */
public class Code02_IsBST {

    //搜索二叉树定义 树的左节点小于父节点，树的右节点大于父节点
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 中序遍历
    public static boolean isBST1(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    private static void in(Node node, ArrayList<Node> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }

    //树型dp
    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }

        return process(head).isBST;
    }

    private static Info process(Node node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int min = node.value;
        int max = node.value;
        if (leftInfo == null && rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }

        if (leftInfo != null && rightInfo == null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }

        if (leftInfo != null && rightInfo != null) {
            min = Math.min(leftInfo.min, rightInfo.min);
            max = Math.max(leftInfo.max, rightInfo.max);
        }

        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= node.value) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= node.value) {
            isBST = false;
        }

        return new Info(isBST, max, min);
    }

    public static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
