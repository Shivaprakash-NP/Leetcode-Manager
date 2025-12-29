### Problem Understanding

The problem "Integer Break" asks us to take a given positive integer `n` (where `n >= 2`) and break it into the sum of at least two positive integers. Our goal is to find a way to break `n` such that the product of these integers is maximized. We need to return this maximum possible product.

For example:
*   If `n = 2`, the only way to break it into at least two positive integers is `1 + 1`. The product is `1 * 1 = 1`.
*   If `n = 10`, we could break it as `1 + 2 + 3 + 4` (product `24`), `2 + 2 + 2 + 2 + 2` (product `32`), `3 + 3 + 4` (product `36`), or `3 + 3 + 2 + 2` (product `36`), etc. The maximum product turns out to be `36` (from `3 + 3 + 4`).

### Approach / Intuition

This problem exhibits characteristics of Dynamic Programming:
1.  **Optimal Substructure:** The optimal solution for `n` can be derived from the optimal solutions of smaller integers. If we break `n` into `j` and `n-j`, the maximum product for `n` will involve `j` multiplied by the best possible product for `n-j`.
2.  **Overlapping Subproblems:** When calculating the maximum product for different `n` values, we might repeatedly need the maximum product for the same smaller integers.

**Core Logic:**

Let `dp[i]` represent the maximum product we can achieve by breaking the integer `i` into at least two positive integers.

To calculate `dp[i]`, we consider all possible ways to make the first cut. Suppose we break `i` into two parts: `j` and `i-j`, where `j` ranges from `1` to `i-1`.

For each such split `(j, i-j)`, we have two main options for the second part (`i-j`):

1.  **Treat `i-j` as a single, unbroken number:** In this case, the product for this split would be `j * (i-j)`. This is important because for small numbers (like 2 or 3), keeping them as a single number might yield a larger product than breaking them further (e.g., `2` is better than `1*1=1`, `3` is better than `1*2=2`).
2.  **Break `i-j` further to get its maximum possible product:** In this case, the product for this split would be `j * dp[i-j]`. Here, `dp[i-j]` already stores the maximum product obtained by breaking `i-j` into at least two parts.

For each `i`, `dp[i]` will be the maximum product found across all possible `j` splits, considering both options mentioned above for the `i-j` part. We initialize `dp[i]` to 0 and update it with the maximum product found.

The base cases are handled implicitly by the loop structure and the `j*(i-j)` term. For `i=1`, the inner loop doesn't run, `dp[1]` remains 0. For `i=2`, `j=1`. We compare `1*dp[1]` (which is `1*0=0`) with `1*(2-1)` (which is `1*1=1`). So `dp[2]` becomes `1`. This is correct, as `2` can only be broken into `1+1` with product `1`.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] dp`: An array of integers used to store the maximum product for each number from `1` to `n`. `dp[i]` stores the maximum product for integer `i`.
*   **Algorithms:**
    *   **Dynamic Programming:** Specifically, a bottom-up approach where we build up solutions for `i` based on previously computed solutions for smaller numbers.

### Code Walkthrough

```java
class Solution {
    public int integerBreak(int n) {
        // 1. Initialize DP array
        // dp[i] will store the maximum product obtainable by breaking integer i.
        // Array size n+1 to store results for numbers from 0 to n.
        int[] dp = new int[n+1];

        // 2. Outer loop: Iterate for each number from 1 to n
        // 'i' represents the current number for which we are calculating the max product.
        // Note: The problem states n >= 2, but we start i from 1 to handle subproblems like dp[1].
        for(int i = 1; i<=n; i++) {
            // 3. Inner loop: Iterate through all possible first parts 'j'
            // We try to break 'i' into two parts: 'j' and 'i-j'.
            // 'j' goes from 1 up to i-1 to ensure both parts are positive and 'i' is broken.
            for(int j = 1; j<i; j++) {
                // 4. DP Transition: Calculate and update dp[i]
                // For the current split (j, i-j):
                //   - j * dp[i-j]: Product if we break 'i-j' further optimally.
                //     dp[i-j] contains the max product for breaking 'i-j' into at least two parts.
                //   - j * (i-j): Product if we treat 'i-j' as a single, unbroken number.
                //     This is crucial for small values of (i-j) where not breaking it (e.g., 2 itself vs 1*1)
                //     yields a better product.
                // We take the maximum of these two options for the current 'j'.
                // Then, we take the maximum between the current dp[i] (max product found so far for 'i')
                // and the product obtained from the current split (j, i-