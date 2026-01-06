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
    long sum = 0;
    long max = 0;
    int MOD = (int)1e9 + 7;
    private long sum(TreeNode node) {
        if(node == null) return 0L;

        long left = sum(node.left);
        long right = sum(node.right);

        return left+right+node.val;
    }

    private long dfs(TreeNode node) {
        if(node == null) return 0L;

        long left = dfs(node.left);
        long right = dfs(node.right);

        long subsum = left+right+node.val;

        max = Math.max(max, (sum-subsum)*subsum);

        return subsum;
    }

    public int maxProduct(TreeNode root) {
        sum = sum(root);
        dfs(root);

        return (int)(max%MOD);
    }
}