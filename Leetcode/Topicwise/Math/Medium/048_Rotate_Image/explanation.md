```markdown
## Rotate Image - Detailed Explanation

### 1. Problem Understanding:

The "Rotate Image" problem on LeetCode asks us to rotate a square 2D matrix representing an image by 90 degrees clockwise *in-place*.  "In-place" means we cannot use extra space proportional to the size of the matrix (O(n^2)). We are allowed to modify the given matrix directly.  Effectively, we must rearrange the elements of the matrix such that each element moves to its new position after the rotation, without allocating a new matrix.

### 2. Approach / Intuition:

The provided solution implements a two-step approach:

1.  **Transpose the matrix:**  This involves swapping elements across the main diagonal (from top-left to bottom-right). After transposing, rows become columns, and columns become rows.
2.  **Reverse each row:**  After transposing, reversing each row of the matrix effectively rotates the image by 90 degrees clockwise.

**Why this approach?**

This method is chosen because it performs the rotation in-place, satisfying the problem's constraint of O(1) extra space.  Transposing and then reversing rows is a relatively simple and efficient way to achieve the desired rotation.  It avoids the complexity of directly calculating the destination index for each element in a single operation.

### 3. Data Structures and Algorithms:

*   **Data Structure:** A 2D integer array (matrix) `int[][] matrix` is the primary data structure.
*   **Algorithms:**
    *   **Transposition:**  Swapping elements across the main diagonal.
    *   **Row Reversal:** Reversing the elements within each row of the matrix.  A two-pointer approach is used for row reversal.

### 4. Code Walkthrough:

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // Transpose the matrix
        for(int i = 0 ; i<n-1 ; i++)
        {
            for(int j = i+1 ; j<n ; j++)
            {
                int tem = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tem;
            }
        }
        // Reverse each row
        for(int i = 0 ; i<n ; i++)
        {
            int l = 0;
            int r = n-1;
            while(l<r)
            {
                int tem = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = tem;
                l++;
                r--;
            }
        }
    }
}
```

*   **`int n = matrix.length;`**: This line retrieves the dimension of the square matrix (number of rows/columns).
*   **`for(int i = 0 ; i<n-1 ; i++)`**: This outer loop iterates from the first row (index 0) to the second-to-last row (index n-2). We iterate `n-1` times because we only need to process the upper triangle of the matrix to transpose it.
*   **`for(int j = i+1 ; j<n ; j++)`**: This inner loop iterates from the element right after the diagonal element in the current row (`i+1`) to the last element in the row (`n-1`). This is because we only need to swap each element *above* the main diagonal with its corresponding element *below* the diagonal.  We avoid redundant swaps and also avoid swapping the diagonal elements themselves.
*   **`int tem = matrix[i][j];`**: Stores the value of the element at `matrix[i][j]` in a temporary variable `tem`.
*   **`matrix[i][j] = matrix[j][i];`**:  Assigns the value of the element at `matrix[j][i]` to `matrix[i][j]`.
*   **`matrix[j][i] = tem;`**:  Assigns the original value of `matrix[i][j]` (stored in `tem`) to `matrix[j][i]`.  These three lines effectively swap `matrix[i][j]` and `matrix[j][i]`.
*   **`for(int i = 0 ; i<n ; i++)`**: This loop iterates through each row of the transposed matrix.
*   **`int l = 0;`**: Initializes a left pointer `l` to the beginning of the current row (index 0).
*   **`int r = n-1;`**: Initializes a right pointer `r` to the end of the current row (index n-1).
*   **`while(l<r)`**: This `while` loop continues as long as the left pointer is less than the right pointer.
*   **`int tem = matrix[i][l];`**: Stores the value of the element at `matrix[i][l]` in a temporary variable `tem`.
*   **`matrix[i][l] = matrix[i][r];`**: Assigns the value of the element at `matrix[i][r]` to `matrix[i][l]`.
*   **`matrix[i][r] = tem;`**:  Assigns the original value of `matrix[i][l]` (stored in `tem`) to `matrix[i][r]`. These three lines swap `matrix[i][l]` and `matrix[i][r]`.
*   **`l++;`**: Moves the left pointer one position to the right.
*   **`r--;`**: Moves the right pointer one position to the left.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n^2)**

    *   The transposition step involves iterating over the upper triangle of the matrix, which takes O(n^2) time.
    *   The row reversal step involves iterating over each element in the matrix once, which also takes O(n^2) time.
    *   Therefore, the overall time complexity is O(n^2) + O(n^2) = O(n^2).

*   **Space Complexity: O(1)**

    *   The algorithm operates in-place, modifying the input matrix directly. It only uses a constant amount of extra space (e.g., the `tem`, `l`, and `r` variables), regardless of the size of the matrix. Therefore, the space complexity is O(1).
```