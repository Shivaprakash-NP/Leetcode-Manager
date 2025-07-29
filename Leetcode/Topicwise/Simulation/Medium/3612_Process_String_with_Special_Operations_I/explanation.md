## LeetCode Problem: Process String with Special Operations I

**1. Problem Understanding:**

The problem asks us to process a string `s` containing characters, including special characters '*', '#', and '%'.  These characters represent specific operations: '*' deletes the last character (if any), '#' duplicates the current string, and '%' reverses the string.  The task is to implement a function that applies these operations sequentially and returns the resulting string.


**2. Approach / Intuition:**

The most straightforward approach is to iterate through the input string character by character. We use a `StringBuilder` to efficiently manipulate the string as we process each character.  This approach avoids the inefficiencies of repeated string concatenation using the `+` operator. For each character encountered:

* If it's '*', we remove the last character from the `StringBuilder`.
* If it's '#', we append a copy of the current `StringBuilder` to itself.
* If it's '%', we reverse the `StringBuilder`.
* Otherwise, we append the character to the `StringBuilder`.


This iterative approach is chosen due to its simplicity and clarity.  It directly reflects the sequential nature of the operations defined in the problem.


**3. Data Structures and Algorithms:**

* **Data Structure:** `StringBuilder` is used for efficient string manipulation.  It allows for character appending, deletion, and reversal in O(1) or O(n) amortized time (for reversal).  Using a `String` directly would lead to significantly worse performance due to the immutability of Strings in Java.
* **Algorithm:** The core algorithm is a simple iterative approach with conditional logic to handle different character types.


**4. Code Walkthrough:**

```java
class Solution {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder(); // Initialize a StringBuilder
        for(char c : s.toCharArray()) { // Iterate through each character in the input string
            if(c == '*') { // '*' operation: delete the last character
                if(sb.length() != 0) sb = new StringBuilder(sb.toString().substring(0,sb.length()-1)); //Avoid exception for empty string
            }
            else if(c == '#') { // '#' operation: duplicate the string
                sb.append(sb); 
            }
            else if(c == '%') { // '%' operation: reverse the string
                sb = sb.reverse();
            }
            else { // Append any other character
                sb.append(c);
            }
        }

        return sb.toString(); // Convert the StringBuilder to a String and return
    }
}
```

* **Line 3:**  A `StringBuilder` named `sb` is created to hold the processed string.
* **Line 4:** A `for-each` loop iterates over each character `c` in the input string `s`.
* **Lines 5-14:**  A series of `if-else if-else` statements handle the different character types and their associated operations.  Note the check for empty `StringBuilder` before removing a character in the '*' case.
* **Line 16:** Finally, the `toString()` method converts the `StringBuilder` back to a String, which is then returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n*m), where n is the length of the input string `s` and m is the maximum length of the string during the processing (which could be much larger than n due to the '#' operation). The loop iterates through the string once (O(n)), and operations like appending and reversing take time proportional to the current length of the `StringBuilder`.  In the worst case (many '#' operations), the `StringBuilder` can grow very large, making the overall time complexity  O(n*m).


* **Space Complexity:** O(m), where m is the maximum length of the string during processing.  The `StringBuilder` stores the intermediate results, and its maximum size determines the space complexity. In the worst case (many '#' operations) m can be significantly larger than n.  If we ignore the space used for the input and output strings, the space complexity is O(m).  However, the output string itself could reach a size of O(2^n), which should be considered in the overall space used by the solution, especially in scenarios with excessive '#' characters.
