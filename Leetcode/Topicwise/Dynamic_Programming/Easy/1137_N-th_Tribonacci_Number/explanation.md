## LeetCode: N-th Tribonacci Number - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find the *n*th number in the Tribonacci sequence.  The Tribonacci sequence is similar to the Fibonacci sequence, but each number is the sum of the three preceding numbers. The sequence starts with T<sub>0</sub> = 0, T<sub>1</sub> = 1, and T<sub>2</sub> = 1.  We need to efficiently calculate T<sub>n</sub> for a given integer *n*.


**2. Approach / Intuition:**

The most straightforward approach to solving this problem is using dynamic programming.  We can build up the Tribonacci sequence iteratively, storing previously calculated values to avoid redundant computations. This is significantly more efficient than a recursive approach, which would suffer from exponential time complexity due to repeated calculations.

Dynamic programming is chosen because it leverages the optimal substructure property inherent in the Tribonacci sequence:  the *n*th Tribonacci number can be efficiently computed from the (n-1)th, (n-2)th, and (n-3)th Tribonacci numbers.


**3. Data Structures and Algorithms:**

* **Data Structure:** We use a 1D array (`dp`) to store the calculated Tribonacci numbers. This array acts as a memoization table, allowing us to access previously computed values in constant time.

* **Algorithm:**  The core algorithm is iterative dynamic programming. We initialize the base cases (T<sub>0</sub>, T<sub>1</sub>, T<sub>2</sub>) and then iteratively compute subsequent Tribonacci numbers using the recurrence relation: T<sub>i</sub> = T<sub>i-1</sub> + T<sub>i-2</sub> + T<sub>i-3</sub>.


**4. Code Walkthrough:**

```java
class Solution {
    public int tribonacci(int n) {
        int[] dp = new int[n+1]; // Initialize DP array to store Tribonacci numbers up to n
        if(n<3) return (n == 0)?0:1; // Handle base cases: T0 = 0, T1 = 1, T2 = 1

        dp[0] = 0; // Initialize base case T0
        dp[1] = 1; // Initialize base case T1
        dp[2] = 1; // Initialize base case T2

        for(int i = 3; i<=n; i++) dp[i] = dp[i-1]+dp[i-2]+dp[i-3]; //Iterative DP calculation

        return dp[n]; // Return the nth Tribonacci number
    }
}
```

* **`int[] dp = new int[n+1];`**:  An array of size `n+1` is created to store the Tribonacci numbers.  We allocate `n+1` because the index of the array represents the Tribonacci number index (from 0 to n).

* **`if(n<3) return (n == 0)?0:1;`**: This handles the base cases for n = 0, 1, and 2 efficiently using a ternary operator.

* **`dp[0] = 0; dp[1] = 1; dp[2] = 1;`**: These lines explicitly initialize the first three Tribonacci numbers in the `dp` array.

* **`for(int i = 3; i<=n; i++) dp[i] = dp[i-1]+dp[i-2]+dp[i-3];`**: This is the core dynamic programming loop. It iterates from 3 up to n (inclusive), calculating each Tribonacci number by summing the three preceding numbers already stored in the `dp` array.

* **`return dp[n];`**: Finally, the function returns the *n*th Tribonacci number stored at index `n` in the `dp` array.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n). The `for` loop iterates `n-2` times, performing a constant amount of work in each iteration.  Therefore, the overall time complexity is linear with respect to *n*.

* **Space Complexity:** O(n).  The `dp` array uses linear space proportional to *n* to store the calculated Tribonacci numbers.  While we could optimize this to O(1) space by only keeping track of the last three Tribonacci numbers, the current solution offers better readability.

In summary, this solution provides an efficient and clear way to calculate the *n*th Tribonacci number using dynamic programming.  The use of an array allows for easy access to previously computed values, resulting in linear time complexity. While the space complexity is linear, it is a reasonable trade-off for the simplicity and readability of the code.
