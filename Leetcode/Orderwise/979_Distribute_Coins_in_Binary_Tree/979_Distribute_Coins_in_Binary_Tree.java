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
    private int dfs(TreeNode node) {
        if(node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        node.val--;
        node.val+=(l+r);
        ans+=Math.abs(node.val);

        return node.val;
    }

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }
}