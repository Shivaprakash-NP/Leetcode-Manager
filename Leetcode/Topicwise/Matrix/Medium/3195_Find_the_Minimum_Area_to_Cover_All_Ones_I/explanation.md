## LeetCode: Find the Minimum Area to Cover All Ones I

**1. Problem Understanding:**

The problem asks us to find the minimum rectangular area within a given binary matrix (a matrix containing only 0s and 1s) that encloses all the cells containing the value 1.  In essence, we need to find the smallest rectangle that completely covers all the "ones" in the matrix.


**2. Approach / Intuition:**

The solution employs a very efficient approach based on finding the minimum and maximum row and column indices containing a '1'.  Since we're looking for the *minimum* area rectangle, we only need to locate the topmost, bottommost, leftmost, and rightmost '1's in the matrix. The rectangle formed by these four points will be the smallest rectangle enclosing all the '1's.  This is because any smaller rectangle would inevitably exclude at least one '1'.  There's no need for complex algorithms like dynamic programming or graph traversal as the problem has a straightforward geometric solution.


**3. Data Structures and Algorithms:**

* **Data Structures:** The input is a 2D integer array (matrix).  The solution uses four integer variables (`top`, `bot`, `l`, `r`) to track the boundaries of the minimum rectangle.
* **Algorithms:** The core algorithm is a simple linear scan of the matrix.  We use `Math.min()` and `Math.max()` to efficiently update the boundary variables as we traverse the matrix.


**4. Code Walkthrough:**

```java
class Solution {
    public int minimumArea(int[][] grid) {
        int n = grid.length; // Number of rows
        int m = grid[0].length; // Number of columns
        int top = n; // Initialize top to the maximum possible row index (out of bounds)
        int bot = 0; // Initialize bottom to the minimum possible row index
        int l = m; // Initialize left to the maximum possible column index (out of bounds)
        int r = 0; // Initialize right to the minimum possible column index

        for(int i = 0; i<n; i++) { // Iterate through rows
            for(int j = 0; j<m; j++) { // Iterate through columns
                if(grid[i][j] == 1) { // If a '1' is found
                    top = Math.min(top, i); // Update top if current row is higher
                    bot = Math.max(bot, i); // Update bottom if current row is lower
                    l = Math.min(l, j); // Update left if current column is further left
                    r = Math.max(r, j); // Update right if current column is further right
                }
            }
        }

        return (bot-top+1)*(r-l+1); // Calculate and return the area
    }
}
```

The code first initializes the boundary variables to values that guarantee they'll be updated upon finding the first '1'. The nested loops iterate through the matrix.  Whenever a '1' is found, the boundary variables are adjusted using `Math.min()` and `Math.max()` to track the smallest enclosing rectangle. Finally, the area is calculated and returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n*m), where 'n' is the number of rows and 'm' is the number of columns. This is because the code iterates through each cell of the matrix once.

* **Space Complexity:** O(1). The solution uses a constant amount of extra space to store the four boundary variables, regardless of the input matrix size.  The space used is independent of the input size, making it constant space complexity.
