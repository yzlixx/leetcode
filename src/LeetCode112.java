/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/5/13 17:06
 */
public class LeetCode112 {
    /**
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 示例 2：
     * <p>
     * <p>
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：root = [1,2], targetSum = 0
     * 输出：false
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

    // [5,4,8,11,null,13,4,7,2,null,null,null,1]
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left,targetSum-root.val) || hasPathSum(root.right,targetSum-root.val);
    }



    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }


        return path(root, targetSum) == 0;
    }

    public int path(TreeNode node, int targetSum) {


        if (node == null) {
            return targetSum;
        }

        int value = node.val;

        if (node.left == null && node.right == null) {
            return targetSum - node.val;
        }

        int left = path(node.left, targetSum - value);
        if(left == 0){
            return  0;
        }

        int right = path(node.right, targetSum - value);
        if(right == 0){
            return 0;
        }

        return targetSum - value != 0 ? targetSum + value : 0;
    }


}
