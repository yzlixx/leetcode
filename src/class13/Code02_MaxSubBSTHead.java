package class13;

import java.util.ArrayList;

/**
 * @author lixiaoxuan
 * @description: 最大搜索二叉树头节点
 * @date 2021/6/21 15:08
 */
public class Code02_MaxSubBSTHead {

//    给定一棵二叉树的头节点head，
//    返回这颗二叉树中最大的二叉搜索子树的头节点


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }

        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftNode = maxSubBSTHead1(head.left);

        Node rightNode = maxSubBSTHead1(head.right);

        return getBSTSize(leftNode) >= getBSTSize(rightNode) ? leftNode : rightNode;
    }

    private static int getBSTSize(Node node) {
        if (node == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(node, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    // 中序遍历
    public static void in(Node node, ArrayList<Node> arr) {
        if (node == null) {
            return;
        }
        in(node.left, arr);
        arr.add(node);
        in(node.right, arr);
    }


    public static Node maxSubBSTHead2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    public static class Info {
        int max;
        int min;
        int maxSubBSTSize;
        Node maxSubBSTHead;

        public Info(int max, int min, int maxSubBSTSize, Node maxSubBSTHead) {
            this.max = max;
            this.min = min;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int max = node.value;
        int min = node.value;
        int maxSubBSTSize = 0;
        Node maxSubBSTHead = null;
        if(leftInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
            maxSubBSTSize = Math.max(maxSubBSTSize,leftInfo.maxSubBSTSize);
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        }

        if(rightInfo != null){
            max = Math.max(max,rightInfo.max);
            min = Math.min(min,rightInfo.min);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }

//        if ((leftInfo == null ? true : (leftInfo.maxSubBSTHead == node.left && leftInfo.max < node.value))
//                && (rightInfo == null ? true : (rightInfo.maxSubBSTHead == node.right && rightInfo.min > node.value))) {
//            maxSubBSTHead = node;
//            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
//                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
//        }
        if(leftInfo == null && rightInfo == null){
            maxSubBSTHead = node;
            maxSubBSTSize = 1;
        }else if(leftInfo !=null && rightInfo == null && leftInfo.maxSubBSTHead == node.left &&leftInfo.max<node.value){
            maxSubBSTHead = node;
            maxSubBSTSize = leftInfo.maxSubBSTSize+1;
        }else if(leftInfo == null && rightInfo !=null && rightInfo.maxSubBSTHead == node.right && rightInfo.min>node.value){
            maxSubBSTHead = node;
            maxSubBSTSize = rightInfo.maxSubBSTSize+1;
        }else if(leftInfo != null && rightInfo != null && leftInfo.maxSubBSTHead == node.left && rightInfo.maxSubBSTHead == node.right && leftInfo.max<node.value && rightInfo.min>node.value){
            maxSubBSTHead = node;
            maxSubBSTSize = leftInfo.maxSubBSTSize+rightInfo.maxSubBSTSize+1;
        }


        return new Info(max, min, maxSubBSTSize, maxSubBSTHead);
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
        int testTimes = 10;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println(maxSubBSTHead1(head));
                System.out.println(maxSubBSTHead2(head));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
