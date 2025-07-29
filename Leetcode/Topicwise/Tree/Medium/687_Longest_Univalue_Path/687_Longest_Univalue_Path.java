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
    int max = 0;
    private int dfs(TreeNode node) {
        if(node == null) return 1001;

        int lp = 0;
        int rp = 0;

        int l = dfs(node.left);
        int r = dfs(node.right);

        if(node.left != null && node.left.val == node.val) lp = l+1;
        if(node.right != null && node.right.val == node.val) rp = r+1;

        max = Math.max(max, rp+lp);

        return Math.max(lp, rp);
    }
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }
}