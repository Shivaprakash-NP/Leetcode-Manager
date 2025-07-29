## LeetCode: Invert Binary Tree - Solution Explained

**1. Problem Understanding:**

The problem asks us to invert a binary tree.  Inverting a binary tree means swapping the left and right children of every node in the tree.  The input is the root of a binary tree, and the output is the root of the inverted binary tree.


**2. Approach / Intuition:**

The most efficient way to solve this problem is using a recursive Depth-First Search (DFS) approach.  We traverse the tree recursively, swapping the left and right children at each node.  This approach is chosen because it elegantly handles the tree's hierarchical structure.  Iterative approaches are possible but are generally less concise and can be harder to understand.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is a binary tree represented using the `TreeNode` class.
* **Algorithm:**  Depth-First Search (DFS) is employed recursively.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This class is a standard definition for a node in a binary tree.  Each node contains an integer value (`val`), a left child (`left`), and a right child (`right`).

* **`dfs(TreeNode node)` function:** This is the recursive helper function that performs the inversion.
    * **`if(node == null) return;`:** This base case handles the scenario where we reach a null node (the end of a branch).  The recursion stops here.
    * **`TreeNode tem = node.left;`:**  A temporary variable `tem` stores a reference to the original left child.
    * **`node.left = node.right;`:** The left child is now assigned the original right child.
    * **`node.right = tem;`:** The right child is assigned the original left child (stored in `tem`).  This completes the swap.
    * **`dfs(node.left);`:** Recursive call to invert the left subtree (which is now the original right subtree).
    * **`dfs(node.right);`:** Recursive call to invert the right subtree (which is now the original left subtree).

* **`invertTree(TreeNode root)` function:** This is the main function that takes the root of the tree as input.
    * **`dfs(root);`:** It calls the `dfs` helper function to start the inversion process at the root.
    * **`return root;`:**  The inverted root is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  This is because each node is visited exactly once during the DFS traversal.  The swapping operation at each node takes constant time.

* **Space Complexity:** O(H) in the worst case, where H is the height of the tree. This space is used for the recursion stack. In the worst case (a skewed tree), the height can be equal to N, resulting in O(N) space complexity. In the best case (a balanced tree), H = log₂N, resulting in O(log₂N) space complexity.  Therefore, the space complexity is dependent on the tree's structure and can range from O(log₂N) to O(N).

This solution is efficient and effectively solves the "Invert Binary Tree" problem using a clear and concise recursive approach.  The use of a temporary variable ensures correct swapping without data loss, and the base case prevents infinite recursion.
