```markdown
## Four Divisors Problem Explanation

### 1. Problem Understanding:

The "Four Divisors" problem asks us to calculate the sum of the sums of divisors for numbers in a given integer array `nums`. However, we only sum the divisors if the number has exactly *four* distinct divisors. If a number in the array doesn't have exactly four divisors, we ignore it when calculating the overall sum.

### 2. Approach / Intuition:

The core idea is to iterate through each number in the input array `nums` and, for each number, find its divisors.  We can efficiently find the divisors by iterating up to the square root of the number.  If `i` is a divisor of `v`, then `v/i` is also a divisor.

The solution keeps track of the count of divisors (`c`) and the sum of divisors (`sum1`) for each number. If, after finding all divisors, the count `c` is exactly 4, then `sum1` (the sum of divisors) is added to the overall result `sum`.

Why this approach?  Iterating up to the square root drastically reduces the number of iterations needed to find all divisors. Checking `if(v/i == i)` handles the case of perfect squares to avoid double-counting the square root as a divisor. By counting the number of divisors as we go, we can quickly determine if a number has exactly four divisors, and only then add the sum of its divisors to the total sum.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  The primary data structure used is an integer array `nums` (the input).
*   **Algorithms:**
    *   **Iteration:**  The code uses nested loops to iterate through the numbers in the input array and find their divisors.
    *   **Divisor Calculation:** The code efficiently calculates divisors by iterating up to the square root of each number.
    *   **Counting:** Counts the number of divisors found to determine if a number has exactly four divisors.

### 4. Code Walkthrough:

```java
class Solution {
    public int sumFourDivisors(int[] nums) {
        int sum = 0; // Initialize the overall sum of divisors of numbers with exactly 4 divisors.
        for(int v : nums) { // Iterate through each number in the input array.
            int sum1 = 0; // Initialize the sum of divisors for the current number.
            int c = 0; // Initialize the count of divisors for the current number.
            for(int i = 1 ; i*i<=v ; i++) { // Iterate from 1 to the square root of the current number.
                if(v%i == 0) { // If i is a divisor of v:
                    c+=2; // Increment the count of divisors by 2 (for i and v/i).
                    sum1+=(v/i); // Add v/i to the sum of divisors.
                    sum1+=(i); // Add i to the sum of divisors.
                    if(v/i == i)
                    {
                        sum1-=i; // If i is the square root of v, subtract i from the sum to avoid double-counting.
                        c--;     // Decrement the count since the divisors are the same.
                    }
                }
            }
            if(c == 4) sum+=sum1; // If the number has exactly 4 divisors, add its sum of divisors to the overall sum.
        }
        return sum; // Return the overall sum.
    }
}
```

*   **`sum = 0;`**: Initializes the variable `sum` which will accumulate the sum of divisors of numbers that have exactly four divisors.
*   **`for(int v : nums)`**:  This is an enhanced for loop that iterates through each element `v` in the `nums` array.
*   **`sum1 = 0;` and `c = 0;`**: For each number `v`, these variables are reset. `sum1` stores the sum of the divisors of `v`, and `c` stores the number of divisors of `v`.
*   **`for(int i = 1 ; i*i<=v ; i++)`**: This loop iterates from 1 up to the square root of `v`.  Iterating only up to the square root optimizes the divisor-finding process.
*   **`if(v%i == 0)`**:  This condition checks if `i` is a divisor of `v`. If it is, then `i` and `v/i` are both divisors of `v`.
*   **`c+=2;`**: If `i` is a divisor, we increment the divisor count by 2 because we've found two divisors: `i` and `v/i`.
*   **`sum1+=(v/i); sum1+=(i);`**: Adds both `i` and `v/i` to the running sum of divisors `sum1`.
*   **`if(v/i == i)`**: This condition checks if `i` is the square root of `v`. If it is, then `i` and `v/i` are the same number, and we've double-counted it.
*   **`sum1-=i; c--;`**:  If `i` is the square root, we subtract `i` from `sum1` to correct the sum, and decrement `c` to fix the count.
*   **`if(c == 4) sum+=sum1;`**: After the inner loop completes (after finding all divisors of `v`), this condition checks if the divisor count `c` is exactly 4. If it is, it means the number `v` has exactly four divisors, so we add the sum of its divisors `sum1` to the overall sum `sum`.
*   **`return sum;`**: Finally, the function returns the overall sum of divisors.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n * sqrt(m)), where n is the length of the `nums` array and m is the maximum value in the `nums` array. The outer loop iterates `n` times, and the inner loop iterates up to the square root of each number in `nums`.
*   **Space Complexity:** O(1). The solution uses only a few integer variables (`sum`, `sum1`, `c`, `i`, `v`), so the space complexity is constant. It does not utilize any extra data structures that scale with the input size.
