## LeetCode: Most Frequent Subtree Sum - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the most frequent subtree sum in a given binary tree.  A subtree sum is the sum of all node values within a subtree rooted at a particular node.  The output should be an array of all subtree sums that occur with the maximum frequency.

**2. Approach / Intuition:**

The solution employs a Depth-First Search (DFS) approach combined with a HashMap to efficiently track subtree sums and their frequencies.

* **DFS Traversal:**  We use a recursive DFS function (`dfs`) to traverse the tree.  For each node, it calculates the subtree sum by recursively computing the sums of its left and right subtrees and adding the node's value.

* **HashMap for Frequency Counting:** A HashMap (`map`) stores each unique subtree sum as a key and its frequency (count) as the value.  The `getOrDefault` method efficiently handles cases where a sum is encountered for the first time.

* **Post-Order Processing:** The DFS function updates the frequency count in the HashMap *after* recursively processing the left and right subtrees. This ensures we have the correct sum for each subtree before updating the frequency.

* **Finding the Maximum Frequency:** After the DFS traversal, we iterate through the HashMap to find the maximum frequency. We build a list of subtree sums that have this maximum frequency.

This approach is efficient because DFS systematically explores the entire tree, and the HashMap provides O(1) average-case lookup, insertion, and deletion, making frequency counting very fast.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `HashMap`: To store subtree sums and their frequencies.  Provides efficient lookups, insertions, and deletions.
    * `ArrayList`: To temporarily store the subtree sums with the maximum frequency before converting to an array.
    * `TreeNode`:  The standard definition for a node in a binary tree.

* **Algorithms:**
    * **Depth-First Search (DFS):**  A recursive algorithm for traversing the tree.
    * **HashMap Operations:**  `getOrDefault`, `put`


**4. Code Walkthrough:**

* **`TreeNode` class:**  A standard definition for a node in a binary tree; not part of the algorithm's logic.

* **`dfs(TreeNode node)`:**
    * **Base Case:** If `node` is null (empty subtree), it returns 0.
    * **Recursive Calls:** Recursively calls `dfs` on the left and right subtrees to get their sums (`l` and `r`).
    * **Subtree Sum Calculation:** Calculates the current subtree's sum (`sub_sum`).
    * **Frequency Update:** Updates the frequency of `sub_sum` in the `map`.  `map.getOrDefault(sub_sum, 0)` safely handles cases where the sum hasn't been seen before.
    * **Return Value:** Returns the `sub_sum` to the calling function.  This is crucial for the parent nodes to correctly calculate their subtree sum.


* **`findFrequentTreeSum(TreeNode root)`:**
    * **DFS Initialization:** Calls `dfs(root)` to start the traversal and populate the `map`.
    * **Frequency Analysis:** Iterates through the `map` to find the maximum frequency (`max`). It keeps track of all sums with that frequency in `ans`.
    * **Result Conversion:** Converts the `ArrayList` `ans` into an integer array using streams for efficient conversion.  This is a concise way to return the result in the required format.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the tree.  The DFS traversal visits each node exactly once.  The final iteration through the HashMap is also proportional to the number of unique subtree sums, which is at most N.

* **Space Complexity:** O(N) in the worst case. The HashMap can store up to N unique subtree sums.  The recursive call stack during DFS can also reach a depth of N in a skewed tree, but this is typically considered part of the overall space complexity.  The `ArrayList` `ans` requires space proportional to the number of elements with the maximum frequency (at most N). Therefore, the overall space complexity remains O(N).
