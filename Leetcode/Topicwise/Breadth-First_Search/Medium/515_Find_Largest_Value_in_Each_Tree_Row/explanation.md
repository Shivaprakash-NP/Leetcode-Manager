## LeetCode: Find Largest Value in Each Tree Row - Solution Explained

**1. Problem Understanding:**

The problem asks us to traverse a binary tree level by level and find the largest value present in each level. The output should be a list containing these largest values, where the i-th element represents the largest value in the i-th level of the tree.


**2. Approach / Intuition:**

The most efficient approach for this problem is using Breadth-First Search (BFS) with a queue.  BFS naturally processes nodes level by level.  We iterate through each level, keeping track of the maximum value encountered at that level. This maximum value is then added to the result list.  This approach is chosen because it directly addresses the level-order traversal requirement of the problem.  Other approaches like Depth-First Search (DFS) would require more complex logic to track the level information.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`: Represents a node in the binary tree (provided in the problem statement).
    * `Queue<TreeNode>`:  A queue is used to implement the BFS algorithm. It stores nodes waiting to be processed level by level.
    * `List<Integer>`:  A list stores the maximum values found in each row of the tree.

* **Algorithms:**
    * **Breadth-First Search (BFS):** A graph traversal algorithm that explores the graph level by level.


**4. Code Walkthrough:**

```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>(); // Initialize the result list
        if(null == root) return ans; // Handle empty tree case

        Queue<TreeNode> q = new LinkedList<>(); // Initialize a queue for BFS
        q.offer(root); // Add the root node to the queue

        while(!q.isEmpty()) { // Continue until the queue is empty
            int n = q.size(); // Get the number of nodes in the current level
            int max = Integer.MIN_VALUE; // Initialize max to the smallest possible integer value
            for(int i = 0 ; i < n ; i++) { // Iterate through all nodes in the current level
                TreeNode node = q.poll(); // Remove the next node from the queue
                max = Math.max(max , node.val); // Update max if a larger value is found

                if(node.right != null) q.offer(node.right); // Add right child to queue
                if(node.left != null) q.offer(node.left); // Add left child to queue
            }
            ans.add(max); // Add the maximum value of the current level to the result list
        }

        return ans; // Return the list of maximum values
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  Each node is visited and processed exactly once.

* **Space Complexity:** O(W), where W is the maximum width (maximum number of nodes) at any level of the binary tree. In the worst-case scenario (a complete binary tree), W could be proportional to N, leading to O(N) space complexity. The space is primarily used by the queue to store nodes in each level.  In a skewed tree, W would be much smaller than N, resulting in a better space complexity.

Therefore, the time complexity is linear with the number of nodes and the space complexity is proportional to the maximum width of the tree (which can be up to the number of nodes in the worst case).
