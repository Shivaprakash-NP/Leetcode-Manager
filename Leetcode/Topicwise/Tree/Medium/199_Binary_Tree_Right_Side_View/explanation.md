# LeetCode: Binary Tree Right Side View - Detailed Solution Explanation

## 1. Problem Understanding

The problem "Binary Tree Right Side View" asks us to return a list containing the values of the nodes visible from the right side of a given binary tree.  In other words, we need to traverse the tree level by level and record the rightmost node's value at each level.


## 2. Approach / Intuition

The solution employs a Depth-First Search (DFS) approach, specifically a preorder traversal with a slight modification.  A level-order traversal (BFS) could also solve this, but DFS is often more concise for tree problems.  The key idea is to track the current level during the traversal. If we encounter a node at a level that hasn't been visited yet, it implies it's the rightmost node at that level (since we process the right subtree before the left). We add its value to the result list.  Prioritizing the right subtree in the recursion ensures we always capture the rightmost node first.

This approach is chosen because it's relatively efficient and avoids the need for a queue, which is typically associated with BFS. The recursive nature of DFS simplifies the code and naturally handles the level-by-level processing.


## 3. Data Structures and Algorithms

* **Data Structures:**
    * `List<Integer> ans`:  An ArrayList to store the values of the rightmost nodes at each level. This is our output.
    * `TreeNode`: The standard definition for a node in a binary tree.

* **Algorithms:**
    * Depth-First Search (DFS) - Preorder Traversal with modification:  A recursive algorithm that systematically explores the tree.
    * Level Tracking:  The `level` parameter in the `dfs` function keeps track of the current level during the traversal.


## 4. Code Walkthrough

```java
class Solution {
    List<Integer> ans = new ArrayList<>(); // Initialize the result list
    private void dfs(TreeNode node , int level) { // Recursive DFS function
        if(node ==  null) return; // Base case: If node is null, do nothing
        if(level == ans.size()) ans.add(node.val); // If we reach a new level, add the node's value
        dfs(node.right , level+1); // Recursively traverse the right subtree first
        dfs(node.left , level+1); // Then, traverse the left subtree
    }
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root , 0); // Start the DFS from the root at level 0
        return ans; // Return the list of rightmost node values
    }
}
```

**Detailed Breakdown:**

* `List<Integer> ans = new ArrayList<>();`:  This line initializes an ArrayList to store the right side view.
* `private void dfs(TreeNode node, int level)`: This is the recursive DFS function.
    * `if(node == null) return;`: This handles the base case where the node is null.
    * `if(level == ans.size()) ans.add(node.val);`: This is the crucial part. If the current `level` is equal to the size of `ans`, it means we've reached a new level for the first time.  Therefore, the current node (`node.val`) must be the rightmost node at that level because of the order of recursive calls.  We add its value to `ans`.
    * `dfs(node.right, level + 1);`: Recursively call `dfs` on the right subtree, incrementing the level. This ensures the right subtree is explored first.
    * `dfs(node.left, level + 1);`: Recursively call `dfs` on the left subtree, also incrementing the level.
* `public List<Integer> rightSideView(TreeNode root)`: This is the main function that initializes the DFS and returns the result.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  We visit each node exactly once.

* **Space Complexity:** O(H) in the worst case, where H is the height of the tree. This is due to the recursive call stack. In the worst-case scenario (a skewed tree), the height can be equal to N. In a balanced tree, H would be log₂N, making the space complexity O(log₂N).  The space used by `ans` is O(H) as well, since the maximum number of levels is equal to the height of the tree.  Therefore, the dominant factor is the recursive call stack's space usage.
