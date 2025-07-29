# LeetCode: Add One Row to Tree - Detailed Explanation

## 1. Problem Understanding

The problem asks us to add a row of nodes with a given value (`val`) at a specified depth (`depth`) in a binary tree.  The new row should be inserted *before* the existing nodes at that depth. If the depth is 1, the new node becomes the root.

## 2. Approach / Intuition

The solution uses a Depth-First Search (DFS) recursive approach.  It cleverly handles the base cases:

* **Depth 1:** Creates a new root node with the given value and attaches the original root as its left child.
* **Depth > 1:**  The `dfs` function recursively traverses the tree. When it reaches the target depth (`d == 1` in the recursive call), it inserts two new nodes (with the given value) as the left and right children of the current node.  Crucially, it preserves the existing children by attaching them as the left child of the newly created left node and the right child of the newly created right node.

This approach is chosen because DFS naturally allows us to explore the tree level by level, efficiently reaching the specified depth.  It avoids the need for complex level-order traversal (BFS) which would require more memory overhead to manage a queue of nodes.

## 3. Data Structures and Algorithms

* **Data Structures:** The primary data structure is the binary tree itself, represented using the `TreeNode` class.  No other significant data structures are used.
* **Algorithms:** The core algorithm is Depth-First Search (DFS) implemented recursively.

## 4. Code Walkthrough

Let's break down the code section by section:

* **`TreeNode` class:** This is a standard definition for a node in a binary tree.

* **`dfs(TreeNode node, int d, int val)`:** This is a recursive helper function.
    * **`if(node == null) return;`:** Base case: if the current node is null, we return (we've reached the end of a branch).
    * **`if(d == 1)`:** This is the crucial part. When the depth `d` reaches 1 (meaning we've reached the target depth in the recursive calls), we create two new nodes (`n1`, `n2`) with the given `val`. We then carefully attach these new nodes as the left and right children of the current node, preserving the original children.
    * **`dfs(node.left , d-1 , val);` and `dfs(node.right , d-1 , val);`:** Recursive calls to explore the left and right subtrees, decrementing the depth (`d-1`) in each call.

* **`addOneRow(TreeNode root, int val, int depth)`:** This is the main function.
    * **`if(1 == depth)`:** Handles the base case where the depth is 1. A new root node is created, and the original root is attached as its left child.
    * **`dfs(root , depth-1 , val);`:**  If the depth is greater than 1, the `dfs` function is called to insert the new row.
    * **`return root;`:** The modified root (with the new row added) is returned.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  In the worst case, the DFS function visits each node once.

* **Space Complexity:** O(H), where H is the height of the tree. This is due to the recursive calls of `dfs`. The space used on the call stack is proportional to the height of the tree.  In a balanced tree, H = log₂(N), while in a skewed tree, H could be N.  Therefore, the space complexity can range from O(log N) to O(N) depending on the tree's structure.  Note that the creation of new nodes adds constant space per level, which is negligible compared to the recursive call stack.

In summary, this solution efficiently solves the "Add One Row to Tree" problem using a concise and well-structured recursive DFS approach.  The time complexity is linear, and the space complexity is dependent on the tree's height, but generally remains relatively efficient.
