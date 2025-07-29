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
    int h;
    private int hei(TreeNode node){
        if(node == null) return 0;

        int l = hei(node.left);
        int r = hei(node.right);

        return Math.max(l, r) + 1;
    }

    private TreeNode dfs(TreeNode node, int d) {
        if(node == null) return node;

        if(d == h-1) return node;

        TreeNode l = dfs(node.left, d+1);
        TreeNode r = dfs(node.right, d+1);

        if(l != null && r != null) return node;

        return (l == null)?r:l;
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        h = hei(root);
        return dfs(root, 0);
    }
}