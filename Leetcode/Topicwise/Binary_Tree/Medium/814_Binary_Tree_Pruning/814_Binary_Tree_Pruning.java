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
    private boolean dfs(TreeNode node) {
        if(node == null) return false;

        boolean l = dfs(node.left);
        boolean r = dfs(node.right);

        if(!l) node.left = null;
        if(!r) node.right = null;

        return l || r || node.val == 1;
    }
    public TreeNode pruneTree(TreeNode root) {
        if(!dfs(root)) return null;
        return root;
    }
}