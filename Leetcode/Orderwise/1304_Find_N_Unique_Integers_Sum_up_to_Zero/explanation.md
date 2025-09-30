## Find N Unique Integers Sum up to Zero: Detailed Explanation

### 1. Problem Understanding:

The problem asks us to create an array of `n` unique integers such that the sum of all integers in the array is equal to zero.

### 2. Approach / Intuition:

The core idea behind this solution is to leverage the property of additive inverses.  We can create a sequence of numbers, calculate their sum, and then add the negative of that sum to the sequence. This ensures that the total sum becomes zero.

Specifically, we can generate a sequence of numbers from 1 to n-1.  The sum of the numbers in this sequence can be calculated using the formula for the sum of the first k natural numbers: k * (k + 1) / 2. In our case, k = n-1. So, sum = (n-1) * n / 2 which is the same as n * (n-1) / 2.  Then, we simply put the negative of the sum at the beginning of our array.

Why choose this approach?

*   **Simplicity:**  It avoids complex calculations or iterative adjustment of values.
*   **Directness:**  It directly constructs the array based on a simple mathematical principle.
*   **Efficiency:**  It has a very low time complexity.

### 3. Data Structures and Algorithms:

*   **Data Structures:** An integer array (`int[]`) is used to store the `n` unique integers.
*   **Algorithms:**  This approach doesn't rely on complex algorithms. It directly calculates the sum of a series of numbers using a formula and performs basic arithmetic operations.

### 4. Code Walkthrough:

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n]; // Create an integer array of size n to store the result.
        int sum = n*(n-1)/2; // Calculate the sum of numbers from 1 to n-1.  Formula for sum of first k natural numbers: k*(k+1)/2. Here, k = n-1.
        ans[0] = -1*sum; // Assign the negative of the sum to the first element of the array (index 0).
        for(int i = 1; i<n; i++) ans[i] = i; // Populate the remaining elements of the array with numbers from 1 to n-1.
        return ans; // Return the created array.
    }
}
```

*   **`int[] ans = new int[n];`**:  This line allocates memory for an integer array named `ans` of size `n`. This array will hold the `n` unique integers that sum to zero.

*   **`int sum = n*(n-1)/2;`**: This line calculates the sum of numbers from 1 to `n-1`. The formula  `n*(n-1)/2`  is used to efficiently compute this sum.  Essentially, it's  (n-1) * n / 2, which is the sum of the first (n-1) natural numbers.

*   **`ans[0] = -1*sum;`**: This line assigns the negative of the calculated `sum` to the first element (index 0) of the `ans` array. This is the crucial step that ensures the overall sum of the array will be zero.

*   **`for(int i = 1; i<n; i++) ans[i] = i;`**:  This `for` loop iterates from `i = 1` to `i = n-1`.  In each iteration, it assigns the value of `i` to the `i`-th element of the `ans` array. This populates the rest of the array with the numbers 1, 2, 3, ..., n-1.

*   **`return ans;`**: Finally, the function returns the `ans` array, which now contains `n` unique integers that sum to zero.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n)**
    *   The code calculates the sum in O(1) time.
    *   The `for` loop iterates `n-1` times, which is O(n).
    *   Therefore, the overall time complexity is dominated by the `for` loop, making it O(n).

*   **Space Complexity: O(n)**
    *   The code creates an integer array `ans` of size `n`.
    *   The space used is directly proportional to the input `n`.
    *   Therefore, the space complexity is O(n).
