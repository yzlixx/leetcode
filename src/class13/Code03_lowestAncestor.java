package class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/21 15:08
 */
public class Code03_lowestAncestor {
    //    给定一棵二叉树的头节点head，和另外两个节点a和b。
//    返回a和b的最低公共祖先
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if(head == null || o1 == null || o2 == null){
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head,null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while(parentMap.get(cur) != null){
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
      while(!o1Set.contains(cur)){
          cur = parentMap.get(cur);
      }
        return cur;
    }

    private static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if(head.left != null){
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if(head.right != null){
            parentMap.put(head.right,head);
            fillParentMap(head.right, parentMap);
        }
    }


    public static Node lowestAncestor2(Node head, Node o1, Node o2) {
        if(head == null || o1 == null || o2 == null){
            return null;
        }
        return process(head,o1,o2).ans;
    }

    private static Info process(Node node, Node o1, Node o2) {
        if(node == null){
            return new Info(false,false,null);
        }
        Info leftInfo = process(node.left, o1, o2);
        Info rightInfo = process(node.right, o1, o2);
        boolean findA = leftInfo.findA || rightInfo.findA || node == o1;
        boolean findB = leftInfo.findB || rightInfo.findB || node == o2;
//        Node ans = null;
//        if(leftInfo.ans != null){
//            ans = leftInfo.ans;
//        }else if(rightInfo.ans != null){
//            ans = rightInfo.ans;
//        }else{
//            if(findA && findB){
//                ans = node;
//            }
//        }
        Node ans = leftInfo.ans == null ? rightInfo.ans : leftInfo.ans;
        if(findA && findB && leftInfo.ans == null && rightInfo.ans == null){
            ans = node;
        }
        return new Info(findA, findB,ans );
    }

    public static class Info{
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
