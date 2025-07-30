```markdown
## Optimal Partition of String Explained

### 1. Problem Understanding:

The problem "Optimal Partition of String" asks us to divide a given string `s` into the minimum number of substrings such that each character appears at most once in each substring.  Essentially, we want to cut the string into as few pieces as possible, with the constraint that no piece contains any repeated characters.

### 2. Approach / Intuition:

The approach is based on a greedy strategy.  We iterate through the string, building a substring as long as we can without repeating any characters. If we encounter a character that already exists in the current substring, it means we need to end the current substring and start a new one, resetting our record of seen characters.

Why is this approach greedy? Because at each character, we make the locally optimal decision to extend the current substring if possible. If we *have* to cut the string to avoid repetition, then we do so immediately.  This leads to an optimal global solution because any other strategy would involve making a cut *before* it's necessary, which would lead to more substrings.

### 3. Data Structures and Algorithms:

*   **Data Structure:** A boolean array `seen` of size 26 is used to keep track of which characters (a-z) have already appeared in the current substring. This acts as a hash set or set data structure but implemented as an array for speed as we only deal with lowercase english characters.
*   **Algorithm:** Iteration through the string (a simple `for` loop) and conditional checking based on whether a character has been seen before. This embodies a greedy algorithm pattern.

### 4. Code Walkthrough:

```java
class Solution {
    public int partitionString(String s) {
        boolean[] seen = new boolean[26]; // Initialize a boolean array to track seen characters
        int ans = 1; // Initialize the number of substrings to 1 (we'll always have at least one substring)
        for (int i = 0 ;  i<s.length() ; i++) { // Iterate through the string
            char c = s.charAt(i); // Get the current character
            if (seen[c - 'a']) { // Check if the character has already been seen in the current substring
                ans++; // If seen, increment the number of substrings
                seen = new boolean[26]; // Reset the seen array to start a new substring
            }
            seen[c - 'a'] = true; // Mark the current character as seen in the current substring
        }
        return ans; // Return the total number of substrings
    }
}
```

*   `boolean[] seen = new boolean[26];`:  Creates a boolean array of size 26.  `seen[i]` will be `true` if the `i`-th letter of the alphabet ('a' + i) has already appeared in the current substring.
*   `int ans = 1;`: Initializes the `ans` variable to 1. This is because even if the string is empty or contains only one distinct character, there will be at least one substring.
*   `for (int i = 0; i < s.length(); i++)`: This loop iterates through each character of the input string `s`.
*   `char c = s.charAt(i);`: Extracts the character at the current index `i` and stores it in the `c` variable.
*   `if (seen[c - 'a'])`:  This is the core logic.
    *   `c - 'a'` calculates the index of the character `c` in the `seen` array. For example, if `c` is 'b', then `c - 'a'` evaluates to 1 (because 'b' is the 2nd letter of the alphabet, index 1).
    *   `seen[c - 'a']` checks if the value at that index in the `seen` array is `true`. If it's `true`, it means the character `c` has already been seen in the current substring.
*   `ans++;`: If the character has been seen, it means we need to end the current substring and start a new one. Therefore, we increment the `ans` counter.
*   `seen = new boolean[26];`: Resets the `seen` array to all `false`. This is essential because we are starting a new substring.  We need to clear the previous substring's character record.
*   `seen[c - 'a'] = true;`: Regardless of whether we incremented the substring count, we now mark the current character `c` as seen in the *current* substring (or new substring).
*   `return ans;`: After iterating through the entire string, the function returns the final value of `ans`, which represents the minimum number of substrings required.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the string `s`. The code iterates through the string once.  All other operations inside the loop take constant time.
*   **Space Complexity:** O(1). The `seen` array has a fixed size of 26 (for the lowercase English alphabet), regardless of the input string's length. Therefore, the space used is constant.
```