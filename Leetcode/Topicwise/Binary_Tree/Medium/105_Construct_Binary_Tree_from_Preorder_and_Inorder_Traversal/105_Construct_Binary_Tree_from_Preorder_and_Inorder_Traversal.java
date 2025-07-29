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

        TreeNode root = new TreeNode(po[ps]);

        int ir = map.get(root.val);
        int nleft = ir-is;

        root.left = build(po , ps+1 , ps+nleft , io , is , ir-1 , map);
        root.right = build(po , ps+nleft+1 , pe , io , ir+1 , ie , map);

        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++) {
            map.put(inorder[i] , i);
        }
        return build(preorder , 0 , preorder.length-1 , inorder ,  0 , inorder.length-1 , map);
    }
}