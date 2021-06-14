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

        return head;
    }
}
