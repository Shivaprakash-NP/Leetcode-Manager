## LeetCode: Check Completeness of a Binary Tree - Solution Explanation

**1. Problem Understanding:**

The problem asks us to determine if a given binary tree is a complete binary tree. A complete binary tree is a binary tree in which all levels are completely filled except possibly the last level, and the last level has all keys as left as possible.  In simpler terms, we need to check if there are any gaps in the tree's level-order traversal before encountering a `null` node.

**2. Approach / Intuition:**

The solution uses a Level Order Traversal (Breadth-First Search) approach.  It iterates through the tree level by level using a queue. The core logic relies on a boolean flag `seen`.  Once a `null` node is encountered (`seen` becomes `true`), it means we've started the possibly incomplete last level. After this, if any non-null node is found, it indicates an incomplete tree because nodes should only appear before nulls on the last level.

This approach is chosen because Level Order Traversal naturally processes nodes level by level, making it efficient to detect violations of the complete binary tree property.  Other traversal methods (pre-order, in-order, post-order) wouldn't naturally expose the level-by-level structure needed for this problem.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `Queue<TreeNode>`: A queue is used to implement the Breadth-First Search. It stores the tree nodes to be processed level by level. `LinkedList` is used as it provides efficient `offer` and `poll` operations.
* **Algorithms:**
    * **Breadth-First Search (BFS):**  The algorithm systematically explores the tree level by level.

**4. Code Walkthrough:**

* `if(root == null) return true;`:  An empty tree is considered complete.
* `Queue<TreeNode> q = new LinkedList<>(); q.offer(root); boolean seen = false;`: A queue is initialized and the root node is added.  `seen` is a boolean flag initialized to `false`, indicating that no `null` node has been encountered yet.
* `while(!q.isEmpty())`: The loop continues as long as there are nodes in the queue.
* `TreeNode node = q.poll();`: A node is removed from the queue for processing.
* `if(node == null) seen = true;`: If the node is `null`, it means we've possibly reached the last level, so `seen` is set to `true`.
* `else { if(seen) return false; q.offer(node.left); q.offer(node.right); }`: If the node is not `null` and `seen` is `true` (meaning a `null` has been previously encountered), it means we found a node after a `null`, violating the complete binary tree property, so we return `false`. Otherwise, the left and right children of the node are added to the queue for processing in the next iteration.
* `return true;`: If the loop completes without finding any violation, it means the tree is complete, and `true` is returned.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Each node is visited and processed exactly once.
* **Space Complexity:** O(W), where W is the maximum width of the tree (the maximum number of nodes at any level). In the worst case (a complete binary tree), W can be O(N) in which case the space complexity would be O(N).  In a balanced tree W would be proportional to logN.  This is due to the queue used in the BFS, which stores nodes at a given level.

In summary, this solution efficiently determines the completeness of a binary tree using a level-order traversal and a simple boolean flag to track the presence of null nodes, resulting in a linear time and space complexity solution.
