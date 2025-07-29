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
    int i = 0;
    private TreeNode build(int[] A , int ub) {
        if(A.length == i || A[i]>ub) return null;
        TreeNode node = new TreeNode(A[i++]);
        node.left = build(A , node.val);
        node.right = build(A , ub);
        return node;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder , Integer.MAX_VALUE);
    }
}