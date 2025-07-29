## Lowest Common Ancestor of Deepest Leaves - LeetCode Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the lowest common ancestor (LCA) of the deepest leaves in a binary tree.  The deepest leaves are the nodes at the maximum depth of the tree.  The LCA is the lowest node in the tree that is an ancestor of all the deepest leaves.

**2. Approach / Intuition:**

The solution uses a two-pass approach:

* **Pass 1 (Depth Calculation):**  The `hei()` function performs a depth-first search (DFS) to determine the height (`h`) of the binary tree.  This is necessary because we need to know the depth of the deepest leaves to find their LCA.

* **Pass 2 (LCA Finding):** The `dfs()` function is a recursive DFS that traverses the tree.  It takes the current node and the current depth (`d`) as input. The core logic lies in the conditional `if(d == h-1) return node;`.  This checks if the current node is at the level of the deepest leaves (depth `h-1`). If it is, it returns that node as a potential deepest leaf.  Otherwise, it recursively calls `dfs` on left and right subtrees, incrementing the depth.  The crucial part is `if(l != null && r != null) return node;`. If both left and right recursive calls return non-null nodes (meaning both subtrees contribute to deepest leaves), then the current node is the LCA. If only one subtree has a deepest leaf, that leaf's parent node is returned.

This approach is efficient because it avoids building a separate data structure to store the deepest leaves.  It directly identifies the LCA during the second DFS.

**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure is the binary tree itself (`TreeNode`). No auxiliary data structures are used explicitly; the recursive calls implicitly use the call stack.

* **Algorithms:** The core algorithm used is Depth-First Search (DFS).


**4. Code Walkthrough:**

* **`hei(TreeNode node)`:** This function recursively calculates the height of the subtree rooted at `node`. It returns 0 for a null node, otherwise it recursively computes the height of the left and right subtrees and returns the maximum height plus 1 (for the current node).

* **`dfs(TreeNode node, int d)`:** This function recursively searches for the LCA of the deepest leaves.
    * The base case `if(node == null) return node;` handles null nodes.
    * `if(d == h-1) return node;` checks if we have reached the level of the deepest leaves. If so, the current node (a deepest leaf) is returned.
    * `TreeNode l = dfs(node.left, d+1);` and `TreeNode r = dfs(node.right, d+1);` recursively call `dfs` on the left and right subtrees.
    * `if(l != null && r != null) return node;` checks if both subtrees have deepest leaves. If true, the current node is the LCA.
    * `return (l == null)?r:l;` returns either the left or right subtree's result (the deepest leaf in that subtree or its ancestor).

* **`lcaDeepestLeaves(TreeNode root)`:** This is the main function.
    * `h = hei(root);` computes the height of the tree.
    * `return dfs(root, 0);` initiates the DFS to find the LCA, starting at the root with depth 0.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Both `hei()` and `dfs()` traverse the tree once.

* **Space Complexity:** O(H), where H is the height of the tree. This is due to the recursive call stack used by both `hei()` and `dfs()`. In the worst case (a skewed tree), H can be equal to N.  In a balanced tree, H is log₂N. Therefore, the space complexity is O(log N) for a balanced tree and O(N) for a skewed tree.

In summary, this solution efficiently finds the LCA of the deepest leaves using a two-pass DFS approach with linear time complexity and logarithmic to linear space complexity depending on the tree's structure.
