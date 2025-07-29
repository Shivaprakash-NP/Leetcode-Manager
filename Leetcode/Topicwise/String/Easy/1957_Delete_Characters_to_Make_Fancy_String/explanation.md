## LeetCode: Delete Characters to Make Fancy String - Solution Explained

**1. Problem Understanding:**

The problem asks us to modify a given string `s` to make it "fancy". A fancy string is defined as a string where no three consecutive characters are the same.  We need to remove characters from the input string to achieve this, while preserving the order of the remaining characters.

**2. Approach / Intuition:**

The solution uses a greedy approach.  It iterates through the string, keeping track of the count of consecutive identical characters.  If the count of consecutive identical characters reaches 3, it skips adding the current character to the result.  This ensures that no three consecutive characters are identical in the final "fancy" string.  A greedy approach is suitable here because we only need to consider the immediate neighborhood of each character (the preceding characters) to determine whether it needs to be removed.  Looking further ahead wouldn't improve the solution.

**3. Data Structures and Algorithms:**

* **Data Structures:**  `StringBuilder` is used to efficiently build the modified string.  Using `StringBuilder` avoids the overhead of repeatedly creating new strings during concatenation, which is more efficient than using String concatenation directly.
* **Algorithms:** The core algorithm is a simple iterative approach with a sliding window of size at most 3.


**4. Code Walkthrough:**

```java
class Solution {
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(); // Initialize StringBuilder to store the result.
        int c = 1; // Initialize counter for consecutive identical characters.

        for(int i = 0; i<n-1; i++) { // Iterate through the string (excluding the last character).
            if(c<3) sb.append(s.charAt(i)); // If less than 3 consecutive identical chars, append to result.
            if(s.charAt(i) == s.charAt(i+1)) c++; // Increment counter if the current character is same as the next.
            else c = 1; // Reset counter if characters are different.
        }
        if(c<3) sb.append(s.charAt(n-1)); //Handle the last character separately.

        return sb.toString(); //Convert StringBuilder to String and return.
    }
}
```

* **`int n = s.length();`**: Gets the length of the input string.
* **`StringBuilder sb = new StringBuilder();`**: Creates a `StringBuilder` object to store the resulting fancy string.
* **`int c = 1;`**: Initializes a counter `c` to 1. This variable tracks the number of consecutive occurrences of the same character.
* **`for(int i = 0; i<n-1; i++)`**:  The loop iterates through the string from the beginning up to the second-to-last character.  This is because the comparison `s.charAt(i) == s.charAt(i+1)` requires accessing `i+1`.
* **`if(c<3) sb.append(s.charAt(i));`**: If the count `c` of consecutive identical characters is less than 3, the current character is appended to the `StringBuilder`.
* **`if(s.charAt(i) == s.charAt(i+1)) c++;`**: If the current character is the same as the next character, the counter `c` is incremented.
* **`else c = 1;`**: If the current character is different from the next, the counter is reset to 1.
* **`if(c<3) sb.append(s.charAt(n-1));`**: After the loop, this line handles the last character of the string.  It appends it to the `StringBuilder` only if it doesn't violate the "fancy" string condition.
* **`return sb.toString();`**: Finally, the `StringBuilder` is converted to a string and returned.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input string. The code iterates through the string once.  All operations within the loop are constant time.
* **Space Complexity:** O(n) in the worst case. This is because the `StringBuilder` could potentially store a copy of the entire input string if no characters need to be removed. In the best case (the input string is already fancy), the space complexity would be O(1).  However, we generally focus on the worst-case scenario for complexity analysis. Therefore, we consider the space complexity to be O(n).
