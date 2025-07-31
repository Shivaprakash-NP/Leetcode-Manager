## Reverse Integer Problem and Solution Explanation

### 1. Problem Understanding:

The "Reverse Integer" problem on LeetCode asks us to reverse the digits of a given 32-bit signed integer. If the reversed value overflows (goes beyond the range of a 32-bit signed integer, which is [-2<sup>31</sup>, 2<sup>31</sup> - 1]), we should return 0.

### 2. Approach / Intuition:

The core idea is to extract the digits of the integer one by one, starting from the least significant digit, and construct the reversed integer. To avoid issues with negative numbers, we first store the sign of the original integer and work with its absolute value.

The main challenge is to detect potential overflow. Multiplying the reversed number by 10 in each step can easily cause it to exceed the `Integer.MAX_VALUE` or fall below `Integer.MIN_VALUE`.  We use a `long` variable to store the reversed number during the construction process. This allows us to temporarily hold values outside the `int` range. After constructing the reversed number (as a `long`), we check if the signed reversed value falls within the `int` range. If it doesn't, we return 0; otherwise, we cast it to `int` and return the result.

This approach is chosen for its simplicity and directness.  It mimics the manual process of reversing a number and explicitly addresses the overflow issue.

### 3. Data Structures and Algorithms:

*   **Data Structures:** We primarily use primitive data types: `int` and `long`. The `long` is used to temporarily store the reversed value to prevent overflow during intermediate calculations.
*   **Algorithms:** We use the modulo operator (%) to extract the last digit of the integer, integer division (/) to remove the last digit, and a `while` loop to iterate through the digits. These operations are fundamental arithmetic operations.

### 4. Code Walkthrough:

```java
class Solution {
    public int reverse(int x) {
        int s = (x>=0)?1:-1;
        x = Math.abs(x);
        long ans = 0;
        while(x>0)
        {
            int d = x%10;
            ans = ans*10+d;
            x/=10;
        }
        if(s*ans>Integer.MAX_VALUE || s*ans<Integer.MIN_VALUE) return 0;
        return s*(int)ans;
    }
}
```

1.  **`int s = (x>=0)?1:-1;`**: This line determines the sign of the input integer `x`. If `x` is non-negative, `s` is set to 1; otherwise, `s` is set to -1.  This stores the sign for later use.

2.  **`x = Math.abs(x);`**: This line takes the absolute value of `x`.  We work with the absolute value to simplify the reversal logic.

3.  **`long ans = 0;`**: This line initializes a `long` variable `ans` to 0. `ans` will store the reversed integer as we build it. The `long` type is used to prevent potential overflow during the construction.

4.  **`while(x>0)`**: This loop continues as long as there are digits remaining in `x` to be processed.

5.  **`int d = x%10;`**: Inside the loop, this line extracts the last digit of `x` using the modulo operator (`%`).  The last digit is stored in `d`.

6.  **`ans = ans*10+d;`**: This is the core logic for reversing the digits. It multiplies the current reversed integer (`ans`) by 10 (shifting the digits to the left) and adds the newly extracted digit `d` to the rightmost position.

7.  **`x/=10;`**: This line removes the last digit from `x` by performing integer division by 10.

8.  **`if(s*ans>Integer.MAX_VALUE || s*ans<Integer.MIN_VALUE) return 0;`**: This is the crucial overflow check.  It multiplies the reversed integer (`ans`) by the original sign (`s`) and checks if the result is outside the valid range for a 32-bit signed integer.  If overflow is detected, the function returns 0.

9.  **`return s*(int)ans;`**: If no overflow occurred, this line casts the `long` value `ans` back to an `int` and multiplies it by the original sign `s` to get the final reversed integer. This integer is then returned.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(log<sub>10</sub>(x)).  The `while` loop iterates once for each digit in the input integer `x`. The number of digits in an integer `x` is proportional to log<sub>10</sub>(x). Therefore, the time complexity is logarithmic.

*   **Space Complexity:** O(1).  The solution uses a fixed amount of extra space, regardless of the size of the input integer. We only use a few integer variables (`s`, `x`, `d`, `ans`), so the space complexity is constant.
