# LeetCode: Distribute Coins in Binary Tree - Detailed Explanation

## 1. Problem Understanding

The problem asks us to determine the minimum number of moves required to distribute coins in a binary tree such that each node has exactly one coin.  We can move one coin from a node to one of its adjacent nodes in a single move.

## 2. Approach / Intuition

The solution employs a Depth-First Search (DFS) traversal to efficiently count the necessary moves.  The core idea is to track the net flow of coins at each node.  Instead of explicitly moving coins, we calculate the difference between the coins at a node and the desired number (1).  This difference represents the number of coins that need to be moved to or from that node.

The `dfs` function recursively explores the tree.  For each node:

1. It recursively calculates the net coin flow from the left and right subtrees.
2. It decrements the node's coin count by 1 (to account for the coin the node should keep).
3. It adds the coin flow from its children to its own coin count.
4. It updates the global `ans` by the absolute value of the node's net coin difference (representing the number of moves needed for that node).
5. Finally, it returns the net coin flow from the current node (including the coin the node keeps) to be passed up to its parent.

This approach avoids explicitly simulating coin movements, making it significantly more efficient than a brute-force approach.

## 3. Data Structures and Algorithms

* **Data Structure:** The primary data structure is the binary tree itself (`TreeNode`).
* **Algorithm:** Depth-First Search (DFS) is used to traverse the tree and efficiently calculate the net coin flow at each node.


## 4. Code Walkthrough

```java
class Solution {
    int ans = 0; // Global variable to store the total number of moves

    private int dfs(TreeNode node) {
        if(node == null) return 0; // Base case: empty subtree, no coin flow

        int l = dfs(node.left); // Recursive call for the left subtree
        int r = dfs(node.right); // Recursive call for the right subtree

        node.val--; // Decrement the node's coin count by 1 (keeping one for itself)
        node.val += (l + r); // Add the net coin flow from children
        ans += Math.abs(node.val); // Update the total moves (absolute difference from 0)

        return node.val; // Return the net coin flow from this node
    }

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans; // Return the total number of moves
    }
}
```


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree. This is because the DFS function visits each node exactly once.

* **Space Complexity:** O(H), where H is the height of the binary tree.  This is due to the recursive call stack used by the DFS function. In the worst case (a skewed tree), H can be equal to N.  In the best case (a balanced tree), H is log₂N.  Therefore, the space complexity is O(H) which is O(N) in the worst case and O(logN) in the best case.

In summary, this solution provides an efficient and elegant way to solve the "Distribute Coins in Binary Tree" problem using DFS and a clever approach of tracking net coin flow instead of explicitly moving coins. The use of recursion allows for a concise and easy-to-understand implementation.
