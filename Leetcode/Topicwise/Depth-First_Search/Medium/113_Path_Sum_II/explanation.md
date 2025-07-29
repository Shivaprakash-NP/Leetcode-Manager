## LeetCode: Path Sum II - Detailed Solution Explanation

**1. Problem Understanding:**

The "Path Sum II" problem asks us to find all root-to-leaf paths in a binary tree that sum up to a given target value.  A root-to-leaf path is a sequence of nodes starting from the root and ending at a leaf node (a node with no children).  The problem requires us to return a list of lists, where each inner list represents a path that meets the target sum.


**2. Approach / Intuition:**

The most efficient approach to solve this problem is Depth-First Search (DFS).  We recursively traverse the tree, keeping track of the current path sum.  At each node, we:

* Add the node's value to the current path sum.
* If we reach a leaf node and the current path sum equals the target sum, we add the current path to the result.
* Otherwise, we recursively explore the left and right subtrees.
* Crucially, after exploring a subtree, we *backtrack* by removing the current node's value from the path sum, ensuring that the path sum accurately reflects the path being explored.  This is vital for correctly exploring multiple paths.

DFS is chosen because it naturally explores all possible paths from the root to the leaves, allowing us to systematically check each path's sum.  Other approaches like Breadth-First Search (BFS) would be less efficient for this particular problem.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`: A custom class representing a node in the binary tree.
    * `List<List<Integer>>`:  A list of lists of integers. This is used to store all the paths that sum up to the target.
    * `List<Integer>`: A list of integers.  This represents a single path from root to leaf.
* **Algorithms:**
    * Depth-First Search (DFS):  A recursive algorithm to explore all paths in the tree.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a binary tree node, not part of the solution algorithm itself.

* **`Solution` Class:**
    * `ans`: A `List<List<Integer>>` to store the result.  Initialized as an empty list.
    * `dfs(TreeNode node, int rem, List<Integer> val)`: This is the recursive DFS function.
        * `if(node == null) return;`: Base case: if we reach a null node (end of a branch), we return.
        * `val.add(node.val);`: Add the current node's value to the current path (`val`).
        * `rem -= node.val;`: Update the remaining sum needed to reach the target.
        * `if(node.left == null && node.right == null && rem == 0)`: Check if we've reached a leaf node and the remaining sum is 0 (meaning the path sum equals the target). If so, add a copy of the current path (`new ArrayList<>(val)`) to `ans`.
        * `else { dfs(node.left, rem, val); dfs(node.right, rem, val); }`: Recursively explore the left and right subtrees.
        * `val.remove(val.size() - 1);`: **Backtracking step**: Remove the current node's value from the path after exploring its subtrees.  This is crucial for exploring other paths correctly.
    * `pathSum(TreeNode root, int targetSum)`: The main function.
        * `List<Integer> val = new ArrayList<>();`: Creates an empty list to store the current path.
        * `if(root == null) return ans;`: Handles the case of an empty tree.
        * `dfs(root, targetSum, val);`: Initiates the DFS traversal.
        * `return ans;`: Returns the list of paths.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. In the worst case (a complete binary tree), we visit each node once.  The recursive calls contribute to the linear time complexity.

* **Space Complexity:** O(H) in average and O(N) in the worst case, where H is the height of the tree and N is the number of nodes. This space is used by the recursion stack (DFS). In the worst-case scenario (a skewed tree), the height can be equal to the number of nodes.  Additionally, we use O(H) space on average to store the paths in the `val` list.  In the worst case, the path length (and therefore the space used by `val`) could reach O(N).  Therefore, overall space complexity is dominated by the recursive stack and the path list, resulting in O(N).


This detailed explanation provides a comprehensive understanding of the provided Java code for solving the LeetCode "Path Sum II" problem.  The use of DFS with backtracking is an efficient and elegant solution to this problem.
