## LeetCode: Binary Tree Inorder Traversal - Solution Explanation

**1. Problem Understanding:**

The problem asks us to traverse a given binary tree and return a list containing the values of all nodes in *inorder* traversal order. Inorder traversal means visiting nodes in the order: left subtree, root node, right subtree.  This produces a sorted list if the tree is a Binary Search Tree (BST).


**2. Approach / Intuition:**

The solution employs a Depth-First Search (DFS) approach using recursion.  This is a natural choice for tree traversal because it systematically explores all branches of the tree.  The recursive `dfs` function elegantly handles the inorder traversal logic:

* **Base Case:** If the current node is `null`, it simply returns (base case of recursion).
* **Recursive Step:** It recursively visits the left subtree, then adds the current node's value to the result list, and finally recursively visits the right subtree.  This ensures the inorder order (left, root, right).


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`: A custom class representing a node in the binary tree.  It contains the node's value (`val`), and pointers to its left and right children (`left`, `right`).
    * `ArrayList<Integer>`:  A dynamic array (List) used to store the inorder traversal sequence of node values.

* **Algorithms:**
    * **Depth-First Search (DFS):** A recursive algorithm used to explore the tree by going deep into each branch before backtracking.  This specific implementation is a form of inorder DFS.



**4. Code Walkthrough:**

* **`TreeNode` Class:** This class is a standard definition for a node in a binary tree, provided by LeetCode.  It's not part of our solution logic but crucial for the problem definition.

* **`Solution` Class:**
    * `List<Integer> ans = new ArrayList<>();`:  An ArrayList named `ans` is initialized to store the inorder traversal result. It's an instance variable, so it persists across recursive calls.
    * `private void dfs(TreeNode node)`: This is the recursive helper function performing the inorder traversal.
        * `if(node == null) return;`: This is the base case. If the node is `null`, there's nothing to process, so the function returns.
        * `dfs(node.left);`: Recursively calls `dfs` on the left subtree. This ensures the left subtree is visited first.
        * `ans.add(node.val);`: Adds the current node's value to the `ans` list.
        * `dfs(node.right);`: Recursively calls `dfs` on the right subtree. This ensures the right subtree is visited after the root.
    * `public List<Integer> inorderTraversal(TreeNode root)`: This is the main function that initiates the inorder traversal. It calls the `dfs` function with the root node and returns the `ans` list.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  This is because each node is visited exactly once during the traversal.

* **Space Complexity:**
    * **Worst Case:** O(N). In a skewed tree (where all nodes have only one child), the recursion depth can reach N, leading to O(N) space complexity for the call stack.
    * **Average Case:** O(log N) for balanced trees. The recursion depth is proportional to the height of the tree, which is typically logarithmic for balanced trees.
    * **Best Case:** O(1) for very small or empty trees.

The space complexity analysis considers the call stack used by the recursive `dfs` function. The ArrayList `ans` itself takes O(N) space, but that's usually not considered in complexity analysis as it's directly related to output size. The dominant factor in space complexity is the recursion depth.
