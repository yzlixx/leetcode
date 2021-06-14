package class12;

import javax.print.DocFlavor;

/**
 * @author lixiaoxuan
 * @description: 是否是平衡二叉树
 * @date 2021/6/13 18:25
 */
public class Code03_IsBalanced {

    // 二叉树的左右子树的高度差为1
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBalanced1(Node head) {
        if (head == null) {
            return true;
        }
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    private static int process1(Node node, boolean[] ans) {
        if (node == null) {
            return 0;
        }

        int leftHeight = process1(node.left, ans);
        int rightHeight = process1(node.right, ans);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static class Info {
        boolean isBalance;
        int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBalanced2(Node head) {
        if (head == null) {
            return true;
        }

        return process2(head).isBalance;
    }

    private static Info process2(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }

        Info leftInfo = process2(node.left);
        Info rightInfo = process2(node.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) < 2;

        return new Info(isBalance, height);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println(isBalanced1(head));
                System.out.println(isBalanced2(head));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
