### Problem Understanding

The "Word Break" problem asks us to determine if a given input string `s` can be segmented into a space-separated sequence of one or more words, where each word must be present in a provided dictionary `wordDict`. Words from the dictionary can be reused multiple times in the segmentation.

For example, if `s = "leetcode"` and `wordDict = ["leet", "code"]`, the answer is `true` because "leetcode" can be segmented as "leet code". If `s = "applepenapple"` and `wordDict = ["apple", "pen"]`, the answer is `true` ("apple pen apple"). However, if `s = "catsandog"` and `wordDict = ["cats", "dog", "sand", "and", "cat"]`, the answer is `false`.

### Approach / Intuition

This problem exhibits optimal substructure and overlapping subproblems, making it a classic candidate for Dynamic Programming (DP). The core idea is to build up a solution for the entire string by solving smaller subproblems.

The intuition behind this specific solution is to iterate through the string `s` from left to right, keeping track of whether each prefix of `s` can be successfully segmented.

Let's define `dp[i]` as a boolean value indicating whether the prefix of `s` ending at index `i` (i.e., `s[0...i]`) can be segmented into words from `wordDict`.
The size of the `dp` array will be `n+1` (where `n` is the length of `s`), to accommodate indices from `0` to `n-1` for the string `s`, and potentially an implicit base case for an empty prefix.

1.  **Initialization:** All `dp` values are initially `false`.
2.  **Iteration