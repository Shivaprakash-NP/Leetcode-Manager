### Problem Understanding

The problem "Smallest Number With All Set Bits" asks us to find the smallest integer `x` that satisfies two conditions:
1.  `x` must be greater than or equal to the given input integer `n` (`x >= n`).
2.  `x` must have all its bits set up to its most significant bit (MSB). This means `x` must be of the form `2^k - 1` for some non-negative integer `k`. Examples of such numbers are:
    *   `k=0`: `2^0 - 1 = 0` (binary `0`)
    *   `k=1`: `2^1 - 1 = 1` (binary `1`)
    *   `k=2`: `2^2 - 1 = 3` (binary `11`)
    *   `k=3`: `2^3 - 1 = 7` (binary `111`)
    *   `k=4`: `2^4 - 1 = 15` (binary `1111`)

For instance, if `n = 5` (binary `101`), the smallest number `x >= 5` with all bits set is `7` (binary `111`). If `n = 8` (binary `1000`), the smallest `x >= 8` with all bits set is `15` (binary `1111`).

### Approach / Intuition

The core idea relies on the observation that numbers with all bits set up to their MSB are always of the form `2^k - 1`. Our goal is to find the smallest `k` such that `2^k - 1 >= n`.

Let's consider the number of bits required to represent `n`.
*   If `n = 5` (binary `101`), it requires 3 bits. The smallest `2^k - 1` that is `>= 5` is `2^3 - 1 = 7`.
*   If `n = 7` (binary `111`), it requires 3 bits. The smallest `2^k - 1` that is `>= 7` is `2^3 - 1 = 7`.
*   If `n = 8` (binary `1000`), it requires 4 bits. The smallest `2^k - 1` that is `>= 8` is `2^4 - 1 = 15`.

Notice a pattern: the `k` we are looking for is essentially the number of bits needed to represent `n` (its "bit length").
The Java `Integer.numberOfLeadingZeros(n)` method is very useful here. For a 32-bit integer, it returns the count of zero bits preceding the highest-order (most significant) one-bit.
*   For `n = 5` (binary `0...0101`), `Integer.numberOfLeadingZeros(5)` returns `29` (since `32 - 3 = 29`).
*   For `n = 8` (binary `0...01000`), `Integer.numberOfLeadingZeros(8)` returns `28` (since `32 - 4 = 28`).

Therefore, `32 - Integer.numberOfLeadingZeros(n)` directly gives us the number of bits required to represent `n`. Let's call this `len`.
Once we have `len`, the desired number `x` is simply `2^len - 1`. This can be efficiently calculated using a bit shift: `(1 << len) - 1`.

*   If `n = 5`, `len = 3`. Result: `(1 << 3) - 1 = 8 - 1 = 7`.
*   If `n = 8`, `len = 4`. Result: `(1 << 4) - 1 = 16 - 1 = 15`.

This approach correctly finds the smallest `2^k - 1` that is greater than or equal to `n`.

### Data Structures and Algorithms

*   **Data Structures:** No complex data structures are used. The solution operates solely with primitive integer types.
*   **Algorithms:**
    *   **Bit Manipulation:** The left shift operator (`<<`) is used to efficiently calculate powers of 2.
    *   **Built-in Function:** `Integer.numberOfLeadingZeros()` is a highly optimized intrinsic function that directly provides the count of leading zero bits.

### Code Walkthrough

```java
class Solution {
    public int smallestNumber(int n) {
        // Step 1: Calculate the number of bits required to represent 'n'.
        // Integer.numberOfLeadingZeros(n) returns the count of zero bits
        // before the most significant '1' bit in the 32-bit binary representation of 'n'.
        //
        // Examples for a 32-bit integer:
        // If n = 5 (binary 0...0101), Integer.numberOfLeadingZeros(5) = 29.
        //   The number of bits needed for 5 is 3 (101). So, len = 32 - 29 = 3.
        // If n = 8 (binary 0...01000), Integer.numberOfLeadingZeros(8) = 28.
        //   The number of bits needed for 8 is 4 (1000). So, len = 32 - 28 = 4.
        // If n = 0, Integer.numberOfLeadingZeros(0) = 32.
        //   The number of bits needed for 0 is 0. So, len = 32 - 32 = 0.
        int len = 32 - Integer.numberOfLeadingZeros(n);

        // Step 2: Construct the number with 'len' set bits.
        // (1 << len) calculates 2 raised to the power of 'len'.
        // Examples:
        // If len = 3, (1 << 3) = 8 (binary 1000).
        // If len = 4, (1 << 4) = 16 (binary 10000).
        //
        // Subtracting 1 from 2^len results in a number where the 'len' least significant bits are all set to 1.
        // This is precisely the form '2^k - 1' we are looking for.
        // Examples:
        // 8 - 1 = 7 (binary 0111)
        // 16 - 1 = 15 (binary 01111)
        //
        // For the special case n = 0:
        // len = 0.
        // (1 << 0) - 1 = 1 - 1 = 0. This is correct as 0 is the smallest number >= 0 with all bits set (0 bits).
        return (1 << len) - 1;
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(1)**
    *   The `Integer.numberOfLeadingZeros(n)` method is implemented using highly optimized CPU instructions (e.