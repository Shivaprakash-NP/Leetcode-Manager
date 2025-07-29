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
    private void dfs(TreeNode node) {
        if(node == null) return;
        TreeNode tem = node.left;
        node.left = node.right;
        node.right = tem;

        dfs(node.left);
        dfs(node.right);
    }
    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }
}