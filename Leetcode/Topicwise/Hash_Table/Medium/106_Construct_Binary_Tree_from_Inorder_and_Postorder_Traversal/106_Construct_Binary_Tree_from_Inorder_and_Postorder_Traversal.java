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
    private TreeNode build(int[] po , int ps , int pe , int[] io , int is , int ie , Map<Integer , Integer> map) {
        if(ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(po[pe]);

        int ir = map.get(root.val);
        int nleft = ir-is;

        root.left = build(po, ps, ps + nleft - 1, io, is, ir - 1, map);
        root.right = build(po, ps + nleft, pe - 1, io, ir + 1, ie, map);

        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++) {
            map.put(inorder[i] , i);
        }
        return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
}