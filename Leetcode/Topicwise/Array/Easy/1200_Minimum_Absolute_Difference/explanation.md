### Problem Understanding

The problem "Minimum Absolute Difference" asks us to find all pairs of elements within a given integer array `arr` that have the smallest possible absolute difference between them. The pairs must be returned in ascending order, meaning for each pair `[a, b]`, `a` must be less than `b`. The final output should be a list of these pairs, where each pair itself is a list of two integers, and the overall list of pairs should also be sorted in ascending order based on the first element of each pair.

### Approach / Intuition

The core idea behind solving this problem efficiently relies on a crucial observation: **if an array is sorted, the minimum absolute difference between any two elements will always be found between adjacent elements.**

Let's illustrate why: Consider three numbers `a < b < c` from a sorted array. The possible positive differences are `b - a`, `c - b`, and `c - a`. Notice that `c - a = (c - b) + (b - a)`. Since `c - b > 0` and `b - a > 0`, it's clear that `c - a` will always be greater than both `c - b` and `b - a`. This logic extends to any non-adjacent elements: the difference between them will always be greater than or equal to the sum of the differences between the adjacent elements that lie between them. Therefore, the smallest possible difference must occur between elements that are directly next to each other in a sorted sequence.

Based on this intuition, the approach is as follows:

1.  **Sort the array:** This step is fundamental. Once the array is sorted, we only need to consider adjacent elements to find the minimum absolute difference.
2.  **Find the minimum difference:** Iterate through the sorted array once, comparing each adjacent pair `(arr[i], arr[i+1])`. Keep track of the smallest difference found so far.
3.  **Collect all pairs with the minimum difference:** Iterate through the sorted array a second time. For each adjacent pair `(arr[i], arr[i+1])`, if their difference is equal to the minimum difference found in the previous step, add this pair `[arr[i], arr[i+1]]` to our result list. Since the array is already sorted, the pairs will naturally be added in the required ascending order.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] arr`: The input array of integers.
    *   `List<List<Integer>> ans`: An `ArrayList` to store the final result. Each inner `List<Integer>` will represent a pair of numbers.
    *   `ArrayList<Integer>`: Used temporarily to construct individual pairs `[a, b]` before adding them to `ans`.
*   **Algorithms:**
    *   `Arrays.sort()`: A standard sorting algorithm provided by Java. For primitive arrays, Java's `Arrays.sort()` typically implements a Dual-Pivot Quicksort.
    *   **Linear Scan (Two Passes):** Two separate loops are used to iterate through the array. The first pass determines the global minimum difference, and the second pass collects all pairs that exhibit this minimum difference.

### Code Walkthrough

```java
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length; // Get the total number of elements in the array.

        // Step 1: Sort the input array.
        // This is crucial because the minimum absolute difference will always be between adjacent elements
        // once the array is in sorted order.
        Arrays.sort(arr);

        // Initialize 'min' to the largest possible integer value.
        // This ensures that any valid difference calculated from the array will be smaller than 'min'
        // during the first comparison, correctly setting our initial minimum.
        int min = Integer.MAX_VALUE;

        // Step 2: First pass - Iterate through the sorted array to find the global minimum absolute difference.
        // We iterate from the first element (index 0) up to the second-to-last element (index n-2).
        // For each 'i', we compare arr[i] with its adjacent element arr[i+1].
        for(int i = 0; i < n - 1; i++) {
            // Calculate the difference between the current adjacent pair.
            // Since the array is sorted, arr[i+1] is always greater than or equal to arr[i],
            // so arr[i+1] - arr[i] directly gives the absolute difference.
            // We update 'min' if the current difference is smaller.
            min = Math.min(min, arr[i+1] - arr[i]);
        }

        // Initialize an empty list of lists to store our final answer.
        // This list will hold all pairs that have the 'min' absolute difference.
        List<List<Integer>> ans = new ArrayList<>();

        // Step 3: Second pass - Iterate through the sorted array again to collect all pairs
        // that match the 'min' absolute difference found in the first pass.
        for(int i = 0; i < n - 1; i++) {
            // Check if the difference between the current adjacent pair is equal to our 'min' difference.
            if(arr[i+1] - arr[i] == min) {
                // If it is, create a new list containing these two elements (arr[i], arr[i+1]).
                // Arrays.asList(arr[i], arr[i+1]) creates a fixed-size list.
                // We wrap it in new ArrayList<>() to create a mutable ArrayList that can be added to 'ans'.
                ans.add(new ArrayList<>(Arrays.asList(arr[i], arr[i+1])));
            }
        }

        // Return the list of all pairs that have the minimum absolute difference.
        // Since the input array was sorted, these pairs are naturally ordered as required.
        return ans;
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   `Arrays.sort(arr)`: This is the most significant operation. For an array of `n` elements, Java's `Arrays.sort()` (which uses Dual-Pivot Quicksort for primitive types) has an average and worst-case time complexity of **O(n log n)**.
    *   First loop (`for(int i = 0; i < n - 1; i++) min = Math.min(...)`): This loop iterates `n-1` times. Each iteration performs constant-time operations (subtraction, comparison, assignment). Thus, this loop contributes **O(n)** time.
    *   Second loop (`for(int i = 0; i < n - 1; i++) { if(...) { ans.add(...) } }`): This loop also iterates `n-1` times. Inside the loop, operations include subtraction, comparison, creating a new `ArrayList` (for two elements, this is effectively constant time), and adding to the `ans` list (amortized constant time). Thus, this loop also contributes **O(n)** time.
    *   Combining these, the dominant factor is the sorting step. Therefore, the overall time complexity is **O(n log n)**.

*   **Space Complexity:**
    *   `Arrays.sort(arr)`: Java's `Arrays.sort()` for primitive types performs an in-place sort, requiring **O(log n)** auxiliary space for the recursion stack (in the case of Quicksort).
    *   `min`, `n`: These are a few integer variables, contributing **O(1)** space.
    *   `ans`: This is the list that stores the final output. In the worst-case scenario (e.g., an array like `[1, 2, 3, 4, 5]` where all adjacent pairs have the same minimum difference), `ans` could store up to `n-1` pairs. Each pair consists of two integers. Therefore, the space required for the `ans` list is **O(n)**.
    *   `new ArrayList<>(Arrays.asList(arr[i], arr[i+1]))`: This creates temporary lists for each pair. Since each list only holds two elements, this is effectively constant space per pair, but accumulates to O(n) for the final `ans` list.
    *   Combining these, the space for the output list dominates. Therefore, the overall space complexity is **O(n)**.