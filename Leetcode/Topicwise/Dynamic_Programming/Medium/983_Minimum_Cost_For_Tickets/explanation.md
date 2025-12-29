### Problem Understanding

The problem "Minimum Cost For Tickets" asks us to find the minimum total cost to travel on a given set of days throughout a year. We are provided with an array `days` which lists the specific days we intend to travel, and an array `costs` which contains the prices for three types of passes: a 1-day pass, a 7-day pass, and a 30-day pass. When a pass is bought on a certain day `d`, it covers travel for a duration starting from day `d`. For example, a 7-day pass bought on day `d` covers travel from day `d` to day `d+6`. The maximum possible day is 365.

In essence, we need to strategically buy passes (1-day, 7-day, or 30-day) to ensure all days specified in the `days` array are covered, while minimizing the total expenditure.

### Approach / Intuition

This problem exhibits characteristics of Dynamic Programming (DP): optimal substructure (the optimal solution for the entire problem depends on optimal solutions for subproblems) and overlapping subproblems (the same subproblems are encountered multiple times). A top-down DP approach with memoization is a natural fit.

**Core Idea:**
We want to find the minimum cost to cover all travel days starting from a particular day `i`. Let `dp[i]` be this minimum cost.

**Recursive Relation (or State Transition):**
When considering day `i`:

1.  **If day `i` is NOT a travel day:** We don't need to buy a ticket for this specific day. The minimum cost from day `i` onwards is simply the minimum cost from day `i+1` onwards.
2.  **If day `i` IS a travel day:** We *must* buy a ticket that covers day `i`. We have three choices:
    *   **Buy a 1-day pass:** The cost is `costs[0]`. This pass covers day `i`. We then need to find the minimum cost to cover all remaining travel days starting from day `i+1`. So, `costs[0] + dp[i+1]`.
    *   **Buy a 7-day pass:** The cost is `costs[1]`. This pass covers days `i` through `i+6`. We then need to find the minimum cost to cover all remaining travel days starting from day `i+7`. So, `costs[1] + dp[i+7]`.
    *   **Buy a 30-day pass:** The cost is `costs[2]`. This pass covers days `i` through `i+29`. We then need to find the minimum cost to cover all remaining travel days starting from day `i+30`. So, `costs[2] + dp[i+30]`.

    The minimum cost for day `i` will be the minimum of these three options.

**Base Case:**
If `i` exceeds the maximum possible day (e.g., `i >= 366`), it means we have successfully covered all required travel days up to this point. The cost from this point onwards is 0.

**Memoization:**
To avoid redundant computations, we store the result for `dp[i]` once it's calculated. If we encounter `dp[i]` again, we simply return the stored value.

This approach systematically explores all possibilities by making local optimal choices (which pass to buy for the current travel day) and combining them to find the global optimum.

### Data Structures and Algorithms

1.  **Algorithm:** Dynamic Programming (specifically, top-down DP with memoization, implemented using recursion).
2.  **Data Structures:**
    *   `int[] day`: This array serves a dual purpose:
        *   It acts as a boolean indicator: `day[d] == 0` means day `d` is not a travel day. `day[d] == -1` means day `d` *is* a travel day, and its minimum cost hasn't been computed yet.
        *   It acts as a memoization table: If `day[d]` is a positive integer, it stores the pre-computed minimum cost to cover travel days starting from day `d`.
    *   `int[] days`: The input array specifying the travel days.
    *   `int[] costs`: The input array specifying the costs for 1-day, 7-day, and 30-day passes.

### Code Walkthrough

```java
class Solution {
    // Recursive helper function for top-down DP with memoization
    private int dfs(int i, int[] day, int[] cost) {
        // Base Case: If we've gone beyond the last possible day (365),
        // all travel days are covered, so no further cost.
        if(i >= 366) return 0;

        // Optimization/Non-travel day check:
        // If day[i] is 0, it means day 'i' is NOT a travel day.
        // We don't need to buy a ticket for this day.
        // The cost from day 'i' onwards is simply the cost from day 'i+1' onwards.
        if(day[i] == 0) return dfs(i+1, day, cost);

        // Memoization Check:
        // If day[i] is not -1 (and not 0, which was handled above),
        // it means we have already computed the minimum cost for day 'i'.
        // Return the stored value directly.
        if(day[i] != -1) return day[i];

        // If day 'i' is a travel day (day[i] == -1) and its cost hasn't been computed yet:
        // Explore the three options for buying a pass:

        // Option 1: Buy a 1-day pass for day 'i'.
        // Cost is cost[0] + minimum cost from day 'i+1' onwards.
        int op1 = dfs(i+1, day, cost) + cost[0];

        // Option 2: Buy a 7-day pass for day 'i'.
        // This covers days 'i' through 'i+6'.
        // Cost is cost[1] + minimum cost from day 'i+7' onwards.
        int op2 = dfs(i+7, day, cost) + cost[1];

        // Option 3: Buy a 30-day pass for day 'i'.
        // This covers days 'i' through 'i+29'.
        // Cost is cost[2] + minimum cost from day 'i+30' onwards.
        int op3 = dfs(i+30, day, cost) + cost[2];

        // Store the minimum of the three options in day[i] (for memoization)
        // and return it.
        return day[i] = Math.min(Math.min(op1, op2), op3);
    }

    // Main public method to calculate minimum cost tickets
    public int mincostTickets(int[] days, int[] costs) {
        // Initialize an array 'day' of size 366 (for days 0 to 365).
        // By default, all elements are 0, indicating non-travel days.
        int[] day = new int[366];

        // Mark actual travel days in the 'day' array.
        // For each day 'd' in the input 'days' array, set day[d] to -1.
        // -1 signifies a travel day for which the minimum cost is yet to be computed.
        for(int d : days) day[d] = -1;

        // Start the recursive DP calculation from