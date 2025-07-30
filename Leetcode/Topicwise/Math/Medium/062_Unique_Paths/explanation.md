## Unique Paths Problem and Solution Explanation

### 1. Problem Understanding:

The "Unique Paths" problem on LeetCode asks us to find the number of distinct paths from the top-left corner to the bottom-right corner of an `m x n` grid. We are only allowed to move down or right at each step.

### 2. Approach / Intuition:

The key insight to solving this problem efficiently is to recognize that any path from the top-left to the bottom-right of the grid will always consist of `m - 1` downward moves and `n - 1` rightward moves. Therefore, the total number of moves is `(m - 1) + (n - 1) = m + n - 2`.

The problem then boils down to choosing which of these `m + n - 2` moves will be downward moves (or alternatively, rightward moves). We can formulate this as a combination problem: we need to choose `m - 1` downward moves out of `m + n - 2` total moves (or `n-1` rightward moves out of `m+n-2` moves).  Mathematically, this is represented as `C(m + n - 2, m - 1)` or `C(m + n - 2, n - 1)`, where `C(n, k)` denotes "n choose k" (the number of combinations of choosing k items from a set of n items).

The formula for `C(n, k)` is `n! / (k! * (n - k)!)`.  However, directly calculating factorials can quickly lead to integer overflow issues, even with `long` data types.  Therefore, instead of calculating the factorials directly, we can simplify the calculation using an iterative approach to compute the combination.

For example, to compute `C(n, k)`, we can use the following formula:

`C(n, k) = (n * (n - 1) * ... * (n - k + 1)) / (k * (k - 1) * ... * 1)`

The chosen approach avoids large factorial computations, preventing potential overflow errors and improving efficiency.  It directly calculates the binomial coefficient in a controlled manner.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  The solution primarily uses primitive data types like `int` and `long`. No complex data structures (e.g., arrays, lists) are required.
*   **Algorithms:**
    *   **Combinations (Binomial Coefficient):**  The core algorithm is the iterative calculation of the binomial coefficient, `C(n, k)`.

### 4. Code Walkthrough:

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;                 
        int k = Math.min(m, n) - 1;          
        long ans = 1;                      
        for (int i = 1; i <= k; i++) 
            ans = ans * (N - i + 1) / i;
        return (int)ans;
    }
}
```

1.  **`int N = m + n - 2;`**:  This line calculates the total number of steps required to reach the bottom-right corner from the top-left corner, as explained earlier.  `N` represents the `n` in `C(n, k)`.

2.  **`int k = Math.min(m, n) - 1;`**: This line determines the smaller value between `m-1` and `n-1`.  We choose the smaller value because `C(n, k) = C(n, n-k)`.  Using the smaller value for `k` will minimize the number of iterations in the following loop. `k` represents the `k` in `C(n, k)`.

3.  **`long ans = 1;`**: This initializes the `ans` variable to 1.  This variable will store the result of the combination calculation. We use `long` to avoid potential integer overflows during the intermediate calculations.

4.  **`for (int i = 1; i <= k; i++)`**: This loop iterates `k` times, calculating the binomial coefficient iteratively.

5.  **`ans = ans * (N - i + 1) / i;`**: This is the core calculation step. It implements the iterative formula for calculating the binomial coefficient.  In each iteration:
    *   `ans` is multiplied by `(N - i + 1)`. This corresponds to the `n * (n - 1) * ... * (n - k + 1)` part of the combination formula's numerator.
    *   `ans` is divided by `i`. This corresponds to the `k * (k - 1) * ... * 1` part of the combination formula's denominator.  The division is performed after the multiplication to reduce the chances of overflow.

6.  **`return (int)ans;`**: Finally, the `long` result (`ans`) is cast to an `int` and returned. Because of the problem constraints, the result is guaranteed to fit within the `int` range.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(min(m, n)). The loop iterates `k` times, where `k = min(m, n) - 1`. Thus the time complexity depends linearly on the smaller of `m` and `n`.
*   **Space Complexity:** O(1). The solution uses only a few constant-sized variables, regardless of the input values of `m` and `n`.  Therefore, the space complexity is constant.
