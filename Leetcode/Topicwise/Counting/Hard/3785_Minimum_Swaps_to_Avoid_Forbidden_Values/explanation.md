### Problem Understanding

The problem, "Minimum Swaps to Avoid Forbidden Values", asks us to find the minimum number of swaps required in an array `nums` such that for every index `i`, the value `nums[i]` is not equal to `forbidden[i]`. We are given two arrays, `nums` and `forbidden`, of the same length `n`.

In simpler terms: We have an array `nums` and a corresponding "rulebook" `forbidden`. For each position `i`, `forbidden[i]` tells us what value *cannot* be at `nums[i]`. Our goal is to rearrange `nums` using the fewest possible swaps so that no `nums[i]` violates its `forbidden[i]` rule.

### Approach / Intuition

The