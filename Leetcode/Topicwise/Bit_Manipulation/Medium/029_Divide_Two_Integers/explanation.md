## Divide Two Integers - Explained

### 1. Problem Understanding:

The "Divide Two Integers" problem requires us to implement integer division *without* using multiplication, division, or modulo operators. Given two integers, `dividend` and `divisor`, we need to return the quotient of their division, handling edge cases like division by zero (which is implicitly handled as we are not throwing an exception) and potential overflow issues. The result should truncate towards zero.

### 2. Approach / Intuition:

The core idea is to repeatedly subtract the divisor from the dividend until the dividend becomes smaller than the divisor. The number of times we successfully subtract is the quotient. Since we can't use multiplication or division directly, we have to rely on repeated subtraction.

The solution addresses several key considerations:

*   **Sign Handling:** The sign of the result depends on the signs of the dividend and divisor. If they have the same sign, the quotient is positive; otherwise, it's negative.
*   **Overflow Prevention:**  Dividing the minimum integer (`Integer.MIN_VALUE`) by -1 results in a value that exceeds the maximum integer (`Integer.MAX_VALUE`). Similarly, dividing `Integer.MAX_VALUE` by 1 will result in `Integer.MAX_VALUE`. We need to handle these special cases to prevent overflow.
*   **Using Long to Avoid Overflow During Calculation:** To prevent integer overflow when calculating absolute values (especially for `Integer.MIN_VALUE`), we convert both the dividend and divisor to `long` data types.
*   **Absolute Values:** To simplify the repeated subtraction, we work with the absolute values of the dividend and divisor.  The sign is handled separately.
*   **Repeated Subtraction:** Instead of incrementing the quotient by 1 each time, a more efficient approach involves using bit manipulation (which isn't implemented here in the provided code). This code uses the most basic repeated subtraction.

### 3. Data Structures and Algorithms:

*   **Data Structures:** No explicit data structures like arrays or lists are used. Primitive data types (int and long) are used.
*   **Algorithms:** The primary algorithm is repeated subtraction.  The `while` loop iteratively subtracts the (absolute value of the) divisor from the (absolute value of the) dividend until the dividend is smaller than the divisor.

### 4. Code Walkthrough:

```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) 
            return Integer.MAX_VALUE;
        
        if(dividend == Integer.MAX_VALUE && divisor == 1)
            return Integer.MAX_VALUE;
        
        int sign = ((dividend < 0) == (divisor < 0)) ? 1 : -1;
        
        if(Math.abs(divisor)==1)
            return divisor*dividend;
            
        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);
        
        int quotient = 0;
        while (lDividend >= lDivisor) {
            lDividend -= lDivisor;
            quotient++;
        }
        
        return sign * quotient;
    }
}
```

*   **`public int divide(int dividend, int divisor)`:** This is the main function that takes two integers, `dividend` and `divisor`, as input and returns their quotient as an integer.

*   **`if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;`:**  This is the first edge case.  Dividing the smallest possible integer by -1 would result in a number one greater than the largest possible integer, causing an overflow.  So we return `Integer.MAX_VALUE`.

*   **`if(dividend == Integer.MAX_VALUE && divisor == 1) return Integer.MAX_VALUE;`:**  This is the second edge case. Dividing the largest possible integer by 1 results in the largest possible integer.  So we return `Integer.MAX_VALUE`.

*   **`int sign = ((dividend < 0) == (divisor < 0)) ? 1 : -1;`:** This determines the sign of the quotient. The `(dividend < 0) == (divisor < 0)` part evaluates to `true` if both dividend and divisor have the same sign (both positive or both negative) and `false` otherwise.  The ternary operator `?:` assigns 1 to `sign` if the condition is true (positive quotient) and -1 if the condition is false (negative quotient).

*  **`if(Math.abs(divisor)==1) return divisor*dividend;`:** If the divisor is 1 or -1, then the result is simply the dividend multiplied by the divisor.

*   **`long lDividend = Math.abs((long) dividend);`** and **`long lDivisor = Math.abs((long) divisor);`:**  These lines convert the `dividend` and `divisor` to their absolute values and store them as `long` integers.  This conversion is important to prevent overflow when taking the absolute value of `Integer.MIN_VALUE`, since `Math.abs(Integer.MIN_VALUE)` is still `Integer.MIN_VALUE`.

*   **`int quotient = 0;`:** This initializes the `quotient` variable to 0. This variable will store the result of the division.

*   **`while (lDividend >= lDivisor) { ... }`:** This `while` loop performs the repeated subtraction. It continues as long as the absolute value of the dividend is greater than or equal to the absolute value of the divisor.

*   **`lDividend -= lDivisor;`:** Inside the loop, the absolute value of the divisor is subtracted from the absolute value of the dividend.

*   **`quotient++;`:** For each successful subtraction, the `quotient` is incremented.

*   **`return sign * quotient;`:** Finally, the function returns the `quotient` multiplied by the `sign` to get the correct signed result.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n), where n is the quotient.**  In the worst-case scenario (e.g., dividing a large number by 1), the `while` loop will iterate a number of times proportional to the quotient. The running time is linearly dependent on the magnitude of the quotient.
*   **Space Complexity: O(1).**  The solution uses a constant amount of extra space, regardless of the input values. The variables `sign`, `lDividend`, `lDivisor`, and `quotient` take up a fixed amount of memory.
