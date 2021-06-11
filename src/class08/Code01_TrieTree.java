package class08;

import java.util.HashMap;

/**
 * @author lixiaoxuan
 * @description: 手写前缀树
 * @date 2021/6/11 11:27
 */
public class Code01_TrieTree {

    public static class Node1 {
        int pass = 0;
        int end = 0;
        Node1[] nexts = new Node1[26];
    }

    public static class Trie1 {

        private Node1 root;

        public Trie1() {
            this.root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            //root.pass+1
            char[] chars = word.toCharArray();
            Node1 curNode = root;
            curNode.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (curNode.nexts[index] == null) {
                    curNode.nexts[index] = new Node1();
                }
                curNode = curNode.nexts[index];
                curNode.pass++;
            }
            curNode.end++;
        }

        public void delete(String word) {
            //先判断是否存在
            if (search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 curNode = root;
            curNode.pass--;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                curNode = curNode.nexts[index];
                curNode.pass--;
            }
            curNode.end--;
        }

        //查看在前缀树中出现的次数
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 curNode = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (curNode.nexts[index] == null) {
                    return 0;
                }
                curNode = curNode.nexts[index];
            }

            return curNode.end;
        }

        //查看在前缀树中前缀出现的次数
        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 curNode = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (curNode.nexts[index] == null) {
                    return 0;
                }
                curNode = curNode.nexts[index];
            }
            return curNode.pass;
        }
    }

    public static class Node2 {
        int pass = 0;
        int end = 0;
        HashMap<Integer, Node2> nexts = new HashMap<>();
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            this.root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            //root.pass+1
            char[] chars = word.toCharArray();
            Node2 curNode = root;
            curNode.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (!curNode.nexts.containsKey(index)) {
                    curNode.nexts.put(index, new Node2());
                }
                curNode = curNode.nexts.get(index);
                curNode.pass++;
            }
            curNode.end++;
        }

        public void delete(String word) {
            if (search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 curNode = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                curNode = curNode.nexts.get(index);
                curNode.pass--;
            }
            curNode.end--;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 curNode = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (!curNode.nexts.containsKey(index)) {
                    return 0;
                }
                curNode = curNode.nexts.get(index);
            }
            return curNode.end;
        }

        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 curNode = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (!curNode.nexts.containsKey(index)) {
                    return 0;
                }
                curNode = curNode.nexts.get(index);
            }
            return curNode.pass;
        }

    }


    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops1!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops2!");
                        System.out.println(arr[j]);
                        System.out.println(ans1);
                        System.out.println(ans2);
                        System.out.println(ans3);
                        break;
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
