package class08;

import java.util.Arrays;

/**
 * @author lixiaoxuan
 * @description: 基数排序(仅针对于正整数)
 * @date 2021/6/11 11:30
 */
public class Code03_RadixSort {
    //    时间复杂度	额外空间复杂度		稳定性
//    选择排序		O(N^2)			O(1)		无
//    冒泡排序		O(N^2)			O(1)		有
//    插入排序		O(N^2)			O(1)		有
//    归并排序		O(N*logN)		O(N)		有
//    随机快排		O(N*logN)	  O(logN)		无
//    堆排序		O(N*logN)		O(1)		无
//========================================================
//    计数排序		O(N)			O(M)		有
//    基数排序		O(N)			O(N)		有

//    1）不基于比较的排序，对样本数据有严格要求，不易改写
//    2）基于比较的排序，只要规定好两个样本怎么比大小就可以直接复用
//    3）基于比较的排序，时间复杂度的极限是O(N*logN)
//    4）时间复杂度O(N*logN)、额外空间复杂度低于O(N)、且稳定的基于比较的排序是不存在的。
//    5）为了绝对的速度选快排、为了省空间选堆排、为了稳定性选归并


    public static void radixSort(int[] arr) {
        if(arr == null || arr.length<2){
            return;
        }
        int maxBits = maxbits(arr);
        int[] help = new int[arr.length];
        for (int i = 1; i <=maxBits; i++) {
            int[] count = new int[10];
            for (int j = 0; j < arr.length; j++) {
                int digit = getDigit(arr[j],i);
                count[digit]++;
            }
            //计算count数组累加和
            for (int j = 1; j < 10; j++) {
                count[j] = count[j-1]+count[j];
            }
            // 根据count数组的数据将arr数据填写到help数组中
            // arr数组从右往左遍历
            for (int j = arr.length-1; j >=0 ; j--) {
                int digit = getDigit(arr[j],i);
                help[count[digit]-1] = arr[j];
                count[digit]--;
            }

            for (int j=0;j<help.length;j++){
                arr[j] = help[j];
            }
        }
    }

    public static int getDigit(int value, int radix) {
        return (value / (int) (Math.pow(10, radix - 1)) % 10);
    }


    // 最大的位数
    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            max = max / 10;
            res++;
        }

        return res;
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 10000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            radixSort(arr2);
            comparator(arr3);
            if (!isEqual(arr2, arr3)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }


}
