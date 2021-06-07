package class06;

import java.util.PriorityQueue;

/**
 * @author lixiaoxuan
 * @description: 堆结构
 * @date 2021/6/7 10:04
 */
public class Code02_Heap {
    /*
     堆是一种完全二叉树的结构，堆需要区分是大根堆和小根堆
     完全二叉树的根节点的左叶节点 （2i+1）,右叶节点（2i+2）
     叶子节点对应得根节点是（i-1）/2
     */

    //自定义大根堆
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heapSize);
            heapSize++;
        }

        //将数值0位置和heapSize位置交换，heapSize-1,0位置与下层叶子节点的最大值交换，继续循环
        public int pop() {
            int result = heap[0];
            swap(heap, 0, --heapSize);
            heapify(0);
            return result;
        }

        //从index位置的叶节点与根节点比大小,大于根节点,交换,继续循环
        private void heapInsert(int index) {

            while (heap[index] > heap[(index - 1)/2]) {
                swap(heap, index, (index - 1)/ 2);
                index = (index - 1)/ 2;
            }
        }

        private void heapify(int index) {
            // 当前位置小于heapSize 并且小于左右叶子节点的值，与最大值交换
            int left = index * 2 + 1;
            while (left < heapSize) { // 如果有左孩子，有没有右孩子，可能有可能没有！
                // 把较大孩子的下标，给largest
                int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
                largest = heap[largest] > heap[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                // index和较大孩子，要互换
                swap(heap, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int[] arr, int index1, int index2) {
            int tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;
        }

    }


    //优先级队列就是一种堆结构
    public static void main(String[] args) {
        //小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> {
            return a - b;
        });
        heap.add(5);
        heap.add(3);
        heap.add(0);
        heap.add(1);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
        //大根堆
        PriorityQueue<Integer> heap2 = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        heap2.add(5);
        heap2.add(3);
        heap2.add(0);
        heap2.add(1);
        while (!heap2.isEmpty()) {
            System.out.println(heap2.poll());
        }


        System.out.println("**********************************");

//        MyMaxHeap my = new MyMaxHeap(5);
//        my.push(100);
//        my.push(20);
//        my.push(34);
//        System.out.println(my.pop());
//        my.push(102);
//        System.out.println(my.pop());
//        my.push(67);
//        my.push(98);
//        System.out.println(my.pop());
//        my.push(61);
//        my.push(21);
//        my.push(92);


        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops1!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops2!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops3!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops4!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    // for test
    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }
}
