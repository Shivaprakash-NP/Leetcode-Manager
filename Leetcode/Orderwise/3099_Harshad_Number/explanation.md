## Harshad Number Problem Explanation and Solution

Here's a detailed explanation of the provided Java code for solving the "Harshad Number" problem:

### 1. Problem Understanding:

The problem asks us to determine if a given number `x` is a Harshad number. A Harshad number (also known as a Niven number) is an integer that is divisible by the sum of its digits. If `x` is a Harshad number, we need to return the sum of its digits. Otherwise, return -1.

### 2. Approach / Intuition:

The approach is straightforward:

1.  **Calculate the sum of the digits of the input number `x`.**  We achieve this by repeatedly extracting the last digit using the modulo operator (`% 10`), adding it to a `sum`, and then removing the last digit by integer division (`/ 10`). This continues until the number becomes 0.
2.  **Check if `x` is divisible by the calculated sum.**  We use the modulo operator again (`%`) to determine if the remainder of `x` divided by `sum` is 0.
3.  **Return the appropriate value.** If the remainder is 0, it means `x` is divisible by `sum`, and we return `sum`.  Otherwise, we return -1.

This approach is chosen because it directly implements the definition of a Harshad number in a simple and efficient manner.

### 3. Data Structures and Algorithms:

*   **Data Structures:** No complex data structures are used. We use integer variables to store the original number, the sum of digits, and a temporary variable.
*   **Algorithms:** The core algorithm is based on the modulo operator (`%`) and integer division (`/`) to extract and remove digits from a number. This is a standard technique for digit-by-digit processing.  The solution also implicitly uses the concept of iteration via the `while` loop to process each digit of the number.

### 4. Code Walkthrough:

```java
class Solution {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int tem = x; // Create a temporary variable 'tem' to store the original number. This is important because we will be modifying 'tem' inside the loop.
        int sum = 0; // Initialize a variable 'sum' to store the sum of the digits.

        while(tem != 0) { // Loop until 'tem' becomes 0. This ensures that we process all digits of the number.
            sum += tem%10; // Extract the last digit of 'tem' using the modulo operator (% 10) and add it to the 'sum'.
            tem/=10; // Remove the last digit of 'tem' by integer division (/= 10).
        }

        return (x%sum==0)?sum:-1; // Check if 'x' is divisible by 'sum'.  If yes, return 'sum'. Otherwise, return -1. This is a concise way to write an if-else statement using the ternary operator.
    }
}
```

**Detailed breakdown:**

1.  **`int tem = x;`:**  This line creates a copy of the input number `x` and stores it in the variable `tem`. This ensures that the original value of `x` is preserved, as the `while` loop will modify `tem`.
2.  **`int sum = 0;`:**  This initializes a variable `sum` to 0. This variable will accumulate the sum of the digits of `x`.
3.  **`while(tem != 0)`:**  This loop continues as long as `tem` is not zero. The loop iteratively processes each digit of `tem`.
4.  **`sum += tem%10;`:**  This line calculates the remainder when `tem` is divided by 10. This remainder is the last digit of `tem`. The last digit is then added to the `sum`.
5.  **`tem/=10;`:** This line performs integer division of `tem` by 10. This effectively removes the last digit from `tem`. For example, if `tem` was 123, `tem /= 10` would make `tem` equal to 12.
6.  **`return (x%sum==0)?sum:-1;`:** This is a ternary operator that checks if `x` is divisible by `sum`.
    *   `x % sum == 0`: This expression calculates the remainder when `x` is divided by `sum`. If the remainder is 0, it means `x` is divisible by `sum`.
    *   `? sum : -1`: This is the ternary operator. If the condition `x % sum == 0` is true, the expression evaluates to `sum`. Otherwise, it evaluates to `-1`. The return statement returns this result.

### 5. Time and Space Complexity:

*   **Time Complexity: O(log<sub>10</sub>(x))**
    *   The `while` loop iterates once for each digit in the number `x`.  The number of digits in `x` is proportional to the base-10 logarithm of `x`. Therefore, the time complexity is O(log<sub>10</sub>(x)).
*   **Space Complexity: O(1)**
    *   The code uses a constant amount of extra space, regardless of the size of the input number `x`.  The variables `tem` and `sum` take up a fixed amount of memory.  Therefore, the space complexity is O(1).
