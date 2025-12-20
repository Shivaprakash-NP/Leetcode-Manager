### Problem Understanding

The problem asks us to calculate a maximum score based on an integer array `nums` and a binary string `s` of the same length `n`. The score is the sum of `nums[i]` values where `s.charAt(i)` is '1', but with a specific greedy selection mechanism for which `nums` values contribute to these '1' positions.

The rules for calculating the score are as follows:
1.  **Initial Prefix:** Iterate through `s` from left to right. For any index `i` where `s.charAt(i)` is '1' *and* this `i` occurs *before* the first '0' in `s`, the value `nums[i]` is directly added to the score. These values are