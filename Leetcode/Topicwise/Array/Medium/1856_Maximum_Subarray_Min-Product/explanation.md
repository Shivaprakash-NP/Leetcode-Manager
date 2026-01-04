### Problem Understanding

The problem "Maximum Subarray Min-Product" asks us to find the maximum possible "min-product" among all non-empty subarrays of a given array `nums` containing positive integers. The "min-product" for a subarray is defined as the product of its minimum element and the sum of all its elements. Since the result can be very large, we need to return the maximum min-product modulo `10^9 + 7`.

For example, if `nums = [1, 2, 3, 2]`:
*   Subarray `[1]`: min=1, sum=1, min-product = 1*1 = 1
*   Subarray `[1, 2]`: min=1, sum=3, min-product = 1*3 = 3
*   Subarray `[1, 2, 3]`: min=1, sum=6, min-product = 1*6 = 6
*   Subarray `[1, 2, 3, 2]`: min=1, sum=8, min-product = 1*8 = 8
*   Subarray `[2, 3, 2]`: min=2, sum=7, min-product = 2*7 = 14
*   Subarray `[2, 3]`: min=2, sum=5, min-product = 2*5 = 10
*   ... and so on.
The goal is to find the largest such min-product.

### Approach / Intuition

A naive approach would be to iterate through all possible subarrays, calculate their minimum and sum, and then their min-product. This would be O(N^3) or O(N^2) with optimizations, which is too slow for `N` up to 10^5.

The key insight to solve this problem efficiently is to fix each element `nums[i]` as the *minimum element* of a potential subarray. If `nums[i]` is the minimum element of a subarray `nums[l...r]`, it implies that all elements `nums[k]` within that range (`l <= k <= r`) must be greater than or equal to `nums[i]`.

To maximize the product `nums[i] * sum(subarray)`, if `nums[i]` is fixed as the minimum, we want to maximize the `sum(subarray)`. This means we should extend the subarray `[l...r]` as much as possible to the left and right, as long as `nums[i]` remains the minimum element within that extended range.

Therefore, for each `nums[i]`, we need to find:
1.  The index `l` of the **first element to its left that is strictly smaller than `nums[i]`**. If no such element exists, `l` would be -1.
2.  The index `r` of the **first element to its right that is strictly smaller than `nums[i]`**. If no such element exists, `r` would be `n` (array length).

Once we have `l` and `r` for a given `nums[i]`, the maximal subarray for which `nums[i]` is the minimum element is `nums[l+1 ... r-1]`. We can then calculate the sum of elements in this range `(nums[l+1] + ... + nums[r-1])` and multiply it by `nums[i]`. We do this for every `i` from `0` to `n-1` and keep track of the maximum product found.

To efficiently find these "previous smaller element" (`pse`) and "next smaller element" (`nse`) indices for all `i`, a **monotonic stack** is the optimal data structure.
To efficiently calculate the sum of elements within a range `[start, end]`, we use **prefix sums**.

### Data Structures and Algorithms

1.  **Prefix Sums:** An array `pre` where `pre[k]` stores the sum of `nums[0]` through `nums[k-1]`. This allows calculating the sum of any subarray `nums[a...b]` as `pre[b+1] - pre[a]` in O(1) time.
2.  **Monotonic Stack:** A stack that maintains elements (or their indices) in a strictly increasing or decreasing order. It's used twice:
    *   To find `pse[i]` (index of the previous element strictly smaller than `nums[i]`) for all `i` by iterating from left to right.
    *   To find `nse[i]` (index of the next element strictly smaller than `nums[i]`) for all `i` by iterating from right to left.

### Code Walkthrough

```java
class Solution {
    int MOD = (int)1e9 + 7; // Modulo constant for the final result

    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;

        // 1. Compute Prefix Sums
        // pre[k] will store the sum of nums[0]...nums[k-1]
        // pre