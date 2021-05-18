/**
 * @author lixiaoxuan
 * @description: 归并排序O(N * logN)
 * @date 2021/5/17 11:28
 */
public class Code_MergeSort {

    /*
     * 归并排序（分治法，两个有序子序列进行合并排序）
     * [2,4,3,5,9,1,3,7,8]归并排序
     * 先二分
     * [2,4,3,5],[9,1,3,7,8]分别进行排序
     * 二分
     * [2,4],[3,5],[9,1],[3,7,8]分别排序
     * 在二分
     * [2],[4]=>归并排序结果[2,4]
     * [3],[5]=>[3,5]
     * [9],[1]=>[1,9]
     * [3],[7]=>[3,7]
     * [8]=>[8]
     * 归并
     * [2,4],[3,5] => [2,3,4,5]
     * [1,9],[3,7] => [1,3,7,9]
     * [2,3,4,5],[1,3,7,9] => [1,2,3,3,4,5,7,9]
     * [1,2,3,3,4,5,7,9],[8] => [1,2,3,3,4,5,7,8,9]
     */

    // 1.递归
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        //无法再次二分
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int MID, int R) {
        int[] help = new int[R - L + 1];
        int cursePoint1 = L;
        int cursePoint2 = MID + 1;
        int count = 0;

        while (cursePoint1 <= MID && cursePoint2 <= R) {
            help[count++] = arr[cursePoint1] >= arr[cursePoint2] ? arr[cursePoint2++] : arr[cursePoint1++];
        }
        while (cursePoint1 <= MID) {
            help[count++] = arr[cursePoint1++];
        }
        while (cursePoint2 <= R) {
            help[count++] = arr[cursePoint2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }


    // 2.迭代，step步长
    /*
     * [2,4,3,5,9,1,3,7,8]
     * 1.step=1
     * [2],[4] => [2,4]
     * [3],[5] => [3,5]
     * [9],[1] => [1,9]
     * [3],[7] => [3,7]
     * [8] => [8]
     * 2.step=2
     * [2,4],[3,5] => [2,3,4,5]
     * [1,9],[3,7] => [1,3,7,9]
     * [8] => [8]
     * 3.step=4
     * [2,3,4,5],[1,3,7,9] => [1,2,3,3,4,5,7,9]
     * [8] =>[8]
     * 4.step = 8
     * [1,2,3,3,4,5,7,9],[8] => [1,2,3,3,4,5,7,8,9]
     */

    public static void mergeSort2(int[] arr) {
        int step = 1;
        while (step <= arr.length) {
            for (int i = 0; i < arr.length; i = i + 2 * step) {
                merge(arr, i, Math.min(i + step - 1, arr.length - 1), Math.min(i + step * 2 - 1, arr.length - 1));
            }
            step = step << 1;
        }
    }


    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

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

}
