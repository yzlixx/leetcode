package class04;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/4 15:00
 */
public class Code_01 {

//    在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
//    例子： [1,3,4,2,5]
//            1左边比1小的数：没有
//            3左边比3小的数：1
//            4左边比4小的数：1、3
//            2左边比2小的数：1
//            5左边比5小的数：1、3、4、 2
//    所以数组的小和为1+1+3+1+1+3+4+2=16


    public static int minSum(int[] arr) {
        int result = 0;
        int step = 1;
        int max = arr.length;
        while (step < max) {
            for (int i = 0; i < max; i = i + step * 2) {
                result += merge(arr, i, Math.min(i + step - 1, arr.length - 1), Math.min(i + 2 * step - 1, arr.length - 1));
            }

            step = step << 1;
        }

        return result;
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int result = 0;
        int[] help = new int[right - left + 1];
        if (left == right) {
            return 0;
        }
        int leftPointer = left;
        int rightPointer = mid + 1;
        int index = 0;
        while (leftPointer <= mid && rightPointer <= right) {
            if (arr[leftPointer] < arr[rightPointer]) {
                result += (arr[leftPointer] * (right - rightPointer+1));
                help[index] = arr[leftPointer++];
                index++;
            }
            if (arr[leftPointer] >= arr[rightPointer]){
                help[index] = arr[rightPointer++];
                index++;
            }
        }

        while (leftPointer <= mid) {
            help[index++] = arr[leftPointer++];
        }
        while (rightPointer <= right) {
            help[index++] = arr[rightPointer++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return result;
    }


    public static int minSum2(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    result += arr[j];
                }
            }
        }
        return result;
    }

    // test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

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

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
//        int testTime = 50000;
//        int maxSize = 1000;
//        int maxValue = 100;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            int result1 = minSum(arr1);
//            int result2 = minSum2(arr2);
//            if (result1 != result2) {
////               printArray(arr2);
////                System.out.println(result1);
////                System.out.println(result2);
//                System.out.println("出错了");
//                break;
//            }
//        }
//        System.out.println("测试结束");
        int result = minSum(new int[]{64,49,5,22,35,2,17,8});
        System.out.println(result);
    }
}
