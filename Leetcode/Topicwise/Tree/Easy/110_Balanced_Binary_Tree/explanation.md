# LeetCode: Balanced Binary Tree - Solution Explained

## 1. Problem Understanding

The problem asks whether a given binary tree is "balanced."  A balanced binary tree is defined as a tree where for every node, the absolute difference between the heights of its left and right subtrees is at most 1, and the left and right subtrees are also balanced.


## 2. Approach / Intuition

The solution employs a post-order traversal approach using recursion.  Instead of simply checking the height difference at each node, it cleverly incorporates a "failure-fast" mechanism.  The `check` function recursively calculates the height of a subtree. If an imbalance is detected (height difference > 1) or an unbalanced subtree is found (indicated by a return value of -1 from a recursive call), it immediately returns -1 to propagate the failure upwards. This avoids unnecessary computations once an imbalance is discovered. This approach is efficient because it short-circuits the traversal as soon as an imbalance is detected.


## 3. Data Structures and Algorithms

* **Data Structure:** The problem utilizes a binary tree, represented using the `TreeNode` class.
* **Algorithm:** The core algorithm is a recursive post-order traversal of the binary tree. The recursive approach efficiently explores the tree's structure.


## 4. Code Walkthrough

Let's break down the Java code step-by-step:

* **`TreeNode` class:** This is a standard definition for a node in a binary tree, containing an integer value (`val`) and references to its left and right children (`left` and `right`).

* **`check(TreeNode node)` function:** This is the heart of the solution. It recursively checks the balance of a subtree rooted at `node`:
    * **Base Case:** If `node` is `null` (empty subtree), it returns 0 (height of an empty tree is 0).
    * **Recursive Calls:** It recursively calls `check` on the left and right subtrees to get their heights (`lh` and `rh`).
    * **Imbalance Check:** If either `lh` or `rh` is -1 (indicating an imbalance in a subtree), it immediately returns -1 to signal an imbalance. It also checks if the absolute difference between `lh` and `rh` is greater than 1, again returning -1 if an imbalance is found.
    * **Height Calculation:** If no imbalance is detected, it returns `1 + Math.max(lh, rh)`, representing the height of the current subtree (1 for the current node plus the maximum height of its children).


* **`isBalanced(TreeNode root)` function:** This is the main function that takes the root of the binary tree as input. It simply calls `check` on the root and returns `true` if `check` returns a value other than -1 (meaning the tree is balanced), and `false` otherwise.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the number of nodes in the binary tree.  In the worst case (a completely balanced tree), the algorithm visits each node once. The recursive calls explore each node exactly once.  The early termination upon detecting an imbalance doesn't change the overall time complexity in the worst case because even a completely balanced tree will need a visit to each node.

* **Space Complexity:** O(H), where H is the height of the binary tree. This is due to the recursive call stack. In the worst case (a skewed tree), H can be equal to N, leading to O(N) space complexity.  In a balanced tree, H is log₂(N), giving O(log N) space complexity.  Therefore, the space complexity is determined by the height of the tree, and it's best described as O(H) or O(min(N, log N)).
