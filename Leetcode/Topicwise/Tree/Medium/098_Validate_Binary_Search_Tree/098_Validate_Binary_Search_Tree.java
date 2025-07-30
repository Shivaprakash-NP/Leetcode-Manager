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
    private void dfs(TreeNode node , ArrayList<Integer> val) {
        if(node == null) return;
        dfs(node.left , val);
        val.add(node.val);
        dfs(node.right , val);
    }
    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> val = new ArrayList<>();
        dfs(root , val);
        for(int i = 0 ; i < val.size()-1 ; i++) {
            if(val.get(i)>=val.get(i+1)) return false;
        }
        return true;
    }
}