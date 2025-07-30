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
    int max = Integer.MIN_VALUE;
    private int sum(TreeNode node) {
        if(node == null) return 0;

        int ls = Math.max(0 , sum(node.left));
        int rs = Math.max(0 , sum(node.right));

        max = Math.max(max , node.val+ls+rs);
        return node.val+ Math.max(ls,rs);
    }
    public int maxPathSum(TreeNode root) {
        sum(root);
        return max;
    }
}