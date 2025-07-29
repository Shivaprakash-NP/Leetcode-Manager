## LeetCode: Construct Binary Search Tree from Preorder Traversal

**1. Problem Understanding:**

The problem asks us to construct a Binary Search Tree (BST) from a given preorder traversal of that tree.  A preorder traversal visits the root node first, then recursively visits the left subtree, and finally the right subtree.  We need to reconstruct the original BST given only this preorder sequence.


**2. Approach / Intuition:**

The solution utilizes a recursive approach.  The key insight is that in a preorder traversal, the first element is always the root of the BST.  Because it's a BST, all nodes in the left subtree must be smaller than the root, and all nodes in the right subtree must be larger.  The algorithm leverages this property.

The `build` function recursively constructs the BST.  It takes the preorder array `A`, and an upper bound `ub`.  The upper bound helps to determine when to stop adding nodes to the right subtree (when a value exceeds the upper bound). If the current element in the preorder array is greater than the upper bound or we've reached the end of the array, we've finished building that subtree and return `null`. Otherwise, a new node is created with the current preorder element. Recursively calls itself to build the left subtree (with the root's value as the upper bound) and the right subtree (with the initially provided `ub` as the upper bound). This ensures that the left subtree only contains values less than the current node and the right subtree only contains values greater than the current node, within the constraints of the upper bound.

This approach is efficient because it directly builds the tree using the preorder traversal information without needing extra data structures like stacks or queues, which would increase time and space complexity.

**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is a `TreeNode` (representing nodes in the BST) and an array `A` to store the preorder traversal.
* **Algorithms:** The core algorithm is recursion.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, which is given and isn't part of the solution's core logic.

* **`i` variable:** This is a global variable used as an index to traverse the `preorder` array. It's incremented each time a node is processed.

* **`build(int[] A, int ub)` function:** This recursive function is the heart of the solution.
    * **Base Case:** `if(A.length == i || A[i]>ub) return null;` This checks for two conditions:  If the index `i` has reached the end of the array (`A.length == i`) or if the current element `A[i]` is greater than the upper bound `ub`, it means we've finished building the current subtree, so it returns `null`.
    * **Recursive Step:** `TreeNode node = new TreeNode(A[i++]);` Creates a new node with the current element from the preorder array and increments `i`.  Then it recursively calls `build` to construct the left subtree (`build(A, node.val)`) using the current node's value as the upper bound for the left subtree and then recursively calls itself to build the right subtree (`build(A, ub)`) using the initially provided upper bound.
    * **Return:** Finally, it returns the newly created `node`.

* **`bstFromPreorder(int[] preorder)` function:** This is the entry point of the solution. It simply initializes the recursive `build` function with the preorder array and `Integer.MAX_VALUE` as the initial upper bound.  `Integer.MAX_VALUE` ensures that initially, there is no upper limit on the values in the right subtree.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the preorder array. Each node is visited and processed exactly once during the recursive construction.

* **Space Complexity:** O(N) in the worst case (a skewed tree). This space is used for the recursion stack.  The height of the recursion stack is equal to the height of the tree. In a skewed tree, the height is N, leading to O(N) space. In a balanced tree, the height is log₂N, leading to O(log₂N) space. Therefore, the worst-case scenario dominates the space complexity analysis.


This solution is efficient and elegantly leverages the properties of preorder traversal and BSTs to construct the tree directly.  The use of recursion and the upper bound parameter make it concise and easy to understand.
