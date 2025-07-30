## Longest Palindrome by Concatenating Two Letter Words Explained

Here's a detailed explanation of the Java code for the "Longest Palindrome by Concatenating Two Letter Words" LeetCode problem:

### 1. Problem Understanding

The problem asks us to find the length of the longest palindrome that can be formed by concatenating words from a given array of two-letter strings.  We can use each word at most once.

For example, if the input is `["lc", "cl", "gg"]`, the longest palindrome we can form is `"lc" + "cl"` or `"cl" + "lc"` with length 4.  If the input is `["ab", "ty", "yt", "lc", "cl", "ab"]`, we can form `"ab" + "ab" + "cl" + "lc" + "ty" + "yt"` which is not palindrome or `"ab"+"ty"+"yt"+"ab"+"cl"+"lc"`. However, we could form a palindrome `"abtyytabcllc"` in a simpler way as `("ab"+"ab") + ("ty"+"yt") + ("cl"+"lc")`. If the input is `["cc","ll","xx"]`, we can form `"cc"`, `"ll"`, or `"xx"`.  Since the length is limited to 2 and we are searching for the *longest* one, we could form `"cc"` and the length is 2.

### 2. Approach / Intuition

The key idea is to identify word pairs that can form palindromes when concatenated. A pair of words can form a palindrome if one word is the reverse of the other.

Here's the high-level strategy:

1.  **Handle non-identical pairs:** Iterate through the `words` array. For each word, check if its reverse exists in a hash map (`unmatched`). If it does, it means we found a palindrome pair. Increment the `palindromePairs` counter and reduce the count of the reversed word in the map. If the reverse doesn't exist, add the current word to the map, keeping track of its frequency. This `unmatched` map stores counts of words that haven't found their palindrome pair yet.
2.  **Handle identical pairs:**  For words where the two characters are the same (e.g., "gg", "aa"), we can only use an even number of them to create a palindrome. We keep track of the counts of these types of words using an array `sameCharPairs`.
3.  **Center of palindrome:** If we have an odd number of identical character pairs, we can use one of these pairs in the *center* of the overall palindrome (e.g., "gg" can be in the center). This is handled by the `hasOddCenter` boolean, which is later used to add to the total length.  We aim to maximize the total length, so it is always beneficial to use an odd center if available.
4.  **Calculate length:** Finally, the length of the longest palindrome is calculated based on the number of palindrome pairs found and whether there's a word with the same characters that can be used as the center.

Why this approach?

*   **Efficiency:** Using a hash map (`unmatched`) provides efficient lookups to find the reverse of a word.  This avoids nested loops, resulting in a more efficient algorithm.
*   **Correctness:**  Separating identical and non-identical pairs handles all possible palindrome constructions.  Identical pairs are treated specially because they can potentially form the central part of a larger palindrome.

### 3. Data Structures and Algorithms

*   **HashMap (`unmatched`)**: Used to store the frequency of words that haven't found their reverse yet.  It provides fast lookups (O(1) on average) for checking if the reverse of a word exists.
*   **Array (`sameCharPairs`)**:  Used to store the counts of words where both characters are the same. The array indices represent characters ('a' to 'z').  This allows for efficient counting and checking for odd occurrences.  O(1) access since the size is fixed to 26.
*   **String Reversal**: A simple algorithm to reverse a string using `StringBuilder`.
*   **Greedy Approach:** We greedily try to match words with their reverses to maximize the length of the palindrome.

### 4. Code Walkthrough

```java
class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> unmatched = new HashMap<>();
        int palindromePairs = 0;
        int[] sameCharPairs = new int[26]; 
        boolean hasOddCenter = false;

        for (String word : words) {
            char a = word.charAt(0), b = word.charAt(1);
            if (a == b) {
                sameCharPairs[a - 'a']++;
            } else {
                String reversed = new StringBuilder(word).reverse().toString();
                if (unmatched.getOrDefault(reversed, 0) > 0) {
                    palindromePairs += 2;
                    unmatched.put(reversed, unmatched.get(reversed) - 1);
                } else {
                    unmatched.put(word, unmatched.getOrDefault(word, 0) + 1);
                }
            }
        }

        int centerUsed = 0;
        for (int count : sameCharPairs) {
            palindromePairs += (count / 2) * 2;
            if (count % 2 == 1) {
                centerUsed = 1; 
            }
        }

        return (palindromePairs + centerUsed) * 2;
    }
}
```

*   **`Map<String, Integer> unmatched = new HashMap<>();`**:  Creates a HashMap to store the counts of unmatched words (words whose reverses haven't been found yet). The key is the word, and the value is the number of times it appears.
*   **`int palindromePairs = 0;`**:  Initializes a counter to keep track of the number of palindrome pairs found (pairs of words that form a palindrome when concatenated). Note that this counts pairs of words, not the length of the combined word.
*   **`int[] sameCharPairs = new int[26];`**:  Creates an array to store the counts of words where both characters are the same (e.g., "gg", "aa"). The index `a - 'a'` represents the character 'a', 'b', ..., 'z'.
*   **`boolean hasOddCenter = false;`**:  Not used.  Instead, `centerUsed` is used to avoid unnecessary calculations.
*   **`for (String word : words)`**:  Iterates through each word in the input array.
*   **`char a = word.charAt(0), b = word.charAt(1);`**:  Extracts the first and second characters of the word.
*   **`if (a == b)`**:  Checks if the two characters are the same.  If they are, it means the word is of the form "aa", "bb", etc.
    *   **`sameCharPairs[a - 'a']++;`**:  Increments the count of the corresponding character in the `sameCharPairs` array.
*   **`else`**:  If the two characters are different, the word is of the form "ab", "lc", etc.
    *   **`String reversed = new StringBuilder(word).reverse().toString();`**:  Reverses the word.
    *   **`if (unmatched.getOrDefault(reversed, 0) > 0)`**: Checks if the reversed word exists in the `unmatched` map.
        *   **`palindromePairs += 2;`**:  If the reversed word exists, it means we found a palindrome pair (word + reversed word). Increment the `palindromePairs` counter. We add 2 because we found a pair that can form a palindrome.
        *   **`unmatched.put(reversed, unmatched.get(reversed) - 1);`**:  Decrements the count of the reversed word in the `unmatched` map.
    *   **`else`**:  If the reversed word doesn't exist in the `unmatched` map.
        *   **`unmatched.put(word, unmatched.getOrDefault(word, 0) + 1);`**:  Adds the current word to the `unmatched` map or increments its count if it already exists.
*   **`int centerUsed = 0;`**: Tracks whether we used an odd pair as a center.
*   **`for (int count : sameCharPairs)`**:  Iterates through the counts of each identical character pair.
    *   **`palindromePairs += (count / 2) * 2;`**:  Adds the number of pairs of identical words to `palindromePairs`.  We divide by 2 and multiply by 2 to only count the even pairs.
    *   **`if (count % 2 == 1)`**:  Checks if there's an odd number of identical words.
        *   **`centerUsed = 1;`**:  If there's an odd number, set the `centerUsed` to 1 because we can use one of them as the center of the palindrome.
*   **`return (palindromePairs + centerUsed) * 2;`**:  Calculates the final length of the longest palindrome. `palindromePairs` is multiplied by 2 since each pair forms a palindrome of length 4.  `centerUsed` is added to account for the center word. Finally, the result is multiplied by 2 because each individual word has a length of 2.

### 5. Time and Space Complexity

*   **Time Complexity:** O(N), where N is the number of words in the input array. The algorithm iterates through the `words` array once and performs constant-time operations for each word (hash map lookups, string reversal).  The iteration over the `sameCharPairs` array takes O(26) time which simplifies to O(1), so it does not change the complexity.
*   **Space Complexity:** O(N) in the worst case, where N is the number of words. This is because the `unmatched` hash map can potentially store all the words if none of them have matching reversed pairs. The `sameCharPairs` array takes O(1) space since its size is fixed (26).
