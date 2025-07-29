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
    Map<Integer , int[]> dep = new HashMap<>();
    Map<Integer , Integer> val2dep = new HashMap<>();
    Map<Integer , Integer> subhei = new HashMap<>();
    private int dfs(TreeNode node , int d) {
        if(node == null) return 0;

        int l = dfs(node.left , d+1);
        int r = dfs(node.right , d+1);

        val2dep.put(node.val , d);

        int h = Math.max(l , r) +1;

        int[] arr = dep.getOrDefault(d , new int[]{0 , 0});
        if(h > arr[0]) {
            arr[1] = arr[0];
            arr[0] = h;
        } else if(h > arr[1]) {
            arr[1] = h;
        }

        dep.put(d , arr);
        subhei.put(node.val , h);

        return h;
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        int height = dfs(root , -1);
        int n = queries.length;
        int[] ans = new int[n];
        for(int i = 0 ; i < n ; i++) {
            int d = val2dep.get(queries[i]);
            int[] h = dep.get(d);
            if(subhei.get(queries[i]) == h[0]) ans[i] = d+h[1];
            else ans[i] = d+h[0];
        }
        return ans;
    }
}