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
    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;

        int sum = Integer.MIN_VALUE;
        int ans = 0;
        int lev = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            lev++;
            int n = q.size();
            int lsum = 0;

            for(int i = 0 ; i<n ; i++) {
                TreeNode node = q.poll();
                lsum += node.val;

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }

            if(sum < lsum) {
                sum = lsum;
                ans = lev;
            }
        }

        return ans;
    }
}