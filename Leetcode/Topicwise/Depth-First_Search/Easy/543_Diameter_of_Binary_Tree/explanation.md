# Diameter of Binary Tree - LeetCode Solution Explained

## 1. Problem Understanding

The problem asks us to find the diameter of a binary tree. The diameter is defined as the maximum number of nodes on the longest path between any two leaf nodes in the tree.  This path may or may not pass through the root.

## 2. Approach / Intuition

The most efficient way to solve this problem is using a Depth-First Search (DFS) approach.  Instead of exploring all possible paths between leaf nodes (which would be exponentially complex), we can leverage the fact that the diameter is always the sum of the depths of the deepest left and right subtrees of *some* node in the tree.  We don't need to explicitly find that node; we can update a global maximum during our DFS traversal.

This approach is chosen because it's significantly more efficient than brute-forcing all paths.  A brute-force approach would have exponential time complexity, whereas the DFS approach achieves linear time complexity.

## 3. Data Structures and Algorithms

* **Data Structure:**  The problem inherently uses a tree (represented by `TreeNode`).  No additional data structures are explicitly used beyond the tree itself and a single integer variable (`dia`) to track the maximum diameter.

* **Algorithm:** Depth-First Search (DFS) is the core algorithm employed.

## 4. Code Walkthrough

The code consists of two main parts: the `dep` function and the `diameterOfBinaryTree` function.

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, providing `val`, `left`, and `right` attributes.

* **`dep(TreeNode node)` function:** This is a recursive helper function that calculates the depth of the tree rooted at the given `node`.  It does the following:

    * **Base Case:** If `node` is `null` (empty subtree), it returns 0.
    * **Recursive Step:** It recursively calculates the depth of the left subtree (`lh`) and the right subtree (`rh`).
    * **Diameter Update:** Crucially, it updates the global `dia` variable with the maximum of the current `dia` and the sum of `lh` and `rh`. This sum represents the diameter of the subtree rooted at the current node.
    * **Return Value:** It returns `1 + Math.max(lh, rh)`, which is the depth of the current subtree (including the current node).

* **`diameterOfBinaryTree(TreeNode root)` function:** This function serves as the entry point.  It calls the `dep` function with the root node and returns the final value of `dia`, which holds the maximum diameter found during the traversal.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  This is because the `dep` function visits each node exactly once during the DFS traversal.

* **Space Complexity:** O(H), where H is the height of the tree.  This space is used by the recursive call stack during the DFS. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  However, for balanced trees, H is log₂(N), leading to O(log N) space complexity.  Therefore, the space complexity is bounded by O(N) in the worst case.


In summary, this solution efficiently solves the Diameter of Binary Tree problem using a clever DFS approach that avoids unnecessary computation. The time complexity is linear, and the space complexity is logarithmic for balanced trees and linear in the worst case.
