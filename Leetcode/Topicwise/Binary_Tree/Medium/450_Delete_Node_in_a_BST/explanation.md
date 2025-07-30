## LeetCode: Delete Node in a BST - Solution Explanation

**1. Problem Understanding:**

The problem asks us to implement a function that deletes a node with a given key from a Binary Search Tree (BST).  The function should handle all possible cases, including deleting nodes with zero, one, or two children.  The function should return the root of the modified BST.

**2. Approach / Intuition:**

The solution uses a recursive helper function (`helper`) and an iterative approach in the main `deleteNode` function.  The core logic revolves around finding the node to be deleted and then handling the three cases:

* **Node with zero children:** Simply remove the node.
* **Node with one child:** Replace the node with its child.
* **Node with two children:** Find the inorder successor (the smallest node in the right subtree) or inorder predecessor (the largest node in the left subtree), replace the node's value with the successor's/predecessor's value, and then recursively delete the successor/predecessor node.

This approach is chosen because it efficiently handles all scenarios and maintains the BST property after deletion.  The iterative approach in `deleteNode` helps to reduce the space complexity compared to a fully recursive solution for traversing the tree to locate the node.  The `helper` function is used to encapsulate the logic for deleting a node with one or two children, making the code cleaner and more readable.


**3. Data Structures and Algorithms:**

* **Data Structure:** Binary Search Tree (BST).
* **Algorithm:** Tree traversal (inorder traversal implicitly in the helper function to find the inorder successor), iterative search.


**4. Code Walkthrough:**

* **`TreeNode` class:** This is a standard definition for a node in a binary tree.

* **`helper(TreeNode node)`:** This recursive function handles the actual deletion of a node.
    * `if(node.left == null) return node.right;`: If the node has only a right child, return the right child.
    * `if(node.right == null) return node.left;`: If the node has only a left child, return the left child.
    * `TreeNode tem = node.left; while(tem.right != null) tem = tem.right;`: This finds the inorder successor (rightmost node in the left subtree).
    * `tem.right = node.right;`:  The successor's right child becomes the original node's right child.
    * `return node.left;`: The left subtree (which now includes the successor and the original node's right subtree) becomes the new subtree.


* **`deleteNode(TreeNode root, int key)`:** This function finds the node to delete and calls the `helper` function.
    * `if(root == null) return root;`: Handles the empty tree case.
    * `if(root.val == key) return helper(root);`: If the root is the node to delete, call `helper`.
    * `TreeNode node = root; while(node != null)`: This loop iterates through the tree to find the node with the given `key`.
    * `if(node.val > key)`: If the current node's value is greater than the `key`, the node to delete is in the left subtree.
    * `if(node.left != null && node.left.val == key) node.left = helper(node.left);`: If the node is found in the left subtree, delete it using `helper` and update the left child of the current node.
    * `else node = node.left;`: Otherwise, move to the left subtree.  Similar logic applies for the `else` block (right subtree).
    * `return root;`: Returns the root of the modified BST.

**5. Time and Space Complexity:**

* **Time Complexity:** O(H), where H is the height of the BST. In the worst case (a skewed tree), H can be equal to N (number of nodes), resulting in O(N) time complexity.  However, for a balanced BST, H is log₂N, resulting in O(log₂N) time complexity.  The search for the node and the operations within `helper` (finding the successor and updating pointers) all take at most O(H) time.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space, regardless of the size of the tree. The recursion depth in `helper` is at most O(H), but this is not considered in space complexity analysis because it's the function call stack space used by recursive calls (not additional data structures). The iterative approach in `deleteNode` avoids creating a stack data structure that would otherwise lead to O(H) space in a fully recursive solution.

In summary, this solution efficiently deletes nodes from a BST while maintaining its properties. The use of both iterative and recursive components provides a balanced approach for optimal performance in terms of time and space complexity, especially for balanced BSTs.
