```markdown
## Reverse Pairs Problem Explanation

### 1. Problem Understanding:

The "Reverse Pairs" problem asks us to find the number of pairs `(i, j)` in an array `nums` such that `i < j` and `nums[i] > 2 * nums[j]`. In simpler terms, we need to count how many times an element at a lower index is more than twice the value of an element at a higher index within the array.

### 2. Approach / Intuition:

The provided solution uses a modified Merge Sort algorithm to efficiently count the reverse pairs. The core idea is to leverage the fact that Merge Sort naturally divides the array into smaller sorted subarrays.

*   **Divide and Conquer:**  Merge Sort recursively divides the array into two halves until we are left with single-element arrays, which are trivially sorted.
*   **Counting during Merge:**  The crucial part is performed in the `merge` function. After the two halves (left and right) are sorted, we can efficiently count reverse pairs between the left and right halves *while merging*.  Since both halves are sorted, we can iterate through the left half and, for each element `nums[i]` in the left half, find the number of elements `nums[j]` in the right half that satisfy `nums[i] > 2 * nums[j]`. Because the right half is sorted, we can use a `while` loop to advance the index `j` in the right half until the condition is no longer met. The number of advanced elements `j` in the right half represents the number of reverse pairs involving the current element `nums[i]` in the left half.
*   **Maintain Sort Order:** The `merge` function also performs the standard merge operation, combining the two sorted halves into a single sorted array. This is crucial to maintain the sorted property required for the counting in subsequent merge steps in the recursive calls.

This approach is chosen because it allows us to count the reverse pairs in `O(n log n)` time, which is more efficient than a brute-force approach that would take `O(n^2)` time. The sorting process enables the efficient counting using the sliding window (index `j`) in the `merge` function.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure used is an array (`nums`).  A temporary array (`temp`) is used during the merge operation.
*   **Algorithms:**
    *   **Merge Sort:** A divide-and-conquer sorting algorithm.
    *   **Two Pointers:**  The `merge` function uses two pointers (index `i` for left subarray and index `j` for right subarray) and a sliding window technique to efficiently count reverse pairs.

### 4. Code Walkthrough:

```java
class Solution {
    private int ans = 0; // Stores the total count of reverse pairs. Initialized to 0.

    public void merge(int[] nums, int l, int m, int r) {
        // nums: The array to be processed
        // l: Starting index of the left subarray
        // m: Ending index of the left subarray
        // r: Ending index of the right subarray

        int j = m + 1; // Initialize 'j' to the starting index of the right subarray.
        for (int i = l; i <= m; i++) // Iterate through each element in the left subarray.
        {
            while (j <= r && nums[i] > 2L * nums[j]) j++; // Find elements in the right subarray satisfying the reverse pair condition.
            // 'j' keeps moving right as long as nums[i] > 2 * nums[j].

            ans += (j - (m + 1)); // Increment the count of reverse pairs.  'j - (m + 1)' gives the number of elements in the right subarray that satisfy the condition for the current element in the left subarray.
        }

        int[] temp = new int[r - l + 1]; // Create a temporary array to store the merged sorted elements.
        int left = l, right = m + 1, k = 0; // Initialize pointers for left, right, and temporary arrays.

        while (left <= m && right <= r) // Merge the two sorted subarrays into the temporary array.
        {
            if (nums[left] <= nums[right]) temp[k++] = nums[left++];
            else temp[k++] = nums[right++];
        }

        while (left <= m) temp[k++] = nums[left++]; // Copy any remaining elements from the left subarray.
        while (right <= r) temp[k++] = nums[right++]; // Copy any remaining elements from the right subarray.

        System.arraycopy(temp, 0, nums, l, temp.length); // Copy the sorted elements back to the original array.
    }

    public void mergesort(int[] nums, int l, int r) {
        // nums: The array to be sorted
        // l: Starting index of the subarray
        // r: Ending index of the subarray

        if (l >= r) return; // Base case: If the subarray has only one element, it is already sorted.
        int m = (l + r) / 2; // Find the middle index.

        mergesort(nums, l, m); // Recursively sort the left subarray.
        mergesort(nums, m + 1, r); // Recursively sort the right subarray.
        merge(nums, l, m, r); // Merge the two sorted subarrays and count reverse pairs.
    }

    public int reversePairs(int[] nums) {
        // nums: The input array.

        ans = 0; // Reset the reverse pairs count.
        mergesort(nums, 0, nums.length - 1); // Sort the array using merge sort and count reverse pairs.
        return ans; // Return the total count of reverse pairs.
    }
}
```

**Detailed Explanation:**

*   **`reversePairs(int[] nums)`:** This is the main function that takes the input array `nums`. It initializes `ans` to 0, calls `mergesort` to sort the array and count reverse pairs, and finally returns the value of `ans`.
*   **`mergesort(int[] nums, int l, int r)`:** This function implements the recursive Merge Sort algorithm.
    *   It takes the array `nums` and the start and end indices (`l` and `r`) of the portion to be sorted.
    *   The base case is when `l >= r`, indicating that the portion has zero or one element, which is already sorted.
    *   It finds the middle index `m` and recursively calls `mergesort` on the left and right halves.
    *   After the left and right halves are sorted, it calls the `merge` function to merge the two sorted halves and count the reverse pairs between them.
*   **`merge(int[] nums, int l, int m, int r)`:** This function merges two sorted subarrays `nums[l...m]` and `nums[m+1...r]` into a single sorted subarray `nums[l...r]` and, most importantly, counts the reverse pairs during the merge process.
    *   The `j` pointer is initialized to `m + 1`, the start of the right subarray.
    *   The outer loop iterates through each element `nums[i]` in the left subarray (`l` to `m`).
    *   The inner `while` loop increments `j` as long as `j` is within the bounds of the right subarray and `nums[i] > 2L * nums[j]`. Note the use of `2L` to avoid integer overflow when multiplying `nums[j]` by 2. This is a critical detail.
    *   After the `while` loop finishes, `j - (m + 1)` represents the number of elements in the right subarray that satisfy the reverse pair condition with `nums[i]`. This value is added to `ans`.
    *   The rest of the `merge` function performs the standard merge operation to combine the two sorted subarrays into a temporary array `temp` and then copies the sorted elements from `temp` back to the original array `nums`. This ensures that the subarray `nums[l...r]` is sorted after the `merge` function completes. `System.arraycopy` is used for efficient array copying.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n log n)
    *   The `mergesort` function has a time complexity of O(n log n) because it divides the array into halves recursively (log n levels) and performs O(n) work at each level to merge the subarrays.
    *   The `merge` function iterates through the left subarray (O(n/2) in the worst case) and uses a `while` loop to advance the `j` pointer in the right subarray. The `while` loop in `merge` might seem like it would increase the complexity, but it moves `j` from `m+1` to `r` at most once for the entire `merge` call, so the amortized cost of this part of the loop for each i is constant.
    *   Therefore, the overall time complexity is dominated by the Merge Sort algorithm, which is O(n log n).
*   **Space Complexity:** O(n)
    *   The `merge` function uses a temporary array `temp` of size `r - l + 1`, which is proportional to the size of the subarray being merged. In the worst case, this can be equal to n (the size of the input array). Therefore, the space complexity is O(n).  The recursive calls of mergesort also contribute O(log n) to the space complexity on the call stack, but this is dominated by the O(n) of the temp array.
