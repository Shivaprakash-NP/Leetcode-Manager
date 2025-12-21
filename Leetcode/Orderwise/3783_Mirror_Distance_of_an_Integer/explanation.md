### Problem Understanding

The problem "Mirror Distance of an Integer" asks us to calculate a specific value for a given integer `n`. Based on the provided code, the "mirror distance" is defined as the absolute difference between the original integer `n` and the integer formed by reversing the order of its digits.

For example, if `n = 123`:
1.  The reversed version of `n` would be `321`.
2.  The mirror distance is the absolute difference: `|123 - 321| = |-198| = 198`.

### Approach / Intuition

The core idea behind the solution is to first obtain the "mirror" (reversed) version of the input integer `n` and then compute the absolute difference between `n` and its mirror.

The most straightforward and robust way to reverse the digits of an integer is to leverage string manipulation:
1.  **Convert the integer to a string:** This allows us to treat the digits as individual characters in a sequence.
2.  **Reverse the string:** Standard string or `StringBuilder` utilities can easily reverse the character sequence.
3.  **Convert the reversed string back to an integer:** This gives us the numerical value of the reversed digits.
4.  **Calculate the absolute difference:** Subtract the original integer from the reversed integer and take the absolute value.

This approach is chosen for its simplicity and clarity. It avoids complex mathematical loops involving modulo (`%`) and division (`/`) operations, which can sometimes be trickier to implement correctly, especially when dealing with potential edge cases like numbers ending in zero (e.g., `120` reversed is `021`, which `Integer.parseInt` correctly interprets as `21`).

### Data Structures and Algorithms

*   **Data Structures:**
    *   `StringBuilder`: This mutable sequence of characters is used to efficiently build and reverse the string representation of the integer. Unlike `String` objects, `StringBuilder` allows modifications (like `reverse()`) without creating new objects, making it more efficient for such operations.
*   **Algorithms:**
    *   **Integer to String Conversion:** The expression `n + ""` (or `String.valueOf(n)`) converts the integer `n` into its decimal string representation.
    *   **String Reversal:** The `StringBuilder.reverse()` method is an in-place algorithm that reverses the order of characters within the `StringBuilder` object.
    *   **String to Integer Conversion:** `Integer.parseInt(String s)` converts a string representation of an integer back into its primitive `int` type.
    *   **Absolute Value:** `Math.abs(int a)` calculates the absolute value of the difference between the two integers.

### Code Walkthrough

```java
class Solution {
    public int mirrorDistance(int n) {
        // 1. Convert the integer 'n' to its string representation and
        //    initialize a StringBuilder with it.
        //    The expression `n + ""` is a concise way to convert an integer to a String.
        //    Example: if n = 123, then (n + "") becomes "123".
        //    The StringBuilder 'sb' is initialized with "123".
        StringBuilder sb = new StringBuilder(n+"");

        // 2. Reverse the sequence of characters currently held within the StringBuilder 'sb'.
        //    This operation modifies 'sb' in place.
        //    Example: if sb contained "123", after this line it will contain "321".
        sb.reverse();

        // 3. Convert the content of the reversed StringBuilder back into an integer.
        //    sb.toString() converts the StringBuilder's content (e.g., "321") into a String object.
        //    Integer.parseInt("321") then parses this string to its integer equivalent (321).
        //    This reversed integer value is stored in the variable 'v'.
        int v = Integer.parseInt(sb.toString());

        // 4. Calculate the absolute difference between the original integer 'n'
        //    and the newly obtained reversed integer 'v'.
        //    Math.abs() ensures that the result is always non-negative.
        //    Example: if n = 123 and v = 321, then Math.abs(123 - 321) = Math.abs(-198) = 198.
        //    This final calculated mirror distance is returned as the result.
        return Math.abs(n-v);
    }
}
```

### Time and Space Complexity

Let `D` be the number of digits in the input integer `n`. The number of digits `D` is approximately `log10(n) + 1`.

*   **Time Complexity:**
    *   `StringBuilder sb = new StringBuilder(n+"");`: Converting an integer to a string takes time proportional to the number of digits, `O(D)`.
    *   `sb.reverse();`: Reversing a `StringBuilder` of length `D` takes `O(D)` time, as it iterates through approximately half of its characters.
    *   `int v = Integer.parseInt(sb.toString());`: Converting a string of length `D` back to an integer takes `O(D)` time.
    *   `return Math.abs(n-v);`: Calculating the absolute difference is a constant time operation, `O(1)`.

    All operations scale linearly with the number of digits. Therefore, the overall time complexity is **O(D)**, which can also be expressed as **O(log n)** since the number of digits grows logarithmically with the value of `n`.

*   **Space Complexity:**
    *   `StringBuilder sb = new StringBuilder(n+"");`: A `StringBuilder` object is created to store the string representation of `n`. This string will have `D` characters.
    *   No other significant data structures are allocated that grow with the input size `n` beyond its digit count.

    Therefore, the overall space complexity is **O(D)**, or equivalently **O(log n)**, due to the storage required for the `StringBuilder`.