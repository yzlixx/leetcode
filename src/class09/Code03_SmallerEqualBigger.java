package class09;

/**
 * @author lixiaoxuan
 * @description: 将单链表按固定值按小中大分区
 * @date 2021/6/11 11:36
 */
public class Code03_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

    }

    // 使用额外空间
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        Node[] arr = new Node[count];

        cur = head;
        count = 0;
        while (cur != null) {
            arr[count++] = cur;
            cur = cur.next;
        }

        partition(arr, pivot);
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        arr[arr.length - 1].next = null;
        return arr[0];
    }

    public static void partition(Node[] arr, int privot) {
        int leastRange = -1;
        int largeRnage = arr.length;
        int index = 0;
        while (index != largeRnage) {
            if (arr[index].value < privot) {
                swap(arr, index++, ++leastRange);
            }
            if (arr[index].value == privot) {
                index++;
            }
            if (arr[index].value > privot) {
                swap(arr, index++, --largeRnage);
            }
        }
    }

    private static void swap(Node[] arr, int i, int i1) {
        Node temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }


    public static Node listPartition2(Node head, int pivot) {
        Node lessHead = null;
        Node lessTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;

        Node cur = head;
        Node save = null;
        while (cur != null) {
            save = cur.next;
            cur.next = null;
            if (cur.value < pivot) {
                if (lessHead == null) {
                    lessHead = cur;
                    lessTail = cur;
                } else {
                    lessTail.next = cur;
                    lessTail = cur;
                }
            }
            if (cur.value == pivot) {
                if (equalHead == null) {
                    equalHead = cur;
                    equalTail = cur;
                } else {
                    equalTail.next = cur;
                    equalTail = cur;
                }
            }
            if (cur.value > pivot) {
                if (bigHead == null) {
                    bigHead = cur;
                    bigTail = cur;
                } else {
                    bigTail.next = cur;
                    bigTail = cur;
                }
            }
            cur = save;
        }

        if (lessHead != null && equalHead != null) {
            lessTail.next = equalHead;
        }

        if (equalHead != null && bigHead != null) {
            equalTail.next = bigHead;
        }

        if (lessHead != null && equalHead == null && bigHead != null) {
            lessTail.next = bigHead;
        }
        return lessHead == null ? equalHead == null ? bigHead : equalHead : lessHead;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        Node head2 = listPartition1(head1, 5);
//        printLinkedList(head2);
        head1 = listPartition2(head1, 4);
        printLinkedList(head1);

    }
}
