```markdown
## LeetCode Problem: First Letter to Appear Twice - Explanation

### 1. Problem Understanding:

The problem asks us to find the first character in a given string `s` that appears twice. We need to iterate through the string and, upon encountering a character for the second time, immediately return that character. If no character appears twice, though in this problem it will never be the case, we return a space.

### 2. Approach / Intuition:

The most efficient way to solve this problem is by using a `HashSet`. A `HashSet` allows us to check for the existence of an element in O(1) average time. The strategy is to iterate through the string `s`, character by character. For each character, we check if it's already present in the `HashSet`.

-   If the character *is* present in the `HashSet`, it means we've encountered it before. Therefore, we return that character.
-   If the character *is not* present in the `HashSet`, it means this is the first time we're seeing it. So, we add it to the `HashSet` and continue iterating.

This approach is chosen because it provides a simple and efficient way to track the characters we've seen so far. Using a `HashSet` avoids nested loops or more complex data structures, leading to optimal performance.

### 3. Data Structures and Algorithms:

*   **Data Structure:** `HashSet` (Java's implementation of a hash table-based set)
*   **Algorithm:** Linear Traversal (iterating through the string) and Hash Table lookup (using `HashSet.contains()` and `HashSet.add()`).

### 4. Code Walkthrough:

```java
class Solution {
    public char repeatedCharacter(String s) {
        Set<Character> val = new HashSet<>(); // Initialize a HashSet called 'val' to store characters we've encountered.
        for(char c : s.toCharArray()) { // Iterate through each character 'c' in the string 's'. We convert the string into an array of characters for easy iteration.
            if(val.contains(c)) return c; // Check if the current character 'c' is already present in the 'val' HashSet. If it is, it means we've seen this character before, so we return it.
            val.add(c); // If the character 'c' is not in the HashSet, it means this is the first time we're seeing it. Add 'c' to the 'val' HashSet to keep track of it.
        }
        return ' '; // This line will only be reached if no character is repeated (which is not possible according to the problem constraints). It returns a space character.
    }
}
```

*   **`Set<Character> val = new HashSet<>();`**: This line creates a new `HashSet` named `val`. This set will store the characters that we have already encountered in the string.
*   **`for(char c : s.toCharArray()) { ... }`**: This loop iterates through the input string `s`. The `s.toCharArray()` method converts the string into an array of characters, allowing us to easily access each character.
*   **`if(val.contains(c)) return c;`**: Inside the loop, this line checks if the current character `c` is already present in the `val` `HashSet`. The `contains()` method of the `HashSet` returns `true` if the set already contains the specified element, and `false` otherwise. If the character is already present, it means that we have encountered it before, so the function immediately returns the character.
*   **`val.add(c);`**: If the character `c` is not already present in the `HashSet`, this line adds the character to the `HashSet`. This ensures that we keep track of the characters that we have already encountered.
*   **`return ' ';`**: This line is reached only if the loop completes without finding a repeating character. This should technically be unreachable given the problem constraints stating there will be a repeated character. In that case, a space is returned.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the string `s`. This is because we iterate through the string once. The `HashSet.contains()` and `HashSet.add()` operations have an average time complexity of O(1).
*   **Space Complexity:** O(n) in the worst-case scenario, where n is the length of the string `s`. This happens when all characters in the string are unique (before the repeating one) because we might store all these unique characters in the `HashSet`. However, since we are dealing with characters, in reality, the space complexity can be considered O(1) because the number of possible unique characters is bounded (e.g. by the size of the ASCII table).
