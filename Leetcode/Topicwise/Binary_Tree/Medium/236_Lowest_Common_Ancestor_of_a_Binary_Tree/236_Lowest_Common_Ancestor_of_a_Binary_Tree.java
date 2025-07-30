/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private TreeNode chk(TreeNode node , TreeNode p , TreeNode q){
        if(node == null || node == p || node == q) return node;

        TreeNode l = chk(node.left , p , q);
        TreeNode r = chk(node.right , p , q);

        if(l == null) return r;
        else if(r == null) return l;
        else return node;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return chk(root , p , q);
    }
}