package class10;

import java.util.Stack;

/**
 * @author lixiaoxuan
 * @description: 非递归的二叉树先中后序遍历
 * @date 2021/6/13 18:17
 */
public class Code03_UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 先序遍历
    public static void pre(Node head){
        if(head == null ){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.value);
            if(pop.right != null){
                stack.add(pop.right);
            }
            if(pop.left != null){
                stack.add(pop.left);
            }
        }
    }

    //中序遍历
    public static void in(Node head){
        if(head == null ){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || head != null){
            if(head != null){
                stack.add(head);
                head = head.left;
            }else{
                Node in = stack.pop();
                System.out.println(in.value);
                head = in.right;
            }
        }
    }

    // 后序遍历（左右头）
    // 先头右左遍历
    public static void pos1(Node head){
        if(head == null ){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack.add(head);
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            stack2.add(pop);
            if(head.left != null){
                stack.add(head.left);
            }
            if(head.right != null){
                stack.add(head.right);
            }
        }

        while (!stack2.isEmpty()){
            Node pop = stack2.pop();
            System.out.println(pop.value);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
//        pos2(head);
//        System.out.println("========");
    }

}
