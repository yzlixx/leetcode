package class04;

public class Code04_BiggerThanRightTwice {

    //    在一个数组中，
//    对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
//    比如：[3,1,7,0,2]
//            3的后面有：1，0
//            1的后面有：0
//            7的后面有：0，2
//            0的后面没有
//            2的后面没有
//            所以总共有5个

    public static int biggerTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr, int left, int right) {
        int ans = 0;
        if (left >= right) {
            return ans;
        }
        int mid = left + ((right - left) >> 1);
        ans += process(arr, left, mid);
        ans += process(arr, mid + 1, right);
        ans += merge(arr, left, mid, right);
        return ans;
    }

    private static int merge(int[] arr, int left, int mid, int right) {

        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (windowR <= right && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - mid - 1;
        }
        int[] help = new int[right - left + 1];
        int leftPoint = left;
        int rightPoint = mid + 1;
        int index = 0;
        while (leftPoint <= mid && rightPoint <= right) {
            if (arr[leftPoint] > arr[rightPoint]) {
                help[index++] = arr[rightPoint++];
            }else{
                help[index++] = arr[leftPoint++];
            }
        }

        while (leftPoint <= mid) {
            help[index++] = arr[leftPoint++];
        }

        while (rightPoint <= right) {
            help[index++] = arr[rightPoint++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }


        return ans;
    }


    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
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
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int ans1 = biggerTwice(arr1);
            int ans2 = comparator(arr2);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
