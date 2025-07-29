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
    private void dfs(TreeNode l, TreeNode r, int lev) {
        if(l == null || r == null) return;

        if(lev%2 == 1) {
            int tem = l.val;
            l.val = r.val;
            r.val = tem;
        }

        dfs(l.left, r.right, lev+1);
        dfs(l.right, r.left, lev+1);
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        if(root.left == null) return root;
        dfs(root.left, root.right, 1);
        return root;
    }
}