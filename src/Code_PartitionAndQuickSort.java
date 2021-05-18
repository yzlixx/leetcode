import java.util.Arrays;

/**
 * @author lixiaoxuan
 * @description: 快速排序
 * @date 2021/5/17 14:26
 */
public class Code_PartitionAndQuickSort {

    /*
     * 1.选定基准值(选择尾部作为基准值)
     * 指针point从数组第一个元素开始
     * 小于基准值区域的右边界,初始值LessRangeR = -1,大于基准值得左边界,初始值MoreRangeL = arr.length-1;
     * case1:指针的元素小于基准值，与小于基准值区域的右边界LessRangeR+1的数据交换，小于基准值区域的右边界LessRangeR++，指针point++
     * case2:指针的元素等于基准值，指针point++
     * case3:指针的元素大于基准值，与大于基准值区域的左边界MoreRangeL-1的数据交换，大于基准区域的左边界MoreRangeL--
     * 交换大于基准值区域的头元素和尾元素， MoreRangeL与arr.length-1位置交换
     */

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static int[] partition(int[] arr, int lessRangeR, int moreRangeL) {
        int left = lessRangeR - 1;
        int right = moreRangeL;
        int point = lessRangeR;
        //基准值
        int partition = arr[moreRangeL];
        while (point < right) {
            if (arr[point] > partition) {
                swap(arr, point, --right);
            } else if (arr[point] < partition) {
                swap(arr, point++, ++left);
            } else {
                point++;
            }
        }
        swap(arr, right, moreRangeL);
        return new int[]{left, right};
    }

    public static void process(int[] arr, int lessRangeR, int moreRangeL) {
        if (lessRangeR > moreRangeL) {
            return;
        }
        int[] partition = partition(arr, lessRangeR, moreRangeL);
        process(arr, lessRangeR, partition[0]);
        process(arr, partition[1] + 1, moreRangeL);

    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("开始测试");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            Arrays.sort(arr2);
            quickSort(arr3);
            if (!isEqual(arr2, arr3)) {
                System.out.println("error");
                succeed = false;
                break;
            }
        }

        System.out.println();
        System.out.println("测试结束");
        System.out.println(succeed ? "Nice!" : "Oops!");


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
}
