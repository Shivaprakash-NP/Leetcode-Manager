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
    private boolean dfs(TreeNode n1 , TreeNode n2) {
        if(n1 == null && n2 == null) return true;
        else if(n1 == null || n2 == null || n1.val != n2.val) return false;

        return (dfs(n1.left, n2.left) && dfs(n1.right, n2.right)) || (dfs(n1.left, n2.right) && dfs(n1.right, n2.left)) ;

    }
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return dfs(root1 , root2);
    }
}