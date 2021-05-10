import java.util.*;

/**
 * @author lixiaoxuan
 * @description: 107. 二叉树的层序遍历 II
 * @date 2021/5/10 15:58
 */
public class LeetCode107 {

    /**
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其自底向上的层序遍历为：
     * <p>
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        listNode(root, 0, list);
        return list;
    }

    public void listNode(TreeNode node, int level,List<List<Integer>> list) {
        if(node == null){
            return;
        }
        if(list.size() == level){
            list.add(0,new ArrayList<>());
        }
        list.get(list.size()-level-1).add(node.val);
        level++;
        listNode(node.left, level, list);
        listNode(node.right, level, list);
    }


}
