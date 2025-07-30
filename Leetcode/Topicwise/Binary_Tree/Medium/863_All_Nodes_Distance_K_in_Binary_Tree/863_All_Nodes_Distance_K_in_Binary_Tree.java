/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode , TreeNode> map = new HashMap<>();
        ArrayList<TreeNode> vis = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        map.put(root , null);
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            for(int i = 0 ; i<n ; i++) {
                TreeNode node = q.poll();
                if(node.left != null) {
                    q.offer(node.left);
                    map.put(node.left , node);
                }
                if(node.right != null) {
                    q.offer(node.right);
                    map.put(node.right , node);
                }
            }
        }
        q.clear();
        int dis = 0;
        vis.add(target);
        q.offer(target);
        while(dis<=k) {
            if(dis==k) {
                while(!q.isEmpty()) {
                    ans.add(q.poll().val);
                }
                break;
            }
            int n = q.size();
            for(int i = 0 ; i<n ; i++) {
                TreeNode node = q.poll();
                if(node.left != null && !vis.contains(node.left)) {
                    q.offer(node.left);
                    vis.add(node.left);
                }
                if(node.right != null && !vis.contains(node.right)) {
                    q.offer(node.right);
                    vis.add(node.right);
                }
                if(map.get(node) != null && !vis.contains(map.get(node))) {
                    q.offer(map.get(node));
                    vis.add(map.get(node));
                }
            }
            dis++;
        }
        return ans;
    }
}