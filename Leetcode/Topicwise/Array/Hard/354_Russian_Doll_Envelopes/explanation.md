### Problem Understanding

The "Russian Doll Envelopes" problem asks us to find the maximum number of envelopes that can be nested one inside another, forming a "Russian doll" chain. We are given a list of envelopes, where each envelope is represented by its `[width, height]`. An envelope `(w1, h1)` can be placed inside another envelope `(w2, h2)` only if `w1 < w2` AND `h1 < h2`. We need to find the longest possible sequence of envelopes that satisfy this nesting condition.

For example, if we have `[[5,4],[6,4],[6,7],[2,3]]`:
*   `(2,3)` can go into `(5,4)` because `2 < 5` and `3 < 4`.
*   `(5,4)` can go into `(6,7)` because `5 < 6` and `4 < 7`.
*   So, `(2,3) -> (5,4) -> (6,7)` is a valid chain of length 3.
*   `(6,4)` cannot go into `(6,7)` because `6 < 6` is false.
*   The maximum length is 3.

This problem is a variation of the Longest Increasing Subsequence (LIS) problem, extended to two dimensions.

### Approach / Intuition

The core idea is to transform the 2D LIS problem into a 1D LIS problem using a clever sorting strategy.

1.  **Sorting Strategy:**
    *   First, sort the envelopes primarily by their `width` in ascending order. This ensures that when we iterate through the sorted envelopes, the widths are non-decreasing.
    *   If two envelopes have the same `width`, sort them by their `height` in *descending* order. This is the crucial trick.

    **Why this specific sort?**
    *   If we have `(w1, h1)` and `(w2, h2)` with `w1 < w2`, then `(w1, h1)` can potentially fit inside `(w2, h2)` if `h1 < h2`.
    *   If `w1 = w2`, then `(w1, h1)` *cannot* fit inside `(w2, h2)` because the strict inequality `w1 < w2` is not met. By sorting heights in *descending* order for equal widths, say we have `(3, 5)` and `(3, 4)`: they will be sorted as `(3, 5), (3, 4)`. When we later apply the 1D LIS algorithm on heights, `5` will be processed before `4`. If `5` is part of an LIS, `4` will not be able to extend that LIS (since `4 < 5`), effectively preventing `(3, 4)` from being considered "after" `(3, 5)` in a valid nesting chain. This correctly handles the "strict inequality" requirement for width.

2.  **Longest Increasing Subsequence (LIS) on Heights:**
    After sorting, the problem reduces to finding the LIS of the `heights` of the envelopes. Because we've handled the width condition through sorting, we just need to find the longest sequence of increasing heights.

    The standard efficient algorithm for LIS uses a dynamic programming array (let's call it `dp` here) and binary search:
    *   `dp[i]` stores the smallest ending element of an increasing subsequence of length `i+1`.
    *   We iterate through the heights of the sorted envelopes. For each height `h`:
        *   We perform a binary search on the `dp` array to find the smallest element that is greater than or equal to `h`.
        *   If `h` is greater than all elements currently in `dp` (meaning it extends the longest subsequence found so far), we append `h` to `dp` and increment the maximum length.
        *   Otherwise, `h` replaces the element found by binary search. This is because `h` allows us to form an increasing subsequence of the same length but ending with a smaller value, which might enable a longer subsequence later.

The final length of the `dp` array (or the `len` variable tracking it) will be the maximum number of Russian doll envelopes.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[][] envelopes`: The input array storing width and height pairs.
    *   `int[] dp`: An array used to store the smallest tail of all increasing subsequences of length `i+1`. This is a crucial part of the LIS algorithm.
*   **Algorithms:**
    *   **Sorting:** `Arrays.sort()` with a custom `Comparator` to implement the specific sorting strategy (width ascending, then height descending for equal widths).
    *   **Binary Search:** Used within the loop to efficiently find the correct position for each height in the `dp` array.
    *   **Dynamic Programming (implicit):** The `dp` array and its update logic form the basis of the O(N log N) LIS algorithm.

### Code Walkthrough

```java
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // dp[i] will store the smallest ending height of an increasing subsequence of length i+1.
        // We use n+1 size to make indexing simpler (dp[1] for length 1, dp[2] for length 2, etc.)
        int[] dp = new int[n+1];
        
        // Initialize dp array with a very large value.
        // This ensures any actual height will be smaller and can replace these values.
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // dp[0] is a sentinel value. An LIS of length 1 can always start with any height h,
        // as h will be greater than Integer.MIN_VALUE.
        dp[0] = Integer.MIN_VALUE;

        // Sort the envelopes.
        // Primary sort key: width (ascending).
        // Secondary sort key (if widths are equal): height (descending).
        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0]; // Sort by width ascending
            return b[1] - a[1]; // If widths are equal, sort by height descending
        });

        // 'len' tracks the current maximum length of the LIS found so far.
        // It also indicates the next available index in 'dp' for a new longest subsequence.
        int len = 0; 

        // Iterate through each sorted envelope.
        for(int[] env : envelopes) {
            int h = env[1]; // Get the height of the current envelope.

            // Binary search to find the correct position for 'h' in the 'dp' array.
            // We are looking for the smallest element in dp that is >= h.
            int l = 0; // Lower bound for binary search (inclusive)
            int r = len; // Upper bound for binary search (exclusive, represents current effective length)

            while(l < r) {
                int m = l + (r-l)/2; // Calculate middle index
                if(dp[m] < h) {
                    // If dp[m] is smaller than h, it means h can extend the subsequence ending at dp[m].
                    // We need to look for a position further to the right.
                    l = m+1;
                } else {
                    // If dp[m] is greater than or equal to h, it means h can potentially replace dp[m]
                    // (or an element to its left) to form a subsequence of the same length but ending
                    // with