## LeetCode: Binary Tree Maximum Path Sum - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the maximum sum of a path in a binary tree.  This path doesn't necessarily have to go from the root to a leaf; it can start and end at any two nodes in the tree.  The path must connect nodes through parent-child relationships.

**2. Approach / Intuition:**

The solution employs a recursive post-order traversal approach.  The core idea is that for each node, we calculate the maximum path sum that *includes* that node. This path may go through its left and/or right subtree. We then compare this maximum path sum with the global maximum found so far and update it accordingly.

Why this approach?  A brute-force approach trying all possible paths would be exponentially complex.  This recursive approach efficiently explores all possible paths without redundant calculations.  The `Math.max(0, sum(...))` line is crucial: it handles negative sub-sums, ensuring that including a negative subtree doesn't inadvertently reduce the overall path sum.

**3. Data Structures and Algorithms:**

* **Data Structure:** The problem utilizes a binary tree represented using the `TreeNode` class.
* **Algorithm:**  The solution uses Depth-First Search (DFS) via recursion to traverse the tree in a post-order fashion.  The `Math.max` function is used extensively for comparison and selection of maximum values.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a node in a binary tree.  It's not part of the algorithm's core logic but essential for representing the tree structure.

* **`max` Variable:** A global variable to store the maximum path sum encountered so far. It's initialized to the smallest possible integer value.

* **`sum(TreeNode node)` function:** This is the recursive helper function.
    * **Base Case:** If `node` is `null`, it returns 0 (no contribution to the sum).
    * **Recursive Calls:** It recursively calculates the maximum path sum from the left (`ls`) and right (`rs`) subtrees.  Crucially, `Math.max(0, ...)` ensures that only non-negative sums are considered; negative sub-sums are treated as 0 to avoid decreasing the overall sum.
    * **Max Path Update:**  `max = Math.max(max, node.val + ls + rs);` updates the global maximum with the current node's value plus the maximum path sums from its left and right subtrees.  This line considers paths that pass *through* the current node.
    * **Return Value:** The function returns `node.val + Math.max(ls, rs)`; this represents the maximum path sum from the current node *downwards*, either through its left or right subtree.  This is important for the calculation at the parent node in the next recursive call.

* **`maxPathSum(TreeNode root)` function:**
    * It initializes the process by calling the `sum()` function on the root node.
    * It returns the value of `max`, which holds the final maximum path sum after the entire tree has been traversed.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Each node is visited exactly once during the recursive traversal.

* **Space Complexity:** O(H), where H is the height of the tree. This space is used for the recursive call stack. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  In the best case (a balanced tree), H is log₂(N), resulting in O(log N) space complexity.


**In summary:** This solution efficiently finds the maximum path sum in a binary tree using a clever recursive approach that avoids redundant calculations.  The use of `Math.max(0, ...)` is crucial for correctly handling negative values in subtrees. The time complexity is linear, and the space complexity is proportional to the height of the tree.
