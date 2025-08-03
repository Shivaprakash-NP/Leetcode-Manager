## LeetCode: Unique Paths II - Detailed Solution Explanation

**1. Problem Understanding:**

The "Unique Paths II" problem asks us to find the number of unique paths from the top-left corner to the bottom-right corner of a grid.  Unlike the standard "Unique Paths" problem, this version includes obstacles represented by the value `1` in the grid.  We cannot move through cells containing obstacles.  A path can only move down or right.

**2. Approach / Intuition:**

The most efficient approach to solve this problem is using dynamic programming.  We create a DP table (`dp`) of the same size as the input grid. `dp[i][j]` will store the number of unique paths to reach cell `(i, j)`.

The base case is `dp[0][0] = 1` if the starting cell is not an obstacle; otherwise, it's 0.  For other cells, the number of paths to reach `(i, j)` is the sum of the paths to reach `(i-1, j)` and `(i, j-1)`, provided these cells are not obstacles. This directly reflects the constraint that we can only move down or right. This approach avoids redundant calculations by storing and reusing previously computed results.


**3. Data Structures and Algorithms:**

* **Data Structures:**  A 2D integer array (`int[][]`) is used to represent both the input grid (`obstacleGrid`) and the DP table (`dp`).
* **Algorithms:** Dynamic Programming is the core algorithm used.  The solution iterates through the grid in a systematic way, building up the DP table.

**4. Code Walkthrough:**

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        //Early exit if start or end is blocked
        if(obstacleGrid[0][0] == 1 || obstacleGrid[n-1][m-1] == 1) return 0;

        int[][] dp = new int[n][m];

        dp[0][0] = 1; // Base case: one path to reach the starting cell (if it's not an obstacle)
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                //Skip current cell if it's the starting cell or an obstacle
                if((i == 0 && j == 0) || obstacleGrid[i][j] == 1) continue;
                //Add paths from above if available
                if(i-1 >= 0) dp[i][j]+=dp[i-1][j];
                //Add paths from left if available
                if(j-1 >= 0) dp[i][j]+=dp[i][j-1];
            }
        }

        return dp[n-1][m-1]; //The result is stored at the bottom-right cell
    }
}
```

* **Lines 1-3:**  Get the dimensions of the grid and handle the trivial cases where either the starting or ending cell is an obstacle.
* **Line 5:** Create the DP table.
* **Line 7:** Initialize the starting cell's value in the DP table to 1.
* **Lines 8-14:** Iterate through the grid.  The `continue` statement skips over the starting cell and any obstacle cells. For other cells, the number of paths is the sum of paths from above and left (if they exist and are reachable).
* **Line 16:** Return the value at the bottom-right cell of the DP table, representing the total number of unique paths.


**5. Time and Space Complexity:**

* **Time Complexity:** O(m*n), where 'm' and 'n' are the dimensions of the grid.  We iterate through the grid once to fill the DP table.
* **Space Complexity:** O(m*n).  We use a DP table of size m*n to store the intermediate results.  In this implementation, we cannot reduce the space complexity because we need to store the result of all subproblems.  However, if we were only interested in the final result, we could optimize space to O(min(m,n)) by using only a 1D array instead of a 2D array.  This optimization is possible because each cell's path count only depends on its top and left neighbours.
