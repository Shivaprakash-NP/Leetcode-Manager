## Percentage of Letter in String - LeetCode Problem Explanation

Here's a detailed explanation of the provided Java code for the LeetCode problem "Percentage of Letter in String".

### 1. Problem Understanding:

The problem asks us to calculate the percentage of a given character (`letter`) within a given string (`s`). The percentage should be an integer, achieved by multiplying the count of the character `letter` by 100, and then dividing by the total length of the string `s`. We need to return this integer percentage.

### 2. Approach / Intuition:

The most straightforward approach is to:

1.  Iterate through the string `s`.
2.  Count the number of times the character `letter` appears.
3.  Calculate the percentage using the formula: `(count * 100) / string_length`.
4.  Return the integer part of the percentage.

This approach is chosen for its simplicity and efficiency. It directly addresses the problem requirement without needing complex algorithms or data structures.

### 3. Data Structures and Algorithms:

*   **Data Structures:** No complex data structures are used. The input is a `String`.  The `toCharArray()` method converts the string to a character array for easy iteration.  A simple integer variable `c` is used to store the count.
*   **Algorithms:** The core algorithm is a simple linear search/iteration through the string.

### 4. Code Walkthrough:

```java
class Solution {
    public int percentageLetter(String s, char letter) {
        int c = 0; // Initialize a counter to store the number of occurrences of 'letter'

        for(char cc : s.toCharArray()) { // Iterate through each character in the string 's'
            if(cc == letter) { // Check if the current character 'cc' is equal to the target 'letter'
                c++; // If they are equal, increment the counter 'c'
            }
        }

        return (c*100)/s.length(); // Calculate and return the percentage as an integer.
    }
}
```

*   **`class Solution { ... }`**:  This is the standard class declaration in LeetCode for problem solutions.
*   **`public int percentageLetter(String s, char letter)`**: This is the method that solves the problem. It takes a string `s` and a character `letter` as input and returns an integer representing the percentage.
*   **`int c = 0;`**: This initializes an integer variable `c` to 0.  `c` will be used to count how many times `letter` appears in `s`.
*   **`for(char cc : s.toCharArray()) { ... }`**: This is a enhanced for loop that iterates through each character in the string `s`. `s.toCharArray()` converts the string into an array of characters. In each iteration, the current character is assigned to the variable `cc`.
*   **`if(cc == letter) { c++; }`**: Inside the loop, this `if` statement checks if the current character `cc` is equal to the target character `letter`. If they are equal, the counter `c` is incremented.
*   **`return (c*100)/s.length();`**:  After the loop finishes, this line calculates the percentage of the `letter` in the string.  It multiplies the count `c` by 100 and divides the result by the length of the string `s`. The integer division truncates any decimal part, so the result is an integer percentage, as required.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the string `s`.  This is because the code iterates through each character of the string once.
*   **Space Complexity:** O(1).  The space used is constant, as we only use a few integer variables.  The `toCharArray()` method does create a new char array, but its size depends on the input size, so we may consider it to contribute to O(n) space complexity in some strict interpretations. However, relative to the algorithmic complexity, it can also be argued to be constant, especially if viewed as a built-in operation that cannot be improved further. In the context of LeetCode solutions, we generally consider O(1) space complexity here, as the extra space used is not affected by the algorithm implemented in the main logic.
