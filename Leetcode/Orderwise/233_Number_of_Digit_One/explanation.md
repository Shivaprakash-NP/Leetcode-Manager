### Problem Understanding

The problem "Number of Digit One" asks us to count the total occurrences of the digit '1' in all non-negative integers up to a given integer `n`. For example, if `n = 13`, we need to count the '1's in numbers like 1, 10, 11, 12, 13.
*   1 contains one '1'.
*   2-9 contain zero '1's.
*   10 contains one '1'.
*   11 contains two '1's.
*   12 contains one '1'.
*   13 contains one '1'.
The total count for `n=13` would be 1 + 1 + 2 + 1 + 1 = 6.

### Approach / Intuition

This problem is a classic application of **Digit Dynamic Programming (Digit DP)**. The core idea of Digit DP is to count numbers (or properties of numbers) within a given range by building them digit by digit from left to right, while using memoization to store results of subproblems.

Here's the intuition behind this specific solution:

1.  **Represent `n` as a String:** It's easier to work with digits if `n` is converted to a string (`s`). This allows us to access individual digits by index.
2.  **Recursive Exploration (DFS):** We use a recursive function (`dfs`) to explore all possible numbers from `0` up to `n`. This function will determine what digit can be placed at the current position and then recursively call itself for the next position.
3.  **Key Parameters for Digit DP:**
    *   `n` (in `dfs`): Represents the number of digits remaining to be placed. When `n` becomes 0, it means a full number has been constructed.
    *   `tight`: A boolean flag (represented as `0` or `1`).
        *   If `tight` is `1` (true), it means we are currently restricted by the digits of the original number `s`. The digit we place at the current position cannot exceed the corresponding digit in `s`.
        *   If `tight` is `0` (false), it means at some previous position, we placed a digit strictly smaller than the corresponding digit in `s`. Therefore, we are no longer restricted by `s` and can place any digit from `0` to `9` for the remaining positions.
    *   `count`: This parameter is specific to *this* problem. It keeps track of how many '1's have been encountered so far in the number being built. When `n` becomes 0 (base case), this `count` represents the number of '1's in the fully formed number.
4.  **Memoization (DP Table):** To avoid redundant computations, we use a 3D DP table (`dp`). `dp[n_remaining][current_count_of_ones][tight_flag]` stores the sum of '1's that can be generated from this state onwards. If a state has already been computed, we simply return its stored value.
5.  **Building Numbers Digit by Digit:**
    *   In each `dfs` call, we determine the `upper_bound (ub)` for the current digit. If `tight` is true, `ub` is the digit from `s` at the current position; otherwise, `ub` is `9`.
    *   We then loop from `i = 0` to `ub`, trying each possible digit `i` for the current position.
    *   For each `i`, we make a recursive call:
        *   `n-1`: One less digit remaining.
        *   Update `tight`: It remains `1` only if the current `tight` was `1` AND we chose the `ub` digit. Otherwise, it becomes `0`.
        *   Update `count`: If `i` is `1`, increment `count`; otherwise, keep it the same.
    *   The results from all these recursive calls are summed up to get the total count of '1's for the current state.

The initial call to `dfs` starts with `s.length()` digits remaining, `tight=1` (as we are initially restricted by `n`), and `count=0` (no '1's counted yet).

### Data Structures and Algorithms

*   **Data Structures:**
    *   `String s`: Stores the input integer `n` as a string to facilitate digit-by-digit processing.
    *   `int[][][] dp`: A 3-dimensional array used for memoization.
        *   `dp[n_remaining][current_count_of_ones][tight_flag]`
        *   `n_remaining`: Index corresponds to the number of digits yet to be placed (from 0 to `s.length()`).
        *   `current_count_of_ones`: Index corresponds to the count of '1's accumulated so far (from 0 to `s.length()`).
        *   `tight_flag`: Index for the boolean `tight` constraint (0 or 1).

*   **Algorithms:**
    *   **Recursion with Memoization (Top-down Dynamic Programming):** The `dfs` function is a recursive approach that explores all possibilities. The `dp` array stores the results of subproblems, preventing redundant calculations and converting the exponential recursive solution into a polynomial one.
    *   **Digit DP:** This is a specialized dynamic programming technique used for problems that involve counting numbers (or their properties) within a range by analyzing their digits.

### Code Walkthrough

```java
class Solution {
    // dfs(s, n_remaining, tight_flag, current_count_of_ones, dp_table)
    private int dfs(String s, int n, int tight, int count, int[][][] dp) {
        // Base Case: If 'n' is 0, it means we have successfully placed all digits
        // for a number. The 'count' parameter now holds the total number of '1's
        // in this fully formed number. We return this count.
        if(n == 0) return count;

        // Memoization Check: If the result for the current state (n, count, tight)
        // has already been computed and stored in the DP table, return it directly.
        // -1 is used as an indicator for uncomputed states.
        if(dp[n][count][tight] != -1) return dp[n][count][tight];

        int ans = 0; // Initialize the answer for the current state

        // Determine the upper bound (ub) for the digit we can place at the current position.
        // The current position corresponds to s.length() - n in the string 's'.
        // If 'tight' is 1, we are restricted by the digit in 's' at this position.
        // s.charAt(s.length()-n) gets the character, -