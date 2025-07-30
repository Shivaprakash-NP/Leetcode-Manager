## LeetCode: Binary Tree Preorder Traversal - Solution Explained

**1. Problem Understanding:**

The problem asks us to perform a preorder traversal of a given binary tree and return the result as a list of integers.  Preorder traversal means visiting the root node first, then recursively traversing the left subtree, and finally the right subtree.

**2. Approach / Intuition:**

The solution employs a Depth-First Search (DFS) approach using recursion.  This is a natural fit for tree traversal problems because it directly reflects the hierarchical structure of the tree.  We recursively explore the tree, visiting nodes in the preorder sequence (Root, Left, Right).  A recursive solution is elegant and concise for this problem.  Iterative solutions using a stack are also possible, but the recursive approach is arguably easier to understand and implement in this case.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`:  A custom class representing a node in the binary tree.  It contains the node's value (`val`), and pointers to its left (`left`) and right (`right`) children.
    * `ArrayList<Integer>`:  Used to store and return the preorder traversal result.  `ArrayList` is chosen for its dynamic resizing capabilities.
* **Algorithms:**
    * **Depth-First Search (DFS):**  The core algorithm used to traverse the tree recursively.
    * **Recursion:**  The recursive `dfs` function efficiently explores the tree's branches.


**4. Code Walkthrough:**

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
    List<Integer> ans = new ArrayList<>(); //Initialize an ArrayList to store the result.
    private void dfs(TreeNode node) { //Recursive helper function for DFS
        if(node == null) return; //Base case: If the node is null, do nothing and return.
        ans.add(node.val); //Add the current node's value to the result list.
        dfs(node.left); //Recursively call dfs on the left subtree.
        dfs(node.right); //Recursively call dfs on the right subtree.
    }
    public List<Integer> preorderTraversal(TreeNode root) { //Main function to initiate the traversal
        dfs(root); //Call the recursive helper function starting from the root.
        return ans; //Return the ArrayList containing the preorder traversal result.
    }
}
```

* **`TreeNode` Definition:** This is a standard definition for a node in a binary tree, provided by LeetCode.

* **`ans` Variable:** An `ArrayList` named `ans` is initialized to store the preorder traversal sequence.  It's an instance variable, so it persists across recursive calls.

* **`dfs(TreeNode node)` function:** This is a recursive helper function.  It takes a `TreeNode` as input.
    * The base case `if (node == null) return;` handles the situation when we reach a null node (end of a branch).
    * `ans.add(node.val);` adds the current node's value to the `ans` list.
    * `dfs(node.left);` recursively calls `dfs` on the left subtree.
    * `dfs(node.right);` recursively calls `dfs` on the right subtree.  The order of these two recursive calls defines the preorder traversal.

* **`preorderTraversal(TreeNode root)` function:** This is the main function that initiates the preorder traversal. It simply calls the `dfs` function with the root node and returns the `ans` list.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  Each node is visited exactly once.

* **Space Complexity:** O(H) in the average case and O(N) in the worst case, where H is the height of the tree.  The space complexity is dominated by the recursive call stack. In the average case, the height of a balanced binary tree is log₂N. In the worst case (a skewed tree), the height can be N, leading to O(N) space complexity.  The `ans` ArrayList also contributes to space complexity, but its size is at most N, so it doesn't change the overall complexity.
