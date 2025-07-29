## LeetCode: Convert Sorted List to Binary Search Tree

**1. Problem Understanding:**

The problem asks us to convert a sorted singly linked list into a balanced binary search tree (BST).  Each node in the linked list represents a value, and the resulting BST should maintain the sorted order from the linked list.

**2. Approach / Intuition:**

The solution uses a recursive Depth-First Search (DFS) approach called `dfs`.  The core idea is to find the middle element of the linked list recursively. This middle element becomes the root of the BST. The left sub-list (elements before the middle) recursively forms the left subtree, and the right sub-list (elements after the middle) recursively forms the right subtree.  This approach ensures a relatively balanced BST, which is crucial for efficient search operations.  A simple recursive approach without finding the middle would potentially lead to a skewed tree.

**3. Data Structures and Algorithms:**

* **Data Structures:** Singly linked list (`ListNode`), Binary tree (`TreeNode`).
* **Algorithms:** Depth-First Search (DFS), linked list traversal, recursive tree construction.

**4. Code Walkthrough:**

* **`ListNode` and `TreeNode` definitions:** These are standard definitions for nodes in a singly linked list and a binary tree, respectively.  They're provided for completeness but aren't part of the core algorithm.

* **`dfs(ListNode head)`:** This recursive function performs the core conversion.
    * **Base Cases:** If `head` is `null` (empty list), it returns `null` (empty tree). If `head.next` is `null` (single-element list), it creates a `TreeNode` with the value and returns it.
    * **Finding the Middle:** It uses two pointers, `s` (slow) and `f` (fast).  `f` moves twice as fast as `s`. When `f` reaches the end, `s` points to the middle element. `p` is used to keep track of the node before `s` for list manipulation.
    * **Splitting the List:** After finding the middle, `p.next` is set to `null`, effectively splitting the linked list into two halves.
    * **Recursive Calls:** A `TreeNode` is created with the middle element's value.  Recursive calls to `dfs` are made with the left and right halves of the list to construct the left and right subtrees.
    * **Return Value:** The newly constructed `TreeNode` (root of the subtree) is returned.

* **`sortedListToBST(ListNode head)`:** This is the main function that simply calls `dfs` to initiate the conversion.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N). Finding the middle element in each recursive call takes O(N) time in the worst case (skewed list, although we mitigate this with slow/fast pointer).  Since the recursion depth is log N (balanced tree), the overall time complexity is O(N log N).  In a perfectly balanced scenario, it's closer to O(N).

* **Space Complexity:** O(log N). This is due to the recursive call stack. The recursion depth is proportional to the height of the balanced BST, which is log N.  The space used by the tree itself is O(N), but this isn't considered in space complexity analysis for recursive algorithms unless explicitly creating and storing the tree during the recursion which is not happening here.


**Potential Improvements/Edge Cases:**

The code is efficient and elegant. One minor improvement could be to add error handling for cases where the input linked list is not sorted (although the problem statement implies it is sorted).  The solution implicitly handles an empty list; no explicit check for this is needed.  No significant edge cases are overlooked in this implementation.
