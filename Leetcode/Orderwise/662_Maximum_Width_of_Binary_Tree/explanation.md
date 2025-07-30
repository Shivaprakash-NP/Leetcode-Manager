## Maximum Width of Binary Tree - LeetCode Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum width of a binary tree.  The width is defined as the maximum number of nodes present on any level of the tree.  We need to consider the width at each level and return the maximum of these widths.

**2. Approach / Intuition:**

The solution uses a Breadth-First Search (BFS) approach implemented with a queue.  Instead of simply tracking nodes, it also keeps track of each node's index within its level.  This index is crucial for calculating the width.  We assign indices such that the left child of a node at index `i` gets index `2i + 1`, and the right child gets `2i + 2`.  This ensures that nodes at the same level have consecutive indices, simplifying width calculation. The minimum index in each level is also tracked to handle potential gaps in indices due to missing nodes. Subtracting the minimum index from each node's index effectively normalizes the indices within each level, making width calculation straightforward.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`: Represents a node in the binary tree.
    * `Pair`: A custom class to store both the `TreeNode` and its index.
    * `Queue<Pair>`: A queue to implement BFS, storing `Pair` objects.

* **Algorithms:**
    * Breadth-First Search (BFS):  Traverses the tree level by level.


**4. Code Walkthrough:**

* **`Pair` class:** This inner class is a simple container to hold a `TreeNode` and its index.

* **`widthOfBinaryTree(TreeNode root)` function:**
    * **Base Case:** If the root is null, the width is 0.
    * **Initialization:** A queue `q` is created to store `Pair` objects, and `max` is initialized to 0 to keep track of the maximum width encountered so far. The root node, along with an index of 0, is added to the queue.
    * **BFS Loop:** The `while` loop iterates as long as the queue is not empty.  In each iteration:
        * `n` stores the number of nodes in the current level.
        * `minind` stores the minimum index in the current level, used for index normalization.
        * `l` and `f` represent the last and first indices in the current level after normalization.
        * The inner `for` loop processes each node in the current level.
            * `curind` calculates the normalized index of the current node.
            * `f` and `l` are updated to track the first and last normalized indices of the current level.
            * The left and right children (if they exist) are added to the queue with their calculated indices.
        * Finally, `max` is updated to the maximum width encountered so far.
    * **Return Value:** The function returns `max`, representing the maximum width of the binary tree.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. This is because each node is visited and processed exactly once during the BFS traversal.

* **Space Complexity:** O(W), where W is the maximum width of the tree. In the worst case (a complete binary tree), W could be proportional to N, making the space complexity O(N) in that scenario.  The space is primarily used by the queue to store nodes at each level.  The space used by other variables is constant.

In summary, this solution efficiently determines the maximum width of a binary tree using BFS and a clever indexing scheme to avoid complex width calculations at each level.  The use of a queue makes the BFS traversal very straightforward and easy to understand.
