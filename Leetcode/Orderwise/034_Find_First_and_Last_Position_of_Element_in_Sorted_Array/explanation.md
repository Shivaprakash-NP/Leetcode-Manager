## Find First and Last Position of Element in Sorted Array - Explained

Here's a breakdown of the provided Java code for solving the "Find First and Last Position of Element in Sorted Array" LeetCode problem.

### 1. Problem Understanding

The problem asks us to find the starting and ending positions of a given `target` value within a sorted array `nums`. If the `target` is not found in the array, we should return `[-1, -1]`. The algorithm's efficiency is expected to be O(log n).

### 2. Approach / Intuition

The core idea is to leverage the fact that the array is sorted to apply Binary Search.  Since we need to find *both* the first and last occurrences, we'll perform Binary Search twice:

*   **First Occurrence:** We modify the binary search to find the leftmost occurrence of the `target`. Whenever we find the target, we record the index and then continue searching in the *left* subarray to see if an earlier occurrence exists.
*   **Last Occurrence:** Similarly, we modify binary search to find the rightmost occurrence of the `target`. Whenever we find the target, we record the index and continue searching in the *right* subarray to find later occurrences.

This approach is efficient because binary search halves the search space in each step, leading to logarithmic time complexity. It's also a natural fit for a sorted array where we want to locate a specific value.

### 3. Data Structures and Algorithms

*   **Data Structure:** The primary data structure is the input `int[] nums` (a sorted integer array).
*   **Algorithm:** Binary Search (modified twice - once for first occurrence and once for last occurrence).

### 4. Code Walkthrough

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int fo = -1;

        // Binary Search for First Occurrence
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]==target) fo = m;  // Target found, record index
            if(nums[m]>=target) r = m-1;  // Look for earlier occurrences in the left half
            else l = m+1;                 // Target is greater, search right half
        }

        l = 0;
        r = nums.length-1;
        int lo = -1;

        // Binary Search for Last Occurrence
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]==target) lo = m;  // Target found, record index
            if(nums[m]<=target) l = m+1;  // Look for later occurrences in the right half
            else r = m-1;                 // Target is smaller, search left half
        }

        return new int[]{fo , lo};
    }
}
```

*   **`searchRange(int[] nums, int target)`:** This is the main function that takes the sorted array `nums` and the `target` value as input.  It returns an integer array of size 2 containing the first and last positions of the `target`, or `[-1, -1]` if the target isn't found.
*   **First Binary Search (Finding First Occurrence):**
    *   `l = 0; r = nums.length - 1;`: Initialize `l` (left pointer) to the start and `r` (right pointer) to the end of the array.
    *   `fo = -1;`: Initialize `fo` (first occurrence) to -1.  This will be updated if the target is found.
    *   `while (l <= r)`: Standard binary search loop.  Continue as long as the left pointer is less than or equal to the right pointer.
    *   `int m = (l + r) / 2;`: Calculate the middle index.
    *   `if (nums[m] == target) fo = m;`: If the element at the middle index equals the target, update `fo` with the middle index.
    *   `if (nums[m] >= target) r = m - 1;`: **Crucial Part:** If the element at the middle index is greater than or equal to the target, it means the first occurrence (if it exists) can only be to the *left* of `m`. So, we move the right pointer to `m - 1`.  This forces the search to continue looking in the left half for the first occurence.
    *   `else l = m + 1;`: If the element at the middle index is less than the target, the target (if it exists) must be to the *right* of `m`. Move the left pointer to `m + 1`.
*   **Second Binary Search (Finding Last Occurrence):**
    *   `l = 0; r = nums.length - 1;`: Reset the left and right pointers to the start and end of the array.
    *   `lo = -1;`: Initialize `lo` (last occurrence) to -1.
    *   `while (l <= r)`: Standard binary search loop.
    *   `int m = (l + r) / 2;`: Calculate the middle index.
    *   `if (nums[m] == target) lo = m;`: If the element at the middle index equals the target, update `lo` with the middle index.
    *   `if (nums[m] <= target) l = m + 1;`: **Crucial Part:** If the element at the middle index is less than or equal to the target, it means the last occurrence (if it exists) can only be to the *right* of `m`. So, we move the left pointer to `m + 1`.  This forces the search to continue looking in the right half for the last occurence.
    *   `else r = m - 1;`: If the element at the middle index is greater than the target, the target (if it exists) must be to the *left* of `m`. Move the right pointer to `m - 1`.
*   **`return new int[]{fo, lo};`:**  Finally, return a new integer array containing the first and last occurrences (`fo` and `lo`).

### 5. Time and Space Complexity

*   **Time Complexity:** O(log n).  Each binary search takes O(log n) time, and we perform two binary searches. Therefore, the overall time complexity is O(2 * log n), which simplifies to O(log n).
*   **Space Complexity:** O(1). The algorithm uses only a few extra variables (left, right, middle, first occurrence, last occurrence). The space used doesn't depend on the size of the input array, so the space complexity is constant.
