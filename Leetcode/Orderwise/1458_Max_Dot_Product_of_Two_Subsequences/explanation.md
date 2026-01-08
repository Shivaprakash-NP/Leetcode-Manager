### Problem Understanding

The problem "Max Dot Product of Two Subsequences" asks us to find the maximum possible dot product between two non-empty subsequences, one chosen from `nums1` and one from `nums2`. A subsequence can be formed by deleting zero or more elements from the original array, and the relative order of the remaining elements must be preserved. The dot product of two subsequences `A = [a1, a2, ..., ak]` and `B = [b1, b2, ..., bk]` (which must have the same length `k`) is `a1*b1 + a2*b2 + ... + ak*bk`.

The key constraints are:
1.  **Non-empty:** Both chosen subsequences must have at least