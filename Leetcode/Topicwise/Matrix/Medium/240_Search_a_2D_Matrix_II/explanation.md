```markdown
## Search a 2D Matrix II - Problem Explanation and Solution

### 1. Problem Understanding:

The "Search a 2D Matrix II" problem requires us to search for a specific `target` integer within a 2D matrix. This matrix has a special property: each row is sorted in non-decreasing order (from left to right), and each column is also sorted in non-decreasing order (from top to bottom).  The task is to efficiently determine if the `target` exists within the matrix and return `true` if it does, and `false` otherwise.

### 2. Approach / Intuition:

The key idea is to exploit the sorted nature of both rows and columns to perform an efficient search, avoiding a brute-force scan of the entire matrix. We start our search from the top-right corner of the matrix. From this position, we can make the following observations:

*   If the current element is equal to the `target`, we've found it and return `true`.
*   If the current element is less than the `target`, it means all elements to the left of the current element in the same row will also be less than the `target` (because the row is sorted). Therefore, we can eliminate the entire current row and move to the next row (increment `r`).
*   If the current element is greater than the `target`, it means all elements below the current element in the same column will also be greater than the `target` (because the column is sorted). Therefore, we can eliminate the entire current column and move to the previous column (decrement `c`).

This approach effectively eliminates either a row or a column in each step, guiding us towards the `target` (if it exists) or narrowing down the search space until we can definitively conclude that the `target` is not present.

The reason this approach is preferable is its efficiency. It avoids checking every element in the matrix, achieving a much better time complexity compared to brute-force methods.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure is the input `int[][] matrix`. No auxiliary data structures are used.
*   **Algorithms:** This solution uses a variation of a search algorithm that leverages the sorted properties of the matrix to eliminate search space iteratively. It can be seen as a form of binary search in two dimensions, although it doesn't exactly fit the standard binary search template.

### 4. Code Walkthrough:

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r = 0; // Initialize row pointer to the first row (top)
        int c = matrix[0].length - 1; // Initialize column pointer to the last column (right)

        // Continue the loop as long as the row pointer is within bounds
        // and the column pointer is within bounds
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) return true; // If we find the target, return true
            if (matrix[r][c] < target) r++; // If current element is smaller, go to next row
            else c--; // If current element is larger, go to previous column
        }

        return false; // If the target is not found after the loop, return false
    }
}
```

1.  **Initialization:**
    *   `int r = 0;`: The `r` variable represents the current row index, initialized to the first row (index 0).
    *   `int c = matrix[0].length - 1;`: The `c` variable represents the current column index, initialized to the last column of the first row.

2.  **`while` loop:**
    *   `while (r < matrix.length && c >= 0)`: The loop continues as long as the row index `r` is within the bounds of the matrix's rows and the column index `c` is within the bounds of the matrix's columns.  This ensures that the search stays within the valid indices of the matrix.

3.  **Conditional Checks:**
    *   `if (matrix[r][c] == target) return true;`: This checks if the current element `matrix[r][c]` is equal to the `target`. If it is, the function immediately returns `true` because the target has been found.
    *   `if (matrix[r][c] < target) r++;`: If the current element is less than the `target`, it means that all elements in the current row to the left of `matrix[r][c]` will also be less than the target. Therefore, we increment the row index `r` to move to the next row, effectively eliminating the current row from the search space.
    *   `else c--;`: If the current element is greater than the `target`, it means that all elements in the current column below `matrix[r][c]` will also be greater than the target. Therefore, we decrement the column index `c` to move to the previous column, effectively eliminating the current column from the search space.

4.  **Return `false`:**
    *   `return false;`: If the `while` loop completes without finding the target (i.e., the row or column index goes out of bounds), it means the target is not present in the matrix. The function then returns `false`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(m + n), where 'm' is the number of rows and 'n' is the number of columns in the matrix.  In the worst-case scenario, we might traverse all the way from the top-right corner to the bottom-left corner, covering at most m rows and n columns.  This is significantly better than a brute-force O(m*n) solution.

*   **Space Complexity:** O(1). The algorithm uses only a constant amount of extra space for the row and column pointers, regardless of the size of the input matrix.  Therefore, the space complexity is constant.
```