class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode startNode = null;

        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.val == start) startNode = node;
            if (node.left != null) {
                parentMap.put(node.left, node);
                q.offer(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                q.offer(node.right);
            }
        }

        q.offer(startNode);
        int minutes = -1; 

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                node.val = -1; 

                if (node.left != null && node.left.val != -1)
                    q.offer(node.left);

                if (node.right != null && node.right.val != -1)
                    q.offer(node.right);

                TreeNode parent = parentMap.get(node);
                if (parent != null && parent.val != -1)
                    q.offer(parent);
            }
            minutes++;
        }

        return minutes;
    }
}
