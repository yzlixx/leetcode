package class12;

import class11.Code04_PrintBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lixiaoxuan
 * @description: 是否是完全二叉树
 * @date 2021/6/13 18:24
 */
public class Code01_IsCBT {
    // 完全二叉树的定义：满二叉树或者从左到又依次变满（堆就是一个完全二叉树）

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


        public static boolean isCBT1(Node head) {
            // 空树认为是完全二叉树
            if (head == null) {
                return true;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            boolean leaf = false;
            while (!queue.isEmpty()) {
                head = queue.poll();
                Node left = head.left;
                Node right = head.right;
                // 判断节点是不是都有左右节点
                if (left == null && right != null) {
                    return false;
                }

                if (leaf && (left != null || right != null)) {
                    return false;
                }

                // 右节点为空的话，后续所有节点必须是叶节点
                if (left == null || right == null) {
                    leaf = true;
                }
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }

            return true;
        }


        public static boolean isCBT2(Node head) {
            if (head == null) {
                return true;
            }
            return process(head).isCBT;
        }

        private static Info process(Node node) {
            if (node == null) {
                return new Info(true, true, 0);
            }
            Info leftInfo = process(node.left);
            Info rightInfo = process(node.right);
            boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
            int height = Math.max(leftInfo.height,rightInfo.height)+1;
            boolean isCBT = false;
            if(isFull){
                isCBT = true;
            }else{
                if(leftInfo.isCBT && rightInfo.isCBT){
                    if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                        isCBT = true;
                    }
                    if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                        isCBT = true;
                    }
                    if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                        isCBT = true;
                    }
                }
            }

            return new Info(isCBT, isFull, height);
        }

        public static class Info {
            boolean isCBT;
            boolean isFull;
            int height;

            public Info(boolean isCBT, boolean isFull, int height) {
                this.isCBT = isCBT;
                this.isFull = isFull;
                this.height = height;
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println(isCBT1(head));
                System.out.println(isCBT2(head));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
