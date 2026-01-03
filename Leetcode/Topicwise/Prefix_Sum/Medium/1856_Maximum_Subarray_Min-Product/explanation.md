### Problem Understanding

The problem "Maximum Subarray Min-Product" asks us to find the largest possible "min-product" among all non-empty subarrays of a given integer array `nums`. A subarray's min-product is defined as the minimum value within that subarray multiplied by the sum of all elements in that subarray. The final result should be returned modulo 10^9 + 7.

For example, if `nums = [1, 2, 3, 2]`:
*   For subarray `[2, 3, 2]`: minimum is 2, sum is 2+3+2=7. Min-product = 2 * 7 = 14.
*   For subarray `[1, 2,