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
    private void dfs(TreeNode node, int d, int val) {
        if(node == null) return;

        if(d == 1) {
            TreeNode n1 = new TreeNode(val);
            TreeNode n2 = new TreeNode(val);

            n1.left = node.left;
            n2.right = node.right;

            node.left = n1;
            node.right = n2;

            return;
        }

        dfs(node.left , d-1 , val);
        dfs(node.right , d-1 , val);
    }
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(1 == depth) {
            TreeNode nr = new TreeNode(val);
            nr.left = root;
            return nr;
        }
        
        dfs(root , depth-1 , val);
        return root;
    }
}