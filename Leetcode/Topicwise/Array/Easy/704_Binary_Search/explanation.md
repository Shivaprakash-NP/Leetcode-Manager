```markdown
## Binary Search - LeetCode Problem Explanation

### 1. Problem Understanding:

The problem asks us to implement a binary search algorithm to find the index of a given `target` value within a sorted array `nums`. If the `target` exists in the array, we should return its index. If the `target` does not exist, we should return -1.

### 2. Approach / Intuition:

The core idea behind binary search is to efficiently search a *sorted* array. Instead of checking each element one by one (as in a linear search), we repeatedly divide the search interval in half.

Here's the intuition:

1.  **Start with the entire array:** Define a left pointer `l` at the beginning and a right pointer `r` at the end of the array.
2.  **Find the middle element:** Calculate the middle index `m` of the current interval (`l` to `r`).
3.  **Compare:**
    *   If `nums[m]` is equal to the `target`, we've found the target, and we return `m`.
    *   If `target` is less than `nums[m]`, it means the `target` (if it exists) must be in the left half of the array. So, we update the right pointer `r` to `m - 1`.
    *   If `target` is greater than `nums[m]`, it means the `target` (if it exists) must be in the right half of the array. So, we update the left pointer `l` to `m + 1`.
4.  **Repeat:** Continue this process of halving the interval until `l` becomes greater than `r`. If `l > r`, it means the `target` is not in the array, so we return -1.

This approach is chosen because binary search offers a significantly faster search time (logarithmic) compared to linear search (linear) when dealing with sorted arrays.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The problem uses a simple `int[]` array as input.  No additional data structures are used.
*   **Algorithms:** The core algorithm used is **Binary Search**.

### 4. Code Walkthrough:

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0; // Initialize the left pointer to the beginning of the array (index 0)
        int r = nums.length-1; // Initialize the right pointer to the end of the array (index nums.length-1)

        while(l<=r) // Continue the search as long as the left pointer is less than or equal to the right pointer
        {
            int m = (r+l)/2; // Calculate the middle index.  Note: can overflow for huge arrays - see improvement notes below
            if(nums[m]==target) return m; // If the middle element is equal to the target, return the middle index

            if(target<nums[m]) r = m-1; // If the target is less than the middle element, the target must be in the left half.  Update the right pointer to m-1
            else l = m+1; // Otherwise (target is greater than the middle element), the target must be in the right half. Update the left pointer to m+1
        }

        return -1; // If the loop finishes without finding the target, the target is not in the array, return -1
    }
}
```

**Detailed Breakdown:**

*   **`int l = 0;`**:  Initializes the left pointer `l` to the start of the array (index 0).  This marks the beginning of our search range.
*   **`int r = nums.length - 1;`**: Initializes the right pointer `r` to the end of the array.  `nums.length - 1` gives the index of the last element. This marks the end of our search range.
*   **`while (l <= r)`**: This `while` loop is the heart of the binary search. It continues as long as the left pointer is less than or equal to the right pointer. This condition ensures that there is still a valid search range.  When `l > r`, it means the search range is empty, and the target isn't found.
*   **`int m = (r + l) / 2;`**: Calculates the middle index `m` of the current search range.  This index is used to access the middle element of the array.  **Important Note:** For extremely large arrays, `(r + l)` might exceed the maximum value of an `int` and cause an integer overflow.  A safer way to calculate the middle index is `m = l + (r - l) / 2;`. This avoids the potential overflow.
*   **`if (nums[m] == target) return m;`**: If the value at the middle index `nums[m]` is equal to the `target`, it means we've found the target, so we return the index `m`.
*   **`if (target < nums[m]) r = m - 1;`**: If the `target` is less than the value at the middle index `nums[m]`, it means the `target` (if it exists) must be in the left half of the current search range. So, we update the right pointer `r` to `m - 1`, effectively reducing the search range to the left half.
*   **`else l = m + 1;`**: If the `target` is greater than or equal to the value at the middle index `nums[m]`, it means the `target` (if it exists) must be in the right half of the current search range. So, we update the left pointer `l` to `m + 1`, effectively reducing the search range to the right half.
*   **`return -1;`**: If the `while` loop finishes without finding the target (i.e., `l > r`), it means the `target` is not present in the array.  Therefore, we return -1.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(log n), where n is the number of elements in the array.  This is because binary search repeatedly halves the search interval.  In each iteration, the size of the search space is reduced by half.  The number of iterations required to find the target (or determine it's not present) is logarithmic with respect to the size of the input array.

*   **Space Complexity:** O(1). Binary search uses a constant amount of extra space, regardless of the size of the input array.  It only uses a few integer variables (`l`, `r`, `m`).  Therefore, the space complexity is constant.
