## LeetCode: Binary Search Tree to Greater Sum Tree - Solution Explained

**1. Problem Understanding:**

The problem asks us to modify a given Binary Search Tree (BST) such that the value of each node becomes the sum of all nodes greater than or equal to itself in the original BST.  In essence, we need to transform the BST into a Greater Sum Tree (GST).


**2. Approach / Intuition:**

The most efficient way to solve this problem is using a **Reverse Inorder Traversal**.  A standard inorder traversal visits nodes in ascending order (left, root, right).  However, to accumulate the sum of *greater* nodes, we need to traverse in *descending* order (right, root, left). This is accomplished using Depth-First Search (DFS) with a recursive helper function.

Why this approach?  Because BSTs are inherently ordered.  By traversing in reverse inorder, we guarantee that we encounter nodes in decreasing order of their values.  This allows us to efficiently accumulate the sum of greater nodes as we go.  Other approaches, like using a separate array to store nodes' values for later processing, would be less efficient due to the need for sorting or additional space.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure is the `TreeNode` representing nodes in the Binary Search Tree.  We also implicitly use a call stack during the recursive DFS.
* **Algorithms:**  The core algorithm used is Depth-First Search (DFS) implemented recursively.  The traversal order is a modified (reversed) inorder traversal.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a node in a binary tree, providing fields for the node's value (`val`) and its left and right children (`left`, `right`).

* **`sum` Variable:** This variable is declared as an instance variable to store the cumulative sum of node values encountered during the traversal. It's initialized to 0.  Using an instance variable avoids passing the sum as a parameter in every recursive call.

* **`dfs(TreeNode node)` Function:** This is the recursive helper function that performs the reverse inorder traversal.
    * **Base Case:** `if(node == null) return;`  Handles the case where we reach a null node (end of a branch).
    * **Recursive Step 1: `dfs(node.right);`:**  Recursively visits the right subtree first. This ensures we process nodes with greater values before the current node.
    * **Sum Accumulation: `sum += node.val;`:**  Adds the current node's value to the running sum (`sum`).
    * **Update Node Value: `node.val = sum;`:**  Updates the current node's value to the accumulated sum, which represents the sum of all greater or equal nodes.
    * **Recursive Step 2: `dfs(node.left);`:** Recursively visits the left subtree.


* **`bstToGst(TreeNode root)` Function:** This is the main function that initializes the `sum` to 0 and calls the `dfs` function to start the traversal from the root node.  It then returns the modified root node.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the BST. This is because each node is visited exactly once during the DFS traversal.

* **Space Complexity:** O(H), where H is the height of the BST. This is due to the space used by the call stack during the recursive calls. In the worst-case scenario (a skewed BST), H can be equal to N, resulting in O(N) space complexity. In the best-case scenario (a balanced BST), H is log₂(N), resulting in O(log₂(N)) space complexity.


**In summary:** This solution elegantly leverages the properties of a BST and the recursive nature of DFS to efficiently transform the BST into a Greater Sum Tree in linear time and logarithmic to linear space, depending on the BST's structure. The use of a single instance variable to maintain the sum optimizes space efficiency compared to passing the sum as a function parameter.
