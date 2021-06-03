/**
 * @author lixiaoxuan
 * @description: 翻转二叉树
 * @date 2021/5/19 13:14
 */
public class LeetCode226 {

    public  class TreeNode {
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

    public TreeNode invertTree(TreeNode root) {
        return process(root);
    }

    public TreeNode process(TreeNode node){
       if(node == null){
           return null;
       }
       TreeNode leftNode = process(node.left);
       TreeNode rightNode = process(node.right);
       node.left = rightNode;
       node.right = leftNode;
       return node;
    }
}
