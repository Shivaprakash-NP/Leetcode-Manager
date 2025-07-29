/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        if(root == null) return ans.toString();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int n = q.size();
            for(int j = 0 ; j < n ; j++) {
                TreeNode node = q.poll();
                ans.append((node==null)?"#":node.val);
                ans.append(",");
                if(node != null) {
                    q.offer(node.left);
                    q.offer(node.right);
                }
            }
        }
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] s = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while(!q.isEmpty() && i < s.length) {
            int n = q.size();
            for(int j = 0 ; j < n ; j++) {
                TreeNode node = q.poll();
                if(i<s.length && !s[i].equals("#")) {
                    TreeNode lnode = new TreeNode(Integer.parseInt(s[i]));
                    node.left = lnode;
                    q.offer(lnode);
                }
                i++;
                if(i<s.length && !s[i].equals("#")) {
                    TreeNode rnode = new TreeNode(Integer.parseInt(s[i]));
                    node.right = rnode;
                    q.offer(rnode);
                }
                i++;
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));