package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author lixiaoxuan
 * @description: 手写加强堆
 * @date 2021/6/7 17:12
 */
public class HeapGreater<T> {

    // 堆结构
    private ArrayList<T> heap = new ArrayList<>();

    // 堆大小
    private int size = 0;

    // 堆比较器
    private final Comparator<? super T> comparator;

    // 对象下标寻址表
    private HashMap<T, Integer> heapMap = new HashMap<>();

    // 构造器
    public HeapGreater(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(T obj) {
        return heapMap.containsKey(obj);
    }

    // 堆添加对象
    public boolean push(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        heap.add(t);
        heapMap.put(t, size);
        heapInsert(size++);
        return true;
    }

    public T peek() {
        return size == 0 ? null : heap.get(0);
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        T result = heap.get(0);
        swap(0, --size);
        heapify(0);
        heapMap.remove(result);
        heap.remove(size);
        return result;
    }

    public boolean remove(T obj) {
        if (!heapMap.containsKey(obj)) {
            return false;
        }
        int index = heapMap.get(obj);
        T t = heap.get(--size);
        if (index != size) {
            swap(index, size);
            resign(t);
        }
        heapMap.remove(obj);
        heap.remove(size);
        return true;
    }


    // 堆节点上升
    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 堆节点下沉
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < size) {
            // 获取左右叶节点的最小值
            int minIndex = left + 1 < size ? (comparator.compare(heap.get(left), heap.get(left + 1)) < 0 ? left : left + 1) : left;
            int min = comparator.compare(heap.get(index), heap.get(minIndex)) < 0 ? index : minIndex;
            if (min == index) {
                break;
            } else {
                swap(index, min);
            }
        }
    }

    public void resign(T obj) {
        heapInsert(heapMap.get(obj));
        heapify(heapMap.get(obj));
    }


    private void swap(int index1, int index2) {
        T t1 = heap.get(index1);
        T t2 = heap.get(index2);
        heap.set(index1, t2);
        heap.set(index2, t1);
        heapMap.put(t1, index2);
        heapMap.put(t2, index1);
    }


    public static void main(String[] args) {
        HeapGreater<Integer> heap = new HeapGreater<>((a, b) -> {
            return a - b;
        });
        heap.push(100);
        heap.push(150);
        heap.push(160);
        System.out.println(heap.peek());
        System.out.println(heap.pop());
        System.out.println("*************************");
        heap.push(4);
        heap.push(127);
        heap.push(190);
        heap.remove(150);
        heap.remove(127);
        System.out.println("*************************");
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }

    }

    public List<T> getAllElements() {
        List<T> ans = new ArrayList<>();
        for (T t : heap) {
            ans.add(t);
        }
        return ans;
    }
}
