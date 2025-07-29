## Maximum Sum BST in Binary Tree - LeetCode Solution Explained

**1. Problem Understanding:**

The problem asks us to find the maximum sum of a Binary Search Tree (BST) subtree within a given binary tree.  A BST subtree is defined as a subtree where every node satisfies the BST property (left subtree values < node value < right subtree values).  The sum is calculated by adding up all the node values within the maximum sum BST subtree. If the input tree is empty or contains no BST subtrees, the function should return 0.


**2. Approach / Intuition:**

The solution employs a post-order traversal using Depth-First Search (DFS).  This approach is chosen because it allows us to efficiently check the BST property bottom-up.  By processing the leaves first, we can determine if each subtree forms a valid BST, and if so, calculate its sum.  The `dfs` function returns an `info` object containing information about the subtree (whether it's a BST, its minimum and maximum values, and its sum).  The global variable `ans` keeps track of the maximum sum encountered so far.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`: A standard node structure for a binary tree.
    * `info`: A custom class to store information about a subtree (is it a BST?, minimum value, maximum value, and sum). This is crucial for efficiently passing information back up the recursion.
* **Algorithms:**
    * Depth-First Search (DFS) - Post-order traversal to process the tree.


**4. Code Walkthrough:**

* **`info` class:** This inner class efficiently bundles together the information required for a node and its subtrees.  It significantly improves the code readability and reduces the need to pass multiple parameters in the recursive function.  `is` indicates if the subtree is a valid BST. `min`, `max`, and `sum` store the minimum, maximum, and sum of values in the subtree respectively.

* **`dfs(TreeNode node)`:** This is the core recursive function.
    * **Base Case:** If `node` is `null`, it returns an `info` object indicating a valid BST with extreme values to ensure correct comparisons in the subsequent checks (min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0).
    * **Recursive Step:** It recursively calls `dfs` on the left and right subtrees (`l` and `r`).
    * **BST Check and Sum Calculation:** It verifies if the current subtree is a BST:
        * `l.is && r.is`: Checks if both subtrees are valid BSTs.
        * `node.val > l.max && node.val < r.min`: Checks if the current node's value satisfies the BST property with respect to its children.
        * If all conditions are met, it calculates the sum of the current subtree (`nsum`) and updates `ans` if this sum is greater than the current maximum.  A new `info` object is created and returned.
    * **Invalid BST:** If any of the BST conditions are false, it returns an `info` object indicating an invalid BST (`is = false`).

* **`maxSumBST(TreeNode root)`:** This function initializes `ans` to 0, calls `dfs` on the root node, and returns the final `ans`.  The handling of an empty `root` is done within this function.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. This is because each node is visited exactly once during the DFS traversal.
* **Space Complexity:** O(H), where H is the height of the tree. This is due to the recursive call stack used in DFS. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity. In the best case (a balanced tree), H is log(N), resulting in O(log N) space complexity. The `info` objects also add a small constant space overhead.


This solution efficiently solves the problem by using a concise DFS approach with a custom `info` structure to manage information about subtrees. This makes the BST checks and sum calculations clear and systematic. The time complexity is optimal, visiting each node only once. The space complexity depends on the tree's height and is generally acceptable for this problem.
