# LeetCode: Flip Equivalent Binary Trees - Solution Explained

**1. Problem Understanding:**

The problem asks whether two given binary trees are "flip equivalent."  This means that one tree can be made identical to the other by flipping some number of subtrees (swapping left and right children).  If the trees have different structures or node values, they are not flip equivalent.

**2. Approach / Intuition:**

The solution uses a recursive Depth-First Search (DFS) approach. The core idea is to compare the nodes of the two trees recursively.  At each node, we check if:

* Both nodes are `null`: If so, we've reached the end of both subtrees, and they are equivalent.
* One node is `null` and the other isn't: They are not equivalent.
* The node values are different: They are not equivalent.
* Otherwise, we recursively check two possibilities:
    * Left subtree of `root1` matches left subtree of `root2` AND right subtree of `root1` matches right subtree of `root2`.
    * Left subtree of `root1` matches right subtree of `root2` AND right subtree of `root1` matches left subtree of `root2`.  This accounts for the "flip" operation.

This approach efficiently explores all possible subtree mappings to determine flip equivalence.  A recursive solution is natural because the problem is inherently recursive:  the equivalence of two trees depends on the equivalence of their subtrees.

**3. Data Structures and Algorithms:**

* **Data Structure:** The problem uses a binary tree represented using the `TreeNode` class.
* **Algorithm:** Depth-First Search (DFS) recursion.

**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a node in a binary tree, containing a value (`val`) and pointers to its left and right children (`left`, `right`).

* **`dfs(TreeNode n1, TreeNode n2)` Function:** This is the recursive helper function.
    * **Base Case:**  `if(n1 == null && n2 == null) return true;`  If both nodes are null, it means both subtrees are exhausted and are equivalent.
    * **Inequivalence Checks:** `else if(n1 == null || n2 == null || n1.val != n2.val) return false;` If either node is null (one subtree is longer than the other) or the node values differ, the trees are not flip equivalent.
    * **Recursive Step:** `return (dfs(n1.left, n2.left) && dfs(n1.right, n2.right)) || (dfs(n1.left, n2.right) && dfs(n1.right, n2.left));` This is the core logic. It checks both possibilities:  a direct match and a flipped match.  The `||` (OR) operator ensures that either scenario being true results in a positive match.

* **`flipEquiv(TreeNode root1, TreeNode root2)` Function:** This function simply calls the `dfs` helper function with the root nodes of the two input trees.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the total number of nodes in the larger tree. In the worst case, the algorithm visits each node once.  The recursive calls create a depth proportional to the height of the tree (which is at most N in a skewed tree), but each node is visited only a constant number of times.

* **Space Complexity:** O(H), where H is the height of the larger tree. This space is used by the recursion call stack. In the worst case (a skewed tree), H can be N, but for balanced trees, H is log₂(N).  Therefore, the space complexity is O(log N) for balanced trees and O(N) for skewed trees.  The space used for the `TreeNode` objects themselves isn't considered in this analysis, as that's part of the input.


This solution is efficient and correctly addresses the problem of determining flip equivalence in binary trees.  The recursive approach, while potentially consuming stack space, is elegant and mirrors the recursive nature of the tree structure itself.
