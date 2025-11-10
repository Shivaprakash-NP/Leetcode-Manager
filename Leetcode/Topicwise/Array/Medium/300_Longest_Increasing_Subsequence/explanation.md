### Problem Understanding

The "Longest Increasing Subsequence" (LIS) problem asks us to find the length of the longest subsequence of a given array of integers such that all elements of the subsequence are in strictly increasing order. A subsequence is formed by deleting zero or more elements from the original array without changing the relative order of the remaining elements.

For example, given `nums = [10, 9, 2, 5, 3, 7, 101, 18]`:
An increasing subsequence could be `[2, 3, 7, 101]`, which has a length of 4.
Another could be `[2, 5, 7, 101]`, also length 4.
The problem asks for the *length* of the longest such subsequence.

### Approach / Intuition

The problem exhibits characteristics of optimal substructure and overlapping subproblems, making it a classic candidate for dynamic programming.

The core idea is to consider each number in the input array `nums` one by one. For each number `nums[i]`, we have two choices:
1.  **Include `nums[i]` in the Longest Increasing Subsequence:** We can only do this if `nums[i]` is strictly greater than the *last element we picked* for our current increasing subsequence. If we pick `nums[i]`, it extends the current subsequence by one, and `nums[i]` becomes the new "last picked element".
2.  **Exclude `nums[i]` from the Longest Increasing Subsequence:** We simply move on to the next number `nums[i+1]` without adding `nums[i]` to our current subsequence. The "last picked element" remains the same.

We want to maximize the length, so we will take the maximum of the lengths resulting from these two choices.

This decision process naturally leads to a recursive solution. To avoid redundant computations for overlapping subproblems, we use memoization (a top-down dynamic programming approach).

**State Definition for Memoization:**
A recursive function `rec(i, pre)` would typically represent the length of the LIS considering elements from index `i` onwards, given that the *previous element included in the LIS* was at index `pre`.

*   `i`: The current index we are considering in the `nums` array.
*   `pre`: The index of the element that was *last included* in our increasing subsequence. This is crucial because `nums[i]` can only be picked if `nums[i] > nums[pre]`. We use `pre = -1` to signify that no element has been picked yet (i.e., `nums[i]` would be the first element of the LIS).

The `dp` table `dp[i][pre+1]` will store the result of `rec(i, pre)`. We use `pre+1` as the column index because `pre` can be -1, and array indices cannot be negative. So, `pre = -1` maps to `dp` column `0`, `pre = 0` maps to `dp` column `1`, and so on.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] nums`: The input array containing the numbers.
    *   `int[][] dp`: A 2D array used for memoization. `dp[i][j]` stores the length of the LIS considering elements from index `i` onwards, where `j-1` is the index of the previously picked element.
*   **Algorithms:**
    *   **Recursion:** The `rec` function calls itself to explore different choices.
    *   **Memoization (Top-down Dynamic Programming):** Storing the results of subproblems in the `dp` table to avoid recomputing them.

### Code Walkthrough

```java
class Solution {
    // Recursive function with memoization
    private int rec(int i, int[] nums, int pre, int[][] dp) {
        int n = nums.length; // Get the total number of elements

        // Base case: If we have considered all elements (i.e., current index 'i' is out of bounds)
        // no more elements can be added to the LIS, so return 0.
        if(i >= n) return 0;

        // Memoization check: If this state (i, pre) has already been computed,
        // return the stored result directly.
        // pre+1 is used because 'pre' can be -1, and array indices must be non-negative.
        if(dp[i][pre+1] != -1) return dp[i][pre+1];

        // Option 1: Do NOT pick nums[i]
        // We move to the next element (i+1) without changing the 'pre' element.
        int nopick = rec(i+1, nums, pre, dp);

        // Option 2: PICK nums[i]
        int pick = 0; // Initialize pick option length to 0

        // Condition for picking:
        // 1. 'pre == -1': This means no element has been picked yet, so nums[i] can be the first element of the LIS.
        // 2. 'nums[pre] < nums[i]': nums[i] must be strictly greater than the previously picked element to maintain increasing order.
        if(pre == -1 || nums[pre] < nums[i]) {
            // If we pick nums[i], the LIS length increases by 1,
            // and 'i' becomes the new 'pre' (previous element index) for subsequent calls.
            pick = 1 + rec(i+1, nums, i, dp);
        }

        // Store the maximum of the two options (pick or nopick) in the DP table
        // and return it. This is the optimal length for the current state (i, pre).
        return dp[i][pre+1] = Math.max(pick, nopick);
    }

    // Main function to find the length of LIS
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // Initialize the DP table.
        // Rows: 'n' for current index 'i' (0 to n-1).
        // Columns: 'n+1' for 'pre+1'. 'pre' can range from -1 to n-1.
        // So 'pre+1' ranges from 0 to n.
        int[][] dp = new int[n][n+1];

        // Fill the DP table with -1 to indicate that states are not yet computed.
        for(int[] d : dp) Arrays.fill(d, -1);

        // Start the recursion from the first element (index 0),
        // with no previous element chosen yet (-1).
        return rec(0, nums, -1, dp);
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(n^2)**
    *   The `dp` table has `n` rows (for the `i` parameter, representing the current index) and `n+1` columns (for the `pre+1` parameter, representing the index of the previously chosen element). This means there are `O(n * n)` unique states.
    *   Each state `(i, pre)` is computed exactly once due to memoization.
    *   Inside the `rec` function, for each state, we perform constant time operations (comparisons, array lookups, `Math.max`).
    *   Therefore, the total time complexity is `O(n * n) = O(n^2)`.

*   **Space Complexity: O(n^2)**
    *   **DP Table:** The `dp` array takes `O(n * n)` space to store the results of the `n^2` states.
    *   **Recursion Stack:** In the worst case, the recursion depth can go up to `n` (when `i` iterates from `0` to `n-1`). This contributes `O(n)` to the space complexity.
    *   Combining these, the dominant factor is the `dp` table, so the total space complexity is `O(n^2)`.