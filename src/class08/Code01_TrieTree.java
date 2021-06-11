package class08;

/**
 * @author lixiaoxuan
 * @description: 手写前缀树
 * @date 2021/6/11 11:27
 */
public class Code01_TrieTree {

    public static class Trie{
        int pass = 0;
        int end = 0;
        Trie[] nexts = new Trie[26];
    }
}
