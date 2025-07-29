## LeetCode: Trim a Binary Search Tree - Solution Explanation

**1. Problem Understanding:**

The problem asks us to create a function that trims a given binary search tree (BST).  This trimming involves removing all nodes whose values are outside the range [low, high] (inclusive). The resulting tree should still be a valid BST.

**2. Approach / Intuition:**

The solution employs a recursive Depth-First Search (DFS) approach.  This is a natural fit for traversing a tree structure.  The core logic relies on the property of BSTs:  nodes smaller than the current node are in the left subtree, and nodes larger are in the right subtree.

The `dfs` function recursively explores the tree. For each node:

* **If the node's value is less than `low`, it's outside the range, so we recursively trim the *right* subtree (since nodes greater than the current node might be within the range).**  We discard the left subtree as it's guaranteed to be out of range.
* **If the node's value is greater than `high`, it's outside the range, so we recursively trim the *left* subtree (since nodes smaller than the current node might be within the range).** We discard the right subtree.
* **Otherwise, the node's value is within the range [low, high].  We recursively trim its left and right subtrees and return the node itself.**

This recursive approach efficiently prunes the unwanted parts of the tree without needing to reconstruct the entire tree.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure is the binary tree (`TreeNode`).
* **Algorithm:** Depth-First Search (DFS) recursion.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, containing the value (`val`) and pointers to the left and right children (`left`, `right`).

* **`dfs(TreeNode node, int l, int r)`:** This recursive function performs the trimming:
    * **`if(node == null) return null;`:** Base case: If the current node is null (we've reached the end of a branch), we return null.
    * **`if(node.val < l) return dfs(node.right, l, r);`:** If the node's value is less than `low`, only the right subtree might contain valid nodes.  We recursively trim the right subtree.
    * **`if(node.val > r) return dfs(node.left, l, r);`:** If the node's value is greater than `high`, only the left subtree might contain valid nodes. We recursively trim the left subtree.
    * **`node.left = dfs(node.left, l, r);`:** Recursively trim the left subtree.
    * **`node.right = dfs(node.right, l, r);`:** Recursively trim the right subtree.
    * **`return node;`:** The node's value is within the range, so we return the (potentially trimmed) node.

* **`trimBST(TreeNode root, int low, int high)`:** This is the main function, simply calling `dfs` on the root node to start the trimming process.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. In the worst case, we might visit every node once.
* **Space Complexity:** O(H), where H is the height of the tree. This is due to the recursive call stack. In the worst-case scenario (a skewed tree), H could be equal to N.  However, for a balanced tree, H would be log₂(N), resulting in a logarithmic space complexity.


**In summary:** This solution elegantly leverages the properties of a BST to efficiently trim the tree using recursion. The DFS approach minimizes unnecessary operations by strategically exploring only the relevant subtrees.  The time complexity is linear, and the space complexity is determined by the height of the tree, making it efficient for most cases.
