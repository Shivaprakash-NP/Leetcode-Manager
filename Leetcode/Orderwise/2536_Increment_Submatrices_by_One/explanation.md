### Problem Understanding

The problem asks us to simulate a series of range update operations on an `n x n` integer matrix, initially filled with zeros. We are given a list of `queries`, where each query `[r1, c1, r2, c2]` represents a submatrix defined by its top-left corner `(r1, c1)` and its bottom-right corner `(r2, c2)`. For each such query, every cell `(x, y)` within the specified submatrix (i.e., `r1 <= x <= r2` and `c1 <= y <= c2`) must be incremented by one. After processing all queries, the goal is to return the final state of the `n x n` matrix.

### Approach / Intuition

A naive approach would be to iterate through each query and, for every cell within the specified rectangle, increment its value in the matrix. If there are `Q` queries and the matrix size is `N x N`, and each query covers a significant portion of the matrix (e.g., `O(N^2)` cells), this would lead to a time complexity of `O(Q * N^2)`, which is too slow for typical constraints (e.g., `N=500`, `Q=50000`).

The key insight to optimize range updates is to use a **2D Difference Array** (also known as a 2D Fenwick tree or segment tree for more complex operations, but a simple difference array works for range adds and final sum). This technique is an extension of the 1D difference array.

**1D Difference Array Review:** To add a value `val` to a range `[L, R]` in a 1D array, we can do `diff[L] += val` and `diff[R+1] -= val`. After all updates, computing prefix sums on the `diff` array gives the final values.

**Extending to 2D:** To increment a rectangle defined by `(x1, y1)` and `(x2, y2)` by one:

1.  **`mat[x1][y1]++`**: This marks the top-left corner where the increment starts. When we compute prefix sums later, this `+1` will propagate to all cells `(i, j)` where `i >= x1` and `j >= y1`.
2.  **`mat[x2+1][y1]--`**: This cancels out the increment for all rows *below* `x2`. Specifically, from row `x2+1` onwards, and from column `y1` onwards, the `+1` effect from `mat[x1][y1]` is nullified.
3.  **`mat[x1][y2+1]--`**: Similarly, this cancels out the increment for all columns *to the right* of `y2`. From column `y2+1` onwards, and from row `x1` onwards, the `+1` effect from `mat[x1][y1]` is nullified.
4.  **`mat[x2+1][y2+1]++`**: This is the crucial "inclusion-exclusion" step. The previous two operations (`mat[x2+1][y1]--` and `mat[x1][y2+1]--`) both applied a decrement to the region starting from `(x2+1, y2+1)` to the bottom-right of the matrix. This means the region `(x2+1, y2+1)` to `(N-1, N-1)` was *double-subtracted*. To correct this, we need to add back one to `mat[x2+1][y2+1]`.

After applying these four point updates for each query, the `mat` array will contain "difference" values. The final step is to convert these difference values into actual sums using a 2D prefix sum calculation.

The 2D prefix sum formula for a cell `(i, j)` is:
`current_value_at_mat[i][j] = mat[i][j] + mat[i-1][j] + mat[i][j-1] - mat[i-1][j-1]`
This formula effectively accumulates the values from above and to the left, while subtracting the top-left diagonal value to avoid double-counting. Special care must be taken for the first row and first column, as they only have one neighbor (above or left) to sum from.

This approach transforms `Q` range updates into `4Q` point updates, followed by two `O(N^2)` passes to compute the prefix sums. The total time complexity becomes `O(Q + N^2)`, which is efficient enough.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[][] mat`: A 2D integer array of size `n x n`. This matrix serves a dual purpose: initially, it acts as the 2D difference array where point updates are applied. After the prefix sum calculations, it holds the final result matrix.

*   **Algorithms:**
    *   **2D Difference Array:** This is the core algorithm used to efficiently record range updates as point updates.
    *   **2D Prefix Sums:** This algorithm is used to transform the difference array back into the final matrix where each cell `(i, j)` stores the sum of all increments applied to it.

### Code Walkthrough

```java
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        // 1. Initialize an n x n matrix with zeros. This will be our 2D difference array.
        int[][] mat = new int[n][n];

        // 2. Process each query to mark the boundaries of increments using the 2D difference array technique.
        for(int[] q : queries) {
            int x1 = q[0]; // Top-left row
            int y1 = q[1]; // Top-left column
            int x2 = q[2]; // Bottom-right row
            int y2 = q[3]; // Bottom-right column

            