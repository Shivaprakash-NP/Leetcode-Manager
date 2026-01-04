### Problem Understanding

The problem "Maximum Matrix Sum" asks us to find the maximum possible sum of all elements in a given `m x n` integer matrix. We are allowed to perform a specific operation any number of times: choose any two adjacent elements (horizontally or vertically) and multiply both of them by -1.

The goal is to manipulate the signs of the numbers in the matrix using this operation such that the final sum of all elements is maximized.

### Approach / Intuition

The crucial insight for this problem lies in understanding the effect of the allowed operation on the signs of the numbers. When we choose two adjacent elements `a` and `b` and multiply both by -1, they become `-a` and `-b`.

1.  **Absolute Values Remain Constant:** Notice that the absolute values of the numbers never change. `|a|` becomes `|-a|`, which is still `|a|`. This means the sum of the absolute values of all elements in the matrix is an invariant. Let `S_abs` be the sum of `|v|` for all `v` in the matrix.

2.  **Parity of Negative Numbers:** The operation `(a, b) -> (-a, -b)` changes the signs of two elements.
    *   If `a > 0, b > 0`: `(+, +) -> (-, -)`. The count of negative numbers increases by 2.
    *   If `a < 0, b < 0`: `(-, -) -> (+, +)`. The count of negative numbers decreases by 2.
    *   If `a > 0, b < 0`: `(+, -) -> (-, +)`. The count of negative numbers remains the same.
    In all cases, the *parity* (whether it's even or odd) of the total number of negative elements in the matrix remains unchanged. This is the key invariant.

3.  **"Moving" Negative Signs:** Since we can apply the operation on *any* two adjacent elements, we can effectively "move" a negative sign around the matrix. For example, if we have `A B C`, applying to `(A, B)` gives `(-A, -B, C)`. Then applying to `(-B, C)` gives `(-A, -(-B), -C) = (-A, B, -C)`. We've effectively moved a negative sign from `A` to `C`. This implies that we can choose which elements will ultimately be negative.

Based on these observations:

*   **Case 1: Even Number of Initial Negatives:** If the initial count of negative numbers (`negcnt`) in the matrix is even, then due to the parity invariant, we can always perform operations such that all numbers in the matrix become positive. We can pair up negative numbers and "cancel" their signs, or propagate signs until they are all positive. For example, if we have `[-1, -2, 3]`, we can apply `(-1, -2) -> (1, 2, 3)`. The maximum sum in this case will be the sum of the absolute values of all original elements (`S_abs`).

*   **Case 2: Odd Number of Initial Negatives:** If the initial count of negative numbers (`negcnt`) is odd, then no matter what operations we perform, we will always be left with at least one negative number in the matrix. To maximize the total sum, this one remaining negative number should be the one that causes the *least reduction* to the sum.
    If we have `S_abs = |v1| + |v2| + ... + |vn|`, and one number `|vk|` must become `-|vk|`, the final sum will be `S_abs - 2 * |vk|`. To maximize this expression, we need to minimize `2 * |vk|`, which means `|vk|` must be the smallest absolute value among all elements in the matrix.

**Algorithm Steps:**

1.  Initialize `sum` to 0 (to store the sum of absolute values).
2.  Initialize `negcnt` to 0 (to count initial negative numbers).
3.  Initialize `low` to `Long.MAX_VALUE` (to find the minimum absolute value).
4.  Iterate through each element `v` in the matrix:
    *   If `v < 0`, increment `negcnt`.
    *   Add `|v|` to `sum`.
    *   Update `low = min(low, |v|)`.
5.  After iterating through all elements:
    *   If `negcnt` is even, return `sum`.
    *   If `negcnt` is odd, return `sum - 2 * low`.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[][] matrix`: The input 2D array.
    *   `long sum`: A `long` variable to store the cumulative sum of absolute values, preventing potential integer overflow for large matrices.
    *   `int negcnt`: An `int` variable to count the number of negative elements.
    *   `long low`: A `long` variable to store the minimum absolute value found in the matrix.

*   **Algorithms:**
    