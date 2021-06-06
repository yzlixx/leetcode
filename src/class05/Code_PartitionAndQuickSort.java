package class05;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lixiaoxuan
 * @description: 快速排序+荷兰国旗问题
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

//    荷兰国旗问题
//
//    给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。


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
        //随机快排，随机基准值
        swap(arr, (int) (Math.random() * (moreRangeL - lessRangeR + 1) + lessRangeR), moreRangeL);
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

    //递归版本
    public static void process(int[] arr, int lessRangeR, int moreRangeL) {
        if (lessRangeR > moreRangeL) {
            return;
        }
        int[] partition = partition(arr, lessRangeR, moreRangeL);
        process(arr, lessRangeR, partition[0]);
        process(arr, partition[1] + 1, moreRangeL);

    }

    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    //迭代版本
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        swap(arr, (int) (Math.random() * length), length - 1);
        int[] partition = partition(arr, 0, length - 1);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, partition[0] - 1));
        stack.push(new Op(partition[1] + 1, length - 1));
        while (!stack.empty()) {
            Op pop = stack.pop();
            //判断边界
            if (pop.l < pop.r) {
                int[] partition1 = partition(arr, pop.l, pop.r);
                stack.push(new Op(pop.l, partition1[0] - 1));
                stack.push(new Op(partition1[1] + 1, pop.r - 1));
            }
        }
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
            quickSort2(arr1);
            if (!isEqual(arr2, arr3) && !isEqual(arr2, arr1)) {
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
