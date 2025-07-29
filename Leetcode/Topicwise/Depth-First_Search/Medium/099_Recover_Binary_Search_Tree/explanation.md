## LeetCode: Recover Binary Search Tree - Solution Explanation

**1. Problem Understanding:**

The problem asks us to correct a Binary Search Tree (BST) that has exactly two nodes swapped.  This means the BST property (left subtree < node < right subtree) is violated at exactly two nodes. We need to find these two nodes and swap their values to restore the BST property.  The input is the root of the corrupted BST, and the solution modifies the tree in-place.


**2. Approach / Intuition:**

The solution employs an in-order traversal of the BST using Depth-First Search (DFS).  The core idea is that during an in-order traversal of a BST, the nodes are visited in ascending order.  If we encounter a node whose value is smaller than its predecessor (the previously visited node), it indicates a violation of the BST property. By tracking these violations, we can identify the two swapped nodes.


This approach is chosen because an in-order traversal naturally reveals the order of nodes in a BST. Any deviation from the ascending order during this traversal directly points to the swapped nodes.  Other approaches might involve more complex tree restructuring or comparisons, making this method more efficient.


**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is the `TreeNode` representing nodes in the binary tree.  Additionally, we use several instance variables to track the swapped nodes.
* **Algorithms:** The core algorithm used is Depth-First Search (DFS) for in-order traversal of the BST.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a node in a binary tree, containing the node's value (`val`) and pointers to its left and right children (`left`, `right`).

* **`Solution` Class:** This class contains the logic to recover the BST.

* **`pre`, `first`, `mid`, `last`:** These instance variables are crucial for tracking the swapped nodes.
    * `pre`: Stores the previously visited node during the in-order traversal.
    * `first`: Stores the first node that violates the BST property (smaller than its predecessor).
    * `mid`: Stores the node that's smaller than `first` (second node involved in the swap).
    * `last`:  Stores the node that comes *after* `mid` and violates the BST property (if there's a second violation; potentially the first node).


* **`dfs(TreeNode node)`:** This recursive function performs the in-order traversal.
    * **Base Case:** If `node` is `null`, it returns.
    * **Recursive Calls:** It recursively calls `dfs` on the left subtree.
    * **Violation Check:** It checks if `pre` (the previous node) is not `null` and the current node's value (`node.val`) is less than `pre.val`. If true, a violation is detected.
    * **Violation Handling:** If it's the first violation (`first == null`), `first` is set to `pre`, and `mid` is set to `node`. If it's a second violation, `last` is set to `node`.
    * **Update `pre`:** `pre` is updated to the current node.
    * **Recursive Call:** It recursively calls `dfs` on the right subtree.


* **`recoverTree(TreeNode root)`:** This function initiates the recovery process.
    * It calls `dfs(root)` to perform the in-order traversal and identify the swapped nodes.
    * It then checks if a second violation (`last`) exists.
    * If `last` is null (only one violation), it swaps the values of `first` and `mid`. Otherwise, it swaps the values of `first` and `last`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the BST. This is because the DFS performs a single in-order traversal, visiting each node exactly once.

* **Space Complexity:** O(H), where H is the height of the BST. This is due to the recursive calls in DFS, which consume stack space proportional to the height of the tree (worst case: O(N) for a skewed tree, best case: O(log N) for a balanced tree).  The instance variables (`pre`, `first`, `mid`, `last`) use constant extra space.

In summary, this solution efficiently recovers the BST with exactly two swapped nodes by leveraging the properties of in-order traversal and requiring only linear time and space proportional to the tree's height.
