### Problem Understanding

The problem "Distinct Subsequences" asks us to count the number of ways a string `t` can be formed as a subsequence of another string `s`. A subsequence is created by deleting zero or more characters from the original string without changing the order of the remaining characters. For example, if `s = "rabbbit"` and `t = "rabbit"`, we need to find how many distinct ways we can pick characters from `s` to form `t` while maintaining their relative order.

### Approach / Intuition

This problem exhibits characteristics suitable for Dynamic Programming (DP):
1.  **Optimal Substructure:** The solution to the main problem can be constructed from solutions to smaller subproblems.
2.  **Overlapping Subproblems:** The same subproblems are encountered multiple times.

Let's define `dp[i][j]` as the number of distinct subsequences of `s[0...i-1]` (the first `i` characters of `s`) that are equal to `t[0...j-1]` (the first `j` characters of `t`).

When considering `dp[i][j]`, we have two main choices regarding the character `s.charAt(i-1)` (the `i`-th character of `s`):

1.  **Don't use `s.charAt(i-1)`:** We choose *not* to use the current character `s.charAt(i-1)` to form `t[0...j-1]`. In this case, the count of distinct subsequences is simply the number of ways to form `t[0...j-1]` using the prefix `s[0...i-2]`. This value is `dp[i-1][j]`. This option is always available.

2.  **Use `s.charAt(i-1)`:** We choose to use the current character `s.charAt(i-1)` to match `t.charAt(j-1)`. This is only possible if `s.charAt(i-1)` is actually equal to `t.charAt(j-1)`. If they are equal, then we have successfully matched the last characters. Now, we need to find the number of ways to form the remaining prefix `t[0...j-2]` using the remaining prefix `s[0...i-2]`. This value is `dp[i-1][j-1]`.

Combining these two possibilities, the recurrence relation becomes:

`dp[i][j] = dp[i-1][j]` (always include the "don't use `s.charAt(i-1)`" case)
If `s.charAt(i-1) == t.charAt(j-1)`, then `dp[i][j] += dp[i-1][j-1]` (add the "use `s.charAt(i-1)`" case).

**Base Cases:**

*   `dp[i][0] = 1` for all `0 <= i <= n`: To form an empty string `t` (length 0), there is always one way: by deleting all characters from any prefix of `s` (including an empty prefix).
*   `dp[0][j] = 0` for all `1 <= j <= m`: To form a non-empty string `t` using an empty string `s`, it's impossible.

The solution builds the `dp` table bottom-up, starting from the base cases and iteratively calculating `dp[i][j]` for increasing `i` and `j`. The final answer will be `dp[n][m]`, representing the number of distinct subsequences of the entire string `s` that form the entire string `t`.

### Data Structures and Algorithms

*   **Data Structure:** A 2D array (`int[][] dp`) is used to store the results of subproblems. This array has dimensions `(n+1) x (m+1)`, where `n` is the length of string `s` and `m` is the length of string `t`.
*   **Algorithm:** Dynamic Programming, specifically an iterative (bottom-up) approach.

### Code Walkthrough

```java
class Solution {
    public int numDistinct(String s, String t) {
        // 1. Edge Case Handling:
        // If the target string 't' is longer than the source string 's',
        // it's impossible to form 't' as a subsequence of 's'.
        // In this scenario, there are 0 distinct subsequences.
        if(t.length() > s.length()) return 0;

        // 2. Initialize Dimensions:
        // Get the lengths of strings s and t for easier access and DP table sizing.
        int n = s.length(); // Length of string s
        int m = t.length(); // Length of string t

        // 3. Initialize DP Table:
        // Create a 2D array 'dp' of size (n+1) x (m+1).
        // dp[i][j] will store the number of distinct subsequences of s[0...i-1] that equal t[0...j-1].
        // The +1 in dimensions accounts for the base cases involving empty prefixes (index 0).
        int[][] dp = new int[n+1][m+1];

        // 4. Base Case Initialization (for empty target string 't'):
        // To form an empty string (t's prefix of length 0, i.e., j=0), there is always 1 way:
        // by deleting all characters from any prefix of 's' (including an empty prefix).
        // This loop sets dp[i][0] = 1 for all i from 0 to n.
        for(int i = 0; i<=n; i++) dp[i][0] = 1;

        // 5. Fill DP Table (Iterative Calculation):
        // Iterate through the prefixes of 's' (from length 1 to n).
        for(int i = 1; i<=n; i++) {
            // Iterate through the prefixes of 't' (from length 1 to m).
            for(int j = 1; j<=m; j++) {
                // Option 1: Don't use s.charAt(i-1)
                // The number of ways to form t[0...j-1] using s[0...i-1]
                // without using s.charAt(i-1) is the same as forming t[0...j-1]
                // using s[0...i-2]. This value is dp[i-1][j].
                dp[i][j] = dp[i-1][j];

                // Option 2: Use s.charAt(i-1)
                // This option is only possible if the current characters match.
                // Note: (i-1) and (j-1) are used to access characters because
                // 'i' and 'j' represent lengths, and strings are 0-indexed.
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    // If s.charAt(i-1) matches t.charAt(j-1), we've used them.
                    // Now, we need to find how many ways we can form the remaining
                    // prefix t[0...j-2] using s[0...i-2]. This value is dp[i-1][j-1].
                    // We add this to dp[i][j] because it represents an additional, distinct way.
                    dp[i][j] += dp[i-1][j-1];
                }
            }
        }

        // 6. Return Result:
        // After filling the entire DP table, dp[n][m] will contain the total
        // number of distinct subsequences of the entire string 's' (length n)
        // that are equal to the entire string 't' (length m).
        return dp[n][m];
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(n * m)**
    *   The code involves two nested loops. The outer loop runs `n` times (for `i` from 1 to `n`), and the inner loop runs `m` times (for `j` from 1 to `m`).
    *   Inside the loops, operations like array access, character comparison, and addition take constant time, O(1).
    *   Therefore, the total time complexity is directly proportional to the product of the lengths of the two strings, `n * m`.

*   **Space Complexity: O(n * m)**
    *   A 2D `dp` array of size `(n+1) x (m+1)` is allocated to store the intermediate results.
    *