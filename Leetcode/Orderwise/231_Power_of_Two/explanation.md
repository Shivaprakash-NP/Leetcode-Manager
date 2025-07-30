## Power of Two - Detailed Explanation

### 1. Problem Understanding:

The problem "Power of Two" asks us to determine if a given integer `n` is a power of two. In other words, we need to check if `n` can be expressed as 2 raised to some non-negative integer exponent. For example, 1, 2, 4, 8, 16 are all powers of two, while 3, 5, 6, 7 are not.

### 2. Approach / Intuition:

The solution utilizes a bit manipulation trick to efficiently determine if a number is a power of two.  The core idea is that a power of two in binary representation has only one bit set to 1 (e.g., 2 = 10, 4 = 100, 8 = 1000).

Therefore, if we subtract 1 from a power of two, all the bits to the right of the only set bit will become 1, and the set bit itself will become 0 (e.g., 2 - 1 = 1 = 01, 4 - 1 = 3 = 011, 8 - 1 = 7 = 0111).

If we then perform a bitwise AND operation between the original number `n` and `n-1`, the result will be 0 if `n` is a power of two.

*   **Why this approach?** Bitwise operations are generally very fast, making this a computationally efficient solution. It avoids loops or recursion, leading to constant time complexity.
*   **Edge Cases:** The solution handles `n = 0` and `n = -2147483648` (Integer.MIN_VALUE) as edge cases because Integer.MIN_VALUE in java is a power of 2 but also negative and needs to be handled separately since n-1 will lead to an overflow. The AND operation on these values doesn't yield zero.

### 3. Data Structures and Algorithms:

*   **Data Structures:** No explicit data structures are used.
*   **Algorithms:** Bit manipulation (Bitwise AND operator `&`).

### 4. Code Walkthrough:

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n==0 || n==-2147483648) return false;
        return (((n&(n-1)) == 0)?true:false);
    }
}
```

1.  **`class Solution { ... }`**: This defines a class named `Solution` which is a standard convention in LeetCode problems.
2.  **`public boolean isPowerOfTwo(int n) { ... }`**: This defines the function `isPowerOfTwo` that takes an integer `n` as input and returns a boolean value indicating whether `n` is a power of two.
3.  **`if(n==0 || n==-2147483648) return false;`**: This is an important check for the edge case where n is 0 or Integer.MIN_VALUE. 0 is not a power of two and Integer.MIN_VALUE fails the next check.
4.  **`return (((n&(n-1)) == 0)?true:false);`**: This is the core logic of the solution.
    *   `n & (n - 1)`: This performs a bitwise AND operation between `n` and `n - 1`.
    *   `(n & (n - 1)) == 0`: This checks if the result of the AND operation is equal to 0. If it is, it means that `n` is a power of two.
    *   `? true : false`: This is a ternary operator. If the condition `(n & (n - 1)) == 0` is true, it returns `true`; otherwise, it returns `false`. This simply makes the boolean logic more explicit.  The entire line could be rewritten as `return (n & (n - 1)) == 0;`

### 5. Time and Space Complexity:

*   **Time Complexity:** O(1).  The solution involves a fixed number of bitwise operations, regardless of the input value.
*   **Space Complexity:** O(1). The solution uses a constant amount of extra space. No additional data structures are created that scale with the input.
