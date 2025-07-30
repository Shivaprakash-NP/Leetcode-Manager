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
    private TreeNode helper(TreeNode node) {
        if(node.left == null) return node.right;
        if(node.right == null) return node.left;
        TreeNode tem = node.left;
        while(tem.right != null) tem = tem.right;
        tem.right = node.right;
        return node.left; 
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(root.val == key) return helper(root);
        TreeNode node = root;
        while(node != null) {
            if(node.val > key) {
                if(node.left != null && node.left.val == key) node.left = helper(node.left);
                else node = node.left;
            } else {
                if(node.right != null && node.right.val == key) node.right = helper(node.right);
                else node = node.right;
            }

        }
        return root;
    }
}