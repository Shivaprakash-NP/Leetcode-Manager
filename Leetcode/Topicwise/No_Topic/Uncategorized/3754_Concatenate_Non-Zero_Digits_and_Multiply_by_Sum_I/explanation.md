### Problem Understanding

The problem asks us to perform a series of operations on a given integer `n`:

1.  **Convert to String:** First, the integer `n` is to be treated as a sequence of digits. This usually implies converting it to a string.
2.  **Filter Non-Zero Digits:** From this sequence, we need to identify and extract only the non-zero digits.
3.  **Concatenate Non-Zero Digits:** These non-zero digits are then concatenated in their original order to form a new number. For example, if `n` is `10203`, the non-zero digits are `1`, `2`, `3`, and the new concatenated number would be `123`.
4.  **Sum Non-Zero Digits:** Simultaneously, we need to calculate the sum of these same non-zero digits. For `10203`, the sum would be `1 + 2 + 3 = 6`.
5.  **Multiply and Return:** Finally, the concatenated number (from step 3) is multiplied by the sum of non-zero digits (from step 4). The result should be returned as a `long` to accommodate potentially large products.
6.  **Edge Case:** If `n` is `0`, the result should be `0`. Also, if after removing all '0's, no digits remain (which only happens if `n` was `0`), the result should also be `0`.

### Approach / Intuition

The core idea revolves around processing `n` digit by digit. Since direct digit access and manipulation of an integer can be cumbersome (involving modulo and division operations), converting `n` to its string representation simplifies the task significantly.

Here's the high-level strategy:

1.  **String Conversion:** Convert the input integer `n` into a `String`. This allows easy iteration over its individual digits as characters.
2.  **Iterate and Process:** Loop through each character of the string.
3.  **Conditional Logic:** Inside the loop, for each character:
    *   Check if the character is '0'.
    *   If it's '0', ignore it and move to the next character (as per the "non-zero digits" requirement).
    *   If it's a non-zero digit:
        *   Append it to a `StringBuilder`. This `StringBuilder` will accumulate the characters that form our new concatenated number. Using `StringBuilder` is efficient for repeated appends compared to `String` concatenation.
        *   Convert the character digit back to its integer value (e.g., '5' becomes 5) and add it to a running `sum` variable.
4.  **Final Calculation:** After the loop finishes:
    *   Check if the `StringBuilder` is empty. If it is, it means `n` was `0` or effectively contained no non-zero digits, so return `0`.
    *   Convert the content of the `StringBuilder` into a `long` using `Long.parseLong()`. This gives us the concatenated non-zero number.
    *   Multiply this `long` value by the `sum` calculated earlier.
    *   Return the product.

This approach was chosen because string manipulation provides a straightforward way to handle digit-by-digit processing, and `StringBuilder` offers optimal performance for building strings incrementally. Using `long` for the sum and the final product ensures we don't encounter integer overflow issues.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `String`: Used to hold the string representation of the input integer `n`, allowing easy character-by-character access.
    *   `StringBuilder`: Employed to efficiently construct the new number by concatenating the non-zero digits. Its `append()` method is optimized for this task.
*   **Algorithms:**
    *   **String Conversion:** The implicit conversion `n + ""` (or explicit `String.valueOf(n)`) is used to turn an `int` into a `String`.
    *   **Iteration:** A standard `for` loop is used to traverse each character (digit) of the string.
    *   **Conditional Logic:** An `if` statement checks if a digit is '0' to decide whether to include it in the concatenation and sum.
    *   **Character to Integer Conversion:** `s.charAt(i) - '0'` is a common idiom to convert a digit character (e.g., '7') to its corresponding integer value (e.g., 7).
    *   **String to Long Conversion:** `Long.parseLong()` is used to convert the final string built by `StringBuilder` into a `long` numeric type, which is necessary for the multiplication.

### Code Walkthrough

```java
class Solution {
    public long sumAndMultiply(int n) {
        // 1. Handle the base case: if n is 0.
        // If n is 0, there are no non-zero digits. Both the concatenated number and the sum would be 0.
        // The product would therefore be 0. This is an early exit for efficiency and clarity.
        if(n == 0) return 0;

        // 2. Convert the integer n into its string representation.
        // This allows us to easily iterate over its individual digits as characters.
        String s = n+""; // Equivalent to String.valueOf(n)

        // 3. Initialize a long variable to store the sum of non-zero digits.
        // Using 'long' ensures that the sum (though unlikely to overflow for int n) and especially the final product
        // can hold large values without overflow.
        long sum = 0;

        // 4. Initialize a StringBuilder to construct the new number formed by concatenating non-zero digits.
        // StringBuilder is more efficient than String concatenation (+) when appending characters repeatedly in a loop.
        StringBuilder sb = new StringBuilder();

        // 5. Iterate through each character (digit) of the string representation of n.
        for(int i = 0; i<s.length(); i++) {
            // 6. Check if the current character is '0'.
            // If it is '0', we skip it as per the problem's requirement to only consider non-zero digits.
            if(s.charAt(i) == '0') continue; // Move to the next iteration of the loop

            // 7. If the digit is not '0':
            //    a. Append the non-zero digit character to the StringBuilder.
            //       This