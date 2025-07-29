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
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode mid = null;
    TreeNode last = null;

    private void dfs(TreeNode node) {
        if(node == null) return;
        dfs(node.left);
        if(pre != null && node.val < pre.val) {
            if(first == null) {
                first = pre;
                mid = node;
            } else last = node;
        }
        pre = node;
        dfs(node.right);
    }
    
    public void recoverTree(TreeNode root) {
        dfs(root);
        int tem = first.val;
        if(last == null) {
            first.val = mid.val;
            mid.val = tem;
        } else {
            first.val = last.val;
            last.val = tem;
        }
    }
}