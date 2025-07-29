## LeetCode: Binary Tree Cameras - Solution Explained

**1. Problem Understanding:**

The problem asks us to determine the minimum number of cameras needed to monitor all nodes in a binary tree. A camera can monitor itself, its parent, and its children.  We need to find the placement of cameras that ensures every node is either a camera or covered by a camera.


**2. Approach / Intuition:**

This solution employs a clever post-order traversal approach with a recursive helper function (`solve`).  Instead of directly placing cameras, it uses a state-based system to track the status of each node:

* **0:** Node is not covered by a camera (needs to be monitored).
* **1:** Node is covered by a camera in its child or parent.
* **2:** Node has a camera.

The recursive function `solve` propagates this status upwards. If a node's children are uncovered (0), a camera must be placed on that node (incrementing `ans` and returning 2).  If a child has a camera (2), the parent is covered (returning 1).  Otherwise, the node is uncovered (returning 0). This bottom-up propagation efficiently determines the minimum camera placements.


**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure is the binary tree itself (`TreeNode`).  The integer variable `ans` acts as a counter for the total number of cameras.
* **Algorithms:** The core algorithm is a post-order traversal of the binary tree using recursion.  This ensures that the status of children is known before processing the parent.


**4. Code Walkthrough:**

* **`TreeNode` Class:** This is a standard definition for a node in a binary tree.
* **`solve(TreeNode node)`:** This recursive function is the heart of the algorithm.
    * **Base Case:** If `node` is `null`, it means we've reached the end of a branch; return 1 (implicitly covered).
    * **Recursive Calls:** It recursively calls `solve` on the left and right children to get their statuses (`l` and `r`).
    * **Camera Placement:** If either `l` or `r` is 0 (uncovered), a camera is needed at the current node.  `ans` is incremented, and 2 is returned to signify the camera presence.
    * **Covered Node:** If either `l` or `r` is 2 (has a camera), the current node is covered, so 1 is returned.
    * **Uncovered Node:** Otherwise, the current node is uncovered (0 is returned).
* **`minCameraCover(TreeNode root)`:** This function initializes the `ans` variable to 0 and then calls `solve` on the root node. If `solve(root)` returns 0, it means the root itself is uncovered, and an additional camera is needed at the root. Finally, it returns the total number of cameras (`ans`).


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree. This is because the `solve` function visits each node exactly once during the post-order traversal.
* **Space Complexity:** O(H), where H is the height of the tree.  This is due to the recursive calls on the call stack. In the worst-case scenario (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  However, for balanced trees, H is log(N), making the space complexity O(log N).


**In summary:**  The solution elegantly uses a post-order traversal and a concise state representation (0, 1, 2) to efficiently determine the minimum number of cameras required to cover the entire binary tree. The recursive approach is both clear and efficient for this problem.
