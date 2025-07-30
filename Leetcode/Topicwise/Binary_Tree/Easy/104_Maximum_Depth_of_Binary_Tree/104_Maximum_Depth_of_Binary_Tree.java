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
    private int dep(TreeNode node) {
        if(node == null) return 0;

        int ldep = dep(node.left);
        int rdep = dep(node.right);

        return 1+Math.max(ldep , rdep);
    }
    public int maxDepth(TreeNode root) {
        return dep(root);
    }
}