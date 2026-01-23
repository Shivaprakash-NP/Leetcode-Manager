### Problem Understanding

The problem asks us to count the number of valid strings of a given length `n` that can be formed using only lowercase vowels ('a', 'e', 'i', 'o', 'u'). The validity of a string is determined by specific rules regarding which vowel can follow another:

*   'a' can only be followed by 'e'.
*   'e' can only be followed by 'a' or 'i'.
*   'i' cannot be followed by another 'i'. (It can be followed by 'a', 'e', 'o', or 'u').
*   'o' can only be followed by 'i' or 'u'.
*   'u' can only be followed by 'a'.

We need to return the total count modulo `10^9 + 7` to handle potentially very large numbers.

### Approach / Intuition

This problem can be modeled as finding the number of paths of length `n` in a directed graph. Each vowel represents a node in the graph, and a directed edge exists from vowel A to vowel B if B can follow A according to the rules.

Let's map the vowels to integers for easier processing:
*   'a' -> 0
*   'e' -> 1
*   'i' -> 2
*   'o' -> 3
*   'u' -> 4

The transition rules translate to the following directed edges:
*   0 ('a') -> 1 ('e')
*   1 ('e') -> 0 ('a'), 2 ('i')
*   2 ('i') -> 0 ('a'), 1 ('e'), 3 ('o'), 4 ('u')
*   3 ('o') -> 2 ('i'), 4 ('u')
*   4 ('u') -> 0 ('a')

We are looking for the total number of sequences of `n` vowels that follow these rules. This is a classic dynamic programming problem. We can use a top-down approach with memoization (recursion with caching).

Let `dp[i][cur_vowel]` represent the number of valid vowel permutations of length `n-i` that start with `cur_vowel` at index `i`.
The base case for our recursion is when we have built a string of length `n`. If we are at index `n-1` (the last character), any valid `cur_vowel` at this position contributes `1` to the total count, as it completes a valid string of length `n`.

For any other index `i` and current vowel `cur_vowel`, the number of valid permutations starting from this state is the sum of `dp[i+1][next_vowel]` for all `next_vowel` that can legally follow `cur_vowel`. We sum these up, taking the modulo at each addition to prevent overflow.

The final answer will be the sum of `dp[0][v]` for all possible starting vowels `v` (i.e., 'a', 'e', 'i', 'o', 'u').

### Data Structures and Algorithms

*   **Data Structures:**
    *   `ArrayList<Integer>[] map`: This is an adjacency list used to represent the directed graph of vowel transitions. `map[vowel_index]` stores a list of integer indices of vowels that can follow `vowel_index`.
    *   `int[][] dp`: A 2D array used for memoization. `dp[i][cur]` stores the computed number of valid permutations of length `n-i` starting with `cur` at index `i`. It's initialized with -1 to indicate uncomputed states.

*   **Algorithms:**
    *   **Depth-First Search (DFS):** The `dfs` function recursively explores all possible valid vowel sequences.
    *   **Memoization (Dynamic Programming - Top-Down):** The `dp` table is used to store the results of subproblems. When `dfs` is called for a state `(i, cur)` that has already been computed, it directly returns the stored result, avoiding redundant calculations.

### Code Walkthrough

```java
class Solution {
    int mod = (int)1e9 + 7; // Modulo constant to prevent integer overflow

    // Recursive DFS function with memoization
    private int dfs(int i, int cur, ArrayList<Integer>[] map, int n, int[][] dp) {
        // Base case: If we have reached the last character position (n-1),
        // this path forms a valid string of length n.
        // So, it contributes 1 to the count.
        if(i == n - 1) return 1;

        // Check memoization table: If this state (i, cur) has been computed before,
        // return the stored result directly.
        if(dp[i][cur] != -1) return dp[i][cur];

        int ans = 0; // Accumulator for the total count from this state

        // Iterate through all possible next vowels 'v' that can follow 'cur'
        for(int v : map[cur]) {
            // Recursively call dfs for the next position (i+1) and the next vowel 'v'.
            // Add the result to 'ans', applying modulo at each step.
            ans = (ans + dfs(i + 1, v, map, n, dp)) % mod;
        }

        // Store the computed result in the dp table and return it.
        return dp[i][cur] = ans;
    }

    public int countVowelPermutation(int n) {
        // Initialize the adjacency list for 5 vowels (0-4)
        ArrayList<Integer>[] map = new ArrayList[5];
        for(int i = 0; i < 5; i++) map[i] = new ArrayList<>();

        // Populate the adjacency list based on the problem's rules:
        // 'a'(0) -> 'e'(1)
        map[0].addAll(Arrays.asList(1));
        // 'e'(1) -> 'a'(0), 'i'(2)
        map[1].addAll(Arrays.asList(0, 2));
        // 'i'(2) -> 'a'(0), 'e'(1), 'o'(3), 'u'(4)
        map[2].addAll(Arrays.asList(0, 1, 3, 4)); // Note: Original code had a slightly different order (1,3,4,0), but functionally equivalent
        // 'o'(3) -> 'i'(2), 'u'(4)
        map[3].addAll(Arrays.asList(2, 4));
        // 'u'(4) -> 'a'(0)
        map[4].addAll(Arrays.asList(0));

        int ans = 0; // Initialize total answer

        // Initialize the memoization table dp[n][5]
        // n rows for string length (indices 0 to n-1)
        // 5 columns for the 5 vowels (indices 0 to 4)
        int[][] dp = new int[n][5];
        // Fill dp table with -1 to mark all states as uncomputed
        for(int[] indp : dp) Arrays.fill(indp, -1);

        // Calculate the total count by starting a permutation with each possible vowel
        // at index 0 and summing their results.
        for(int i = 0; i < 5; i++) {
            ans = (ans + dfs(0, i, map, n, dp)) % mod;
        }

        return ans; // Return the final total count
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   The `dfs` function is called for each unique state `(i, cur)`.
    *   There are `n` possible values for `i` (from `0` to `n-1`).
    *   There are `5` possible values for `cur` (representing the 5 vowels).
    *   Thus, there are `n * 5` distinct states for which `dfs` will be executed at most once (due to memoization).
    *   Inside each `dfs` call, there's a loop that iterates through the `map[cur]` list. The maximum number of outgoing edges from any vowel is 4 (for 'i'). This is a constant factor.
    *   Therefore, the total time complexity is `O(n * 5 * max_degree_of_vowel_graph)`, which simplifies to `O(n)` because 5 and `max_degree` are constants.

*   **Space Complexity:**
    *   `dp` table: The `dp` table has dimensions