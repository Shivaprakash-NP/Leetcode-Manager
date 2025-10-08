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
    private TreeNode build(int[] arr, int l, int r) {
        if(l > r) return null;
        if(l == r) return new TreeNode(arr[l]);

        int max = -1;
        int maxi = -1;

        for(int i = l; i<=r; i++) {
            if(arr[i] > max) {
                max = arr[i];
                maxi = i;
            }
        }

        if(maxi == -1) return null;

        TreeNode node = new TreeNode(arr[maxi]);
        node.left = build(arr, l, maxi-1);
        node.right = build(arr, maxi+1, r);

        return node;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length-1);
    }
}