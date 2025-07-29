# LeetCode: Longest Univalue Path - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find the length of the longest path in a binary tree where every node along the path has the same value.  This path doesn't need to necessarily pass through the root. It can start and end anywhere within the tree.


**2. Approach / Intuition:**

The solution employs a Depth-First Search (DFS) approach.  Instead of trying to find all possible paths and comparing their lengths,  we use a recursive helper function (`dfs`) to efficiently explore the tree.  The core idea is that for each node:

* We recursively calculate the length of the univalue path extending from its left and right children.
* If a child node has the same value as the current node, we extend the path.
* We update the global maximum path length (`max`) whenever a longer path is found.
* The recursive function returns the maximum univalue path length rooted *at* the current node (this is crucial for efficient calculation, it avoids redundant path explorations).


This approach is efficient because it avoids redundant calculations by reusing the results of the recursive calls from subtrees. We directly build the path length incrementally.


**3. Data Structures and Algorithms:**

* **Data Structure:** The primary data structure used is a binary tree (`TreeNode`).
* **Algorithm:** Depth-First Search (DFS) is used to traverse the tree recursively.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree.

* **`Solution` class:**
    * **`max` variable:** A global variable to store the maximum univalue path length found so far. Initialized to 0.
    * **`dfs(TreeNode node)` function:** This recursive function performs the DFS.
        * **Base Case:** If `node` is `null`, it returns 1001 (a value larger than any possible path length, preventing errors in the comparisons later).  A large value is chosen to ensure the logic works correctly when encountering null subtrees.
        * **Recursive Calls:** It recursively calls `dfs` on the left and right children (`l` and `r`).
        * **Path Length Calculation:** `lp` and `rp` store the univalue path lengths extending from the left and right children, respectively.  These are only incremented if the child node has the same value as the current node.
        * **Maximum Path Update:** `max` is updated with the maximum of `max` and `lp + rp`.  This captures the case where the longest path passes *through* the current node.
        * **Return Value:** The function returns `Math.max(lp, rp)`, representing the maximum univalue path length rooted *at* the current node. This value is used to extend the path when recursively going up the tree.
    * **`longestUnivaluePath(TreeNode root)` function:** This function serves as the entry point. It calls `dfs` on the root node and returns the final `max` value.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Each node is visited exactly once during the DFS traversal.

* **Space Complexity:** O(H), where H is the height of the tree. This is due to the recursive call stack used in the DFS. In the worst-case scenario (a skewed tree), H can be equal to N, resulting in O(N) space complexity. In a balanced tree, H is log(N), leading to O(logN) space complexity.  Therefore, the space complexity is dominated by the recursion depth, which is at most the height of the tree.


This solution provides an efficient and concise way to solve the Longest Univalue Path problem using DFS and a clever way of utilizing the return value of the recursive function to track the path.  The use of a large default return value in the base case gracefully handles edge conditions of null subtrees.
