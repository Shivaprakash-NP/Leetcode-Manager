### Problem Understanding

The problem asks us to count the number of pairs of words `(words[i], words[j])` such that `i < j`, and `words[j]` can be obtained by applying a Caesar cipher shift to `words[i]`. A Caesar cipher involves shifting every letter in a word by a fixed amount (e.g., 'a' becomes 'b', 'b' becomes 'c', etc., for a shift of +1). The shift wraps around the alphabet (e.g., 'z' shifted by +1 becomes 'a').

### Approach / Intuition

The core idea is to normalize each word into a "canonical" form. If two words are Caesar cipher equivalents of each other, they must have the same length, and they will normalize to the *same* canonical form.

The chosen canonical form is defined by shifting every character in a word such that its *first* character becomes 'a'.
Let's illustrate with examples:
*   **"abc"**: The first character is 'a'. No shift needed. Canonical form: "abc".
*   **"bcd"**: The first character is 'b'. To make 'b' become 'a', we need a shift of -1 (or +25). Applying this shift to all characters: 'b' -> 'a', 'c' -> 'b', 'd' -> 'c'. Canonical form: "abc".
*   **"za"**: The first character is 'z'. To make 'z' become 'a', we need a shift of +1 (or -25). Applying this shift to all characters: 'z' -> 'a', 'a' -> 'b'. Canonical form: "ab".

Once all words are converted to their canonical forms, we can count the occurrences of each unique canonical form. If a particular canonical form appears `k` times, it means there are `k` original words that are all Caesar-cipher-equivalent to each other. From these `k` words, we can form `k * (k - 1) / 2` unique pairs (this is the combination formula "n choose 2"). We sum these counts for all unique canonical forms to get the total number of pairs.

### Data Structures and Algorithms

1.  **`HashMap<String, Long>`**: This is the primary data structure used. It maps each unique canonical string (the key) to the count of how many original words normalize to that canonical string (the value).
2.  **String Manipulation / Character Arithmetic**: The process of converting a word to its canonical form involves iterating through its characters and performing arithmetic operations (`-`, `+`, `%`) to apply the Caesar shift.
3.  **Frequency Counting**: The `HashMap` is used to efficiently count the frequencies of the canonical forms.
4.  **Combinatorics**: The formula `k * (k - 1) / 2` is used to calculate the number of pairs from `k` equivalent items.

### Code Walkthrough

```java
class Solution {
    public long countPairs(String[] words) {        
        // 1. Initialize a HashMap to store canonical forms and their frequencies.
        // Key: The canonical string (e.g., "abc" for "bcd").
        // Value: The number of times this canonical string has been generated from input words.
        Map<String, Long> map = new HashMap<>();
        
        // 2. Initialize a counter for the total number of valid pairs.
        long cnt = 0L;

        // 3. First pass: Iterate through each word in the input array.
        for(String s : words) {
            // 3a. Determine the shift amount needed for the current word 's'.
            // The goal is to make the first character 'a'.
            // So, if s.charAt(0) is 'c', sh will be ('c' - 'a') = 2.
            // This means we need to subtract 2 from each character's position to shift it.
            int sh = (s.charAt(0)-'a');

            // 3b. Use a StringBuilder for efficient construction of the new canonical string.
            StringBuilder sb = new StringBuilder();
            
            // 3c. Iterate through each character of the current word 's'.
            for(char c : s.toCharArray()) {
                // Convert the character 'c' to its 0-25 alphabet index.
                // Apply the shift 'sh'.
                // Add 26 and then take modulo 26 to handle negative results (e.g., 'a' - 2 = -2, becomes 24 ('y')).
                int nc = (c-'a'-sh+26)%26;
                
                // Convert the new 0-25 index back to a character and append to StringBuilder.
                sb.append((char)(nc+'a'));
            }

            // 3d. Get the final canonical string from the StringBuilder.
            String ns = sb.toString();
            
            // 3e. Update the frequency map: increment the count for this canonical string.
            // If 'ns' is not yet in the map, getOrDefault returns 0, then we add 1.
            map.put(ns, map.getOrDefault(ns, 0L)+1L);
        }

        // 4. Second pass: Calculate pairs from the frequencies stored in the map.
        // Iterate over the counts (frequencies) of each unique canonical string.
        for(long k : map.values()) {
            // If 'k' words normalize to the same canonical form, it means these 'k' words
            // are all Caesar-cipher-equivalent to each other.
            // The number of distinct pairs we can form from 'k' such words is given by the
            // combination formula "k choose 2": k * (k - 1) / 2.
            cnt += (k*(k-1))/2;
        }
        
        // 5. Return the accumulated total count of valid pairs.
        return cnt;
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   Let `N` be the number of words in the input array `words`.
    *   Let `L` be the maximum length of any word in `words`.
    *   The first `for` loop iterates `N` times (once for each word).
    *   Inside this loop:
        *   `s.charAt(0)-'a'` is `O(1)`.
        *   `s.toCharArray()` takes `O(L)` time.
        *   The inner `for` loop iterates `L` times. Inside, character arithmetic and `StringBuilder.append()` are `O(1)`.
        *   `sb.toString()` takes `O(L)` time to create the new string.
        *   `map.put()` (and `getOrDefault`) takes `O(L)` on average, as string hashing and equality comparison depend on the string's length.
    *   Therefore, the first loop contributes `O(N * L)` to the total time complexity.
    *   The second `for` loop iterates `M` times, where `M` is the number of unique canonical forms found (at most `N`). Inside this loop, `O(1)` operations are performed. So, this loop contributes `O(M)`, which is at most `O(N)`.
    *   Combining these, the overall time complexity is **`O(N * L)`**.

*   **Space Complexity:**
    *   The `HashMap` stores up to `M` unique canonical strings as keys. In the worst case, `M` can be `N` (if all words normalize to distinct canonical forms).
    *   Each canonical string can have a maximum length of `L`.
    *   Therefore, the space required to store the keys in the map is `O(M * L)`. Since `M <= N`, this is `O(N * L)` in the worst case.
    *   The `StringBuilder` temporarily uses `O(L)` space for each word during its construction.
    *   Thus, the overall space complexity is **`O(N * L)`** in the worst case.