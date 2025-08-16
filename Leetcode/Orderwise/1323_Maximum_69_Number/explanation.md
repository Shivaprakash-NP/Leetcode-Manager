## LeetCode: Maximum 69 Number - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to find the largest possible number by changing at most one digit '6' in the input integer `num` to '9'.  We are given an integer and need to return the largest integer possible by this single substitution.

**2. Approach / Intuition:**

The most efficient way to solve this problem is to iterate through the digits of the number, represented as a string. We look for the first occurrence of the digit '6'.  Upon finding a '6', we replace it with '9' and immediately return the resulting integer. If no '6' is found, it means no replacement is possible, and the original number is returned. This approach is chosen because it's straightforward, avoids unnecessary iterations, and has optimal time complexity.  Converting to a string allows easy digit-by-digit manipulation.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is `StringBuilder`.  This mutable string allows efficient character replacement without creating entirely new strings in each iteration.
* **Algorithms:** The algorithm used is a simple linear scan. We iterate through the digits of the number once.


**4. Code Walkthrough:**

```java
class Solution {
    public int maximum69Number (int num) {
        StringBuilder sb = new StringBuilder(Integer.toString(num)); // Convert the integer to a StringBuilder for easy manipulation
        for(int i = 0; i<sb.length(); i++) { // Iterate through each digit
            if(sb.charAt(i) == '6') { // Check if the digit is '6'
                sb.setCharAt(i, '9'); // Replace '6' with '9'
                return Integer.parseInt(sb.toString()); // Convert back to integer and return the result
            }
        }
        return num; // If no '6' is found, return the original number
    }
}
```

* **Line 1:** We create a `Solution` class containing the `maximum69Number` method.
* **Line 2:**  The input integer `num` is converted to a string using `Integer.toString(num)` and then into a `StringBuilder` object. This allows efficient modification of individual characters.
* **Line 3:** A `for` loop iterates through each character (digit) of the string.
* **Line 4:** Inside the loop, `sb.charAt(i) == '6'` checks if the current digit is '6'.
* **Line 5:** If a '6' is found, `sb.setCharAt(i, '9')` replaces it with '9'.
* **Line 6:** `Integer.parseInt(sb.toString())` converts the modified `StringBuilder` back to an integer, and this value is immediately returned.  The function terminates at this point.
* **Line 8:** If the loop completes without finding a '6', the original number `num` is returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the number of digits in the input integer.  The loop iterates through each digit at most once.  The conversion to and from string and integer are also linear time operations in relation to the number of digits.
* **Space Complexity:** O(n), where n is the number of digits in the input integer. This is due to the use of `StringBuilder` which holds a copy of the input number as a string.  In the worst-case scenario (no '6' found), the space used is proportional to the number of digits.  While the input integer itself takes constant space, the string representation is linearly dependent on the size of the input.  The space complexity could be argued as O(log n) if considering the number of bits needed to represent the integer, but considering the implementation the string representation is more accurate.
