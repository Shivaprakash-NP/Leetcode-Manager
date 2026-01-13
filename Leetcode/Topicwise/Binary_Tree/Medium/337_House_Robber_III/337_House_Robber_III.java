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
    Map<TreeNode, Integer> rob;
    Map<TreeNode, Integer> norob;
    private int dfs(TreeNode node, boolean pre) {
        if(node == null) return 0;

        if(pre && rob.containsKey(node)) return rob.get(node);
        if(!pre && norob.containsKey(node)) return norob.get(node);

        int pick = 0;
        int nopick = dfs(node.left, false) + dfs(node.right, false);

        if(pre) rob.put(node, nopick);
        if(!pre) pick = node.val + dfs(node.left, true) + dfs(node.right, true);
        if(!pre) norob.put(node, Math.max(nopick, pick));

        return Math.max(pick, nopick);
    }   

    public int rob(TreeNode root) {
        rob = new HashMap<>();
        norob = new HashMap<>();

        return dfs(root, false);
    }
}