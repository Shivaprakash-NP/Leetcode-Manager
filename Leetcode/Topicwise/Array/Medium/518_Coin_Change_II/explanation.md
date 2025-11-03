### Problem Understanding

The "Coin Change II" problem asks us to find the total number of *distinct combinations* of coins that can sum up to a given `amount`. We are provided with an integer `amount` and an array of integers `coins`, where each `coins[i]` represents a coin denomination. We can use an unlimited number of coins of any given denomination. The order of coins in a combination does not matter; for example, `[1, 2]` is considered the same combination as `[2, 1]`.

### Approach / Intuition

This problem is a classic dynamic programming (DP) problem, closely related to the unbounded knapsack or subset sum problem. The core idea is to build up a solution for the target `amount` by leveraging solutions for smaller sub-amounts.

Let `dp[i]` represent the number of distinct combinations of coins that sum up to `i`.

1.  **Base Case:**
    *   `dp[0] = 1`. There is exactly one way to make an amount of 0: by using no coins. This base case is crucial for the recurrence relation to correctly propagate counts.

2.  **Recurrence Relation:**
    *   When we consider a new `coin` denomination `c`, we want to update the `dp` array for all amounts `a` from `c` up to the total `amount`.
    *   For each `amount a`, if `a >= c`, we can form `a` by either:
        *   Not using the current `coin c` (these ways are already counted in `dp[a]` from previous coin iterations).
        *   Using at least one `coin c`. If we use one `coin c`, the remaining amount we need to make is `a - c`. The number of ways to make `a - c` (using the current coin `c` and all previously considered coins) is given by `dp[a - c]`.
    *   So, the recurrence relation becomes: `dp[a] = dp[a] + dp[a - c]`.

3.  **Order of Loops (Crucial for Combinations):**
    The order of iteration is critical to distinguish between combinations and permutations.
    *   **Outer loop: `for each coin c`**
    *   **Inner loop: `for each amount a from c to amount`**

    This specific order ensures that we count combinations. When we process a `coin c`, `dp[a - c]` already holds the number of ways to make `a - c` using *all coins considered so far, including `c`*. By adding `dp[a - c]` to `dp[a]`, we are effectively saying: "If we want to make `amount a` using `coin c`, we can take `