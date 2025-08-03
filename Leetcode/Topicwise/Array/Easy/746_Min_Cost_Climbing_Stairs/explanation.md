## LeetCode: Min Cost Climbing Stairs - Solution Explanation

**1. Problem Understanding:**

The "Min Cost Climbing Stairs" problem asks us to find the minimum cost to reach the top of a staircase.  Each step has an associated cost, and we can climb one or two steps at a time.  The top of the staircase is considered to be one step beyond the last step with an associated cost.  We need to determine the minimum total cost to reach the top.


**2. Approach / Intuition:**

This solution uses dynamic programming (DP). The core idea is to build a table (the `dp` array) where `dp[i]` stores the minimum cost to reach step `i`.  We can reach step `i` either from step `i-1` or step `i-2`. Therefore, the minimum cost to reach step `i` is the minimum of the costs to reach `i-1` and `i-2`, plus the cost of step `i` itself.  This forms a recursive relationship that we can solve iteratively using the DP table.  We choose DP because it avoids redundant calculations by storing and reusing previously computed results.  A recursive solution without memoization would have exponential time complexity.


**3. Data Structures and Algorithms:**

* **Data Structure:**  A 1D array (`dp`) is used to store the minimum cost to reach each step. This is the DP table.
* **Algorithm:** Dynamic Programming is the core algorithm used to solve this problem efficiently.


**4. Code Walkthrough:**

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n]; // Initialize DP array to store minimum costs
        dp[0] = cost[0];       // Base case: cost to reach step 0 is cost[0]
        dp[1] = cost[1];       // Base case: cost to reach step 1 is cost[1]

        for(int i = 2; i<n; i++) 
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i]; //DP recurrence relation

        return Math.min(dp[n-1], dp[n-2]); // Return min cost to reach the top (n-1 or n-2)
    }
}
```

* **`int n = cost.length;`**:  Gets the number of cost entries.
* **`int[] dp = new int[n];`**: Creates a DP array of size `n` to store the minimum costs to reach each step.
* **`dp[0] = cost[0];` and `dp[1] = cost[1];`**: Sets the base cases for the DP table. The minimum cost to reach step 0 is simply the cost of step 0, and similarly for step 1.
* **`for(int i = 2; i<n; i++) dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];`**: This is the core DP loop. For each step `i` (starting from 2), it calculates the minimum cost to reach that step by considering the minimum cost from either the previous step (`dp[i-1]`) or the step before that (`dp[i-2]`), and adding the cost of the current step (`cost[i]`).
* **`return Math.min(dp[n-1], dp[n-2]);`**:  Since we can reach the top (which is considered one step beyond the last step) from either the second-to-last step (`n-2`) or the last step (`n-1`), we return the minimum cost between these two options.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the number of steps. The DP loop iterates through the array once.
* **Space Complexity:** O(n).  We use a DP array of size n to store the minimum costs.  This could be optimized to O(1) using only three variables to keep track of the minimum costs of the last three steps.  However, this solution uses the more readable DP array approach.


This detailed explanation should provide a thorough understanding of the provided Java code for solving the "Min Cost Climbing Stairs" problem on LeetCode.
