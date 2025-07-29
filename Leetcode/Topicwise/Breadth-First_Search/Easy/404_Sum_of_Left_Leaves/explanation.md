# LeetCode: Sum of Left Leaves - Detailed Explanation

## 1. Problem Understanding

The problem asks us to calculate the sum of all the values of the leaf nodes that are located on the left side of their parent nodes in a given binary tree.  A leaf node is a node without any children (left or right).

## 2. Approach / Intuition

The most efficient approach is to use Depth-First Search (DFS) or, more specifically, a recursive Depth-First Search.  We traverse the tree recursively.  For each node, we check if its left child exists and is a leaf node. If it is, we add its value to the running sum.  We then recursively call the DFS function on both the left and right subtrees to explore the rest of the tree.  This approach directly addresses the problem's requirements without unnecessary computations or data structures.  Other approaches like Breadth-First Search (BFS) would work but introduce unnecessary overhead for this specific problem.

## 3. Data Structures and Algorithms

* **Data Structure:**  The problem uses a binary tree represented by the `TreeNode` class.
* **Algorithm:** Depth-First Search (DFS) is used for traversal.  The recursive nature of DFS elegantly handles the hierarchical structure of the binary tree.


## 4. Code Walkthrough

Let's break down the Java code step by step:

```java
/**
 * Definition for a binary tree node.
 * ... (TreeNode class definition) ...
 */
class Solution {
    int ans = 0; // Initialize the sum of left leaves to 0.  This is an instance variable to store the running sum across recursive calls.

    private void dfs(TreeNode node) { //Recursive DFS function
        if(node == null) return; // Base case: if the node is null, do nothing and return.

        if(node.left != null && node.left.left == null && node.left.right == null) { //Check if left child exists and is a leaf
            ans += node.left.val; //Add left child's value to the sum if it is a leaf.
        }

        dfs(node.left); // Recursive call on the left subtree
        dfs(node.right); // Recursive call on the right subtree
    }
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root); //Start the DFS traversal from the root node
        return ans; // Return the final sum of left leaves.
    }
}
```


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree. This is because each node is visited exactly once during the DFS traversal.

* **Space Complexity:** O(H), where H is the height of the binary tree. This is due to the space used by the recursive call stack. In the worst case (a skewed tree), H can be equal to N, resulting in O(N) space complexity.  In the best case (a balanced tree), H is log₂N, resulting in O(log₂N) space complexity.  The instance variable `ans` uses constant space.


In summary, the provided solution efficiently solves the "Sum of Left Leaves" problem using a recursive DFS approach with optimal time and space complexities for a binary tree traversal.  The code is concise and easy to understand, making it a good example of a well-structured solution to a tree-based problem.
