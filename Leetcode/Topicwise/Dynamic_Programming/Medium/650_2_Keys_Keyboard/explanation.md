### Problem Understanding

The problem "2 Keys Keyboard" asks us to find the minimum number of operations to obtain exactly `n` 'A' characters on a notepad. We start with a single 'A'. We have two allowed operations:
1.  **Copy All:** Copies all characters currently on the notepad. This copied content replaces any previously copied content.
2.  **Paste:** Pastes the last copied content onto the notepad.

For example, to get 3 'A's:
1.  Start: 'A' (1 char)
2.  Copy All: 'A' is copied. (1 operation)
3.  Paste: 'AA' (2 chars). (1 operation)
4.  Paste: 'AAA' (3 chars). (1 operation)
Total operations: 3.

Another example, to get 6 'A's:
1.  Start: 'A' (1 char)
2.  Copy All: 'A' is copied. (1 op)
3.  Paste: 'AA' (2 chars). (1 op)
4.  Paste: 'AAA' (3 chars). (1 op)
5.  Copy All: 'AAA' is copied. (1 op)
6.  Paste: 'AAAAAA' (6 chars). (1 op)
Total operations: 5.

The goal is to find the *minimum* such operations for a given `n`.

### Approach / Intuition

This problem exhibits optimal substructure and overlapping subproblems, making it a good candidate for dynamic programming. Let `dp[i]` represent the minimum number of operations required to obtain `i` 'A' characters.

1.  **Base Case:** We start with one 'A', so `dp[1] = 0` operations are needed to have 1 'A'.

2.  **General Case (`dp[i]` for `i > 1`):**
    To obtain `i` characters, we must have arrived at this state by pasting some content. Suppose we had `j` characters on the notepad and then performed a `Copy All` operation, followed by `(i/j - 1)` `Paste` operations to reach `i` characters. This sequence of operations is only possible if `j` is a divisor of `i`.

    The steps involved in going from `j` characters to `i` characters (where `i` is a multiple of `j`):
    *   1 operation for `Copy All` (copying the `j` characters).
    *   `(i/j - 1)`