## LeetCode: Symmetric Tree - Solution Explanation

**1. Problem Understanding:**

The problem asks whether a given binary tree is symmetric. A symmetric tree is one where the left subtree is a mirror image of the right subtree.  This means that nodes at corresponding positions have the same values, and the left subtree of a node mirrors the right subtree, and vice versa recursively.  An empty tree is considered symmetric.


**2. Approach / Intuition:**

The solution uses a recursive helper function (`chk`) to efficiently check for symmetry.  The core idea is to compare the left subtree of the root with the right subtree recursively.  Instead of directly comparing the structure, we recursively compare the right child of the left subtree with the left child of the right subtree, and the left child of the left subtree with the right child of the right subtree. This mirroring is the key to efficiently determining symmetry.  This approach avoids the need for explicit tree traversal or other complex data structures.  A recursive approach is natural and elegant for this tree-based problem because it directly mirrors the recursive nature of the tree structure itself.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure is the binary tree itself, represented using the `TreeNode` class.  No additional data structures are explicitly used; the recursion implicitly uses the call stack.
* **Algorithm:**  The algorithm utilizes recursion. The `chk` function performs a depth-first traversal of the tree, comparing mirrored nodes at each level.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, containing an integer value (`val`) and references to its left and right children (`left` and `right`).

* **`chk(TreeNode p, TreeNode q)` function:** This recursive helper function compares two subtrees (`p` and `q`) to see if they are mirror images of each other.
    * **Base Cases:**
        * `if(p == null && q == null) return true;`: If both subtrees are empty (null), they are symmetric, so return `true`.
        * `if(p == null || q == null) return false;`: If only one subtree is empty, they are not symmetric, so return `false`.
        * `if(p.val != q.val) return false;`: If the values of the root nodes of the subtrees are different, they are not symmetric, so return `false`.
    * **Recursive Step:** `return chk(p.right , q.left) && chk(p.left , q.right);`: This is the core of the algorithm.  It recursively calls `chk` to compare the right child of `p` with the left child of `q` and the left child of `p` with the right child of `q`.  The `&&` operator ensures that both comparisons must be true for the subtrees to be symmetric.

* **`isSymmetric(TreeNode root)` function:** This function initiates the symmetry check.
    * `if(root.left == null && root.right == null) return true;`: Handles the case of an empty tree (or a tree with only the root node), which is considered symmetric.
    * `return chk(root.left , root.right);`: Calls the `chk` function to compare the left and right subtrees of the root.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. This is because each node is visited at most once during the recursive traversal.

* **Space Complexity:** O(H), where H is the height of the tree. This is due to the recursive call stack. In the worst case (a skewed tree), the height can be equal to N, resulting in O(N) space complexity. In the best case (a balanced tree), the height is log₂(N), resulting in O(log₂(N)) space complexity.  Therefore, the space complexity is dominated by the recursion depth which is proportional to the height of the tree.
