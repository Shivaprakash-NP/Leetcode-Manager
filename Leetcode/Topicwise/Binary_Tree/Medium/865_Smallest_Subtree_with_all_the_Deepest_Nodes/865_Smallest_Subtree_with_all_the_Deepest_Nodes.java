/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int d = 0;
    private int max(TreeNode node) {
        if(node == null) return 0;

        int l = max(node.left);
        int r = max(node.right);

        return 1+Math.max(l , r);
    }

    private TreeNode dfs(TreeNode node, int cd) {
        if(node == null) return null;

        if(cd == d) return node;

        TreeNode l = dfs(node.left, cd+1);
        TreeNode r = dfs(node.right, cd+1);

        if(l!= null && null != r) return node;

        return (null == l)?r:l;
    }
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        d = max(root);
        return dfs(root, 1);
    }
}