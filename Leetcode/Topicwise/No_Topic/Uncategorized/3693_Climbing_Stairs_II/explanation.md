## LeetCode Problem: Climbing Stairs II - Detailed Explanation

### 1. Problem Understanding:

The problem, rephrased, is: You are climbing a staircase with `n` steps. You can take steps of size 1, 2, or 3. Each step `i` has a cost associated with landing on it: `costs[i]`.  If you take a step of size 1, the cost is `costs[i] + 1`. If you take a step of size 2, the cost is `costs[i+1] + 4`. If you take a step of size 3, the cost is `costs[i+2] + 9`.  You start at step 0. Find the minimum total cost to reach the top of the staircase (which we can consider as reaching `n`).

### 2. Approach / Intuition:

The problem screams for a dynamic programming solution because it involves finding the minimum cost among multiple overlapping subproblems. The intuition is that the minimum cost to reach step `i` can be found by considering the minimum cost to reach step `i-1`, `i-2`, or `i-3` and adding the corresponding cost of taking a step from those positions.

*   **Overlapping Subproblems:** The cost to reach step 5, for example, depends on the costs to reach steps 2, 3, and 4.  Calculating these independently multiple times would be inefficient.
*   **Optimal Substructure:** The optimal (minimum) cost to reach step `i` is composed of the optimal (minimum) costs to reach steps `i-1`, `i-2`, and `i-3` plus the costs of taking the final step from those positions.

Therefore, a top-down (recursive with memoization) dynamic programming approach is suitable here. We use a `dp` array to store the minimum cost to reach each step and avoid recalculating these values.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `int[] dp`: An array of integers to store the minimum cost to reach each step.  `dp[i]` represents the minimum cost to reach step `i`. It's initialized with `-1` to indicate that the cost to reach each step hasn't been calculated yet.
*   **Algorithm:** Dynamic Programming (Top-Down/Memoization)
    *   **Recursion:** The `rec` function recursively explores the possible paths to reach the top.
    *   **Memoization:** The `dp` array is used to store the results of subproblems, preventing redundant calculations. If `dp[i]` is not `-1`, it means the minimum cost to reach step `i` has already been calculated, so we can simply return the stored value.

### 4. Code Walkthrough:

```java
class Solution {
    private int rec(int i, int[] cost, int[] dp) {
        if(i == cost.length) return 0; // Base case: Reached the top, cost is 0
        if(dp[i] != -1) return dp[i]; // Memoization: Return stored value if already calculated

        int a = cost[i]+1+rec(i+1, cost, dp); // Cost of taking a step of size 1
        int b = Integer.MAX_VALUE;
        int c = Integer.MAX_VALUE;
        if(i+1 < cost.length) b = cost[i+1]+4+rec(i+2, cost, dp); // Cost of taking a step of size 2
        if(i+2 < cost.length) c = cost[i+2]+9+rec(i+3, cost, dp); // Cost of taking a step of size 3

        return dp[i] = Math.min(a, Math.min(b, c)); // Store and return the minimum cost
    }

    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n+1]; // Initialize dp array
        Arrays.fill(dp, -1); // Fill with -1 to indicate uncalculated values
        return rec(0, costs, dp); // Start the recursion from step 0
    }
}
```

*   **`climbStairs(int n, int[] costs)` function:**
    *   Takes the number of steps `n` and the cost array `costs` as input.
    *   Creates the `dp` array of size `n+1`.  Note: size n+1 is crucial as the base case for `rec` is when i == cost.length, and `cost.length` is `n`.
    *   Initializes all elements of the `dp` array to `-1`.
    *   Calls the `rec` function starting from step 0 (`rec(0, costs, dp)`) and returns the result.

*   **`rec(int i, int[] cost, int[] dp)` function:**
    *   **Base Case:** `if (i == cost.length) return 0;`  If `i` is equal to `cost.length`, it means we've reached the top (or the end of the array). The cost to reach the top from the top is 0.
    *   **Memoization:** `if (dp[i] != -1) return dp[i];` If the minimum cost to reach step `i` has already been calculated (i.e., `dp[i]` is not `-1`), return the stored value directly. This avoids redundant calculations.
    *   **Calculate costs for each step size:**
        *   `a = cost[i] + 1 + rec(i + 1, cost, dp);`:  Calculates the cost of taking a step of size 1 from step `i`. It adds the cost of the current step `cost[i]`, the cost of taking a step of size 1 (+1), and the minimum cost to reach the top from step `i + 1` (obtained recursively).
        *   `b = Integer.MAX_VALUE;` and `c = Integer.MAX_VALUE;` These are initialized to prevent incorrect `min` calculations if the next steps are out of bounds.
        *   `if (i + 1 < cost.length) b = cost[i + 1] + 4 + rec(i + 2, cost, dp);`:  Calculates the cost of taking a step of size 2 from step `i`. It adds the cost of the next step `cost[i+1]`, the cost of taking a step of size 2 (+4), and the minimum cost to reach the top from step `i + 2` (obtained recursively). This is only calculated if `i+1` is within the bounds of `costs`.
        *   `if (i + 2 < cost.length) c = cost[i + 2] + 9 + rec(i + 3, cost, dp);`:  Calculates the cost of taking a step of size 3 from step `i`. It adds the cost of the step after the next one, `cost[i+2]`, the cost of taking a step of size 3 (+9), and the minimum cost to reach the top from step `i + 3` (obtained recursively). This is only calculated if `i+2` is within the bounds of `costs`.
    *   **Store and Return the Minimum Cost:** `return dp[i] = Math.min(a, Math.min(b, c));` Calculates the minimum cost among taking steps of size 1, 2, and 3. It stores this minimum cost in `dp[i]` and returns it.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where `n` is the number of steps.  The `rec` function is called at most `n+1` times because of memoization. Each `rec` call takes constant time (O(1)) to calculate the minimum cost among three possibilities. Thus, the overall time complexity is O(n).
*   **Space Complexity:** O(n), where `n` is the number of steps. This is due to the `dp` array, which stores the minimum cost to reach each step.  The recursion depth is also at most n, but it is dominated by the size of the `dp` array.
