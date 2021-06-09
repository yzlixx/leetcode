package class07;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author lixiaoxuan
 * @description: 最大线段重合问题（用堆的实现）
 * @date 2021/6/7 15:37
 */
public class Code01_CoverMax {

//    给定很多线段，每个线段都有两个数[start, end]，
//    表示线段开始位置和结束位置，左右都是闭区间
//    规定：
//      1）线段的开始和结束位置一定都是整数值
//      2）线段重合区域的长度必须>=1
//    返回线段最多重合区域中，包含了几条线段


    //找出所有线段的开始位置和结束位置，循环遍历每个线段的重合次数
    public static int maxCover1(int[][] lines) {
        int ans = 0;
        //所有线段的最小点
        int min = Integer.MAX_VALUE;
        //所有线段的最大点
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            int[] line = lines[i];
            min = line[0] < min ? line[0] : min;
            max = line[1] > max ? line[1] : max;
        }

        //判断通过开始位置+0.5的线段数
        for (double i = min + 0.5; i < max; i = i + 1) {
            int result = 0;
            for (int j = 0; j < lines.length; j++) {
                int start = lines[j][0];
                int end = lines[j][1];
                if (start < i && i < end) {
                    result++;
                }
            }
            ans = ans > result ? ans : result;
        }
        return ans;
    }

    public static int maxCover2(int[][] m) {
        int ans = 0;
        // 现将lines按start大小排序
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, (a, b) -> {
            return a.start - b.start;
        });
        //小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            int start = line.start;
            //堆不为空,删除堆中小于start大小的数据
            while (!heap.isEmpty()) {
                if (heap.peek() > start) {
                    break;
                }
                heap.poll();
            }
            heap.add(lines[i].end);
            ans = heap.size() > ans ? heap.size() : ans;
        }
        return ans;
    }

    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>((a, b) -> {
            return a.start - b.start;
        });
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                for (int j = 0; j < lines.length; j++) {
                    System.out.println(lines[j][0] + "," + lines[j][1]);
                }
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }
}
