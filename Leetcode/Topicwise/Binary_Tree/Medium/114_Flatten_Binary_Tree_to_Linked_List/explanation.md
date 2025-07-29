# LeetCode: Flatten Binary Tree to Linked List - Solution Explained

## 1. Problem Understanding

The problem asks us to convert a given binary tree into a linked list (specifically, a right-sided linked list).  This means that after the transformation, the original tree structure is lost, and all nodes are connected only through their right children, forming a singly linked list. The order of nodes in the linked list should be the same as a pre-order traversal of the original tree (though reversed in the right branch).

## 2. Approach / Intuition

The solution uses a recursive Depth-First Search (DFS) approach. The core idea is to recursively flatten the right subtree first, then the left subtree, and finally connect the current node to the previously processed node (`pre`).  This ensures that nodes are added to the linked list in the desired reverse pre-order traversal.

We chose a recursive DFS approach because it elegantly handles the hierarchical nature of the binary tree. Recursion naturally explores each subtree until it reaches the leaves, and the `pre` variable keeps track of the last processed node, making the connection to form the linked list straightforward. Iterative solutions are possible but often more complex to implement and understand.

## 3. Data Structures and Algorithms

* **Data Structures:** The primary data structure is the binary tree itself, represented using `TreeNode` objects.  We also implicitly use a call stack during the recursive DFS.
* **Algorithms:** The core algorithm is a Depth-First Search (DFS) traversal of the binary tree, implemented recursively.


## 4. Code Walkthrough

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, containing an integer value (`val`) and left and right child pointers (`left` and `right`).

* **`Solution` class:** This class contains the `flatten` method, which orchestrates the flattening process.

* **`pre` variable:** This static variable acts as a pointer to the previously processed node. It's crucial for linking nodes in the linked list. It is initially `null`.

* **`dfs(TreeNode node)` method:** This recursive method performs the core flattening logic:
    * **Base Case:** `if(node == null) return;`  Handles the case when we reach a null node (end of a branch).
    * **Recursive Calls:** `dfs(node.right); dfs(node.left);` This ensures that the right subtree is flattened first, followed by the left subtree. This order is crucial for the reverse pre-order traversal.
    * **Linking Nodes:** `node.right = pre; node.left = null;`  After processing the subtrees, the current node's right child is set to the `pre` node (the previously processed node), effectively linking it to the linked list.  The left child is set to `null` to maintain the linked list structure.
    * **Update `pre`:** `pre = node;` The `pre` variable is updated to point to the current node, so it will be the previous node for the next recursive call.


* **`flatten(TreeNode root)` method:** This is the main method that initiates the flattening process by calling the `dfs` method with the root node. It handles the edge case of an empty tree.

## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  Each node is visited exactly once during the DFS traversal.

* **Space Complexity:** O(H) in the average case, where H is the height of the binary tree. This is due to the recursive call stack. In the worst-case scenario (a skewed tree), the height can be equal to N, resulting in O(N) space complexity.  However, the space used by the `pre` variable is constant, O(1).

In summary, this solution efficiently flattens the binary tree into a linked list using a recursive DFS approach, achieving optimal time complexity and reasonable space complexity for most cases.  The recursive nature provides a clean and concise solution, while the use of the `pre` variable cleverly handles the linking of nodes.
