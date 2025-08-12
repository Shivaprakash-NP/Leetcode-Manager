## LeetCode: Power of Three - Solution Explanation

**1. Problem Understanding:**

The "Power of Three" problem asks whether a given positive integer `n` can be expressed as 3 raised to some non-negative integer power (i.e., is it of the form 3<sup>k</sup> where k ≥ 0).  For example, 27 is a power of three (3<sup>3</sup>), but 10 is not.


**2. Approach / Intuition:**

The provided Java code uses a recursive approach.  The core idea is to repeatedly divide the input number `n` by 3. If `n` is a power of three, repeated division by 3 will eventually result in 1. If at any point the remainder is not 0 (meaning it's not divisible by 3), then `n` cannot be a power of three.  This approach leverages the property of powers of three: they are always divisible by 3, except for the base case of 1.

This recursive approach is chosen for its simplicity and clarity in expressing the core logic.  While iterative approaches are possible, the recursive solution neatly mirrors the mathematical definition of a power of three.


**3. Data Structures and Algorithms:**

* **Data Structures:** No significant data structures are used beyond the integer input `n` and implicit function call stack for the recursion.
* **Algorithms:** The primary algorithm is recursion.


**4. Code Walkthrough:**

* **`rec(int n)` function:** This is a recursive helper function.
    * **`if(n == 1) return true;`:** This is the base case. If `n` becomes 1, it means we've successfully divided the original number by 3 until we reached 1, confirming it's a power of three.
    * **`if(n%3 != 0) return false;`:** This checks if `n` is divisible by 3. If not, it immediately returns `false` because a power of three must be divisible by 3.
    * **`return rec(n/3);`:** This is the recursive step.  It recursively calls `rec()` with `n` divided by 3.  This continues until the base case (`n == 1`) is reached or a non-divisible case is encountered.

* **`isPowerOfThree(int n)` function:** This is the main function.
    * **`if(n == 0) return false;`:** This handles the edge case of input 0. 0 is not considered a power of three.
    * **`return rec(n);`:** This calls the recursive helper function `rec()` to perform the actual power-of-three check.



**5. Time and Space Complexity:**

* **Time Complexity:** O(log<sub>3</sub>n). The recursive function `rec()` is called roughly log<sub>3</sub>n times, as we repeatedly divide `n` by 3 until it becomes 1.  The number of divisions is logarithmic with respect to the input `n`.

* **Space Complexity:** O(log<sub>3</sub>n).  The space complexity is determined by the depth of the recursion stack. In the worst case (when `n` is a power of three), the depth of the recursion is proportional to log<sub>3</sub>n.  This is because each recursive call adds a new stack frame.

**Potential Improvements:**

While the recursive solution is elegant, it's susceptible to stack overflow errors for extremely large values of `n`. An iterative approach would be more robust in such scenarios.  Additionally, a more efficient approach would involve checking if n is divisible by the largest power of 3 smaller than Integer.MAX_VALUE.  This would avoid the repeated divisions.
