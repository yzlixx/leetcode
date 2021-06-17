package class11;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/13 18:23
 */
public class Code07_PaperFolding {

    //    请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
//    给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
//    例如:N=1时，打印: down N=2时，打印: down down up
    public static void printAllFolds(int N) {
        //构建二叉树
        if (N >= 1) {
            Node head = createTree(N, "down");
            //二叉树的中序遍历
            in(head);
        }
    }

    // 中序遍历
    private static void in(Node head) {
        if (head == null) {
            return;
        }

        in(head.left);
        System.out.println(head.value);
        in(head.right);

    }

    private static Node createTree(int n, String value) {
        if (n == 0) {
            return null;
        }
        Node res = new Node(value);
        res.left = createTree(n - 1,"down");
        res.right = createTree(n - 1, "up");

        return res;
    }


    public static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }
}
