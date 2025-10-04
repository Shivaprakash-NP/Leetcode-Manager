```markdown
## Longest Common Subsequence (LCS) - Detailed Explanation

### 1. Problem Understanding:

The Longest Common Subsequence (LCS) problem asks us to find the length of the longest subsequence common to two given strings.  A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.  For example, "ace" is a subsequence of "abcde". The key difference between a subsequence and a substring is that a subsequence doesn't need to be contiguous.

### 2. Approach / Intuition:

The chosen approach is **Dynamic Programming (specifically, Memoization using Recursion)**.  Here's the intuition:

*   **Optimal Substructure:** The LCS problem has optimal substructure.  This means that the optimal solution to the problem can be constructed from optimal solutions to its subproblems.  If the last characters of the two strings match, then the LCS length is 1 plus the LCS length of the two strings without those last characters. If the last characters don't match, then the LCS length is the maximum of the LCS lengths obtained by either removing the last character from the first string or removing the last character from the second string.

*   **Overlapping Subproblems:** The recursive calls to solve the subproblems will overlap. For example, when calculating `LCS(s1[0...i], s2[0...j])`, we may need to calculate `LCS(s1[0...i-1], s2[0...j-1])` multiple times.  This suggests that memoization is a good way to avoid redundant calculations.

Memoization is chosen over tabulation (bottom-up DP) because it often leads to more readable and concise code, especially when the problem structure lends itself naturally to recursion.  Tabulation could also be used, but it might require more careful initialization and iteration order.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `String`: The input strings `text1` and `text2`.
    *   `int[][] dp`: A 2D array to store the results of the subproblems (memoization table).  `dp[i][j]` stores the length of the LCS of `text1[0...i]` and `text2[0...j]`.

*   **Algorithms:**
    *   **Recursion:** The `rec` function implements a recursive approach to solve the LCS problem.
    *   **Dynamic Programming (Memoization):** The `dp` array is used to store intermediate results, avoiding redundant calculations.

### 4. Code Walkthrough:

```java
class Solution {
    private int rec(String s1, String s2, int i1, int i2, int[][] dp) {
        if(i1<0 || i2<0) return 0;
        if(dp[i1][i2] != -1) return dp[i1][i2];

        if(s1.charAt(i1) == s2.charAt(i2)) 
            return dp[i1][i2] = 1+rec(s1, s2, i1-1, i2-1, dp);

        return dp[i1][i2] = Math.max(rec(s1, s2, i1, i2-1, dp), rec(s1, s2, i1-1, i2, dp));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[Math.max(l1, l2)][Math.max(l1, l2)];
        for(int[] d : dp) Arrays.fill(d, -1);
        return rec(text1, text2, l1-1, l2-1, dp);    
    }
}
```

*   **`longestCommonSubsequence(String text1, String text2)`:**
    *   `int l1 = text1.length();`: Stores the length of the first string.
    *   `int l2 = text2.length();`: Stores the length of the second string.
    *   `int[][] dp = new int[Math.max(l1, l2)][Math.max(l1, l2)];`: Creates a 2D array `dp` to store the results of subproblems. The size is `max(l1, l2) x max(l1,l2)`. It's important to realize the dp array only needs to be of size `l1 x l2`. This original submission is incorrect, but it still passes because all calls to dp access indices within bounds of l1 and l2. The fix for this memory issue would be to change the line to `int[][] dp = new int[l1][l2];`.
    *   `for(int[] d : dp) Arrays.fill(d, -1);`: Initializes all elements of the `dp` array to -1. This indicates that the subproblems have not been solved yet.
    *   `return rec(text1, text2, l1-1, l2-1, dp);`: Calls the recursive function `rec` to calculate the LCS length, starting from the last characters of the strings. `l1-1` and `l2-1` are used to access the last character index since string indices are 0-based.

*   **`rec(String s1, String s2, int i1, int i2, int[][] dp)`:**
    *   `if(i1<0 || i2<0) return 0;`: Base case: If either string is empty (i.e., we have reached the beginning of either string), the LCS length is 0.
    *   `if(dp[i1][i2] != -1) return dp[i1][i2];`: Checks if the result for the current subproblem has already been calculated and stored in the `dp` array. If so, return the stored value. This is the memoization step.
    *   `if(s1.charAt(i1) == s2.charAt(i2)) return dp[i1][i2] = 1+rec(s1, s2, i1-1, i2-1, dp);`: If the last characters of the current substrings match, then the LCS length is 1 plus the LCS length of the substrings without those characters. The result is stored in `dp[i1][i2]` before returning.
    *   `return dp[i1][i2] = Math.max(rec(s1, s2, i1, i2-1, dp), rec(s1, s2, i1-1, i2, dp));`: If the last characters don't match, then the LCS length is the maximum of the LCS lengths obtained by either removing the last character from the first string or removing the last character from the second string.  The result is stored in `dp[i1][i2]` before returning.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(m\*n), where `m` is the length of `text1` and `n` is the length of `text2`.  Each cell `dp[i][j]` in the `dp` array is computed at most once. The `rec` function will only be called O(m*n) times because of memoization.

*   **Space Complexity:** O(m\*n), primarily due to the `dp` array. The recursion call stack contributes O(min(m, n)) in the worst case which is less significant than the dp array. As mentioned previously, the dp array should actually be `l1 x l2`. The original incorrect solution creates a dp array of `max(l1,l2) x max(l1,l2)` which is unnecessary and costs additional memory.
