package class09;

/**
 * @author lixiaoxuan
 * @description: 复制带随机指针的单链表
 * @date 2021/6/11 11:37
 */
public class Code04_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }
}
