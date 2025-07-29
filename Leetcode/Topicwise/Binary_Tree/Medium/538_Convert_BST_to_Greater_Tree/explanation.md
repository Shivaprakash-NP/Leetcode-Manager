## LeetCode: Convert BST to Greater Tree - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to modify a given Binary Search Tree (BST) in-place.  The modification involves converting it into a Greater Tree. A Greater Tree is a tree where each node's value is the sum of all nodes' values greater than or equal to its own value in the original BST.


**2. Approach / Intuition:**

The most efficient way to solve this problem is using a **Reverse In-order Traversal** (also known as a right-root-left traversal).  A standard in-order traversal (left-root-right) would visit nodes in ascending order.  However, we need to accumulate the sum of nodes *greater than or equal to* the current node's value.  Therefore, we traverse the tree in reverse in-order (right-root-left).

This approach is chosen because:

* **Efficiency:**  It avoids the need for extra space to store the sums or to build a new tree. It modifies the original tree in-place.
* **Correctness:**  By processing the right subtree first, we ensure that the sum correctly accumulates the values of all greater nodes before updating the current node's value.

**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure is a `TreeNode`, representing the nodes of the Binary Search Tree.  No other significant data structures are used; it's an in-place modification.
* **Algorithms:** The core algorithm is a Depth-First Search (DFS) implemented using recursion. Specifically, it's a modified reverse in-order traversal.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a node in a binary tree, containing the node's value (`val`) and pointers to its left and right children (`left`, `right`).

* **`sum` variable:** This instance variable is crucial. It accumulates the sum of nodes' values encountered so far during the traversal. It is initialized to 0.

* **`dfs(TreeNode node)` function:** This is the recursive helper function performing the reverse in-order traversal.
    * **Base Case:** If `node` is `null` (we've reached the end of a branch), the function simply returns.
    * **Recursive Step:**
        * `dfs(node.right);`: Recursively calls `dfs` on the right subtree first. This ensures that all nodes with greater values are processed before the current node.
        * `sum += node.val;`: Adds the current node's value to the running `sum`.
        * `node.val = sum;`: Updates the current node's value with the accumulated `sum`.  This is the core of the conversion.
        * `dfs(node.left);`: Recursively calls `dfs` on the left subtree.


* **`convertBST(TreeNode root)` function:** This is the main function. It simply initializes the `sum` to 0 (although this is not strictly necessary because it's an instance variable already initialized to 0) and calls `dfs` starting from the root node. Then, it returns the modified `root`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the BST.  This is because the DFS algorithm visits each node exactly once.

* **Space Complexity:** O(H), where H is the height of the BST. This space is used for the call stack during the recursive calls of the `dfs` function. In the worst-case scenario (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  In the best-case scenario (a balanced tree), H is log₂(N), resulting in O(log₂(N)) space complexity.  Therefore, the space complexity is generally considered O(H) or in the worst case O(N).  The space complexity is better than a solution that creates a new tree which would require O(N) space regardless of the tree's shape.

This solution is highly efficient and elegant for solving the "Convert BST to Greater Tree" problem on LeetCode.  It leverages the properties of BSTs and the power of recursion to achieve a linear time complexity solution with optimal space usage.
