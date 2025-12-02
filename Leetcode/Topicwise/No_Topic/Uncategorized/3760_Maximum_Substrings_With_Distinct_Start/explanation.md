### Problem Understanding

The provided Java code implements a function `maxDistinct(String s)`. Despite the title "Maximum Substrings With Distinct Start" which might suggest a more complex substring-related problem, the code's actual functionality is much simpler: it calculates and returns the total number of *distinct* lowercase English characters present in the input string `s`.

For example:
*   If `s = "abca"`, the distinct characters are 'a', 'b', 'c'. The function would return 3.
*   If `s = "banana"`, the distinct characters are 'b', 'a', 'n'. The function would return 3.
*   If `s = "hello"`, the distinct characters are 'h', 'e', 'l', 'o'. The function would return 4.

### Approach / Intuition

The core idea behind this solution is to use a frequency counting mechanism. Since we are dealing with lowercase English letters, there are only 26 possible distinct characters ('a' through 'z'). We can efficiently track which characters appear in the string by using a small array.

The strategy involves two main steps:
1.  **Marking Presence:** Iterate through the input string `s`. For each character encountered, mark its presence in a frequency array. We don't necessarily need to count *how many times* it appears, just *that it appears at least once*. Incrementing a counter for each character is a simple way to achieve this.
2.  **Counting Distinct:** After processing the entire string, iterate through the frequency array. Any entry in the array that has a count greater than zero signifies a character that was present in the original string. Sum up these positive counts to get the total number of distinct characters.

This approach is chosen because it's highly efficient for a fixed, small alphabet size (like 26 lowercase letters). An array provides O(1) access time for character frequencies, making the overall process very fast.

### Data Structures and Algorithms

*   **Data Structure:**
    *   `int[] map`: An array of integers of size 26. This array serves as a frequency map (or hash table) where each index corresponds to a lowercase English letter (`map[0]` for 'a', `map[1]` for 'b', ..., `map[25]` for 'z'). It stores the count of occurrences for each character.

*   **Algorithms:**
    *   **Iteration:** The solution uses two `for` loops to iterate: one over the input string's characters and another over the frequency map.
    *   **Frequency Counting:** The core technique is to use the `map` array to count the occurrences of each character. By checking if a count is `> 0`, we determine if a character is distinct.

### Code Walkthrough

Let's break down the code section by section:

```java
class Solution {
    public int maxDistinct(String s) {
        // 1. Initialize a frequency map for lowercase English letters.
        // The array has 26 elements, one for each letter from 'a' to 'z'.
        // By default, all elements are initialized to 0.
        int[] map = new int[26];

        // 2. Populate the frequency map by iterating through the input string.
        // s.toCharArray() converts the string into an array of characters.
        // The enhanced for-loop then iterates over each character 'c' in this array.
        for(char c : s.toCharArray()) {
            // 'c - 'a'' converts a character (e.g., 'a', 'b') into a 0-indexed integer (e.g., 0, 1).
            // For example, 'a' - 'a' = 0, 'b' - 'a' = 1, ..., 'z' - 'a' = 25.
            // map[index]++ increments the count for the character corresponding to 'index'.
            // This effectively marks that the character 'c' has appeared in the string.
            map[c-'a']++;
        }

        // 3. Initialize a counter for distinct characters.
        int cnt = 0;

        // 4. Iterate through the frequency map to count distinct characters.
        // This loop goes through each value 'v' (which is the count for a specific character)
        // stored in the 'map' array.
        for(int v : map) {
            // If a character's count 'v' is greater than 0, it means that character
            // appeared at least once in the input string 's'.
            if(v > 0) {
                // Increment the distinct character counter.
                cnt++;
            }
        }

        // 5. Return the final count of distinct characters.
        return cnt;
    }
}
```

### Time and Space Complexity

#### Time Complexity

*   **`s.toCharArray()`**: Converting the string `s` to a character array takes O(N) time, where N is the length of the string.
*   **First loop (`for(char c : s.toCharArray())`)**: This loop iterates N times (once for each character in the string). Inside the loop, `map[c-'a']++` is an array access and increment operation, which takes constant time, O(1). Therefore, this loop contributes O(N) to the total time complexity.
*   **Second loop (`for(int v : map)`)**: This loop iterates 26 times, once for each element in the `map` array (representing 'a' through 'z'). The number 26 is a constant, independent of the input string's length. Inside the loop, the `if` condition and `cnt++` are O(1) operations. Therefore, this loop contributes O(1) to the total time complexity.

Combining these, the total time complexity is O(N) + O(N) + O(1) = **O(N)**, where N is the length of the input string `s`.

#### Space Complexity

*   **`int[] map = new int[26];`**: This array has a fixed size of 26 integers, regardless of the input string's length. This consumes a constant amount of memory. Therefore, it contributes **O(1)** to the space complexity.
*   **`s.toCharArray()`**: This method creates a new `char` array that is a copy of the string `s`. This temporary array will have a size of N (the length of the string). If we consider this temporary array as auxiliary space, then it contributes **O(N)** to the space complexity.

Considering both, the auxiliary space complexity is **O(N)** primarily due to the `s.toCharArray()` call. If the problem context allows iterating over the string using `s.charAt(i)` without converting it to a `char[]`, then the auxiliary space would be **O(1)** (for the `map` array). In LeetCode contexts, `toCharArray()` often implies O(N) auxiliary space, but the frequency map itself is O(1).