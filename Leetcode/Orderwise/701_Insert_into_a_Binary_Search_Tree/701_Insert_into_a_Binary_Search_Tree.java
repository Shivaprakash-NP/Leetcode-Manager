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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode tem = root;
        while(true) {
            if(tem.val < val) {
                if(tem.right == null) {
                    tem.right = new TreeNode(val);
                    break;
                }
                tem = tem.right;
            } else {
                if(tem.left == null) {
                    tem.left = new TreeNode(val);
                    break;
                }
                tem = tem.left;
            }
        }
        return root;
    }
}