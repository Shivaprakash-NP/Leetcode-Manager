### Problem Understanding

The problem "Maximum Length of Pair Chain" asks us to find the longest possible sequence of pairs, where each subsequent pair in the sequence must satisfy a specific condition. Given a collection of pairs `[a, b]`, a pair `[c, d]` can follow `[a, b]` if `b < c`. We need to find the maximum length of such a chain that can be formed from the given pairs.

For example, if we have pairs `[[1,2], [7,8], [3,4]]`:
*   `[1,2]` can be followed by `[3,4]` (since `2 < 3`).
*   `[3,4]` can be followed by `[7,8]` (