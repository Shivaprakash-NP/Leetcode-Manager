```markdown
## Single Element in a Sorted Array - Detailed Explanation

### 1. Problem Understanding:

The problem states that we are given a sorted array of integers where every element appears exactly twice, except for one element which appears only once.  Our task is to find this single element that appears only once.

### 2. Approach / Intuition:

The core idea is to use **binary search**. Since the array is sorted, we can leverage this property to efficiently locate the single element. The crux of the binary search is to observe the patterns around the middle element `nums[m]`.

Here's the intuition:

*   If `nums[m]` is different from both its neighbors (`nums[m-1]` and `nums[m+1]`), then `nums[m]` is the single element.
*   If `nums[m]` is equal to one of its neighbors, the side that doesn't have the single element will have an **even** number of elements. The side with the single element will have an **odd** number of elements. By identifying which half has an odd number of elements, we can narrow down our search. Specifically, we need to check the indices:

    *   If `m` is even: If `nums[m] == nums[m+1]`, the single element is on the right (move `l`). Else, the single element is on the left (move `r`).
    *   If `m` is odd: If `nums[m] == nums[m-1]`, the single element is on the right (move `l`). Else, the single element is on the left (move `r`).  This is due to the paired nature of elements - if an odd index element is equal to the previous element it's part of a pair and the singular element will be to the right.

Choosing Binary Search: Binary search is appropriate because it allows us to reduce the search space by half in each iteration, resulting in logarithmic time complexity, which is far more efficient than a linear search.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Array (the input array `nums`)
*   **Algorithm:** Binary Search

### 4. Code Walkthrough:

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        int ans = 0;
        if(r==0) return nums[0]; // Handle the case of a single element array
        if(nums[0]!=nums[1]) return nums[0]; // Handle the case where the single element is the first element
        if(nums[r]!=nums[r-1]) return nums[r]; // Handle the case where the single element is the last element

        while(l<=r)
        {
            int m = (l+r)/2; // Calculate the middle index

            if(nums[m]!=nums[m-1] && nums[m]!=nums[m+1]) return nums[m]; // Check if nums[m] is the single element

            if((m+1)%2==0) // Case 1: m is even
            {
                if(nums[m-1]==nums[m]) l = m+1; // If nums[m-1] == nums[m], the single element is on the right side
                else r = m-1; // Else, the single element is on the left side
            }
            else // Case 2: m is odd
            {
                if(nums[m+1]==nums[m]) l = m+1; // If nums[m+1] == nums[m], the single element is on the right side
                else r = m-1; // Else, the single element is on the left side
            }
        }
        return nums[l]; // This line is unreachable in normal cases, but required by Java's syntax.
    }
}
```

*   **`int l = 0; int r = nums.length-1;`:** Initializes the left (`l`) and right (`r`) pointers for the binary search.
*   **`int ans = 0;`:** Initializes a variable `ans`. This isn't actually used in the final version of the loop or returned. It's somewhat vestigial.
*   **`if(r==0) return nums[0];`:** Handles the edge case when the array has only one element. In that case, that element is the single non-duplicate.
*   **`if(nums[0]!=nums[1]) return nums[0];`:** Handles the edge case where the single element is the first element of the array.
*   **`if(nums[r]!=nums[r-1]) return nums[r];`:** Handles the edge case where the single element is the last element of the array.
*   **`while(l<=r)`:** The main loop for the binary search continues as long as the left pointer is less than or equal to the right pointer.
*   **`int m = (l+r)/2;`:** Calculates the middle index `m`.
*   **`if(nums[m]!=nums[m-1] && nums[m]!=nums[m+1]) return nums[m];`:**  The crucial check! If `nums[m]` is different from both its neighbors, it's the single non-duplicate, so we return it.
*   **`if((m+1)%2==0)`:** Checks if `m` is even. Note: We add 1 before taking the modulo to account for array starting from index 0.
    *   **`if(nums[m-1]==nums[m]) l = m+1;`:** If `nums[m]` is equal to its left neighbor, it means the single element is on the right side of `m`, so we update `l = m + 1;`.
    *   **`else r = m-1;`:** Otherwise, the single element is on the left side of `m`, so we update `r = m - 1;`.
*   **`else`:**  This handles the case when `m` is odd.
    *   **`if(nums[m+1]==nums[m]) l = m+1;`:** If `nums[m]` is equal to its right neighbor, it means the single element is on the right side of `m`, so we update `l = m + 1;`.
    *   **`else r = m-1;`:** Otherwise, the single element is on the left side of `m`, so we update `r = m - 1;`.
*   **`return nums[l];`:** After the loop finishes (which should not happen under normal input as one of the returns inside should execute), return `nums[l]`. Required by Java to guarantee a return.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(log n), where n is the length of the array. This is because we are using binary search, which halves the search space in each iteration.
*   **Space Complexity:** O(1). We are only using a constant amount of extra space for variables like `l`, `r`, and `m`, regardless of the size of the input array. Therefore, the space complexity is constant.
