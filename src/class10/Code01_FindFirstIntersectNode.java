package class10;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/11 11:52
 */
public class Code01_FindFirstIntersectNode {

    //    给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返回null
    //    【要求】
    //    如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度 请达到O(1)。
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        // 都没有环节点
        if(loop1 == null && loop2 == null){
           return noLoop(head1, head2);
        }

        if(loop1 !=null && loop2 !=null){
            return bothLoop(head1, head2,loop1, loop2);
        }
        return null;
    }

    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
      if(loop1 == loop2){
          int len1 = 0;
          Node cur1 = head1;
          while(cur1 != loop1){
              len1++;
              cur1 = cur1.next;
          }
          int len2 = 0;
          Node cur2 = head2;
          while(cur2 != loop2){
              len2++;
              cur2 = cur2.next;
          }
          int diff = Math.abs(len1-len2);
          cur1 = head1;
          cur2 = head2;
          while(diff>0){
              if(len1>len2){
                  cur1 = cur1.next;
              }else{
                  cur2 = cur2.next;
              }
              diff--;
          }

          while(cur1 != null){

              if(cur1 == cur2){
                  return cur1;
              }
              cur1 = cur1.next;
              cur2 = cur2.next;
          }
      }

      // 判断环上有无交点
        Node cur1 = loop1.next;
        while(cur1 != loop2){
            if(cur1 == loop1){
                break;
            }
            cur1 = cur1.next;
        }

        if(cur1 == loop1){
            return null;
        }else{
            return loop1;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        //获取两个链表的长度
        int len1 = 0;
        Node cur1 = head1;
        while (cur1 != null){
            len1++;
            cur1 = cur1.next;
        }
        int len2 = 0;
        Node cur2 = head2;
        while (cur2 != null){
            len2++;
            cur2 = cur2.next;
        }
        int diff = Math.abs(len1-len2);
        cur1 = head1;
        cur2 = head2;
        while(diff>0){
            if(len1>len2){
                cur1 = cur1.next;
            }else{
                cur2 = cur2.next;
            }
            diff--;
        }

        while(cur1 != null){

            if(cur1 == cur2){
                return cur1;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return null;
    }


    //获取链表环的初始节点
    public static Node getLoopNode(Node head) {

        if (head == null|| head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        while (fast != slow) {
            if(fast == null){
               break;
            }
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }

        if(fast !=null && fast == slow){
            fast = head;
            while (fast != slow){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        return null;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
