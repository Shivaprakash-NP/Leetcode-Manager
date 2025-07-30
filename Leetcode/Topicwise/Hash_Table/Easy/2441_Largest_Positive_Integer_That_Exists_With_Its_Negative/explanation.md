## Largest Positive Integer That Exists With Its Negative - Explained

Here's a breakdown of the provided Java code solving the LeetCode problem "Largest Positive Integer That Exists With Its Negative".

### 1. Problem Understanding:

The problem asks us to find the largest positive integer `K` in a given integer array `nums` such that `-K` also exists in the array. If no such `K` exists, we should return -1.

### 2. Approach / Intuition:

The approach leverages the power of sorting to efficiently search for pairs of numbers `K` and `-K`. The core idea is:

1.  **Sort the array:**  Sorting allows us to use a two-pointer approach to find the desired pair quickly.
2.  **Two-Pointer Technique:** Initialize two pointers, `l` at the beginning of the array and `r` at the end.
3.  **Comparison:**
    *   If `nums[r]` is equal to the negative of `nums[l]`, it means we found our `K` and `-K` pair.  Since the array is sorted, `nums[r]` will be the largest positive integer that meets the condition.
    *   If `nums[r]` is smaller than the absolute value of `nums[l]`, it means the potential `-K` is too small. We need to move the left pointer `l` forward to find a potentially larger `-K`.
    *   If `nums[r]` is greater than the absolute value of `nums[l]`, it means the potential `K` is too small. We need to move the right pointer `r` backward to find a potentially larger `K`.

This approach is chosen because sorting and the two-pointer technique enable us to avoid nested loops and achieve a more efficient solution than brute-force methods.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Array (`nums`)
*   **Algorithm:**
    *   Sorting (specifically `Arrays.sort()` in Java, which often uses a variant of quicksort or mergesort)
    *   Two-Pointer technique

### 4. Code Walkthrough:

```java
class Solution {
    public int findMaxK(int[] nums) {
        Arrays.sort(nums); // Sort the input array in ascending order.
        int l = 0; // Initialize the left pointer to the start of the array.
        int r = nums.length-1; // Initialize the right pointer to the end of the array.

        while(l<r) { // Continue as long as the left pointer is to the left of the right pointer.
            if(nums[r] == -1*(nums[l])) return nums[r]; // If nums[r] is the negative of nums[l], we found a match.  Return nums[r] (the positive K).

            if(nums[r] < Math.abs(nums[l])) l++; // If nums[r] is smaller than the absolute value of nums[l], move the left pointer to the right. This is because we need a potentially larger negative number (-K).

            else r--; // If nums[r] is larger than the absolute value of nums[l], move the right pointer to the left. This is because we need a potentially smaller positive number (K).
        }

        return -1; // If no such K is found, return -1.
    }
}
```

*   **`Arrays.sort(nums);`**: This line sorts the input array `nums` in ascending order. This crucial step enables the efficient two-pointer approach.
*   **`int l = 0;`**: Initializes the left pointer `l` to the beginning of the sorted array.
*   **`int r = nums.length - 1;`**: Initializes the right pointer `r` to the end of the sorted array.
*   **`while(l < r)`**: This loop continues as long as the left pointer is less than the right pointer. This ensures that we have a valid range to search within.
*   **`if(nums[r] == -1 * (nums[l])) return nums[r];`**: This condition checks if the element at the right pointer is the negative of the element at the left pointer. If they are, it means we have found our `K` (which is `nums[r]`) and `-K` (which is `nums[l]`).  Since the array is sorted, this is the largest possible `K`, so we return it.
*   **`if(nums[r] < Math.abs(nums[l])) l++;`**: If the value at the right pointer (positive K) is smaller than the absolute value of the element at the left pointer (-K), it indicates that we need a larger negative number to find a match. Therefore, we increment the left pointer (`l++`).
*   **`else r--;`**: If the value at the right pointer is greater than or equal to the absolute value of the element at the left pointer, it indicates that we need a smaller positive number to find a match. Therefore, we decrement the right pointer (`r--`). Note that if `nums[r] == Math.abs(nums[l])` then `r--` also works because it means `nums[l]` is positive. This case is impossible because of the `if(nums[r] == -1 * (nums[l])) return nums[r];` condition earlier.
*   **`return -1;`**: If the `while` loop completes without finding a matching `K` and `-K`, it means no such pair exists in the array. In this case, the function returns `-1`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n log n). The dominant operation is sorting the array using `Arrays.sort()`, which typically has a time complexity of O(n log n) using efficient sorting algorithms like quicksort or mergesort. The two-pointer approach within the `while` loop takes O(n) time in the worst case, but it is dominated by the sorting time complexity.
*   **Space Complexity:** O(1) or O(log n) depending on the sorting algorithm used by `Arrays.sort()`. In-place sorting algorithms like heapsort result in O(1) space complexity, while algorithms like mergesort might use O(log n) auxiliary space. If the implementation of `Arrays.sort()` uses a temporary array of size n, then the space complexity is O(n).  We assume that `Arrays.sort()` uses O(log n) space.
