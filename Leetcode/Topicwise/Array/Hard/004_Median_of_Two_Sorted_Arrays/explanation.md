## Median of Two Sorted Arrays - Explained

Here's a detailed explanation of the Java code provided for the "Median of Two Sorted Arrays" LeetCode problem.

### 1. Problem Understanding

The problem asks us to find the median of two sorted arrays, `nums1` and `nums2`, combined into a single sorted array.  The median is the middle element of a sorted array if the array's length is odd, and the average of the two middle elements if the array's length is even.  The challenge lies in finding the median efficiently without actually merging the two arrays, ideally in logarithmic time complexity.

### 2. Approach / Intuition

The core idea behind this solution is to use **binary search**. Instead of merging the two arrays, we aim to find the correct "partition" or cut points in each array such that:

1.  All elements to the left of the cut points are less than or equal to all elements to the right of the cut points.
2.  The total number of elements to the left of the cut points equals (n1 + n2 + 1) / 2, where n1 and n2 are the lengths of `nums1` and `nums2`, respectively. This ensures we're targeting the median.

The algorithm iteratively adjusts the cut point in the shorter array (`nums1`) using binary search. Once the correct cut point is found, the median can be calculated based on the elements immediately to the left and right of the cut points in both arrays.

**Why Binary Search?**

Binary search is effective because the cut point selection process narrows down the search space by half in each iteration. This allows us to achieve a logarithmic time complexity, which is crucial for efficiency, especially with large arrays.

**Ensuring Correct Partition:**

The conditions `l1 <= r2` and `l2 <= r1` ensure that the elements to the left of the cut points are smaller than or equal to the elements to the right. `l1` and `l2` are the largest elements to the left of the cuts, while `r1` and `r2` are the smallest elements to the right.

### 3. Data Structures and Algorithms

*   **Data Structures:**  The code primarily uses integer arrays (`nums1`, `nums2`) and integer variables to store indices and intermediate calculations. No complex data structures are used.
*   **Algorithms:**
    *   **Binary Search:**  The core algorithm used to find the correct partition.
    *   **Conditional Logic:**  `if-else` statements are used to determine the search direction (adjust `low` or `high`), and to calculate the median based on whether the combined array length is even or odd.

### 4. Code Walkthrough

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        // Ensure nums1 is the shorter array
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int low = 0, high = n1; // Initialize binary search range
        int n = n1 + n2; // Total length of the merged array

        while (low <= high) {
            int mid1 = (low + high) / 2; // Cut point in nums1
            int mid2 = (n1 + n2 + 1) / 2 - mid1; // Cut point in nums2 (derived from mid1)

            // Determine l1, l2, r1, r2: elements around the cut points
            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE; // Left element of cut in nums1
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE; // Left element of cut in nums2
            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE; // Right element of cut in nums1
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE; // Right element of cut in nums2

            // Check if the cut points are correct
            if (l1 <= r2 && l2 <= r1) {
                // Calculate median based on even or odd length
                if (n % 2 == 1) return Math.max(l1, l2); // Odd length: median is max of left sides
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0; // Even length: median is average of max left and min right
            } else if (l1 > r2) {
                // Adjust search range: move cut in nums1 to the left
                high = mid1 - 1;
            } else {
                // Adjust search range: move cut in nums1 to the right
                low = mid1 + 1;
            }
        }
        return 0.0; // Should not reach here if arrays are sorted correctly. Indicates error.
    }
}
```

**Line-by-line explanation:**

1.  `int n1 = nums1.length;` and `int n2 = nums2.length;`: Get the lengths of the two arrays.
2.  `if (n1 > n2) return findMedianSortedArrays(nums2, nums1);`:  This is an optimization.  It ensures that `nums1` is the shorter array. This simplifies the binary search logic because we perform binary search on the shorter array.  This also reduces the search space.
3.  `int low = 0, high = n1;`: Initialize `low` and `high` for binary search. The search space is the possible cut points in `nums1`. The cut point can be anywhere from 0 (no elements from nums1 are on the left side) to `n1` (all elements from nums1 are on the left side).
4.  `int n = n1 + n2;`: Calculate the total length of the combined array.
5.  `while (low <= high)`: The binary search loop continues until the correct cut point is found.
6.  `int mid1 = (low + high) / 2;`: Calculate the middle point in `nums1`. This is our potential cut point.
7.  `int mid2 = (n1 + n2 + 1) / 2 - mid1;`: Calculate the corresponding cut point in `nums2`. `(n1 + n2 + 1) / 2` represents the number of elements that must be on the left side of the combined array for the cut points to be valid. We subtract `mid1` (the number of elements from `nums1` on the left) to determine `mid2` (the number of elements from `nums2` on the left).
8.  `int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;`: Get the element to the left of the cut in `nums1`. If `mid1` is 0, it means there are no elements to the left, so we use `Integer.MIN_VALUE` to ensure that `l1` is less than any element in `nums2`.
9.  `int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;`: Get the element to the left of the cut in `nums2`.  Similar to `l1`, `Integer.MIN_VALUE` is used if `mid2` is 0.
10. `int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;`: Get the element to the right of the cut in `nums1`. If `mid1` is `n1`, it means there are no elements to the right, so we use `Integer.MAX_VALUE` to ensure that `r1` is greater than any element in `nums2`.
11. `int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;`: Get the element to the right of the cut in `nums2`. Similar to `r1`, `Integer.MAX_VALUE` is used if `mid2` is `n2`.
12. `if (l1 <= r2 && l2 <= r1)`: This is the crucial condition that checks if the cut points are valid. It verifies that all elements on the left side are less than or equal to all elements on the right side.
13. `if (n % 2 == 1) return Math.max(l1, l2);`: If the combined length is odd, the median is simply the maximum of the left elements (`l1` and `l2`).
14. `return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;`: If the combined length is even, the median is the average of the maximum of the left elements and the minimum of the right elements.
15. `else if (l1 > r2) high = mid1 - 1;`: If `l1 > r2`, it means the cut point in `nums1` is too far to the right.  We need to move it left by decreasing `high`.
16. `else low = mid1 + 1;`: If `l2 > r1`, it means the cut point in `nums1` is too far to the left. We need to move it right by increasing `low`.
17. `return 0.0;`:  This line should not be reached if the input arrays are sorted and the logic is correct. It's included as a safety net and might indicate an error in the algorithm's logic.

### 5. Time and Space Complexity

*   **Time Complexity:** O(log(min(n1, n2))), where n1 and n2 are the lengths of the two arrays. This is because we perform binary search on the shorter array.
*   **Space Complexity:** O(1).  The algorithm uses a constant amount of extra space, regardless of the input size.  We only store a few integer variables.
