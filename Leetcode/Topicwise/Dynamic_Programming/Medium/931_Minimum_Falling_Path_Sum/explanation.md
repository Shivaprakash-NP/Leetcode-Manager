## Minimum Falling Path Sum Explained

**1. Problem Understanding:**

The "Minimum Falling Path Sum" problem asks us to find the minimum sum of a path from the top row to the bottom row of a square matrix.  At each step, we can move to one of the three adjacent cells directly below the current cell (i.e., diagonally left, straight down, or diagonally right).  The goal is to find the path that results in the smallest total sum.

**2. Approach / Intuition:**

This solution uses dynamic programming.  Instead of exploring all possible paths exhaustively (which would be very inefficient), it builds up the solution iteratively.  The core idea is to maintain an array (`pre` and `cur`) representing the minimum path sum to reach each cell in the current row.  We iterate through the rows, calculating the minimum path sum to each cell in the current row based on the minimum path sums to the adjacent cells in the previous row.  This avoids redundant calculations by reusing previously computed results.  This bottom-up approach is efficient because it systematically explores only the necessary paths.

**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is a 1D array (`pre` and `cur`).  `pre` stores the minimum path sums to each cell in the *previous* row, while `cur` stores the minimum path sums to each cell in the *current* row being processed.
* **Algorithms:** The core algorithm is dynamic programming, specifically a bottom-up approach.  It also incorporates a simple linear scan at the end to find the minimum value in the final `pre` array.


**4. Code Walkthrough:**

* **`int n = matrix.length;`**: This line gets the number of rows (and columns, since it's a square matrix).

* **`int[] pre = new int[n]; for(int i = 0; i<n; i++) pre[i] = matrix[0][i];`**: This initializes the `pre` array with the values from the first row of the matrix.  These are the minimum path sums to reach each cell in the first row (since there are no preceding rows).

* **`for(int i = 1; i<n; i++) { ... }`**: This loop iterates through the rows of the matrix, starting from the second row (index 1).

* **`int[] cur = new int[n];`**:  A new array `cur` is created for each row to store the minimum path sums for the current row.

* **`cur[0] = Math.min(pre[0], pre[1]) + matrix[i][0];`**: This line calculates the minimum path sum to the first cell in the current row (`cur[0]`). It considers only two paths from the previous row: from the cell directly above or from the diagonally right cell above, adding the current cell's value.

* **`for(int j = 1; j<n-1; j++) cur[j] = Math.min(pre[j-1], Math.min(pre[j], pre[j+1]))+matrix[i][j];`**: This loop calculates the minimum path sum for the inner cells (excluding the first and last) in the current row. It considers three paths from the previous row: from the cell directly above, the diagonally left cell above, and the diagonally right cell above.

* **`cur[n-1] = Math.min(pre[n-1], pre[n-2])+matrix[i][n-1];`**: This line calculates the minimum path sum to the last cell in the current row, considering only the two paths from the previous row: from the cell directly above and the diagonally left cell above.

* **`pre = cur;`**: After processing a row, `pre` is updated to `cur` so that the minimum path sums for the current row become the basis for calculating the minimum path sums for the next row.

* **`int ans = Integer.MAX_VALUE; for(int v : pre) ans = Math.min(ans, v);`**: This part iterates through the final `pre` array (containing the minimum path sums to each cell in the last row) and finds the minimum value among them, which represents the overall minimum falling path sum.

* **`return ans;`**: The minimum falling path sum is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n^2), where n is the number of rows (and columns) in the matrix.  The nested loops iterate through each cell of the matrix once.

* **Space Complexity:** O(n). The space used is dominated by the `pre` and `cur` arrays, each of size n.  Although we create a new `cur` array in each iteration,  it's not accumulating space; it replaces the previous `cur` with the updated one. Therefore, the space complexity remains linear.

This dynamic programming approach is significantly more efficient than a brute-force approach which would have exponential time complexity.
