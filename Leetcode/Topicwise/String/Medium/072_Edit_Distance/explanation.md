## Edit Distance Problem Explanation

Here's a breakdown of the provided Java code for solving the LeetCode "Edit Distance" problem:

### 1. Problem Understanding:

The Edit Distance problem asks us to find the minimum number of operations required to transform one string (`word1`) into another string (`word2`). The allowed operations are:

*   **Insertion:** Insert a character.
*   **Deletion:** Delete a character.
*   **Substitution:** Replace a character.

Essentially, we need to determine the minimum "distance" between the two strings, where distance is measured in terms of the number of edits.

### 2. Approach / Intuition:

The solution employs a dynamic programming approach with memoization (top-down DP). Here's the intuition:

*   **Recursive Structure:**  The core idea is to break down the problem into smaller, overlapping subproblems.  Consider comparing `word1[i...]` and `word2[j...]`.  We have two main possibilities:
    *   If `word1[i]` and `word2[j]` are equal, we don't need any operation. We can simply move to the next characters in both strings (`word1[i+1...]` and `word2[j+1...]`).
    *   If `word1[i]` and `word2[j]` are different, we have three choices:
        *   **Substitute:** Replace `word1[i]` with `word2[j]`.  Then, we solve the subproblem for `word1[i+1...]` and `word2[j+1...]`.  This requires one operation.
        *   **Delete:** Delete `word1[i]`.  Then, we solve the subproblem for `word1[i+1...]` and `word2[j...]`. This requires one operation.
        *   **Insert:** Insert `word2[j]` before `word1[i]`. Then, we solve the subproblem for `word1[i...]` and `word2[j+1...]`. This requires one operation.
    We choose the operation that leads to the minimum edit distance.

*   **Base Cases:** The base cases are when we reach the end of one or both strings. If we reach the end of `word1` (i.e., `i == word1.length()`), the remaining characters in `word2` need to be inserted. Similarly, if we reach the end of `word2`, the remaining characters in `word1` need to be deleted. If we reach the end of both strings, the edit distance is 0.

*   **Memoization:**  The recursive approach, without memoization, would lead to redundant calculations. We use a 2D array `dp` to store the results of previously computed subproblems. `dp[i][j]` stores the minimum edit distance between `word1[i...]` and `word2[j...]`. If we encounter a subproblem that has already been solved (i.e., `dp[i][j] != -1`), we simply return the stored value.

This approach is chosen because it naturally expresses the problem's recursive structure and dynamic programming allows to avoid exponential time complexity by storing the already computed results of subproblems.

### 3. Data Structures and Algorithms:

*   **Data Structure:**
    *   `dp[][]`: A 2D integer array used for memoization. `dp[i][j]` stores the minimum edit distance between the suffixes `word1[i...]` and `word2[j...]`.
*   **Algorithm:**
    *   **Dynamic Programming (Memoization):** A top-down dynamic programming approach to efficiently solve the overlapping subproblems.
    *   **Recursion:** The problem is recursively divided into smaller subproblems.

### 4. Code Walkthrough:

```java
class Solution {

    private int rec(int i, int j, String s, String t, int[][] dp) {
        if(i == s.length() || j == t.length()) {
            return Math.max(s.length()-i, t.length()-j);
        }

        if(dp[i][j] != -1) return dp[i][j];

        int ans = 0;
        if(s.charAt(i) == t.charAt(j)) {
            ans = rec(i+1, j+1, s, t, dp);
        } else {
            int op1 = rec(i+1, j+1, s, t, dp)+1;
            int op2 = rec(i+1, j, s, t, dp)+1;
            int op3 = rec(i, j+1, s, t, dp)+1;
            ans = Math.min(op1, Math.min(op2, op3));
        }

        return dp[i][j] = ans;
    }
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1+1][l2+1];

        for(int[] d : dp) Arrays.fill(d, -1);
        return rec(0, 0, word1, word2, dp);
    }
}
```

*   **`minDistance(String word1, String word2)`:**
    *   `l1 = word1.length();`: Gets the length of `word1`.
    *   `l2 = word2.length();`: Gets the length of `word2`.
    *   `int[][] dp = new int[l1+1][l2+1];`: Initializes the `dp` array with dimensions `(l1+1) x (l2+1)`.  The extra row and column are needed to handle the base cases where one or both strings are empty.
    *   `for(int[] d : dp) Arrays.fill(d, -1);`: Initializes all elements of the `dp` array to -1, indicating that the subproblems haven't been solved yet.
    *   `return rec(0, 0, word1, word2, dp);`: Calls the recursive function `rec` to start the dynamic programming process, starting from the beginning of both strings (indices 0 and 0).

*   **`rec(int i, int j, String s, String t, int[][] dp)`:**
    *   `if(i == s.length() || j == t.length()) { return Math.max(s.length()-i, t.length()-j); }`:  **Base Case:** If we've reached the end of either `s` (word1) or `t` (word2), return the number of remaining characters in the other string. This represents the number of insertions or deletions needed to make the strings equal.  `Math.max(s.length()-i, t.length()-j)` returns the remaining length.
    *   `if(dp[i][j] != -1) return dp[i][j];`: **Memoization:** Checks if the result for the subproblem `(i, j)` is already stored in `dp`. If so, return the stored value directly.
    *   `int ans = 0;`: Initializes the variable to store the minimum edit distance for the current subproblem.
    *   `if(s.charAt(i) == t.charAt(j)) { ans = rec(i+1, j+1, s, t, dp); }`: If the characters at the current indices `i` and `j` are equal, no operation is needed.  Recursively call `rec` for the next characters in both strings.
    *   `else { ... }`: If the characters are not equal, we need to perform an operation (insert, delete, or substitute).
        *   `int op1 = rec(i+1, j+1, s, t, dp)+1;`: **Substitute:** Replace `s.charAt(i)` with `t.charAt(j)`. Cost is 1 plus the cost of editing the remaining substrings.
        *   `int op2 = rec(i+1, j, s, t, dp)+1;`: **Delete:** Delete `s.charAt(i)`. Cost is 1 plus the cost of editing the remaining substrings.
        *   `int op3 = rec(i, j+1, s, t, dp)+1;`: **Insert:** Insert `t.charAt(j)` before `s.charAt(i)`. Cost is 1 plus the cost of editing the remaining substrings.
        *   `ans = Math.min(op1, Math.min(op2, op3));`: Choose the minimum cost among the three operations.
    *   `return dp[i][j] = ans;`: Store the calculated minimum edit distance `ans` in `dp[i][j]` and return it.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(m * n), where 'm' is the length of `word1` and 'n' is the length of `word2`. This is because each cell in the `dp` array is computed only once due to memoization. In the worst case, the `rec` function will be called for all possible combinations of `i` and `j`, where `0 <= i <= m` and `0 <= j <= n`.

*   **Space Complexity:** O(m * n), where 'm' is the length of `word1` and 'n' is the length of `word2`. This is because of the `dp` array which has dimensions `(m+1) x (n+1)`. In addition to the `dp` array, the recursive calls will add to the call stack, however, the maximum depth of the recursion is limited by `max(m, n)`, so the space required by the call stack is O(max(m, n)), which is less than O(m * n).  Therefore, the dominant space complexity is due to the `dp` array.
