### Problem Understanding

The problem "Wildcard Matching" asks us to determine if a given input string `s` can be matched by a pattern string `p`. The pattern string `p` can contain two special wildcard characters:

*   `?`: Matches any single character.
*   `*`: Matches any sequence of characters, including an empty sequence.

The match must cover the *entire* input string `s` (not just a substring).

For example:
*   `s = "aa"`, `p = "a"` -> `false` (pattern too short)
*   `s = "aa"`, `p = "aa"` -> `true`
*   `s = "aaa"`, `p = "a*"` -> `true` (`*` matches "aa")
*   `s = "ab"`, `p = "?*"` -> `true` (`?` matches 'a', `*` matches 'b')
*   `s = "adceb"`, `p = "*a*b"` -> `true` (`*` matches empty, 'a', `*` matches "dce", 'b')
*   `s = "acdcb"`, `p = "a*c?b"` -> `false`

### Approach / Intuition

This problem is a classic example that can be solved efficiently using **recursion with memoization** (which is a top-down dynamic programming approach). The core idea is to break down the problem into smaller, overlapping subproblems and store their results to avoid redundant computations.

Let's define a function `dfs(i, j)` which returns `true` if the substring `s[i...s.length()-1]` matches the pattern `p[j...p.length()-1]`, and `false` otherwise.

The intuition behind the recursive steps is as follows:

1.  **Base Cases:**
    *   If we have processed all characters in `s` (`i >= s.length()`):
        *   For a match to be possible, all remaining characters in `p` (from `j` onwards) *must* be `*`. If any non-`*` character is found, it's a mismatch.
        *   If all remaining characters are `*`, it's a match.
    *   If we have processed all characters in `p` (`j >= p.length()`) but `s` still has characters left (`i < s.length()`):
        *   This is a mismatch, as there's no more pattern to match the remaining string characters.

2.  **Recursive Steps (considering `p.charAt(j)`):**

    *   **Case 1: `p.charAt(j)` is `?` or `p.charAt(j)` matches `s.charAt(i)`:**
        *   If the current characters match (either literally or `?` acting as a wildcard), then we need to check if the rest of the string `s` matches the rest of the pattern `p`.
        *   Recursively call `dfs(i+1, j+1)`.

    *   **Case 2: `p.charAt(j)` is `*`:**
        *   This is the most crucial part. The `*` wildcard can represent *any* sequence of characters, including an empty sequence. This gives us two possibilities:
            *   **`*` matches an empty sequence:** In this scenario, we effectively ignore the `*` and try to match `s[i...]` with `p[j+1...]`. This means we advance only the pattern pointer: `dfs(i, j+1)`.
            *   **`*` matches one or more characters:** Here, `*` matches `s.charAt(i)`. Since `*` can match *multiple* characters, it effectively "consumes" `s.charAt(i)` but remains active to potentially match `s.charAt(i+1)`, `s.charAt(i+2)`, etc. So, we advance the string pointer, but keep the pattern pointer at `j` (to allow `*` to match more characters): `dfs(i+1, j)`.
        *   Since `*` can do *either* of these, the result for this state is `true` if *either* possibility leads to a match: `dfs(i, j+1) || dfs(i+1, j)`.

    *   **Case 3: `p.charAt(j)` is a regular character and does not match `s.charAt(i)`:**
        *   This is a direct mismatch. Return `false`.

**Memoization:** To optimize, we use a 2D array `dp[s.length()][p.length()]` to store the results of `dfs(i, j)`. `dp[i][j]` will store `1` for `true`, `0` for `false`, and `-1` for uncomputed states. Before computing `dfs(i, j)`, we check `dp[i][j]`. If it's not `-1`, we return the stored result.

### Data Structures and Algorithms

*   **Algorithm:** Recursion with Memoization (Top-down Dynamic Programming).
*   **Data Structure:**
    *   `int[][] dp`: A 2D integer array of size `s.length() x p.length()` used for memoization.
        *   `dp[i][j] = -1` indicates the subproblem `(i, j)` has not been computed yet.
        *   `dp[i][j] = 1` indicates `s[i...]` matches `p[j...]`.
        *   `dp[i][j] = 0` indicates `s[i...]` does not match `p[j...]`.

### Code Walkthrough

```java
/*
This is just rec so if u see ? or same char you need to match and go to i+1, j+1
else if u see * then you can either match a empty substring do to i, j+! or just match 1 char in s and keep that * alive that is i+1, j

this keeping * alive is due to avoid TLE bcoz if u run a loop if length s.length() everytime u see a * then that will give TLE
*/

class Solution {
    String s; // Stores the input string 's'
    String p; // Stores the pattern string 'p'

    // Recursive helper function with memoization
    private boolean dfs(int i, int j, int[][] dp) {
        // Base Case 1: String 's' is fully consumed
        if(i >= s.length()) {
            // If 's' is exhausted, the remaining pattern 'p[j...]' must only contain '*'
            for(int k = j; k<p.length(); k++) {
                if(p.charAt(k) != '*') return false; // Found a non-'*' character, mismatch
            }
            return true; // All remaining pattern characters are '*', so it's a match
        }
        
        // Base Case 2: Either 's' or 'p' is fully consumed (and 's' wasn't handled by Base Case 1)
        // This specifically means 'p' is exhausted (j >= p.length()) but 's' is not (i < s.length()).
        // If 's' was exhausted, it would have been caught by the first if-block.
        if(i >= s.length() || j >= p.length()) return false;

        // Memoization check: If this subproblem (i, j) has already been computed, return the stored result
        if(dp[i][j] != -1) return dp[i][j] == 1;

        boolean is = false; // Variable to store the result of the current subproblem

        // Case: Current pattern character is '?' or matches the current string character
        if(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
            // Match, move both pointers forward
            is = dfs(i+1, j+1, dp);
        } 
        // Case: Current pattern character is '*'
        else if(p.charAt(j) == '*') {
            // '*' can either:
            // 1. Match an empty sequence: Move pattern pointer 'j' forward, keep string pointer 'i'
            // 2. Match the current character s.charAt(i): Move string pointer 'i' forward, keep '*' active (pattern pointer 'j')
            // If either of these possibilities leads to a match, then 'is' is true
            is = dfs(i, j+1, dp) || dfs(i+1, j, dp);
        } 
        // Case: Current pattern character is a regular character and does not match s.charAt(i)
        else {
            return false; // Mismatch
        }

        // Store the computed result in the DP table before returning
        dp[i][j] = is ? 1 : 0;

        return is; // Return the result
    }

    // Public method to start the wildcard matching process
    public boolean isMatch(String a, String b) {
        s = a; // Assign input string 'a' to instance variable 's'
        p = b; // Assign pattern string 'b' to instance variable 'p'
        int n = s