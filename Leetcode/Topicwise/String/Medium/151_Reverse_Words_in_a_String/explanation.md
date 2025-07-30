## Reverse Words in a String - LeetCode Problem Explanation

Here's a detailed breakdown of the Java code solution for the LeetCode problem "Reverse Words in a String":

### 1. Problem Understanding:

The problem asks us to reverse the order of words in a given string.  Leading and trailing spaces should be removed, and multiple spaces between words should be reduced to a single space.  The output should be a string with the words in reverse order, separated by single spaces.

**Example:**

Input: `"  the sky is blue  "`
Output: `"blue is sky the"`

Input: `"a good   example"`
Output: `"example good a"`

### 2. Approach / Intuition:

The core idea is to:

1.  **Clean the input string:** Remove leading/trailing spaces and condense multiple spaces into single spaces.
2.  **Split the string into words:**  Divide the cleaned string into an array of individual words.
3.  **Reverse the order of words:** Iterate through the array of words in reverse order.
4.  **Build the reversed string:** Construct a new string by appending the words in reverse order, adding a single space between them.

This approach is chosen because it's relatively straightforward and utilizes common string manipulation techniques. It avoids complex in-place manipulations, making it easier to understand and implement. Using `split` and `StringBuilder` provides efficiency for creating the new string.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `String`: The input string.
    *   `String[]`: An array to store the individual words after splitting the input string.
    *   `StringBuilder`: A mutable string builder used to efficiently construct the reversed string.
*   **Algorithms:**
    *   `String.trim()`: Removes leading and trailing whitespace from the input string.
    *   `String.split(regex)`: Splits the string into an array of substrings based on a regular expression (in this case, one or more whitespace characters).
    *   Iteration (for loop): Used to iterate through the array of words in reverse order.

### 4. Code Walkthrough:

```java
class Solution {
    public String reverseWords(String s) {
        String[] ss = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i = ss.length-1 ; i>=0 ; i--)
        {
            sb.append(ss[i]);
            if(i!=0) sb.append(" ");
        }
        return sb.toString();
    }
}
```

1.  **`class Solution { ... }`**: This is the standard class declaration required by LeetCode.
2.  **`public String reverseWords(String s) { ... }`**: This is the function that takes the input string `s` and returns the reversed string.
3.  **`String[] ss = s.trim().split("\\s+");`**: This is the core of the cleaning and splitting process.
    *   `s.trim()`:  Removes any leading or trailing spaces from the input string `s`. This ensures that the reversed string doesn't start or end with a space.
    *   `.split("\\s+")`: Splits the trimmed string into an array of substrings (words) based on the regular expression `"\\s+"`.  `\s` represents any whitespace character (space, tab, newline, etc.). `+` means "one or more occurrences".  Therefore, it splits the string at sequences of one or more whitespace characters, effectively handling multiple spaces between words.
4.  **`StringBuilder sb = new StringBuilder();`**:  Creates a `StringBuilder` object named `sb`. `StringBuilder` is used for efficient string concatenation, especially within loops, because it's mutable (unlike the immutable `String` class).
5.  **`for(int i = ss.length-1 ; i>=0 ; i--) { ... }`**: This loop iterates through the `ss` array (the array of words) in *reverse* order, from the last word to the first.
6.  **`sb.append(ss[i]);`**: Appends the current word (`ss[i]`) to the `StringBuilder`.
7.  **`if(i!=0) sb.append(" ");`**:  Appends a space to the `StringBuilder` *only if* it's not the last word.  This prevents adding a trailing space at the end of the reversed string.  `i!=0` checks if we are not at the first element.
8.  **`return sb.toString();`**: Converts the `StringBuilder` `sb` to a `String` and returns the reversed string.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input string `s`.
    *   `trim()` takes O(n) in the worst case to iterate through string.
    *   `split()` takes O(n) in the worst case to iterate through string.
    *   The `for` loop iterates through the array of words, which in the worst case can be of length O(n) (if the entire string is a single word with many spaces).
    *   `StringBuilder` operations (`append()`) are generally O(1) on average, contributing O(n) overall.

*   **Space Complexity:** O(n), where n is the length of the input string `s`.
    *   The `String[] ss` array can store up to `n` words in the worst case (if the input string consists of only space separated characters).
    *   The `StringBuilder` `sb` can hold a string of length up to `n`.
    *   `trim()` creates a new string potentially of size O(n).

In summary, the algorithm has a linear time complexity O(n) and linear space complexity O(n) due to the creation of the string array and the string builder.
