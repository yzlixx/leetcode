package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lixiaoxuan
 * @description: 求二叉树最宽的层有多少个节点
 * @date 2021/6/13 18:21
 */
public class Code05_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if(head == null){
            return 0;
        }
        //<K,V> =>节点，层数
        HashMap<Node,Integer> map = new HashMap<>();
        map.put(head,1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int curLevel = 1;
        int curLevelNode = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int curNodeLevel = map.get(node);
            if(node.left != null){
                queue.add(node.left);
                map.put(node.left,curNodeLevel+1);
            }
            if(node.right != null){
                queue.add(node.right);
                map.put(node.right,curNodeLevel+1);
            }

            if(curNodeLevel == curLevel){
                curLevelNode++;
            }else{
                max = Math.max(max,curLevelNode);
                curLevel++;
                curLevelNode = 1;
            }
        }

        return  Math.max(max,curLevelNode);
    }


    public static int maxWidthNoMap(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curLevelEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            if(poll.left != null){
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if(poll.right != null){
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            curLevelNodes++;
            if(poll == curLevelEnd){
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 0;
                curLevelEnd = nextEnd;
            }
        }
        return max;
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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
