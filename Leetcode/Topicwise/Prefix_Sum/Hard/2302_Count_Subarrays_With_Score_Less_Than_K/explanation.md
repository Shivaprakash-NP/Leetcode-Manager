## LeetCode Problem: Count Subarrays With Score Less Than K

Here's a detailed explanation of the Java code provided for the "Count Subarrays With Score Less Than K" problem.

### 1. Problem Understanding:

The problem asks us to count the number of subarrays in a given integer array `nums` whose "score" is strictly less than a given value `k`. The "score" of a subarray is defined as the product of the sum of its elements and its length.

For example, given `nums = [2, 1, 4, 3, 5]` and `k = 10`, we need to find all subarrays that satisfy `sum_of_elements * length_of_subarray < 10`.

### 2. Approach / Intuition:

The core idea behind the solution is to use a *sliding window* technique.  Here's the reasoning:

*   **Monotonicity:** The score of a subarray `nums[i:j]` depends on both its length (`j - i + 1`) and the sum of its elements.  As we expand the window (increase `j`), both the sum and the length tend to increase. This allows us to maintain a valid window (a window whose score is less than `k`) and efficiently adjust its left boundary.
*   **Sliding Window:** We maintain a window defined by two pointers, `l` (left) and `r` (right). The window represents a subarray `nums[l:r]`. We iterate through the array using the right pointer `r`.  For each `r`, we expand the window by including `nums[r]` in the sum. Then, we shrink the window from the left (increment `l`) as long as the score of the current window (`sum * (r - l + 1)`) is greater than or equal to `k`.
*   **Counting Subarrays:**  Once we have a valid window (score less than `k`), the number of valid subarrays ending at index `r` is simply the length of the window, which is `r - l + 1`. We accumulate this count into the `ans` variable.

Why is sliding window appropriate?  Brute force (checking every possible subarray) would be O(n^2) or O(n^3). The sliding window approach leverages the monotonicity described above to achieve O(n) time complexity.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `int[] nums`: The input array of integers.
*   **Algorithms:**
    *   **Sliding Window:**  The core algorithm used for efficiently iterating through subarrays.
    *   **Two Pointers:** `l` and `r` are used as pointers to define the sliding window's boundaries.

### 4. Code Walkthrough:

```java
class Solution {
    public long countSubarrays(int[] nums, long k) {
        long ans = 0; // Initialize the count of valid subarrays
        int n = nums.length; // Get the length of the input array
        long sum = 0; // Initialize the sum of elements within the current window
        int l = 0; // Initialize the left pointer of the sliding window

        for (int r = 0; r < n; r++) { // Iterate through the array using the right pointer
            sum += nums[r]; // Expand the window by including the current element nums[r]

            // Shrink the window from the left as long as the score is greater than or equal to k
            while (l <= r && sum * (r - l + 1) >= k) {
                sum -= nums[l]; // Remove the leftmost element from the sum
                l++; // Move the left pointer to the right
            }

            // After the while loop, the window [l, r] is valid (score < k).
            // The number of valid subarrays ending at index r is (r - l + 1).
            ans += (r - l + 1);  
        }
        return ans; // Return the total count of valid subarrays
    }
}
```

*   **Initialization:**
    *   `ans = 0`:  This variable stores the final count of subarrays with a score less than `k`.
    *   `n = nums.length`: Stores the size of the input array for efficient access.
    *   `sum = 0`: This variable tracks the sum of elements within the current sliding window.
    *   `l = 0`:  The left pointer of the sliding window, initially at the beginning of the array.
*   **Outer Loop (`for (int r = 0; r < n; r++)`):**
    *   Iterates through the array using the right pointer `r`. Each iteration considers subarrays ending at index `r`.
    *   `sum += nums[r]`:  The current element `nums[r]` is added to the `sum`, expanding the sliding window.
*   **Inner Loop (`while (l <= r && sum * (r - l + 1) >= k)`):**
    *   This loop shrinks the window from the left if the current window's score is too high (greater than or equal to `k`).
    *   `sum -= nums[l]`: The leftmost element `nums[l]` is removed from the `sum`.
    *   `l++`: The left pointer `l` is moved one position to the right, effectively shrinking the window.
    *   The loop continues until either the left pointer `l` exceeds the right pointer `r` or the window's score becomes less than `k`.
*   **Counting Valid Subarrays (`ans += (r - l + 1)`):**
    *   After the inner loop completes, the current window `[l, r]` represents a valid subarray whose score is less than `k`.
    *   The number of valid subarrays ending at index `r` is `r - l + 1`. We add this to the `ans` variable.
*   **Return Value:**
    *   The function returns `ans`, which is the total count of subarrays in `nums` whose score is less than `k`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n).  Although there is a nested `while` loop, the left pointer `l` can only increment up to `n` times in total.  Therefore, the total number of iterations of the `while` loop across all iterations of the `for` loop is at most `n`. Thus, both the `for` and `while` loops effectively iterate through the array once in the worst case, resulting in a linear time complexity.
*   **Space Complexity:** O(1).  The algorithm uses a fixed number of variables (`ans`, `n`, `sum`, `l`, `r`) regardless of the input size. Thus, the space complexity is constant.
