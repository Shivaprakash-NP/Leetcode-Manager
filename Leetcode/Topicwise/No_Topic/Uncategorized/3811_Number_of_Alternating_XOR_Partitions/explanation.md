### Problem Understanding

The problem asks us to find the number of ways to partition an array `nums` into contiguous subarrays such that the XOR sum of these subarrays alternates between two given `target1` and `target2`. Specifically, the first subarray's XOR sum must be `target1`, the second's `target2`, the third's `target1`, and so on. Every element in `nums` must belong to exactly one partition. We need to return the count modulo `10^9 + 7`.

### Approach / Intuition

This problem has characteristics that strongly suggest a dynamic programming approach, specifically using recursion with memoization (top-down DP):

1.  **Optimal Substructure:** The solution to partitioning the entire array depends on solutions to partitioning