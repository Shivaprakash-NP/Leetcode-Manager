## Find Peak Element Explained

### 1. Problem Understanding:

The problem "Find Peak Element" asks us to find a peak element in a given integer array `nums`. A peak element is defined as an element that is strictly greater than its neighbors. The array is guaranteed to have at least one peak. The problem also states that `nums[-1] = nums[n] = -∞`. If multiple peaks exist, we can return the index to any of the peaks.  The key constraint is that the solution must run in O(log n) time.

### 2. Approach / Intuition:

The core idea is to use binary search to efficiently find a peak element. Since the problem requires O(log n) time complexity, a linear search approach is not suitable. The intuition is based on the fact that even if the array is not sorted, the implicit "mountain-like" structure implied by the infinity boundaries allows us to narrow down the search space by half in each iteration.

Here's why binary search works:

*   If `nums[mid]` is greater than both its neighbors, we've found a peak.
*   If `nums[mid]` is less than `nums[mid + 1]`, it means the peak must be on the right side of `mid`. This is because the slope is increasing, implying a peak will eventually be encountered.
*   If `nums[mid]` is less than `nums[mid - 1]`, it means the peak must be on the left side of `mid`. This is because the slope is decreasing, implying a peak was present before.

By repeatedly halving the search space based on these conditions, we can quickly converge to a peak element.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Integer array (`nums`)
*   **Algorithm:** Binary Search

### 4. Code Walkthrough:

```java
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1 || nums[0]>nums[1]) return 0;
        if(nums[nums.length-1]>nums[nums.length-2]) return nums.length-1;
        int l = 1;
        int r = nums.length-2;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]>nums[m+1] && nums[m]>nums[m-1]) return m;
            if(nums[m]>nums[m+1]) r = m-1;
            else l = m+1;
        }
        return -1;
    }
}
```

*   **`class Solution { ... }`**: Defines the class containing the solution method.
*   **`public int findPeakElement(int[] nums) { ... }`**:  This is the main function that takes an integer array `nums` as input and returns the index of a peak element.
*   **`if(nums.length == 1 || nums[0]>nums[1]) return 0;`**: This handles the edge case where the array has only one element or the first element is greater than the second. In either case, the first element is a peak, so we return index 0.
*   **`if(nums[nums.length-1]>nums[nums.length-2]) return nums.length-1;`**: This handles the edge case where the last element is greater than the second to last element.  In this case, the last element is a peak, so we return its index ( `nums.length - 1` ).
*   **`int l = 1;`**: Initializes the left pointer `l` to 1. We start from index 1 because the first element is already handled in the initial `if` condition.
*   **`int r = nums.length-2;`**: Initializes the right pointer `r` to `nums.length - 2`.  We end at index `nums.length - 2` because the last element is already handled in the initial `if` condition.
*   **`while(l<=r)`**:  The standard binary search loop continues as long as the left pointer is less than or equal to the right pointer.
*   **`int m = (l+r)/2;`**: Calculates the middle index `m`.
*   **`if(nums[m]>nums[m+1] && nums[m]>nums[m-1]) return m;`**:  This is the core logic of finding the peak.  It checks if `nums[m]` is greater than both its neighbors `nums[m+1]` and `nums[m-1]`. If it is, then `m` is the index of a peak element, so we return `m`.
*   **`if(nums[m]>nums[m+1]) r = m-1;`**: If `nums[m]` is greater than `nums[m+1]`, it means the peak is on the left side, so we update `r` to `m - 1`.
*   **`else l = m+1;`**: Otherwise (`nums[m]` is less than or equal to `nums[m+1]`), it means the peak is on the right side, so we update `l` to `m + 1`.
*   **`return -1;`**: If the loop finishes without finding a peak (which shouldn't happen given the problem constraints), return -1 to indicate an error.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(log n). The binary search algorithm repeatedly halves the search space in each iteration, leading to logarithmic time complexity.
*   **Space Complexity:** O(1). The algorithm uses only a constant amount of extra space for variables like `l`, `r`, and `m`, regardless of the input size. Therefore, the space complexity is constant.
