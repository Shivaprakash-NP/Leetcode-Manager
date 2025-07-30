# LeetCode: Search in a Binary Search Tree

## 1. Problem Understanding

The problem asks us to implement a function that searches for a specific value (`val`) within a given Binary Search Tree (BST) and returns the node containing that value if found. If the value is not present in the BST, the function should return `null`.


## 2. Approach / Intuition

This problem leverages the fundamental property of a Binary Search Tree:  for any node, all values in its left subtree are smaller, and all values in its right subtree are larger.  This allows us to perform an efficient search using a iterative approach. We start at the root and compare the target value with the current node's value.

* If the target value is equal to the current node's value, we've found the node and return it.
* If the target value is less than the current node's value, we move to the left subtree (because smaller values are on the left).
* If the target value is greater than the current node's value, we move to the right subtree (because larger values are on the right).

This process continues until either we find the target value or we reach a `null` node (meaning the value is not in the tree).  An iterative approach is chosen over recursion for potential efficiency gains, especially with very deep trees (recursion might hit stack overflow limits in extreme cases).


## 3. Data Structures and Algorithms

* **Data Structure:** Binary Search Tree (`TreeNode`)
* **Algorithm:** Iterative Tree Traversal (similar to Depth-First Search but guided by the BST property)


## 4. Code Walkthrough

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode demo = root; // Assign the root to a working variable
        while(demo != null) { // Iterate while we haven't reached the end of a branch
            if(demo.val == val) return demo; // Found the value, return the node
            else if(demo.val < val) demo = demo.right; // Value is larger, go right
            else demo = demo.left; // Value is smaller, go left
        }
        return demo; // Value not found, return null (demo will be null at this point)
    }
}
```

The code is concise and straightforward.  The `while` loop efficiently traverses the BST. The use of `demo` as a working variable avoids repeated access of the `root` variable within the loop, improving readability and potentially performance in some scenarios.


## 5. Time and Space Complexity

* **Time Complexity:** O(H), where H is the height of the Binary Search Tree. In the worst case (a skewed tree), H can be equal to N (number of nodes), resulting in O(N) time complexity. In the best case (a balanced tree), H is log₂(N), resulting in O(log₂(N)) time complexity.

* **Space Complexity:** O(1).  The algorithm uses a constant amount of extra space regardless of the input tree size.  This is because we are only using a single variable (`demo`) to track the current node during traversal.  The iterative approach avoids the recursive call stack space that a recursive solution might use.
