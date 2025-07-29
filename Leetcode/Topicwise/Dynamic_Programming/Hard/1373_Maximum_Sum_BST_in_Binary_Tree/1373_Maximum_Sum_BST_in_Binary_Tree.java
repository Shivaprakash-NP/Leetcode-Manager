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
class info {
    int min , max , sum;
    boolean is;
    info(boolean is , int min , int max , int sum) {
        this.is = is;
        this.min = min;
        this.max = max;
        this.sum = sum;
    }
}
class Solution {
    int ans = 0;
    private info dfs(TreeNode node) {
        if(node == null) return new info(true , Integer.MAX_VALUE , Integer.MIN_VALUE , 0);

        info l = dfs(node.left);
        info r = dfs(node.right);

        if(l.is && r.is && node.val > l.max && node.val < r.min) {
            int nsum = node.val+l.sum+r.sum;
            ans = Math.max(ans , nsum);
            return new info(true , Math.min(l.min , node.val) , Math.max(r.max , node.val) , nsum);
        }

        return new info(false , 0 , 0 , 0);
    }
    public int maxSumBST(TreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return ans;
    }
}