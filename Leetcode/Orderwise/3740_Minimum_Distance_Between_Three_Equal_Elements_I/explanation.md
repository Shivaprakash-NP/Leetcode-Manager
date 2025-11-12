### Problem Understanding

The problem "Minimum Distance Between Three Equal Elements I" asks us to find the minimum "distance" between three elements in a given integer array `nums` that have the same value. The "distance" is defined as the sum of the pairwise differences of their indices. Specifically, if we find three indices `i`, `j`, and `k` such that `i < j < k` and `nums[i] == nums[j] == nums[k]`, we need to calculate `(j-i) + (k-j) + (k-i)` and find the smallest such value across all valid triplets. If no such triplet of three equal elements exists in the array, we should return -1.

### Approach / Intuition

The core idea behind this solution is a brute-force approach. Since we need to find a combination of *three* indices (`i`, `j`, `k`) that satisfy certain conditions (`i < j < k` and `nums[i] == nums[j] == nums[k]`), the most straightforward way is to iterate through all possible unique combinations of three distinct indices and check if they meet the criteria.

1.  **Iterate all triplets:** We use three nested loops.
    *   The outer loop picks the first index `i`.
    *   The middle loop picks the second index `j`, ensuring `j` is always greater than `i` (`j = i+1`). This guarantees `j-i` is positive and we consider unique pairs.
    *   The inner loop picks the third index `k`, ensuring `k` is always greater than `j` (`k = j+1`). This guarantees `k-j` and `k-i` are positive and we consider unique triplets in increasing order of indices.
2.  **Check equality:** Inside the innermost loop, for each triplet `(i, j, k)`, we check if the values at these indices are equal: `nums[i] == nums[j] && nums[j] == nums[k]`.
3.  **Calculate distance and minimize:** If the values are equal, we calculate the specified distance: `(j-i) + (k-j) + (k-i)`. This sum simplifies to `2 * (k-i)`. We then update a running minimum variable with this new distance if it's smaller than the current minimum.
4.  **Handle no solution:** We initialize our minimum distance variable to `Integer.MAX_VALUE`. If, after checking all possible triplets, this variable remains `Integer.MAX_VALUE`, it means no three equal elements were found, so we return -1. Otherwise, we return the stored minimum distance.

This approach is chosen because it directly translates the problem's requirements into code and is simple to implement. For typical constraints on array size in "Part I" problems, an `O(N^3)` solution is often acceptable.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] nums`: The input array, a simple array of integers.
    *   `int min`: A single integer variable used to store the minimum distance found so far.
*   **Algorithms:**
    *   **Brute-force search:** The core algorithm involves iterating through all possible combinations of three distinct indices.
    *   **Comparison and Minimization:** Using `Math.min()` to keep track of the smallest distance encountered.

### Code Walkthrough

```java
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length; // Get the total number of elements in the array.
        int min = Integer.MAX_VALUE; // Initialize 'min' to the largest possible integer value.
                                     // This ensures that the first valid distance found will always be smaller
                                     // and correctly set as the initial minimum.

        // Outer loop: Iterates through all possible indices for the first element 'i'.
        // It goes up to n-1, but practically, it needs to leave room for at least two more elements (j and k).
        for(int i = 0; i < n; i++) {
            // Middle loop: Iterates through all possible indices for the second element 'j'.
            // It starts from 'i + 1' to ensure 'j' is always after 'i', satisfying 'i < j'.
            for(int j = i + 1; j < n; j++) {
                // Inner loop: Iterates through all possible indices for the third element 'k'.
                // It starts from 'j + 1' to ensure 'k' is always after 'j', satisfying 'j < k'.
                // Together, these three loops generate all unique triplets (i, j, k) where i < j < k.
                for(int k = j + 1; k < n; k++) {
                    // Condition check: Verify if the elements at indices i, j, and k are all equal.
                    if(nums[i] == nums[j] && nums[j] == nums[k]) {
                        // If they are equal, calculate the distance.
                        // The distance is (j-i) + (k-j) + (k-i).
                        // This expression simplifies to 2 * (k-i).
                        int currentDistance = (j - i) + (k - j) + (k - i);
                        // Update 'min' with the smaller of its current value and the newly calculated distance.
                        min = Math.min(min, currentDistance);
                    }
                }
            }
        }

        // After checking all possible triplets, determine the return value.
        // If 'min' is still Integer.MAX_VALUE, it means no three equal elements were found.
        // In this case, return -1 as per problem requirements.
        // Otherwise, return the minimum distance found.
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(N^3)**
    *   The solution involves three nested `for` loops.
    *   The outer loop runs approximately `N` times (where `N` is `nums.length`).
    *   The middle loop runs approximately `N` times for each iteration of the outer loop.
    *   The inner loop runs approximately `N` times for each iteration of the middle loop.
    *   Inside the innermost loop, operations like comparisons, arithmetic calculations, and `Math.min()` are all constant time operations.
    *   Therefore, the total number of operations is roughly proportional to `N * N * N`, leading to a cubic time complexity.

*   **Space Complexity: O(1)**
    *   The solution uses a fixed number of extra variables: `n`, `min`, `i`, `j`, `k`, and `currentDistance`.
    *   The amount of memory used does not depend on the size of the input array `nums`.
    *   Hence, the space complexity is constant.