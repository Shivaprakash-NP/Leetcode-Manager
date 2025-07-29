## LeetCode: Reverse Odd Levels of Binary Tree - Solution Explanation

**1. Problem Understanding:**

The problem asks us to reverse the nodes at odd levels of a binary tree.  "Reversing" means swapping the values of nodes at symmetric positions across the center of each level.  Level 1 is the root, level 2 is its children, level 3 is the grandchildren, and so on.  Only odd-numbered levels are affected.

**2. Approach / Intuition:**

The solution uses a Depth-First Search (DFS) approach with a recursive helper function (`dfs`).  Instead of traversing the tree level by level, which would require additional data structures like queues, this approach leverages the inherent symmetry of the problem.  For each level, it recursively mirrors the traversal, comparing and swapping nodes that are symmetrically opposite each other. This is efficient because it directly addresses the core requirement of swapping values at odd levels without the overhead of managing levels explicitly.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is the binary tree itself (`TreeNode`). No additional data structures are needed for the chosen recursive approach.
* **Algorithm:** Depth-First Search (DFS) is employed through recursion.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, providing `val`, `left`, and `right` attributes.

* **`dfs(TreeNode l, TreeNode r, int lev)`:** This recursive helper function performs the core logic.
    * `if(l == null || r == null)`: This base case handles the situation when one of the symmetric nodes is missing (e.g., an incomplete binary tree).  It stops further recursion.
    * `if(lev%2 == 1)`: This condition checks if the current level (`lev`) is odd.  If it is, the values of the left (`l`) and right (`r`) nodes are swapped using a temporary variable (`tem`).
    * `dfs(l.left, r.right, lev+1)`: Recursive call for the left subtree, mirroring the traversal.
    * `dfs(l.right, r.left, lev+1)`: Recursive call for the right subtree, maintaining the symmetry.

* **`reverseOddLevels(TreeNode root)`:** This function serves as the entry point.
    * `if(root.left == null)`: This handles the case of an empty or single-node tree.  It returns the root directly as no reversal is needed.
    * `dfs(root.left, root.right, 1)`: It initiates the recursive `dfs` function, starting from the left and right children of the root at level 1.
    * `return root`: The modified root is returned.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  Each node is visited and potentially swapped exactly once during the DFS traversal.

* **Space Complexity:** O(H), where H is the height of the binary tree. This space is used by the call stack during the recursive calls of the `dfs` function. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  However, for balanced trees, H is log₂(N), resulting in O(log N) space complexity.


**Expert Note:** The code assumes that the input tree is a valid binary tree.  Error handling (e.g., checking for `null` root) could be added for robustness.  The solution elegantly avoids using queues or explicit level tracking, making it concise and efficient for this specific problem.  It exploits the inherent mirror symmetry in the problem definition for an optimal approach.
