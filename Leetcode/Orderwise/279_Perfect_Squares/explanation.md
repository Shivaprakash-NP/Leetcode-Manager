### Problem Understanding

The problem "Perfect Squares" asks us to find the minimum number of perfect square numbers (e.g., 1, 4, 9, 16, ...) that sum up to a given positive integer `n`. For example, if `n = 12`, the answer is 3 because `12 = 4 + 4 + 4`. If `n = 13`, the answer is 2 because `13 = 4 + 9`.

### Approach / Intuition

The provided solution uses a **recursive backtracking (Depth-First Search) approach with pruning**.

The core idea is to explore different combinations of perfect squares that sum up to `n` and keep track of the minimum count found so far.

1.  