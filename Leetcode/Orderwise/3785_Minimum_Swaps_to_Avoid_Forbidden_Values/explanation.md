### Problem Understanding

The problem "Minimum Swaps to Avoid Forbidden Values" asks us to find the minimum number of swaps required in an array `nums` such that for every index `i`, `nums[i]` is not equal to `forbidden[i]`. Both `nums` and `forbidden` are integer arrays of the same length `n`.

In simpler terms, we are given an array `nums` and a set of "forbidden" values for each position `i` (specified by `forbidden[i]`). Our goal is to rearrange `nums` using the fewest possible swaps so that no element `nums[i]` is at a position `i` where its value matches `forbidden[i]`. The code specifically focuses on the most direct violations: cases where `nums[i]