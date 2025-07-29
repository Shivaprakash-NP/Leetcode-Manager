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
    private int dfs(TreeNode node) {
        if(node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);

        if(0 == l || 0 == r) return l+r+1;
        
        return Math.min(l , r)+1;
    }
    public int minDepth(TreeNode root) {
        return dfs(root);
    }
}