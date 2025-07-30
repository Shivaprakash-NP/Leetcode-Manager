## LeetCode: Binary Tree Postorder Traversal - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to traverse a given binary tree and return a list containing the values of its nodes in postorder. Postorder traversal means visiting the left subtree, then the right subtree, and finally the root node itself.  This process is repeated recursively for every subtree within the main tree.


**2. Approach / Intuition:**

The chosen approach is a recursive Depth-First Search (DFS) using a helper function.  Postorder traversal inherently lends itself to recursion because the order of operations – left, right, then root – directly translates to recursive calls.  Iterative solutions are possible (using a stack), but recursion often provides a more concise and easier-to-understand solution for this specific problem, especially for beginners. This recursive approach elegantly mirrors the definition of postorder traversal.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `TreeNode`:  A custom class representing a node in the binary tree.  It contains an integer value (`val`) and references to its left and right children (`left` and `right`).
    * `ArrayList<Integer>`: Used to store the postorder traversal result.  `ArrayList` is chosen for its dynamic resizing capabilities.
* **Algorithms:**
    * **Depth-First Search (DFS):**  The core algorithm used for traversing the tree recursively.
    * **Recursion:** The fundamental technique employed to implement the DFS.


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
    List<Integer> ans = new ArrayList<>(); // Instance variable to store the result
    private void dfs(TreeNode node) { // Recursive helper function
        if(node == null) return; // Base case: if node is null, do nothing
        dfs(node.left); // Recursive call for the left subtree
        dfs(node.right); // Recursive call for the right subtree
        ans.add(node.val); // Add the current node's value to the result after processing children
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root); // Initiate the recursive traversal from the root
        return ans; // Return the list containing the postorder traversal
    }
}
```

* **`TreeNode` Definition:** This is a standard definition for a binary tree node in Java.  It's not part of the solution's logic but essential for defining the input structure.

* **`Solution` Class:**
    * `List<Integer> ans = new ArrayList<>();`: An instance variable to store the postorder traversal result.  It's initialized as an empty ArrayList. Using an instance variable avoids repeatedly creating new lists during each recursive call, thus improving efficiency.
    * `private void dfs(TreeNode node)`: This recursive helper function performs the postorder traversal.
        * `if(node == null) return;`: This is the base case of the recursion. If the current node is null, the function simply returns, stopping the recursion.
        * `dfs(node.left);`: Recursive call to traverse the left subtree.
        * `dfs(node.right);`: Recursive call to traverse the right subtree.
        * `ans.add(node.val);`: After processing both subtrees, the value of the current node is added to the `ans` list. This ensures the postorder traversal order (left, right, root).
    * `public List<Integer> postorderTraversal(TreeNode root)`: This is the main function that takes the root of the binary tree as input. It calls the `dfs` helper function to start the traversal and then returns the `ans` list.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  Each node is visited exactly once.

* **Space Complexity:** O(H) in the average case, where H is the height of the binary tree. This is due to the recursive call stack.  In the worst-case scenario (a skewed tree), the space complexity becomes O(N) because the height of the tree can be equal to N.  The `ans` ArrayList also contributes O(N) space, but this is dominated by the recursive call stack in a skewed tree, therefore we often only focus on the recursive stack space.

In summary, the provided code offers an efficient and elegant recursive solution to the Binary Tree Postorder Traversal problem.  The use of a helper function improves readability and organization.  The time and space complexities are optimal for this approach.
