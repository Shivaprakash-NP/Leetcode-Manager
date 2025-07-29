# Lowest Common Ancestor of a Binary Search Tree - LeetCode Solution Explained

**1. Problem Understanding:**

The problem asks us to find the lowest common ancestor (LCA) of two nodes, `p` and `q`, in a given Binary Search Tree (BST). The LCA is the lowest node in the tree that has both `p` and `q` as descendants (either in its left or right subtree).

**2. Approach / Intuition:**

This solution leverages the crucial property of a BST:  the values in the left subtree are smaller than the root's value, and the values in the right subtree are larger.  We can use this property to efficiently navigate the tree.

The algorithm iteratively moves down the tree.  If both `p` and `q` are smaller than the current node's value, the LCA must be in the left subtree. Conversely, if both are larger, it's in the right subtree.  If one is smaller and the other is larger, then the current node *is* the LCA because it's the lowest node encompassing both.

This approach is chosen because it's highly efficient, requiring only a single pass down the tree.  Recursive solutions are possible, but this iterative approach avoids the overhead of function calls and is arguably more readable.


**3. Data Structures and Algorithms:**

* **Data Structure:**  The primary data structure is the binary search tree itself, represented using `TreeNode` objects.
* **Algorithm:** The algorithm uses an iterative approach based on a `while` loop and conditional statements to traverse the BST.  No specialized algorithms like depth-first search (DFS) or breadth-first search (BFS) are explicitly used, as the BST's properties guide the traversal.


**4. Code Walkthrough:**

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) { // Continue until we reach the end of the tree (null)
            if(root.val > p.val && root.val > q.val) root = root.left; //Both p and q are smaller, go left
            else if(root.val < p.val && root.val < q.val) root = root.right; //Both p and q are larger, go right
            else return root; // One is smaller and one is larger, current node is LCA
        }
        return root; //Should not reach here if p and q are present in the tree.  Returns null if not found.
    }
}
```

- **`while(root != null)`:** This loop continues as long as we haven't reached the end of a branch in the tree (represented by `null`).
- **`if(root.val > p.val && root.val > q.val)`:** This condition checks if both `p` and `q` are smaller than the current node's value. If true, the LCA must lie in the left subtree, so we move to the left child (`root = root.left`).
- **`else if(root.val < p.val && root.val < q.val)`:** This condition checks if both `p` and `q` are larger than the current node's value. If true, the LCA must lie in the right subtree, so we move to the right child (`root = root.right`).
- **`else return root;`:** If neither of the above conditions is true, it means one of `p` and `q` is smaller than `root.val` and the other is larger.  This implies the current node `root` is the LCA, so it's returned.
- **`return root;`:** This line is technically unreachable if both `p` and `q` are guaranteed to be present in the BST. However, it's good practice to include it to handle potential edge cases (though a more robust solution might explicitly handle cases where p or q is not found).


**5. Time and Space Complexity:**

* **Time Complexity:** O(H), where H is the height of the BST. In the worst case (a skewed tree), H can be equal to N (the number of nodes), resulting in O(N) time complexity. However, for a balanced BST, H is log₂(N), leading to O(log N) time complexity.  The algorithm makes at most one pass down the tree.
* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space regardless of the input size.  It only uses a few variables to store node references and values.  There's no recursion stack build-up as in a recursive solution.
