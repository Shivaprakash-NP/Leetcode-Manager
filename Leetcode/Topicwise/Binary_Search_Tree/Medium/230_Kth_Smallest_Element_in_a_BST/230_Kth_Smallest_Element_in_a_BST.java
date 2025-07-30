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
    int c = 0;
    private void dfs(TreeNode node , int k) {
        if(node == null) return;
        dfs(node.left , k);
        c++;
        if(c == k) ans = node.val;
        dfs(node.right , k);
    }
    public int kthSmallest(TreeNode root, int k) {
        dfs(root , k);
        return ans;
    }
}