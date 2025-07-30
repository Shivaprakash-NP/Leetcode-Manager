```markdown
## Search in Rotated Sorted Array - Detailed Explanation

### 1. Problem Understanding:

The problem "Search in Rotated Sorted Array" asks us to find the index of a target value in a sorted array that has been rotated an unknown number of times.  If the target is not present, we should return -1.  The crucial constraint is that we must achieve a runtime complexity of O(log n).

For example, the array `[4, 5, 6, 7, 0, 1, 2]` is a rotated sorted array. It was originally `[0, 1, 2, 4, 5, 6, 7]` and has been rotated 4 times to the right. We need to efficiently search for a `target` in such an array.

### 2. Approach / Intuition:

The core idea is to adapt binary search to work on the rotated sorted array.  The standard binary search relies on the array being completely sorted.  However, even though the array is rotated, at least *one* half of the array (from `l` to `m` or from `m` to `r`) *must* be sorted.  We can leverage this property.

The approach is as follows:

1.  Find the middle element `nums[m]`.
2.  If `nums[m]` is equal to the `target`, we've found it, so return its index.
3.  If `nums[l] <= nums[m]`, it means the left half of the array (`nums[l]` to `nums[m]`) is sorted.
    *   If the `target` falls within the range of the sorted left half (i.e., `nums[l] <= target <= nums[m]`), we search the left half by setting `r = m - 1`.
    *   Otherwise, the `target` must be in the unsorted right half (if it exists), so we search the right half by setting `l = m + 1`.
4.  If `nums[l] > nums[m]`, it means the right half of the array (`nums[m]` to `nums[r]`) is sorted.
    *   If the `target` falls within the range of the sorted right half (i.e., `nums[m] <= target <= nums[r]`), we search the right half by setting `l = m + 1`.
    *   Otherwise, the `target` must be in the unsorted left half (if it exists), so we search the left half by setting `r = m - 1`.

This approach allows us to continuously narrow down the search space by half in each iteration, achieving O(log n) time complexity.  Standard linear search would be O(n), which wouldn't meet the problem's requirement.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The code uses a simple integer array (`int[] nums`).
*   **Algorithms:** The core algorithm is a modified **Binary Search**.

### 4. Code Walkthrough:

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]==target) return m;
            if(nums[l]<=nums[m])
            {
                if(target<=nums[m] && target>=nums[l]) r = m-1;
                else l = m+1;
            }
            else
            {
                if(nums[m]<=target && target<=nums[r]) l = m+1;
                else r = m-1;
            }
        }
        return -1;
    }
}
```

*   **`class Solution { ... }`**:  Defines the class containing the `search` method.
*   **`public int search(int[] nums, int target)`**:  This is the main function that takes the rotated sorted array `nums` and the `target` value as input, and returns the index of the target if found, otherwise -1.
*   **`int l = 0;`**: Initializes the left pointer `l` to the start of the array (index 0).
*   **`int r = nums.length-1;`**: Initializes the right pointer `r` to the end of the array (index `nums.length - 1`).
*   **`while(l<=r)`**: This loop continues as long as the left pointer is less than or equal to the right pointer, meaning there's still a search space to consider.
*   **`int m = (l+r)/2;`**: Calculates the middle index `m`.  Note: In Java, this performs integer division, which is what we want. For very large `l` and `r`, `(l+r)` could potentially overflow.  In other languages or for production code, consider using `m = l + (r - l) / 2;` to avoid potential integer overflow.
*   **`if(nums[m]==target) return m;`**:  If the middle element is equal to the `target`, we've found it, so we return the middle index `m`.
*   **`if(nums[l]<=nums[m])`**:  This checks if the left half of the array (from `l` to `m`) is sorted in ascending order.
    *   **`if(target<=nums[m] && target>=nums[l]) r = m-1;`**:  If the `target` is within the range of the sorted left half, we search the left half by moving the right pointer `r` to `m - 1`.
    *   **`else l = m+1;`**:  Otherwise, the `target` must be in the right half (if present), so we search the right half by moving the left pointer `l` to `m + 1`.
*   **`else`**: This block executes if the left half is not sorted, meaning the right half is sorted.
    *   **`if(nums[m]<=target && target<=nums[r]) l = m+1;`**: If the `target` is within the range of the sorted right half, we search the right half by moving the left pointer `l` to `m + 1`.
    *   **`else r = m-1;`**: Otherwise, the `target` must be in the left half (if present), so we search the left half by moving the right pointer `r` to `m - 1`.
*   **`return -1;`**:  If the loop completes without finding the `target`, it means the `target` is not present in the array, so we return -1.

### 5. Time and Space Complexity:

*   **Time Complexity: O(log n)**. Because the solution utilizes binary search, in each iteration, the search space is reduced by half. This logarithmic behavior results in a time complexity of O(log n), where n is the size of the input array.
*   **Space Complexity: O(1)**. The solution uses only a constant amount of extra space for variables like `l`, `r`, and `m`. The space used does not depend on the size of the input array, making it constant space complexity.
