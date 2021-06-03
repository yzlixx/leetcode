package class02;

/**
 * @author lixiaoxuan
 * @description: 位运算
 * @date 2021/6/1 14:10
 */
public class Code_01 {

    // 异或运算等于无进位相加

    //如何不用额外变量交换两个数

    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 13;
        swap(a, b);
        System.out.println("a=" + a + ",b=" + b);

    }
}
