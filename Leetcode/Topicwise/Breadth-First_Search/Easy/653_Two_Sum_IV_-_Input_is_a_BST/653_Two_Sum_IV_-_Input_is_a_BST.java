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
    Stack<TreeNode> st_N = new Stack<>();
    Stack<TreeNode> st_P = new Stack<>();

    private int next(){
        TreeNode node = st_N.pop();
        if(node.right != null) pushLeft(node.right);
        return node.val;
    }

    private int pre() {
        TreeNode node = st_P.pop();
        if(node.left != null) pushRight(node.left);
        return node.val;
    }

    private void pushLeft(TreeNode node) {
        while(node != null) {
            st_N.push(node);
            node = node.left;
        }
    }

    private void pushRight(TreeNode node) {
        while(node != null) {
            st_P.push(node);
            node = node.right;
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        pushLeft(root);
        pushRight(root);
        int l = next();
        int r = pre();
        while(l!=r) {
            if(l+r == k) return true;
            if(l+r < k) l = next();
            else r = pre();
        }
        return false;
    }
}