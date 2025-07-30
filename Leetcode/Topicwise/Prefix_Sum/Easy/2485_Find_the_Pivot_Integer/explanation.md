```markdown
## LeetCode Problem: Find the Pivot Integer - Explanation

### 1. Problem Understanding:

The problem asks us to find a "pivot integer" within the range [1, n].  A pivot integer `x` satisfies the condition that the sum of integers from 1 to `x` is equal to the sum of integers from `x` to `n`.  If such an integer exists, we return it; otherwise, we return -1.

### 2. Approach / Intuition:

The key idea is to calculate the total sum of integers from 1 to `n` upfront.  Then, instead of calculating the sum from 1 to `x` and `x` to `n` separately in each iteration, we can iterate *backwards* from `n` to 1 and keep track of the sum of integers from `i` to `n` (represented by `s`).  For a given `i`, we can then easily derive the sum from 1 to `i-1` as `sum - s`.

Why this approach?  Calculating the total sum upfront avoids redundant calculations within the loop.  Iterating backwards and maintaining a running sum from `i` to `n` allows us to efficiently check the pivot condition in each iteration.  We only need to update `s` incrementally instead of recalculating `sum(x to n)` each time. This makes the solution significantly faster than a naive approach that calculates both sums in each iteration.

### 3. Data Structures and Algorithms:

*   **No explicit data structures are used.** The solution works primarily with integer variables.
*   **Algorithm:** The core algorithm is based on calculating the total sum using the formula for the sum of an arithmetic series, and then iterating backward while maintaining a running sum to check for the pivot condition. The formula used is `n * (n + 1) / 2`.

### 4. Code Walkthrough:

```java
class Solution {
    public int pivotInteger(int n) {
        int sum = n*(n+1) / 2; // Calculate the total sum from 1 to n using the arithmetic series formula.

        int s = 0; // Initialize 's' to 0. 's' will store the sum of integers from 'i' to 'n' in each iteration.

        for(int i = n ; i>0 ; i--) { // Iterate backward from 'n' down to 1.
            if(s+i == sum-s) return i; // Check if 'i' is the pivot.
                                         //  - 's + i' is the sum from 'i' to 'n'.
                                         //  - 'sum - s' is the sum from 1 to 'i-1', and adding 'i' provides the sum from 1 to 'i'.  Sum from 1 to `i-1`  is derived from sum from `1 to n` - sum from `i to n`. Since `s` already represents the sum of `i` to `n`, then `sum-s` is the required sum.
                                         // If the sum from 1 to i is equal to the sum from i to n, then return 'i'.

            s += i; // Update 's' to include the current 'i'.  This maintains the running sum from 'i' to 'n' for the next iteration.
        }

        return -1; // If no pivot integer is found within the loop, return -1.
    }
}
```

*   `int sum = n*(n+1) / 2;`:  This line calculates the sum of all integers from 1 to `n` using the formula for the sum of an arithmetic series. This optimizes the calculation as it's done only once at the beginning.
*   `int s = 0;`: This initializes a variable `s` to 0. This variable will accumulate the sum of numbers from the current index `i` down to `n` during the backward iteration.
*   `for(int i = n ; i>0 ; i--)`: This loop iterates backward from `n` down to 1.
*   `if(s+i == sum-s) return i;`: Inside the loop, this condition checks if the current number `i` is the pivot integer.
    *   `s + i` calculates the sum of numbers from `i` to `n`.
    *   `sum - s` computes the sum of numbers from 1 to `i-1`. The sum of numbers from 1 to i becomes `sum - s +i`. Since, we want the sum from 1 to `i` to be equal to the sum of numbers from `i` to `n`, we can check for the equality: `sum-s == s+i`.
*   `s += i;`: After checking if the current number `i` is the pivot, `s` is updated to include `i` in the running sum of numbers from `i` down to `n` for the next iteration.
*   `return -1;`: If the loop completes without finding a pivot integer, the function returns -1.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n)** - The `for` loop iterates from `n` down to 1, performing a constant amount of work in each iteration. Therefore, the time complexity is linear with respect to `n`.
*   **Space Complexity: O(1)** - The solution uses only a few integer variables (`sum`, `s`, `i`), so the space used is constant regardless of the value of `n`. Therefore, the space complexity is constant.
