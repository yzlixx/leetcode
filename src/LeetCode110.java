import java.util.List;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/5/13 15:38
 */
public class LeetCode110 {

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     * 示例 2：
     *
     *
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     * 示例 3：
     *
     * 输入：root = []
     * 输出：true
     *
     */


    public static class TreeNode {
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

    /**
     * 至底向上
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return depth(root) != -1;
    }

    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = depth(node.left);
        if(left == -1) {
            return -1;
        }
        int right = depth(node.right);
        if(right == -1){
            return -1;
        }
        return Math.abs(left-right)>1?-1:Math.max(left,right)+1;
    }

}
