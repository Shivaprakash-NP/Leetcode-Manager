## LeetCode: Ways to Express an Integer as Sum of Powers (Detailed Explanation)

**1. Problem Understanding:**

The problem asks us to find the number of ways to express a given integer `n` as a sum of powers of integers, where each integer is raised to the power `x`.  For example, if `n = 10` and `x = 2`, we want to find how many ways we can represent 10 as a sum of perfect squares (1² + 3² = 10 is one way, but 10 = 10¹ is not allowed since it must be powers of integers greater than 0).  The solution should handle the possibility of a very large number of ways, hence the use of modulo operations to prevent integer overflow.


**2. Approach / Intuition:**

The solution employs a recursive approach with memoization (dynamic programming) to efficiently count the ways.  The core idea is to explore two possibilities for each integer `i` (starting from 1):

* **Take:** Include `i<sup>x</sup>` in the sum.  This recursively calls the function with the remaining sum (`rem - i<sup>x</sup>`) and increments `i`.
* **Skip:** Exclude `i<sup>x</sup>` from the sum. This recursively calls the function with the same remaining sum (`rem`) but increments `i`.

Memoization (using the `dp` array) stores the results of subproblems to avoid redundant calculations, significantly improving efficiency.  This top-down dynamic programming approach is well-suited for this problem because it naturally breaks down the problem into overlapping subproblems.

**3. Data Structures and Algorithms:**

* **Data Structures:** A 2D array (`dp`) is used for memoization.  It stores the number of ways to express a remaining sum (`rem`) using integers starting from `n` and raised to the power `x`.
* **Algorithms:**  Recursion with memoization (dynamic programming) is the core algorithm.  The `Math.pow()` function is used to calculate powers.

**4. Code Walkthrough:**

* **`MOD = 1000000007;`:** Defines a constant for modulo operation to prevent integer overflow.

* **`rec(rem, n, x, dp)`:** This is the recursive helper function.
    * **`if(rem == 0) return 1;`:** Base case: If the remaining sum is 0, it means we found a valid way to express the original number, so we return 1.
    * **`if(rem < 0) return 0;`:** Base case: If the remaining sum is negative, it means we've exceeded the original number, so we return 0.
    * **`long p = (long) Math.pow(n, x);`:** Calculates `n<sup>x</sup>`. Casting to `long` prevents potential integer overflow.
    * **`if((int)p > rem) return 0;`:**  If `n<sup>x</sup>` is greater than the remaining sum, we can't include it, so we return 0.
    * **`if(dp[rem][n] != -1) return dp[rem][n];`:** Checks if the subproblem has already been solved. If yes, return the stored result.
    * **`long take = rec(rem-(int)p, n+1, x, dp);`:** Recursively calls the function after including `n<sup>x</sup>` in the sum.
    * **`long skip = rec(rem, n+1, x, dp);`:** Recursively calls the function after skipping `n<sup>x</sup>`.
    * **`return dp[rem][n] = (take + skip)%MOD;`:** Stores and returns the total number of ways (modulo `MOD`).

* **`numberOfWays(n, x)`:** This is the main function.
    * **`long[][] dp = new long[n+1][n+1];`:** Initializes the memoization array.
    * **`for(int i = 0; i<=n; i++) Arrays.fill(dp[i], -1L);`:** Fills the array with -1 to indicate that subproblems haven't been solved yet.
    * **`return (int)rec(n, 1, x, dp) % MOD;`:** Calls the recursive helper function with the initial values and returns the result (modulo `MOD`).


**5. Time and Space Complexity:**

* **Time Complexity:** O(n<sup>2</sup>), where n is the input integer.  The recursive function explores at most n levels, and at each level, it makes at most 2 recursive calls. However, due to memoization, each subproblem is solved only once. The time spent on calculating powers is negligible compared to the recursive calls.

* **Space Complexity:** O(n<sup>2</sup>). This is dominated by the `dp` array, which has dimensions (n+1) x (n+1). The call stack for recursion can also contribute to space complexity in the worst case, but in this case, it's bounded by the depth of recursion, which is at most n.  Therefore, the space complexity is primarily determined by the size of the memoization table.


The use of memoization significantly reduces the time complexity from an exponential complexity (without memoization) to a polynomial complexity.  Without memoization, the same subproblems would be recalculated multiple times, leading to a much slower solution.
