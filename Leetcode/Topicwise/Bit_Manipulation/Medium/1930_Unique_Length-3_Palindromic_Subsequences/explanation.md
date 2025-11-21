### Problem Understanding

The problem asks us to find the number of *unique* palindromic subsequences of length 3 within a given string `s`.

Let's break down what that means:
*   **Subsequence:** A subsequence is formed by deleting zero or more characters from a string, maintaining the relative order of the remaining characters. For example, "ace" is a subsequence of "abcde".
*   **Length 3:** We are looking for subsequences with exactly three characters.
*   **Palindromic:** A palindrome reads the same forwards and backward. For a length-3 subsequence `c1 c2 c3`, this means `c1` must be equal to `c3`. So, all such palindromes will have the form `c X c`, where `c` is any character and `X` is any character.
*   **Unique:** If we can form `aba` in multiple ways (e.g., `s = "abacaba"`, `aba` from indices 0,1,2 and `aba` from indices 4,5,6), it only counts as one unique palindrome ("aba").

In essence, we need to count how many distinct `c X c` patterns exist as subsequences in `s`.

### Approach / Intuition

The core idea is to iterate through all possible characters that can form the *outer* part of the palindrome (`c` in `c X c`). Since there are only 26 lowercase English letters ('a' through 'z'), this is a manageable outer loop.

For each possible character `c` (from 'a' to 'z'):
1.  **Find the "widest" possible range for `c`:** To maximize the chances of finding different middle characters `X`, we should look for the *first* occurrence of `c` in the string and the *last* occurrence of `c`. Let's say these indices are `i` and `j` respectively. If `c` appears at `s[i]` and `s[j]`, then any character `s[k]` where `i < k < j` can potentially be the middle character `X` to form the palindrome `s[i] s[k] s[j]`.
2.  **Collect unique middle characters:** Once we have found the first (`i`) and last (`j`) indices for a specific character `c`, we iterate through all characters `s[k]` where `k` is strictly between `i` and `j` (`i < k < j`). We collect all these `s[k]` characters into a `Set` to automatically handle uniqueness.
3.  **Count and sum:** The size of this `Set` represents the number of unique middle characters `X` that can form `c X c` palindromes for the current outer character `c`. We add this count to our total answer.

We repeat this process for all 26 possible outer characters. If a character `c` appears less than twice in the string, it cannot form a `c X c` palindrome, so we simply skip it.

This approach ensures that we count each unique palindrome `c X c` exactly once because:
*   We iterate through all possible `c`'s.
*   For each `c`, we find all *unique* `X`'s that can be formed between its first and last occurrences. This covers all possible `c X c` palindromes for that `c`.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `Set<Character>` (specifically `HashSet`): Used to efficiently store and count unique middle characters (`X`) found between the first and last occurrences of an outer character `c`. The `HashSet` provides average O(1) time complexity for adding elements and checking for existence, and its `size()` method gives the count of unique elements.
*   **Algorithms:**
    *   **Iterative Search:** Nested loops are used to iterate through characters and their indices.
    *   **Character Iteration:** An outer loop iterates through all 26 possible lowercase English letters.
    *   **Linear Scan:** An inner loop performs a linear scan of the input string `s` to find the first and last occurrences of the current character `c`.
    *   **Range Iteration:** Another inner loop iterates over a sub-range of the string (between the first and last occurrences of `c`) to collect middle characters.

### Code Walkthrough

```java
class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans = 0; // Initialize the total count of unique length-3 palindromic subsequences
        int n = s.length(); // Get the length of the input string for efficient access

        // Outer loop: Iterate through all possible characters for the 'c' in 'c X c'
        for(char c = 'a'; c<='z'; c++) {
            int i = -1; // Stores the index of the first occurrence of character 'c'
            int j = -1; // Stores the index of the last occurrence of character 'c'

            // Inner loop: Find the first and last occurrences of the current character 'c'
            for(int k = 0; k<n; k++) {
                if(s.charAt(k) == c) { // If the character at current index 'k' matches 'c'
                    if(i == -1) {
                        i = k; // If 'i' is still -1, this is the first 'c' found
                    } else {
                        j = k; // Otherwise, update 'j' to the current index 'k'. This ensures 'j' always holds the *last* found index.
                    }
                }
            }

            // If 'j' is still -1, it means 'c' appeared 0 or 1 time.
            // We need at least two occurrences of 'c' to form 'c X c'.
            if(j == -1) {
                continue; // Skip to the next character 'c'
            }

            // Initialize a HashSet to store unique middle characters for the current 'c'
            Set<Character> set = new HashSet<>();

            // Loop through the characters *between* the first 'c' (at index i) and the last 'c' (at index j)
            // These characters are potential 'X's for the palindrome 'c X c'
            for(int k = i+1; k<j; k++) {
                set.add(s.charAt(k)); // Add each character in this range to the set. HashSet handles uniqueness automatically.
            }

            // The number of unique characters in the set is the number of unique 'c X c' palindromes
            // that can be formed with the current outer character 'c'.
            ans += set.size();
        }

        return ans; // Return the total count of unique palindromic subsequences
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   The outer loop runs 26 times (for each character from 'a' to 'z').
    *   Inside the outer loop:
        *   The first inner loop (`for(int k = 0; k<n; k++)`) iterates through the entire string `s` once to find `i` and `j`. This takes `O(n)` time.
        *   The second inner loop (`for(int k = i+1; k<j; k++)`) iterates through a substring of `s` (at most `n` characters). Inside this loop, `set.add()` operation takes `O(1)` time on average for a `HashSet`. This also takes `O(n)` time in the worst case.
    *   Combining these, for each of the 26 characters, we perform `O(n) + O(n) = O(n)` work.
    *   Therefore, the total time complexity is `26 * O(n) = O(n)`.

*   **Space Complexity:**
    *   `ans`, `n`, `i`, `j`, `k` variables take `O(1)` space.
    *   The `HashSet<Character> set` stores unique characters. In the worst case, it can store all 26 possible lowercase English letters. Since 26 is a constant, the space used by the set is `O(1)`.
    *   Therefore, the total space complexity is `O(1)`.