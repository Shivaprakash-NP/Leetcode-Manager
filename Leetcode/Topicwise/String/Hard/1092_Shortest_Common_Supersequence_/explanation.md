### Problem Understanding

The problem asks us to find the *shortest* possible string that contains both given strings, `s` and `t`, as subsequences. A subsequence is formed by deleting zero or more characters from another string without changing the order of the remaining characters. For example, "ace" is a subsequence of "abcde". We need to construct this "Shortest Common Supersequence" (SCS) and return it.

### Approach / Intuition

The core idea behind finding the Shortest Common Supersequence (SCS) is deeply related to finding the Longest Common Subsequence (LCS).

1.  **Relationship to LCS:**
    Let `n` be the length of string `s` and `m` be the length of string `t`.
    Let `L` be the length of their Longest Common Subsequence (LCS).
    The length of the Shortest Common Supersequence (SCS) can be calculated as `n + m - L`.
    Why? Because the `n` characters of `s` and the `m` characters of `t` must all appear in the SCS. However, the `L` characters that are common to both `s` and `t` (i.e., form the LCS) only need to be included *once* in the SCS. By summing `n` and `m`, we effectively count these `L` common characters twice. Subtracting `L` once corrects this double-counting, giving us the minimum total characters needed.

2.  **Constructing the SCS:**
    To construct the actual SCS string, we can leverage the dynamic programming (DP) table used to calculate the LCS length. We'll backtrack through this `dp` table, starting from `dp[n][m]` (representing the LCS of the full strings `s` and `t`).

    *   **If `s.charAt(i-1) == t.charAt(j-1)`:** This means the current characters from both strings match. This character is part of the LCS. To form the SCS, we include this character *once* and then move diagonally up-left in the `dp` table (decrementing both `i` and `j`).
    *   **If `s.charAt(i-1) != t.charAt(j-1)`:** The characters don't match. We need to decide which character to include in the SCS and which path to follow in the `dp` table.
        *   We look at `dp[i-1][j]` (LCS of `s[0...i-2]` and `t[0...j-1]`) and `dp[i][j-1]` (LCS of `s[0...i-1]` and `t[0...j-2]`).
        *   If `dp[i-1][j] > dp[i][j-1]`, it implies that the LCS path that led to `dp[i][j]` *came from `dp[i-1][j]`*. This means `s.charAt(i-1)` was *not* part of the LCS at this step. To ensure `s` is a subsequence of our SCS, we *must* include `s.charAt(i-1)` in the SCS. We then move up (decrement `i`).
        *   If `dp[i][j-1] >= dp[i-1][j]`, it implies the LCS path came from `dp[i][j-1]` (or both paths lead to the same LCS length). In this case, `t.charAt(j-1)` was *not* part of the LCS at this step. We *must* include `t.charAt(j-1)` in the SCS. We then move left (decrement `j`).
    *   **Remaining Characters:** After the backtracking loop finishes, one of the indices (`i` or `j`) might still be greater than 0. This means there are remaining characters in `s` or `t` (or both, if the strings were completely disjoint) that were not part of any common subsequence and haven't been added to the SCS yet. We append all these remaining characters.

Since we are building the string by backtracking from the end, the characters will be appended in reverse order. Therefore, the final step is to reverse the constructed string.

### Data Structures and Algorithms

*   **Algorithm:** Dynamic Programming (specifically, a variant of the Longest Common Subsequence algorithm) for calculating LCS lengths, followed by a backtracking approach to reconstruct the SCS.
*   **Data Structures:**
    *   `int[][] dp`: A 2D array to store the lengths of the Longest Common Subsequence (LCS) for all prefixes of `s` and `t`. `dp[i][j]` will store the LCS length of `s.substring(0, i)` and `t.substring(0, j)`.
    *   `StringBuilder sb`: Used for efficient string construction, as characters are appended one by one.

### Code Walkthrough

```java
class Solution {
    public String shortestCommonSupersequence(String s, String t) {
        int n = s.length();
        int m = t.length();

        // dp[i][j] will store the length of the Longest Common Subsequence
        // between s.substring(0, i) and t.substring(0, j).
        // The table size is (n+1) x (m+1) to handle empty string base cases.
        int[][] dp = new int[n+1][m+1];

        // --- Step 1: Fill the DP table to calculate LCS lengths ---
        // Iterate through all possible prefixes of s (from length 1 to n)
        for(int i = 1; i<=n; i++) {
            // Iterate through all possible prefixes of t (from length 1 to m)
            for(int j = 1; j<=m; j++) {
                // If the current characters s.charAt(i-1) and t.charAt(j-1) match
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    // The LCS length increases by 1, based on the LCS of the
                    // previous prefixes (s.substring(0, i-1) and t.substring(0, j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    // If characters do not match, the LCS length is the maximum of:
                    // 1. LCS of s.substring(0, i-1) and t.substring(0, j) (s without its last char)
                    // 2. LCS of s.substring(0, i) and t.substring(0, j-1) (t without its last char)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // --- Step 2: Backtrack through the DP table to construct the SCS ---
        StringBuilder sb = new StringBuilder();
        int i = n; // Start from the end of string s
        int j = m; // Start from the end of string t

        // Continue as long as there are characters in both strings to consider
        while(i > 0 && j > 0) {
            // If the current characters match (this character is part of LCS)
            if(s.charAt(i-1) == t.charAt(j-1)) {
                sb.append(s.charAt(i-1)); // Append it once to SCS
                i--; // Move diagonally up-left in DP table
                j--;
            }
            // If characters don't match, we need to decide which character to include.
            // We check which path (from s or from t) led to the current LCS length.
            else if(dp[i-1][j] > dp[i][j-1]) {