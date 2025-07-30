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
    Comparator<int[]> comp = (a, b) -> {
        if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
        return Integer.compare(a[1], b[1]);
    };

    TreeMap<Integer , ArrayList<int[]>> map = new TreeMap<>();
    private void assign(TreeNode node , int i , int j) {
        if(node == null) return ;
        map.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{j, node.val});
        assign(node.left , i-1 , j+1);
        assign(node.right , i+1 , j+1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        assign(root , 0 , 0);
        for(int v : map.keySet()) {
            ArrayList<Integer> sub = new ArrayList<>();
            Collections.sort(map.get(v), comp);
            for(int[] vv : map.get(v)) sub.add(vv[1]);
            ans.add(sub);
        }
        return ans;
    }
}