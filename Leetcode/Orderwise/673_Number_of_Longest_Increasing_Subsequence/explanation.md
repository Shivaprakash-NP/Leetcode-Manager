### Problem Understanding

The problem "Number of Longest Increasing Subsequence" asks us to find the *count* of all possible longest increasing subsequences (LIS) within a given array of integers `nums`. An increasing subsequence is a sequence of numbers from the original array that are in strictly increasing order. We need to find the maximum possible length of such a subsequence, and then count how many distinct subsequences achieve that maximum length.

For example, if `nums = [1, 3, 5, 4, 7]`:
*   LIS length is 4.
*   The LISs are `[1, 3, 5, 7]` and `[1, 3, 4, 7]`.
*   The count of LIS is 2.

### Approach / Intuition

This problem is a variation of the classic Longest Increasing Subsequence problem, which is typically solved using dynamic programming. To find the *number* of LIS, we need to extend the standard DP approach to not only track the length but also the count of subsequences achieving that length.

The core idea is to iterate through the array `nums` and for each element `nums[i]`, determine the length of the longest increasing subsequence *ending at `nums[i]`* and the *number* of such subsequences.

We'll use two DP arrays:

1.  `dp[i]`: Stores the length of the longest increasing subsequence ending at `nums[i]`.
2.  `cnt[i]`: Stores the number of longest increasing subsequences ending at `nums[i]`.

**Initialization:**
Every single number `nums[i]` itself forms an increasing subsequence of length 1. So, we initialize `dp[i] = 1` and `cnt[i] = 1` for all `i`.

**Iteration:**
For each `nums[i]` (starting from `i=0` to `n-1`):
We look at all previous elements `nums[j]` where `j < i`.
If `nums[j] < nums[i]`, it means `nums[i]` can potentially extend an increasing subsequence that ends at `nums[j]`.

When `nums[j] < nums[i]`:
*   **Case 1: `dp[j] + 1 > dp[i]`**
    This means we found a *new, longer* increasing subsequence ending at `nums[i]`.
    The new length for `dp[i]` becomes `dp[j] + 1`.
    Since this is a new longest length for subsequences ending at `nums[i]`, the count `cnt[i]` should now be equal to `cnt[j]`, because all the LISs ending at `nums[j]` can now be extended by `nums[i]` to form this new longest length.
*   **Case 2: `dp[j] + 1 == dp[i]`**
    This means we found another increasing subsequence ending at `nums[i]` that has the *same length* as the current `dp[i]`.
    In this scenario, we add `cnt[j]` to `cnt[i]`. This is because the `cnt[j]` subsequences ending at `nums[j]` can also be extended by `nums[i]` to form subsequences of this length, contributing to the total count for `dp[i]`.
*   **Case 3: `dp[j] + 1 < dp[i]`**
    This path does not yield a longer or equally long increasing subsequence ending at `nums[i]`. We simply ignore it for updating `cnt[i]`. However, `dp[i]` is always updated to `Math.max(dp[i], dp[j] + 1)` to ensure it stores the true maximum length.

**Final Calculation:**
After filling both `dp` and `cnt` arrays, we need to find the overall maximum length `max` among all values in the `dp` array.
Finally, we iterate through the `dp` array one last time. For every index `i` where `dp[i]` is equal to this overall `max` length, we add `cnt[i]` to our total answer. This sums up the counts of all LISs, regardless of where they end.

### Data Structures and Algorithms

1.  **Arrays:**
    *   `nums`: The input array.
    *   `dp`: An integer array to store the length of the longest increasing subsequence ending at each index.
    *   `cnt`: An integer array to store the number of longest increasing subsequences ending at each index.
2.  **Dynamic Programming:** The solution uses a bottom-up dynamic programming approach to build up solutions for subproblems (LIS ending at `i`) and combine them to solve the main problem.
3.  **Nested Loops:** Two nested loops are used to iterate through all possible pairs `(j, i)` where `j < i`, which is characteristic of many DP problems involving subsequences.

### Code Walkthrough

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i] stores the length of the longest increasing subsequence ending at nums[i]
        int[] dp = new int[n]; // Using n instead of n+1 as indices 0 to n-1 are used
        // cnt[i] stores the number of longest increasing subsequences ending at nums[i]
        int[] cnt = new int[n]; // Using n instead of n+1

        // Initialize dp and cnt arrays.
        // Each number itself forms an LIS of length 1, and there's 1 such LIS.
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);

        // Main DP loop: Iterate through each number nums[i]
        // to determine LIS length and count ending at nums[i]
        for(int i = 0; i < n; i++) {
            // Inner loop: Check all previous numbers nums[j] (where j < i)
            for(int j = 0; j < i; j++) {
                // If nums[j] is smaller than nums[i], then nums[i] can extend an LIS ending at nums[j]
                if(nums[j] < nums[i]) {
                    // Scenario 1: Found a NEW LONGER LIS ending at nums[i]
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1; // Update length
                        cnt[i] = cnt[j];   // The count becomes the count of LISs ending at nums[j]
                                           // because all those LISs are now extended by nums[i]
                    }
                    // Scenario 2: Found an LIS of the SAME LENGTH ending at nums[i]
                    else if(dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];  // Add the count of LISs ending at nums[j]
                                           // to the current count for nums[i]
                    }
                    // Note: dp[i] is implicitly updated by the first if condition.
                    // The line below is redundant if `dp[j]+1 > dp[i]` is handled first,
                    // but harmless as it just takes the max.
                    // A more concise way to handle dp[i] update would be:
                    // dp[i] = Math.max(dp[i], dp[j] + 1);
                    // and then handle cnt[i] based on the comparison.
                    // The current code structure correctly updates dp[i] in the first 'if'
                    // and then the 'else if' for cnt[i] is based on the *current* dp[i] value.
                    // However, the `dp[i] = Math.max(dp[i], dp[j] + 1);` line at the end of the if block
                    // ensures dp[i] is always the max length, even if it wasn't updated in the first 'if'
                    // (which it would have been) or if the length was shorter (which it wouldn't be).
                    // Let's analyze the original structure:
                    // if(dp[j]+1 > dp[i]) { cnt[i] = cnt[j]; dp[i] = dp[j] + 1; }
                    // else if(dp[j]+1 == dp[i]) { cnt[i] += cnt[j]; }
                    // This is the correct logic. The line `dp[i] = Math.max(dp[i], dp[j] + 1);`
                    // at the end of the `if(nums[j]<nums[i])` block is slightly misplaced/redundant
                    // if `dp[i]` is already updated in the first `if`.
                    // A cleaner way is:
                    // int currentLen = dp[j] + 1;
                    // if (currentLen > dp[i]) {
                    //     dp[i] = currentLen;
                    //     cnt[i] = cnt[j];
                    // } else if (currentLen == dp[i]) {
                    //     cnt[i] += cnt[j];
                    // }
                    // The provided code's logic for `dp[i]` update is:
                    // `if(dp[j]+1 > dp[i]) cnt[i] = cnt[j];` (this doesn't update dp[i] yet)
                    // `if(dp[j]+1 == dp[i]) cnt[i] += cnt[j];`
                    // `dp[i] = Math.max(dp[i], dp[j] + 1);` (this line actually updates dp[i])
                    // This means `dp[i]` is always updated to the max, but `cnt[i]` logic relies on `dp[i]` *before* this `Math.max` update.
                    // Let's re-verify the provided code's `cnt[i]` logic with `dp[i]`'s update being last.
                    // Example: nums = [1, 3, 2]
                    // i=0: nums[0]=1. dp=[1], cnt=[1]
                    // i=1: nums[1]=3.
                    //   j=0: nums[0]=1 < nums[1]=3.
                    //     dp[0]+1=2. dp[1]=1.
                    //     `if(2 > 1)` is true. `cnt[1] = cnt[0]` -> `cnt[1]=1`.
                    //     `if(2 == 1)` is false.
                    //     `dp[1] = Math.max(1, 2)` -> `dp[1]=2`.
                    //   Result: dp=[1,2], cnt=[1,1]
                    // i=2: nums[2]=2.
                    //   j=0: nums[0]=1 < nums[2]=2.
                    //     dp[0]+1=2. dp[2]=1.
                    //     `if(2 > 1)` is true. `cnt[2] = cnt[0]` -> `cnt[2]=1`.
                    //     `if(2 == 1)` is false.
                    //     `dp[2] = Math.max(1, 2)` -> `dp[2]=2`.