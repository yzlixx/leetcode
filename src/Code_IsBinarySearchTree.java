import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaoxuan
 * @description: TODO
 * @date 2021/5/16 13:29
 */
public class Code_IsBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //判断是否是搜索二叉树
    private List<Integer> list = new ArrayList<>();


    public boolean isBinarySearchTree1(TreeNode root){
        //中序遍历
        if(root == null){
            return true;
        }
        getTreeNode(root);
        if(list.size()<2){
            return true;
        }
        int temp = list.get(0);
        for(int i = 1;i<list.size();i++){
            int value = list.get(i);
            if(value<=temp){
                return false;
            }else{
                temp = value;
            }
        }

        return true;
    }

    // 中序遍历
    public void getTreeNode(TreeNode node){
        if(node.left != null) {
            getTreeNode(node.left);
        }
       list.add(node.val);
        if(node.right != null) {
            getTreeNode(node.right);
        }
    }

    public class Info{
        private int max;
        private int min;
        private boolean isBST;

        public Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public boolean isBinarySearchTree2(TreeNode root){
        //递归
       return process(root).isBST;
    }

    public Info process(TreeNode node){
        if(node == null){
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int max = node.val;
        int min = node.val;

        if(leftInfo != null){
            max = Math.max(leftInfo.max,max);
            min = Math.min(leftInfo.min,min);
        }

        if(rightInfo != null){
            max = Math.max(rightInfo.max,max);
            min = Math.min(rightInfo.min,min);
        }

        boolean isBTS = true;


        if(leftInfo !=null && !leftInfo.isBST){
            isBTS = false;
        }

        if(rightInfo !=null && !rightInfo.isBST){
            isBTS = false;
        }

        boolean leftMaxLessX = leftInfo == null ? true:(leftInfo.max<node.val);
        boolean rightMinLessX = rightInfo == null ? true:(rightInfo.min>node.val);

        if(!(leftMaxLessX && rightMinLessX)){
            isBTS = false;
        }

        return new Info(max,min,isBTS);
    }


}
