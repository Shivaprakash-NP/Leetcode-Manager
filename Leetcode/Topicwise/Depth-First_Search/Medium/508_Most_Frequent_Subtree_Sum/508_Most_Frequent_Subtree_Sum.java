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
    Map<Integer , Integer> map = new HashMap<>();

    private int dfs(TreeNode node) {
        if(node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        int sub_sum = l+node.val+r;
        map.put(sub_sum , map.getOrDefault(sub_sum , 0) + 1);

        return sub_sum;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        int val = dfs(root);
        int max = 1;
        ArrayList<Integer> ans = new ArrayList<>();

        for(int v : map.keySet()) {
            int f = map.get(v);
            if(f > max) {
                ans.clear();
                max = f;
                ans.add(v);
            } else if(f == max) ans.add(v);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}