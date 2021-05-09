import java.util.HashMap;

public class LeetCode105 {

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
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

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }


        return build(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    public TreeNode build(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
        if (pr <= pl || ir <= il) {
            return null;
        }
        TreeNode node = new TreeNode();
        node.val = preorder[pl];
        int index = map.get(preorder[pl]);
        int length = index - il;
        node.left = build(preorder, inorder, pl + 1, pl + length + 1, il, il + length);
        node.right = build(preorder, inorder, pl + length + 1, pr, index + 1, ir);
        return node;
    }

    public static void main(String[] args) {
        LeetCode105 lee = new LeetCode105();
        TreeNode treeNode = lee.buildTree(new int[]{3, 9, 6, 8, 20, 15, 7}, new int[]{6, 9, 8, 3, 15, 20, 7});
    }

}
