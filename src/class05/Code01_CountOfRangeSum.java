package class05;

public class Code01_CountOfRangeSum {

    // https://leetcode.com/problems/count-of-range-sum/

//    给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
//
//    区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

    /*
       [1,3,5,-3,-2,2] =>范围[-1,4]

       [1],[1,3],[1,3,5,-3,-2],...
     */


    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //求累加和数组
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(int[] sum, int left, int right, int lower, int upper) {
        if (left == right) {
            if (sum[left] >= lower && sum[left] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = left + ((right - left) >> 1);
        int leftCount = process(sum, left, mid, lower, upper);
        int rightCount = process(sum, mid + 1, right, lower, upper);
        int mergeCount = merge(sum, left, mid, right, lower, upper);
        return leftCount + rightCount + mergeCount;
    }

    private static int merge(int[] sum, int left, int mid, int right, int lower, int upper) {

        int count = 0;
        int windowL = left;
        int windowR = left;

        for (int i = mid + 1; i <= right; i++) {
            int low = sum[i] - upper;
            int up = sum[i] - lower;
            while (windowL <= mid && sum[windowL] < low) {
                windowL++;
            }
            while (windowR <= mid && sum[windowR] <= up) {
                windowR++;
            }

            count += windowR - windowL;

        }


        int[] help = new int[right - left + 1];
        int leftPoint = left;
        int rightPoint = mid + 1;
        int index = 0;
        while (leftPoint <= mid && rightPoint <= right) {
            if (sum[leftPoint] > sum[rightPoint]) {
                help[index++] = sum[rightPoint++];
            } else {
                help[index++] = sum[leftPoint++];
            }
        }

        while (leftPoint <= mid) {
            help[index++] = sum[leftPoint++];
        }

        while (rightPoint <= right) {
            help[index++] = sum[rightPoint++];
        }

        for (int i = 0; i < help.length; i++) {
            sum[left + i] = help[i];
        }

        return count;
    }
}

