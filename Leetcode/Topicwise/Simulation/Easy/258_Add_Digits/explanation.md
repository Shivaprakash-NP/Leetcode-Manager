## LeetCode: Add Digits - Solution Explanation

**1. Problem Understanding:**

The "Add Digits" problem asks us to repeatedly sum the digits of a non-negative integer until a single-digit number is obtained.  For example, if the input is 38, the process would be: 3 + 8 = 11, then 1 + 1 = 2. The final result is 2.

**2. Approach / Intuition:**

The provided Java code employs a direct iterative approach. It repeatedly sums the digits of the input number until a single-digit result is achieved.  This is a straightforward way to solve the problem and avoids the use of recursion, making it relatively easy to understand and implement.  A more efficient approach (as we'll see later) exists using the modulo operator and mathematical properties, but this solution is clear and functional.

**3. Data Structures and Algorithms:**

The solution utilizes only simple integer variables. No sophisticated data structures like arrays or linked lists are needed.  The algorithm is essentially iterative, using a `while` loop to repeatedly process the digits.

**4. Code Walkthrough:**

```java
class Solution {
    public int addDigits(int num) {
        int sum = 0; // Initialize a variable to store the sum of digits.
        int tem = num; // Create a temporary variable to avoid modifying the original input.

        while(tem != 0) { // Loop continues until all digits are processed (tem becomes 0).
            int d = tem%10; // Extract the last digit using the modulo operator.
            sum += d;       // Add the digit to the sum.
            int q = sum/10; // Check if the sum is greater than 9.
            sum = q + sum%10; // If sum > 9, reduce it to a single digit by recursively summing its digits.
            tem/=10;        // Remove the last digit from the temporary number.
        }
        int q = sum/10;     //A final check to ensure we end up with a single digit.
        sum = q + sum%10;   //Reduces the sum to a single digit if necessary.
        return sum;         //Return the final single-digit sum.
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** The time complexity is not strictly linear, O(N), where N is the number of digits. In the worst case, if the input number has a sum of digits that is a multiple of 9, the loop might run several times before reducing it to a single digit. However, the number of times the loop runs will still be relatively small compared to the size of the number, making it practically efficient and almost linear.

* **Space Complexity:** The space complexity is O(1).  The algorithm uses only a constant amount of extra space to store variables (`sum`, `tem`, `d`, `q`).  The space used does not depend on the size of the input number.


**Improved Approach (Mathematical Optimization):**

A much more efficient solution exists that leverages the digital root concept. The digital root of a number is the recursive sum of its digits until a single-digit number is obtained.  The digital root can be calculated directly using the formula: `digitalRoot = (num - 1) % 9 + 1`.  This has O(1) time complexity, which is significantly better than the iterative approach.  However, the provided code's clarity makes it a suitable solution for beginners.
