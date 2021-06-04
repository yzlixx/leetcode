package class02;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/1 14:18
 */
public class Code_02 {


    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 1, 1, 3, 3, 3, -17, -17, 6, 6, 6, 6};
        code05(arr,2,4);
    }

    // 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
    public static void get(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // 怎么把一个int类型的数，提取出最右侧的1来
    // a^(~a+1)    a取反+1 == a的相反数  a^(-a)


    // 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
    public static void code04(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightOne = eor & ((~eor) + 1);
        int one = 0;
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i] & rightOne;
            if (v != 0) {
                one ^= arr[i];
            }
        }
        System.out.println(one);
        System.out.println(eor ^ one);
    }


//    一个数组中有一种数出现K次，其他数都出现了M次，
//    M > 1,  K < M
//    找到，出现了K次的数，
//    要求，额外空间复杂度O(1)，时间复杂度O(N)

    public static void code05(int[] arr, int k, int m) {
        int result = 0;
        int[] index = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                index[j] += (arr[i]>>j & 1);
            }
        }
        for (int i = 0; i < index.length; i++) {
            index[i] %= m;
            if (index[i] != 0) {
                result += (1 << i);
            }
        }
        System.out.println(result);
    }



}
