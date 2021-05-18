import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/5/14 14:57
 */
public class LeetCode113 {

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     * 示例 2：
     *
     *
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [1,2], targetSum = 0
     * 输出：[]
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


    public  List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        ArrayList<Integer> list = new ArrayList<>();


        path(root,targetSum,list,ans);

        return ans;
    }


    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }

        ArrayList<Integer> path = new ArrayList<>();
        process(root,targetSum,0,path,ans);
        return ans;
    }



    public void process(TreeNode node,int targetSum,int preSum,ArrayList<Integer> path, List<List<Integer>> ans){
        //叶子节点
        if(node.left == null && node.right == null){
            if(preSum+node.val == targetSum){
                path.add(node.val);
                List<Integer> copy = copy(path);
                ans.add(copy);
                path.remove(path.size()-1);
            }
        }

        path.add(node.val);
        preSum += node.val;

        if(node.left != null){
            process(node.left,targetSum,preSum,path,ans);
        }

        if(node.right != null){
            process(node.right,targetSum,preSum,path,ans);
        }

        path.remove(path.size()-1);

    }


    public void path(TreeNode node,int targetSum ,ArrayList<Integer> path, List<List<Integer>> ans){


        int value = node.val;
        if(node.left == null && node.right == null){
            if(targetSum == value){
                path.add(value);
                List<Integer> copy = copy(path);
                ans.add(copy);
                path.remove(path.size()-1);
            }
            return;
        }

        path.add(value);
        if(node.left != null) {
            path(node.left, targetSum - value, path, ans);
        }
        if(node.right != null) {
            path(node.right, targetSum - value, path, ans);
        }

        path.remove(path.size()-1);


    }

    public List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }

}
