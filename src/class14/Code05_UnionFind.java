package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code05_UnionFind {

    // 并查集

//   1)  有若干个样本a、b、c、d…类型假设是V
//   2)  在并查集中一开始认为每个样本都在单独的集合里
//   3)  用户可以在任何时候调用如下两个方法：
//    boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
//    void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
//   4） isSameSet和union方法的代价越低越好


//    1）每个节点都有一条往上指的指针
//    2）节点a往上找到的头节点，叫做a所在集合的代表节点
//    3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
//    4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可

//    并查集的优化
//    1）节点往上找代表点的过程，把沿途的链变成扁平的
//    2）小集合挂在大集合的下面
//    3）如果方法调用很频繁，那么单次调用的代价为O(1)，两个方法都如此


//    并查集的应用
//    解决两大块区域的合并问题
//    常用在图等领域中

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }


    public static class UnionFind<V> {

        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parentMap;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : values) {
                Node node = new Node(v);
                nodes.put(v, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(V a, V b) {
            return findFather(new Node(a)) == findFather(new Node(b));
        }

        private Node<V> findFather(Node node) {
            Stack<Node<V>> path = new Stack<>();
            Node<V> cur = node;
            while (cur != parentMap.get(cur)) {
                path.add(cur);
                cur = parentMap.get(cur);
            }

            // 将路径扁平化
            while (!path.isEmpty()) {
                parentMap.put(path.pop(), cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node big = aSize > bSize ? aHead : bHead;
                Node small = aSize < bSize ? aHead : bHead;
                parentMap.put(small, big);
                sizeMap.put(big, aSize + bSize);
                sizeMap.remove(small);
            }
        }

        public int size() {
            return sizeMap.size();
        }
    }
}
