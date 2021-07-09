package class14;

import java.util.PriorityQueue;

public class Code02_LessMoneySplitGold {

//    一块金条切成两半，是需要花费和长度数值一样的铜板的。
//    比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
//
//    例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
//
//    如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
//    但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
//            输入一个数组，返回分割的最小代价。

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println(lessMoney1(arr));
                System.out.println(lessMoney2(arr));
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    // 暴力
    private static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int lessMoney = 0;
        return process(arr, lessMoney);
    }

    private static int process(int[] arr, int lessMoney) {
        if (arr.length == 1) {
            return lessMoney;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyArray(arr, i, j), lessMoney + arr[i] + arr[j]));
            }
        }
        return ans;

    }

    public static int[] copyArray(int[] arr, int index1, int index2) {
        int[] help = new int[arr.length - 1];
        int helpIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index1 && i != index2) {
                help[helpIndex++] = arr[i];
            }
        }
        help[helpIndex] = arr[index1] + arr[index2];
        return help;
    }

    // 哈夫曼树，最优路径树
    private static int lessMoney2(int[] arr) {
        int lessMoney = 0;
        // 小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int cur = 0;
        while (queue.size() > 1) {
            cur = queue.poll() + queue.poll();
            lessMoney += cur;
            queue.add(cur);
        }
        return lessMoney;
    }

}
