### Problem Understanding

The "Dungeon Game" problem asks us to determine the minimum initial health a knight must have to successfully navigate a dungeon. The dungeon is represented by a 2D grid, where each cell `(i, j)` contains an integer `dungeon[i][j]`. This integer signifies a health change: a positive value means the knight gains health, and a negative value means they lose health. The knight starts at the top-left cell `(0, 0)` and must reach the bottom-right cell `(m-1, n-1)`, where a princess is located. The knight can only move right or down. A critical constraint is that the knight's health must *never* drop below 1 at any point, including upon entering a cell, after processing its health change, and before moving to the next cell.

### Approach / Intuition

This problem is a classic dynamic programming (DP) problem due to its optimal substructure and overlapping subproblems. The key challenge is that we need to find a *minimum* initial health, and the health changes are cumulative and can be positive or negative.

**Why Dynamic Programming and Backward Approach?**

1.  **Forward Approach Difficulty:** If we try to solve this from `(0,0)` forward to `(m-1, n-1)`, it's difficult. At any cell `(i,j)`, we'd need to know the minimum health required to *enter* that cell. This minimum health depends on all possible paths *from* `(0,0)` *to* `(i,j)`, and also on the future path choices *from* `(i,j)` *to* `(m-1, n-1)`. The "minimum initial health" requirement makes a greedy forward approach problematic.

2.  **Backward DP Intuition:** A more intuitive approach is to work backward from the destination `(m-1, n-1)` to the starting point `(0,0)`. Let `dp[i][j]` represent the *minimum health the knight needs to have *upon entering* cell `(i, j)` to guarantee survival all the way to the princess at `(m-1, n-1)`.

    *   **Base Case (Destination Cell `dp[m-1][n-1]`):**
        When the knight is at the princess's cell `(m-1, n-1)`, they must survive the health change in `dungeon[m-1][n-1]` and still have at least 1 health *after* it.
        If `dungeon[m-1][n-1]` is positive (e.g., +5), the knight gains health. To have 1 health after gaining 5, they only need 1 health upon entering. `1 - (+5) = -4`.
        If `dungeon[m-1][n-1]` is negative (e.g., -10), the knight loses health. To have 1 health after losing 10, they must enter with `1 - (-10) = 11` health.
        In general, the health needed *before* processing `dungeon[m-1][n-1]` to ensure 1 health *after* is `1 - dungeon[m-1][n-1]`.
        However, the health upon entering can never be less than 1. So, we take `Math.max(1, 1 - dungeon[m-1][n-1])`.

    *   **Recursive Relation (General Cell `dp[i][j]`):**
        From cell `(i, j)`, the knight can move either down to `(i+1, j)` or right to `(i, j+1)`. To minimize the initial health needed at `(0,0)`, at `(i,j)` we should choose the path that requires *less* health upon leaving `(i,j)` (i.e., upon entering the next cell).
        So, the minimum health needed *after* processing `dungeon[i][j]` (and thus upon entering the next cell) is `Math.min(dp[i+1][j], dp[i][j+1])`. Let's call this `requiredHealthForNextStep`.

        Now, to have `requiredHealthForNextStep` health *after* processing `dungeon[i][j]`, the knight must enter `(i, j)` with `requiredHealthForNextStep - dungeon[i][j]` health.
        Again, the health upon entering `(i,j)` must be at least 1.
        Therefore, `dp[i][j] = Math.max(1, requiredHealthForNextStep - dungeon[i][j])`.

This backward approach systematically calculates the minimum health required at each cell, ensuring that the knight always has at least 1 health throughout the journey. The final answer will be `dp[0][0]`.

### Data Structures and Algorithms

*   **Data Structure:** A 2D array, `int[][] dp`, of the same dimensions as the input `dungeon`. This table stores the minimum health required to enter each cell `(i, j)` and survive to the end.
*   **Algorithm:** Dynamic Programming, specifically a bottom-up (iterative) approach. The DP table is filled starting from the bottom-right corner and moving towards the top-left.

### Code Walkthrough

```java
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // dp[i][j] will store the minimum health required to enter cell (i,j)
        // and survive to the princess at (m-1, n-1).
        int[][] dp = new int[m][n];

        // 1. Base Case: Calculate dp[m-1][n-1] (the destination cell)
        // The knight needs to have at least 1 health AFTER passing through dungeon[m-1][n-1].
        // If dungeon[m-1][n-1] is positive (e.g., +5), 1 - 5 = -4. Math.max(1, -4) = 1.
        //    Meaning, if they enter with 1 health, they'll have 1+5=6 health, which is fine.
        // If dungeon[m-1][n-1] is negative (e.g., -10), 1 - (-10) = 11. Math.max(1, 11) = 11.
        //    Meaning, they need 11 health to enter, to have 11-10=1 health remaining.
        dp[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);

        // 2. Fill the last column (excluding