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
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> val = new ArrayList<>();

    private void dfs(TreeNode node) {
        if(node == null) return;

        dfs(node.left);
        val.add(node.val);
        dfs(node.right);
    }

    private void bs(int v) {
        int l = 0;
        int r = val.size()-1;
        int c = -1;
        int f = -1;

        while(l<=r) {
            int m = l+(r-l)/2;
            int vv = val.get(m);

            if(vv == v) {
                c = vv;
                f = vv;
                break;
            } else if(vv < v) {
                f = vv;
                l = m+1;
            } else {
                c = vv;
                r = m-1;
            }
        }
        ans.add(Arrays.asList(f,c));
    }
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        dfs(root);

        for(int v : queries) bs(v);

        return ans;
    }
}