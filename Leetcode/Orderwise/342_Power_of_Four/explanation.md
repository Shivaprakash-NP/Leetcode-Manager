## LeetCode: Power of Four - Detailed Explanation

**1. Problem Understanding:**

The problem "Power of Four" asks us to determine if a given positive integer `n` is a power of four (i.e., it can be expressed as 4<sup>k</sup> for some non-negative integer k).  For example, 16 (4<sup>2</sup>) is a power of four, while 12 is not.  The input `n` will always be a non-negative integer.


**2. Approach / Intuition:**

The solution uses recursion to efficiently check if a number is a power of four.  The core idea is that repeatedly dividing a power of four by four will eventually result in 1. If at any point the remainder is not 0 (meaning it's not divisible by 4), or we reach 0 before 1, then the number is not a power of four.  This approach is chosen because it directly reflects the mathematical definition of a power of four and is relatively easy to implement recursively.  A non-recursive iterative approach would be equally viable, but recursion often leads to cleaner and more concise code in this specific scenario.


**3. Data Structures and Algorithms:**

* **Data Structures:** No explicit data structures are used beyond the built-in integer type.
* **Algorithms:** Recursion is the primary algorithm used.


**4. Code Walkthrough:**

```java
class Solution {
    private boolean rec(int n) {
        if(n == 1) return true; // Base case: If n becomes 1, it's a power of four.
        if(n == 0 || n%4 != 0) return false; // Base cases: If n is 0 or not divisible by 4, it's not a power of four.

        return rec(n/4); // Recursive step: Divide n by 4 and recursively check.
    }

    public boolean isPowerOfFour(int n) {
        return rec(n);    
    }
}
```

* **`rec(int n)` function:** This recursive helper function does the main work.
    * **`if(n == 1) return true;`**: This is the base case. If `n` is 1, it means we have successfully reduced the original number through repeated division by 4, indicating it was a power of 4.
    * **`if(n == 0 || n%4 != 0) return false;`**: This handles two base cases:
        * `n == 0`: If `n` becomes 0 during the process, the original number wasn't a power of 4.
        * `n%4 != 0`: If `n` is not divisible by 4 at any point, it cannot be a power of 4.
    * **`return rec(n/4);`**: This is the recursive step.  We recursively call `rec` with `n/4`. This effectively continues the process of dividing by 4 until one of the base cases is met.

* **`isPowerOfFour(int n)` function:** This is the main function that simply calls the recursive helper function `rec(n)` to get the result.


**5. Time and Space Complexity:**

* **Time Complexity:** O(log<sub>4</sub>n). The recursive function `rec` is called approximately log<sub>4</sub>n times, as we are repeatedly dividing n by 4.  The number of recursive calls is directly proportional to the number of times we can divide n by 4 before reaching 1.  This is logarithmic time complexity.

* **Space Complexity:** O(log<sub>4</sub>n).  The space complexity is determined by the recursive call stack.  In the worst case, the depth of the recursion is proportional to log<sub>4</sub>n, leading to a logarithmic space complexity.  This is because each recursive call adds a new frame to the call stack.  For very large numbers, this could become a concern, though in practice it's unlikely to be a significant problem for typical input sizes.  An iterative solution would reduce space complexity to O(1).


**Improvements:**

While the recursive solution is elegant, an iterative solution would be slightly more efficient in terms of space complexity. An iterative solution would avoid the overhead of recursive function calls.  Additionally, error handling for negative input could be added for robustness.
