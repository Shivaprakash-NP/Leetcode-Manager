### Problem Understanding

The problem "Delete and Earn" presents an array of integers `nums`. Our goal is to maximize the total points earned by performing a specific operation. The operation is:
1.  Choose any `nums[i]` from the array.
2.  Earn `nums[i]` points.
3.  *Crucially*, after earning points for `nums[i]`, you must delete *every* occurrence of `nums[i] - 1` and *every* occurrence of `nums[i] + 1` from the array.
This process is repeated until no more numbers can be chosen.

In simpler terms, if you decide to take a number `x`, you get all `x`'s present in the array, but then you cannot take any `x-1` or `x+1`. You want to find the maximum total points you can accumulate.

### Approach / Intuition

The core constraint "if you take `x`, you cannot take `x-1` or `x+1`" immediately brings to mind a classic dynamic programming problem: the House Robber problem. In House Robber, you cannot rob adjacent houses. Here, you cannot "rob" adjacent *numbers*.

Let's break down the intuition:

1.  **Pre-processing for Efficiency:** The problem states "if you choose `nums[i]`, you earn `nums[i]` points". If there are multiple occurrences of the same number, say `x`, and you decide to "take" `x`, you effectively earn `x` for *each* `x` present. For example, if `nums = [3, 3, 3, 4]`, and you decide to take `3`, you earn `3+3+3 = 9` points. Then you must delete all `2`s and `4`s. This suggests that we should first consolidate the points for each distinct number. We can create an auxiliary array (or map) where the index represents a number, and its value stores the *total* points you would earn if you decided to take that number.

    *   **Example:** `nums = [2, 2, 3, 3, 3, 4]`
        *   The maximum number is `4`.
        *   Create an array `point_sums` of size `max_val + 1`.
        *   `point_sums[2] = 2 + 2 = 4` (If we take 2, we get 4 points)
        *   `point_sums[3] = 3 + 3 + 3 = 9` (If we take 3, we get 9 points)
        *   `point_sums[4] = 4` (If we take 4, we get 4 points)
        *   All other `point_sums[i]` would be 0.

2.  **Dynamic Programming (House Robber Analogy):** Now, the problem transforms into: given an array `point_sums`, where `point_sums[i]` is the total value you get if you pick number `i`, and picking `i` forbids picking `i-1` and `i+1`, find the maximum total value.
    *   Let `dp[i]` be the maximum points we can earn considering numbers up to `i`.
    *   To calculate `dp[i]`, we have two choices for number `i`:
        *   **Choice 1: Don't take number `i`.** If we don't take `i`, our maximum points are simply the maximum points we could earn considering numbers up to `i-1`. So, `dp[i] = dp[i-1]`.
        *   **Choice 2: Take number `i`.** If we take `i`, we earn `point_sums[i]`. Since we took `i`, we *cannot* take `i-1`. Therefore, we must add `point_sums[i]` to the maximum points earned considering numbers up to `i-2`. So, `dp[i] = point_sums[i] + dp[i-2]`.
    *   Combining these, `dp[i] = Math.max(dp[i-1], point_sums[i] + dp[i-2])`.

3.  **Space Optimization:** Notice that `dp[i]` only depends on the two previous `dp` values (`dp[i-1]` and `dp[i-2]`). This allows us to optimize the space complexity from `O(max_val)` to `O(1)` by only storing these two previous values.
    *   We can use `cur` to represent `dp[i-1]` (the maximum points considering numbers up to the previous one).
    *   We can use `pre` to represent `dp[i-2]` (the maximum points considering numbers up to two previous ones).
    *   In each iteration, we calculate a new `val` (which would be `dp[i]`). Then, `pre` becomes the old `cur`, and `cur` becomes the new `val`.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] nums`: The input array.
    *   `int[] point`: An auxiliary array used to store the aggregated points for each number. Its size is determined by the maximum value in `nums`.
*   **Algorithms:**
    *   Dynamic Programming: Specifically, a space-optimized variant of the House Robber problem.
    *   Iteration: Used for finding the maximum value, populating the `point` array, and executing the DP recurrence.

### Code Walkthrough

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        // 1. Find the maximum number in the input array.
        // This step is crucial to determine the required size of our 'point' array.
        // The 'point' array will map each number 'v' to the total points earned if 'v' is chosen.
        int max = 0;
        for(int v : nums) {
            max = Math.max(max, v); // Update 'max' if 'v' is larger.
        }

        // 2. Create and populate the 'point' array.
        // 'point[i]' will store the sum of all occurrences of number 'i' from 'nums'.
        // The array size is 'max + 1' to accommodate numbers from 0 up to 'max'.
        // (Note: problem constraints usually state positive numbers, so point[0] will likely remain 0).
        int[] point = new int[max + 