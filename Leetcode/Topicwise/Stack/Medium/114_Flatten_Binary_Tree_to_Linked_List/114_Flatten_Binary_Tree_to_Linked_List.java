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
    TreeNode pre = null;

    private void dfs(TreeNode node) {
        if(node == null) return;

        dfs(node.right);
        dfs(node.left);

        node.right = pre;
        node.left = null;

        pre = node;
    }
    
    public void flatten(TreeNode root) {
        if(root == null) return;
        dfs(root);
    }
}