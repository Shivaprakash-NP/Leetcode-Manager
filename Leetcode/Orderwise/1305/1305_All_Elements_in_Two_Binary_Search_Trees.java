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
    private void dfs(TreeNode node, List<Integer> store) {
        if(node == null) return;

        dfs(node.left, store);
        store.add(node.val);
        dfs(node.right, store);
    }
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> store1 = new ArrayList<>();
        List<Integer> store2 = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        dfs(root1, store1);
        dfs(root2, store2);
        int l = 0;
        int r = 0;
        while(l<store1.size() && r<store2.size()) {
            if(store1.get(l) < store2.get(r)) {
                ans.add(store1.get(l));
                l++;
            } else {
                ans.add(store2.get(r));
                r++;
            }
        }
        while(l<store1.size()) {
            ans.add(store1.get(l));
            l++;
        }
        while(r<store2.size()) {
            ans.add(store2.get(r));
            r++;
        }
        return ans;
    }
}