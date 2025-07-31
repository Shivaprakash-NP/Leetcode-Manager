## LeetCode Problem: Check if Array Is Sorted and Rotated - Explanation

### 1. Problem Understanding:

The problem asks us to determine whether a given array of integers is a sorted array that has been rotated.  A sorted array means the elements are in non-decreasing order. A rotated array means that the original sorted array has been shifted by some number of positions. For example, `[3,4,5,1,2]` is a rotated version of `[1,2,3,4,5]`.  `[1,2,3,4,5]` itself can be considered a rotated version of itself with a rotation of 0. We need to return `true` if the input array `nums` is a sorted and rotated array, and `false` otherwise.

### 2. Approach / Intuition:

The core idea is based on the following observations:

*   **Sorted Array:** A sorted array has no decreasing segments (i.e., where `nums[i] > nums[i+1]`).
*   **Rotated Sorted Array:** A rotated sorted array will have at most one decreasing segment. This is because the rotation splits the sorted array into two sorted parts, and the only place where a decrease happens is at the "break" between these two sorted parts.
*   **Checking the rotation:** After finding the "break", we need to make sure that the first part of the array (from index 0 to `ii`) is actually greater than the last element of the original array `nums[nums.length - 1]`. This confirms that the rotated array follows the sorted property.

**Why this approach?** This approach is chosen for its simplicity and efficiency. It directly leverages the properties of a sorted and rotated array, allowing us to identify the rotation point (if it exists) and verify the sorted order in a single pass through most of the array. It avoids more complex sorting or searching algorithms.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The code directly works with the input `int[] nums` array. No other auxiliary data structures are used.
*   **Algorithms:**
    *   **Iteration:** The code iterates through the array to identify the decreasing segment.
    *   **Comparison:** The core logic relies on comparing adjacent elements (`nums[i] > nums[i+1]`) to find the decreasing segment and then comparing other elements (`nums[i]<nums[nums.length-1]`) to determine if the rotated array is sorted or not.

### 4. Code Walkthrough:

```java
class Solution {
    public boolean check(int[] nums) {
        int demo = 0; // Counts the number of decreasing segments
        int ii = 0;  // Stores the index of the last element of the first sorted portion (before rotation)
        for(int i = 0 ; i<nums.length-1 ; i++)
            if(nums[i]>nums[i+1])
            {
                demo++; // Increment the count if a decreasing segment is found
                ii = i; // Update the index of the "break" point
            } 
        if(demo==0) return true; // If no decreasing segment is found, the array is already sorted
        else if(demo>=2) return false; // If more than one decreasing segment is found, it cannot be a rotated sorted array
        for(int i = 0 ; i<=ii ; i++)
            if(nums[i]<nums[nums.length-1]) return false; // Check the elements before the break
        return true; // If all checks pass, the array is a rotated sorted array
    }
}
```

1.  **Initialization:**
    *   `demo = 0;`:  A counter to keep track of the number of decreasing segments (where `nums[i] > nums[i+1]`).
    *   `ii = 0;`: Stores the index `i` of the last element *before* the potential "rotation point." If no rotation exists, `ii` will remain 0.

2.  **Finding the Decreasing Segment(s):**
    *   The `for` loop iterates from `i = 0` to `nums.length - 2`.
    *   `if (nums[i] > nums[i+1])`: This checks if a decreasing segment is found.
        *   `demo++;`: If a decreasing segment is found, the `demo` counter is incremented.
        *   `ii = i;`: The index `i` is stored in `ii`. `ii` stores the location of the last element *before* the start of the increasing sequence after the rotation, so to speak.

3.  **Checking the Number of Decreasing Segments:**
    *   `if (demo == 0) return true;`: If `demo` is 0, it means no decreasing segment was found, so the array is already sorted. Return `true`.
    *   `else if (demo >= 2) return false;`: If `demo` is greater than or equal to 2, it means there are more than one decreasing segment, so it cannot be a rotated sorted array. Return `false`.

4.  **Checking the Rotation:**
    *   `for (int i = 0; i <= ii; i++) if (nums[i] < nums[nums.length - 1]) return false;`: The final loop iterates through the elements before the `ii` index (inclusive).
        *   `if (nums[i] < nums[nums.length - 1]) return false;`: Checks if elements before the `ii` index are smaller than the last element in the original array. If any of the elements is smaller than the last element in `nums`, it means that it is impossible that the array is rotated in a sorted fashion, thus we return `false`.
        **Why this check?** Consider the example `[6, 1, 2, 3, 4, 5]`.  Here, `nums.length - 1` is 5, and `nums[5]` is 5. The code ensures all the elements from index `0` to `ii` which is `0` are greater than or equal to `nums[nums.length - 1]`, i.e. `5` in our example. This part of the rotated array should be sorted in non-decreasing order and it should be greater than the last element of the whole array.

5.  **Return True:**
    *   `return true;`: If all the above checks pass, the array is a rotated sorted array.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array `nums`. The code iterates through the array at most twice (once to find the decreasing segment and once to check the order).
*   **Space Complexity:** O(1). The code uses a fixed number of variables ( `demo`, `ii`) regardless of the size of the input array. Therefore, the space complexity is constant.
