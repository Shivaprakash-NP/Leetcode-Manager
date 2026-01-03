### Problem Understanding

The problem asks us to determine if a given integer `n` is an "ugly number". An ugly number is defined as a positive integer whose prime factors are limited to 2, 3, and 5. By convention, 1 is also considered an ugly number (as it has no prime factors, or all its prime factors are vacuously from the set {2, 3, 5}). Numbers like 6 (2 * 3), 8 (2 * 2 * 2), 15 (3 * 5) are ugly. Numbers like 7, 14 (2 * 7), 26 (2 * 13) are not ugly because they contain prime factors other than 2, 3, or 5.

### Approach / Intuition

The core idea behind solving this problem comes directly from the definition of an ugly number. If a number `n` is ugly, it means that all its prime factors must be either 2, 3, or 5.

This implies that if we take an ugly number and continuously divide it by 2, then by 3, then by 5 (removing all occurrences of these prime factors), the number should eventually reduce to 1. If, after dividing out all possible factors of 2, 3, and 5, the number is still greater than 1, it means that the original number must have contained at least one prime factor other than 2, 3, or 5 (e.g., 7, 11, 13, etc.). In such a case, the number is not ugly.

**Example:**
Let's take `n = 12`:
1.  Divide by 2: `12 / 2 = 6` (still divisible by 2)
2.  Divide by 2: `6 / 2 = 3` (not divisible by 2 anymore)
3.  Divide by 3: `3 / 3 = 1` (not divisible by 3 anymore)
4.  Not divisible by 5.
5.  Result is 1. So, 12 is ugly.

Let's take `n = 14`:
1.  Divide by 2: `14 / 2 = 7` (not divisible by 2 anymore)
2.  Not divisible by 3.
3.  Not divisible by 5.
4.  Result is 7. Since 7 is not 1, 14 is not ugly.

**Edge Cases:**
*   **`n = 0`**: Ugly numbers are positive integers. 0 is not positive, so it's not ugly.
*   **`n < 0`**: Negative numbers are also not positive integers, so they are not ugly. The current logic correctly handles this: a negative number will never become 1 through division by positive numbers, so `return n == 1` will correctly evaluate to `false`.
*   **`n = 1`**: 1 is an ugly number. The loops won't execute as 1 is not divisible by 2, 3, or 5. `n` remains 1, and `return n == 1` correctly evaluates to `true`.

### Data Structures and Algorithms

*   **Data Structures:** No complex data structures are used. The solution operates solely on the input integer `n`.
*   **Algorithms:**
    *   **Iterative Division:** The core algorithm involves repeated division of the number by its prime factors (2, 3, 5) using `while` loops.
    *   **Modulo Operator:** The modulo operator (`%`) is used to check for divisibility.

### Code Walkthrough

```java
class Solution {
    public boolean isUgly(int n) {
        // 1. Handle the edge case for n = 0.
        // Ugly numbers are defined as positive integers. 0 is not positive.
        if(n == 0) return false;

        // 2. Repeatedly divide n by 2 until it's no longer divisible by 2.
        // This removes all prime factors of 2 from n.
        while(n%2 == 0) n = n/2;

        // 3. Repeatedly divide n by 3 until it's no longer divisible by 3.
        // This removes all prime factors of 3 from n.
        while(n%3 == 0) n = n/3;

        // 4. Repeatedly divide n by 5 until it's no longer divisible by 5.
        // This removes all prime factors of 5 from n.
        while(n%5 == 0) n = n/5;

        // 5. After dividing out all factors of 2, 3, and 5:
        // If the remaining n is 1, it means all its original prime factors were indeed 2, 3, or 5.
        // Thus, the number is ugly.
        // If n is not 1 (e.g., it's 7, 11, etc.), it means it had other prime factors,
        // and therefore it's not an ugly number.
        // This also correctly handles negative numbers, as they would never become 1.
        return n == 1;
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(log n)**
    *   The `while` loops repeatedly divide `n` by 2, 3, or 5. In the worst case, `n` is reduced by a factor of at least 2 in each step of the first loop, then by 3 in the second, and by 5 in the third.
    *   The number of divisions required to reduce `n` to 1 is logarithmic with respect to `n`. For example, repeatedly dividing by 2 takes `log_2(n)` steps.
    *   Since we perform a constant number of such logarithmic operations (for factors 2, 3, and 5), the overall time complexity is proportional to `log n`.

*   **Space Complexity: O(1)**
    *   The solution uses a fixed, constant amount of memory regardless of the input `n`. Only a single integer variable `n` is modified in place. No auxiliary data structures are created.