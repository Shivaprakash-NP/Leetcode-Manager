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
    static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode n, int i) {
            node = n;
            index = i;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Queue<Pair> q = new LinkedList<>();
        int max = 0;
        q.offer(new Pair(root , 0));
        while(!q.isEmpty()) {
            int n = q.size();
            int minind = q.peek().index;
            int l = 0, f = 0;
            for(int i = 0 ; i<n ; i++) {
                Pair p = q.poll();
                int curind = p.index-minind;
                TreeNode node = p.node;

                if(i == 0) f = curind;
                if(i == n-1) l = curind;

                if(node.left != null) q.offer(new Pair(node.left , 2*curind+1));
                if(node.right != null) q.offer(new Pair(node.right , 2*curind+2));
            }
            max = Math.max(max , l-f+1);
        }
        return max;
    }
}