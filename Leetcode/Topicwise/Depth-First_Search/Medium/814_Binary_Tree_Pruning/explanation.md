## LeetCode: Binary Tree Pruning - Detailed Explanation

**1. Problem Understanding:**

The problem "Binary Tree Pruning" asks us to remove all nodes from a binary tree whose value is 0 and *all* of whose descendants also have a value of 0.  Essentially, we're pruning branches that contain only zeros.


**2. Approach / Intuition:**

The solution uses a depth-first search (DFS) approach implemented recursively.  The core logic is based on a recursive helper function (`dfs`) that traverses the tree post-order.  For each node:

* It recursively checks if the left and right subtrees need pruning.
* If a subtree (left or right) only contains 0s, it's removed by setting the corresponding child pointer to `null`.
* The function returns `true` if the subtree (including the current node) contains at least one node with the value 1; otherwise, it returns `false`.

This post-order traversal ensures that we can correctly prune subtrees before making decisions about their parent nodes. The main `pruneTree` function simply calls the `dfs` function and returns `null` if the entire tree is to be pruned (contains only 0s).

This approach was chosen because DFS naturally lends itself to recursively processing tree structures.  Post-order traversal is ideal because it allows us to determine if a subtree needs pruning before altering the tree structure itself.

**3. Data Structures and Algorithms:**

* **Data Structure:**  The problem uses a binary tree.  No additional data structures are explicitly used beyond the implicit call stack for the recursive DFS.
* **Algorithm:** Depth-First Search (DFS) with post-order traversal.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, containing an integer value (`val`) and pointers to the left and right children (`left`, `right`).

* **`dfs(TreeNode node)` function:**
    * **`if(node == null) return false;`:** Base case for the recursion. An empty subtree contains no 1s.
    * **`boolean l = dfs(node.left);`** and **`boolean r = dfs(node.right);`:** Recursively calls `dfs` on the left and right subtrees.  These calls return `true` if the respective subtrees contain at least one 1, and `false` otherwise.
    * **`if(!l) node.left = null;`** and **`if(!r) node.right = null;`:**  If a subtree (left or right) contains only 0s (`l` or `r` is `false`), its connection to the parent node is severed by setting the child pointer to `null`. This is the pruning step.
    * **`return l || r || node.val == 1;`:** This line is crucial. It returns `true` if either the left subtree, the right subtree, or the current node itself has a value of 1.  Otherwise, it returns `false`, indicating that this subtree contains only 0s and can be pruned.

* **`pruneTree(TreeNode root)` function:**
    * **`if(!dfs(root)) return null;`:** The main function starts by calling `dfs` on the root. If `dfs` returns `false` (meaning the entire tree contains only 0s), the root is set to `null`.
    * **`return root;`:** Otherwise, the original root is returned (possibly with some branches pruned).

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  This is because the `dfs` function visits each node exactly once.

* **Space Complexity:** O(H), where H is the height of the binary tree.  This space is used for the recursive call stack. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity. In the best case (a balanced tree), H is log₂(N), resulting in O(log₂(N)) space complexity.  No additional data structures are used beyond the implicit call stack.

This solution is efficient because it avoids unnecessary traversal.  It prunes branches as it goes, ensuring that nodes are visited only once.
