```markdown
## Largest Odd Number in String

### 1. Problem Understanding:

The problem asks us to find the largest odd number that is a substring of a given string `num` consisting of digits. If no such substring exists, we should return an empty string.  By "largest", we mean the longest substring that represents an odd number.

### 2. Approach / Intuition:

The key idea is to iterate through the string from right to left.  An odd number always ends with an odd digit (1, 3, 5, 7, or 9). So, if we find the rightmost odd digit, the substring from the beginning of the original string up to and including that odd digit will be the largest odd number substring. We prioritize finding the rightmost odd digit to ensure the longest possible substring.  If we reach the beginning of the string without finding any odd digits, it means there's no odd number substring, so we return an empty string.

This approach is efficient because it avoids generating and comparing all possible substrings. We directly look for the defining characteristic of an odd number (the last digit) to quickly identify the largest possible one.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  The primary data structure used is the input string `num`. No other significant data structures are used.
*   **Algorithms:** The solution uses a simple linear scan from right to left and the `substring` method.

### 4. Code Walkthrough:

```java
class Solution {
    public String largestOddNumber(String num) {
        for(int i = num.length()-1 ; i>=0 ; i--)
            if (Character.getNumericValue(num.charAt(i)) % 2 == 1)
                return num.substring(0,i+1);
        return "";
    }
}
```

*   **`class Solution { ... }`:** This defines the class containing our solution method.
*   **`public String largestOddNumber(String num) { ... }`:** This is the main method that takes a string `num` as input and returns the largest odd number substring.
*   **`for(int i = num.length()-1 ; i>=0 ; i--)`:** This loop iterates through the string `num` from the last character to the first.  `num.length()-1` gets the index of the last character. `i>=0` ensures we don't go out of bounds. `i--` decrements the index in each iteration.
*   **`if (Character.getNumericValue(num.charAt(i)) % 2 == 1)`:**  This is the core logic.
    *   `num.charAt(i)` gets the character at index `i`.
    *   `Character.getNumericValue(num.charAt(i))` converts the character digit to its integer value. For example, '5' becomes 5.
    *   `% 2 == 1` checks if the integer value is odd. If the remainder when divided by 2 is 1, it's odd.
*   **`return num.substring(0,i+1);`:** If an odd digit is found, this line extracts the substring from the beginning of the string (index 0) up to and including the odd digit (index `i`). `substring(0, i+1)` returns the substring starting at index 0 and ending at index `i` (exclusive of `i+1`, so including `i`). This is the largest odd number substring. The function immediately returns this substring.
*   **`return "";`:** If the loop completes without finding any odd digits, it means there's no odd number substring in the input string.  In this case, the function returns an empty string.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the length of the string `num`.  In the worst case, we might have to iterate through the entire string to find the rightmost odd digit or determine that no odd digit exists. The `substring` operation in Java typically has O(N) time complexity as well, but since we only call substring once upon finding the *first* (rightmost) odd digit, the dominating factor is the single pass through the string. Thus, the overall time complexity remains O(N).

*   **Space Complexity:** O(1).  We're using a constant amount of extra space regardless of the input string's size.  The `substring` method can create a new string object, but this new string's size is bounded by the input string's size. However, in terms of *auxiliary* space used by the algorithm itself, it's constant. We are not using any auxiliary data structures that scale with the size of the input.
