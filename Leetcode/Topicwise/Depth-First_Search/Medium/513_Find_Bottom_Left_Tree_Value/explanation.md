# LeetCode: Find Bottom Left Tree Value - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the value of the leftmost node at the bottom level of a given binary tree.  The bottom level is defined as the deepest level in the tree.  If the tree is empty, the problem is undefined (the provided code assumes a non-empty tree).

**2. Approach / Intuition:**

The solution uses a Breadth-First Search (BFS) algorithm implemented with a queue. BFS explores the tree level by level.  Because we process nodes level by level, the last node we encounter in each level will be the rightmost node of that level.  Since we process from left to right, the last node processed in the final (bottom) level will be the bottom-left node. We keep track of the value of this node.

This approach is chosen because BFS naturally explores the tree level by level, making it straightforward to identify the bottom level.  Other approaches, like Depth-First Search (DFS), would require extra bookkeeping to track the depth and leftmost node at each depth.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `Queue<TreeNode>`: A queue is used to implement the BFS algorithm.  It stores `TreeNode` objects, which represent nodes in the binary tree.  `LinkedList` is used as the underlying implementation of the queue.
* **Algorithms:**
    * **Breadth-First Search (BFS):**  A graph traversal algorithm that explores the tree level by level.


**4. Code Walkthrough:**

```java
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int val = root.val; // Initialize val with root's value (handles single node tree).
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root); // Add the root node to the queue.

        while(!q.isEmpty()) { 
            int n = q.size(); // Get the number of nodes at the current level.

            for(int i = 0 ; i < n ; i++) { 
                TreeNode node = q.poll(); //Remove and process the node.
                if(node.right != null) q.offer(node.right); // Add right child if it exists.
                if(node.left != null) q.offer(node.left); // Add left child if it exists.
                if(i == n-1) val = node.val; // Update val with the last node of current level.
            }
        }

        return val;
    }
}
```

* **Initialization:** The code initializes `val` with the root's value. This is a crucial step to handle cases where the tree has only one node.
* **BFS Loop:** The `while` loop continues as long as the queue is not empty.
* **Level Processing:** Inside the loop, `n` stores the size of the queue, representing the number of nodes at the current level.
* **Node Processing:** The `for` loop iterates through each node at the current level.  Each node is dequeued using `q.poll()`. Its children are enqueued if they exist.
* **Updating `val`:** The crucial line `if(i == n-1) val = node.val;` updates `val` to the value of the last node processed in the current level (rightmost node).  This ensures that after the last level, `val` holds the bottom-left value.
* **Return Value:**  Finally, the function returns `val`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. This is because each node is visited and processed exactly once during the BFS traversal.
* **Space Complexity:** O(W), where W is the maximum width (maximum number of nodes at any level) of the binary tree. In the worst-case scenario (a complete binary tree), W can be O(N). The space is used to store the queue.  In the best-case scenario (a skewed tree), the space complexity would be O(log N). Therefore, the space complexity is generally considered O(N) in the worst case.

The solution provides an efficient way to solve the problem with a linear time complexity and a space complexity proportional to the maximum width of the tree.  In most scenarios, this approach will be efficient and acceptable.
