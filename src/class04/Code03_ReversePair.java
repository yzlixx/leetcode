package class04;


/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/6/4 17:08
 */
public class Code03_ReversePair {

//    在一个数组中，
//    任何一个前面的数a，和任何一个后面的数b，
//    如果(a,b)是降序的，就称为逆序对
//            返回数组中所有的逆序对

    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
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
        int[] help = new int[right - left + 1];
        int leftPoint = left;
        int rightPoint = mid + 1;
        int index = 0;
        while (leftPoint <= mid && rightPoint <= right) {
            if (arr[leftPoint] > arr[rightPoint]) {
                ans += (mid - leftPoint + 1);
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
                if (arr[i] > arr[j]) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (reverPairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
