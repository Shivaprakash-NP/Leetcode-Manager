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
    int ans = 0;

    private void dfs(TreeNode node , long sum , long t , Map<Long , Integer> psum) {
        if(node == null) return;

        sum += node.val;
        ans += psum.getOrDefault(sum-t , 0);

        psum.put(sum , psum.getOrDefault(sum , 0)+1);

        dfs(node.left , sum , t , psum);
        dfs(node.right , sum , t , psum);

        psum.put(sum , psum.get(sum)-1);
    }
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> psum = new HashMap<>();
        psum.put(0L, 1); 
        dfs(root , 0 , (long)targetSum , psum);
        return ans;
    }
}