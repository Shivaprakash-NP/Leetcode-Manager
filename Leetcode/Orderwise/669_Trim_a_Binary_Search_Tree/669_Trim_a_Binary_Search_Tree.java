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
    private TreeNode dfs(TreeNode node, int l, int r) {
        if(node == null) return null;

        if(node.val < l) return dfs(node.right, l, r);
        if(node.val > r) return dfs(node.left, l, r);

        node.left = dfs(node.left, l, r);
        node.right = dfs(node.right, l, r);

        return node;
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        return dfs(root , low , high);
    }
}