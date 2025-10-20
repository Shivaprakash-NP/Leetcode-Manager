## Lexicographically Smallest Permutation Greater Than Target Explanation

### 1. Problem Understanding:

The problem asks us to find the lexicographically smallest permutation of a given string `s` that is greater than another string `target`. If no such permutation exists, we should return an empty string. The string `s` has a permutation that makes it greater than `target`.

### 2. Approach / Intuition:

The approach used here is a recursive backtracking strategy. The core idea is to build the permutation character by character, ensuring it remains greater than `target` at each step.  The approach tries to match the `target` string as much as possible to find the lexicographically *smallest* greater permutation.

Here's the breakdown:

*   **Matching Prefix:** The recursion attempts to match the characters of the `target` string as long as possible.  If the current character of `target` is available (present in the character counts of `s`), the recursion proceeds by adding that character to the developing permutation.
*   **Finding a Larger Character:** If, at any point, the current character of `target` cannot be matched, the algorithm searches for the smallest character *greater* than the current character of `target` that is available in `s`.
*   **Greedily Appending Remaining Characters:** If a larger character is found, it's appended to the current permutation.  The remaining characters in `s` are then appended in ascending order (lexicographically smallest) to complete the permutation. This ensures that the created permutation is the smallest greater than `target`.
*   **Base Case:** When the permutation has the same length as `target`, a comparison is made. If it's greater than `target` and smaller than the current `ans`, it becomes the new `ans`.
*   **Backtracking:** The character counts (`map`) are maintained and restored after each recursive call to explore different possibilities.

The recursive approach is chosen because it naturally allows us to explore all possible prefixes of the permutation and find the smallest greater one.

### 3. Data Structures and Algorithms:

*   **String:** The input strings `s` and `target` are strings. The output `ans` is also a string.
*   **Integer Array (Frequency Map):** The `map` array (of size 26) is used to store the frequency of each character ('a' to 'z') in the string `s`.
*   **Recursion:** The `solve` function is a recursive function that explores the search space.
*   **StringBuilder:** Used to efficiently construct the permutation string.
*   **String Comparison (compareTo):** Used to compare strings lexicographically.

### 4. Code Walkthrough:

```java
class Solution {
    String ans; // Stores the lexicographically smallest greater permutation found so far. Initialized to null.

    private void solve(int i, String s, String t, int[] map) {
        int n = t.length();
        // Base case: If we have built a permutation of the same length as target.
        if(n == i) {
            // Check if the built permutation 's' is greater than target 't'.
            if(s.compareTo(t) > 0) {
                // If 's' is greater and smaller than current 'ans' (or 'ans' is null), update 'ans'.
                if(ans == null || ans.compareTo(s) > 0) ans = s;
            }
            return;
        }

        // Try to match the current character of target
        if(map[t.charAt(i)-'a'] > 0) {
            // If the current character of target exists in 's', use it.
            map[t.charAt(i)-'a']--; // Decrement the count of that character in the map.
            solve(i+1, s+t.charAt(i), t, map); // Recursively call solve with the updated string 's' and map.
            map[t.charAt(i)-'a']++; // Backtrack: Restore the count of the character in the map.
        }

        // Find the smallest character greater than t.charAt(i)
        for(int j = t.charAt(i)-'a'+1; j<26; j++) {
            if(map[j] > 0) {
                // If a larger character is found.
                StringBuilder sb = new StringBuilder(s);
                sb.append((char)('a'+j)); // Append the larger character to the current permutation.
                int[] temp = map.clone();  // Create a copy of the character count map.
                temp[j]--; // Decrement the count of the character we just used.

                // Greedily append the remaining characters in ascending order.
                for(int k = 0; k<26; k++) {
                    while(temp[k]-- > 0) sb.append((char)('a'+k));
                }

                String d = sb.toString();  // Convert the StringBuilder to a String.
                // Update 'ans' if the new permutation is smaller and greater than 'target'
                if(ans == null || ans.compareTo(d) > 0) ans = d;
                return; // Optimization: Return immediately since we've found the smallest greater permutation.
            }
        }
    }

    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] map = new int[26]; // Character frequency map for 's'.

        ans = null; // Initialize the answer to null.
        for(char c : s.toCharArray()) map[c-'a']++; // Populate the frequency map.

        solve(0, "", target, map); // Start the recursive process.

        return ans==null?"":ans; // Return the answer or an empty string if no such permutation exists.
    }
}
```

### 5. Time and Space Complexity:

*   **Time Complexity:**  The worst-case time complexity is difficult to precisely determine due to the recursive nature and the pruning step (returning after finding a better permutation).  In the worst case, it could explore a significant portion of the permutation space, leading to a complexity close to O(26! / (n1! * n2! * ... * n26!)) which is factorial in nature and n1..n26 representing frequency of the characters. However, the pruning step after finding a better permutation, in most cases, can stop the recursion, making it way faster in average cases. The `solve` method explores various combinations of characters to find a lexicographically greater permutation. Inside solve the loop goes through 26 characters, so the complexity will be high. It's more like an exponential complexity with a possible pruning to make it closer to `O(n)` in certain cases.
*   **Space Complexity:** The space complexity is primarily due to the recursion depth and the `map` array. The recursion depth can be at most the length of the target string `t`, which is `O(n)` where n is the length of `t`.  The `map` array takes up O(1) space because it's a fixed-size array of 26 integers (representing the English alphabet).  The use of `StringBuilder` within the loop can also contributes to memory usage with max size `O(n)`. Hence, the space complexity is approximately O(n).
