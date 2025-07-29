# LeetCode: Sum Root to Leaf Numbers - Detailed Explanation

## 1. Problem Understanding

The problem "Sum Root to Leaf Numbers" asks us to calculate the sum of all numbers formed by the digits along each root-to-leaf path in a binary tree.  Each node's value represents a digit in the number.

## 2. Approach / Intuition

The most efficient approach to this problem is Depth-First Search (DFS).  We traverse the tree recursively, building up the number as we go down each path.  When we reach a leaf node (a node with no children), we add the accumulated number to the total sum.  This avoids the need for complex path tracking or iterative approaches which could be less efficient.  A recursive DFS neatly handles the exploration of all possible paths simultaneously.

## 3. Data Structures and Algorithms

* **Data Structure:** The primary data structure is the binary tree itself (`TreeNode`).  No other significant data structures are used besides integers to track the current number and the total sum.

* **Algorithm:** Depth-First Search (DFS) is the core algorithm employed for traversing the binary tree.


## 4. Code Walkthrough

**4.1 `TreeNode` Class:**

This class is a standard definition for a node in a binary tree, providing the `val` (value), `left` (left child), and `right` (right child) members.

**4.2 `dfs(TreeNode node, int num)` Function:**

This is a recursive helper function that performs the DFS traversal.

* `if(node == null) return 0;`: This is the base case for recursion. If the current node is null (we've reached the end of a branch), we return 0, contributing nothing to the sum.

* `num = num*10 + node.val;`: This is the crucial step. It appends the current node's value to the existing number (`num`).  We multiply `num` by 10 to shift existing digits to the left, making space for the new digit.

* `if(node.left == null && node.right == null) return num;`:  This is the leaf node check. If it's a leaf node (no children), we've reached the end of a path, so we return the accumulated number `num`, adding it implicitly to the final sum.

* `int l = dfs(node.left , num);`: Recursively calls `dfs` on the left subtree, passing the current accumulated number.

* `int r = dfs(node.right , num);`: Recursively calls `dfs` on the right subtree, passing the current accumulated number.

* `return l+r;`: This line adds the sums returned from the left and right subtrees, aggregating the results from all paths originating from the current node.


**4.3 `sumNumbers(TreeNode root)` Function:**

This is the main function that initiates the DFS traversal by calling `dfs` with the root node and an initial number of 0.  It simply returns the result of `dfs`.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  This is because each node is visited exactly once during the DFS traversal.

* **Space Complexity:** O(H), where H is the height of the binary tree.  This space is used for the recursive call stack. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  In the best case (a balanced tree), H is log₂(N), leading to O(log N) space complexity.  Therefore, the space complexity is generally described as O(H) or O(N) in the worst case.
