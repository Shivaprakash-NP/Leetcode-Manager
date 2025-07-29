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
    int ans = 0;
    private void dfs(TreeNode node) {
        if(node == null) return;

        if(node.left != null && node.left.left == null && node.left.right == null) ans += node.left.val;

        dfs(node.left);
        dfs(node.right);
    }
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root);
        return ans;
    }
}