# LeetCode: Max Consecutive Ones III - Solution Explained

## 1. Problem Understanding

The problem "Max Consecutive Ones III" asks us to find the length of the longest subarray containing at most `k` zeros.  In other words, we're allowed to flip at most `k` zeros to ones to maximize the length of a consecutive sequence of ones.

## 2. Approach / Intuition: Sliding Window

The optimal approach for this problem is using a **sliding window** technique.  We maintain a window within the input array `nums`. The window expands to the right (`r`) as long as the number of zeros within the window (`c`) does not exceed `k`.  If the number of zeros exceeds `k`, we shrink the window from the left (`l`) until the condition is met again.  Throughout this process, we track the maximum length (`len`) of the window encountered so far. This approach efficiently explores all possible subarrays without needing to check every single one exhaustively.  It's chosen because it offers a time complexity significantly better than brute-force approaches.


## 3. Data Structures and Algorithms

* **Data Structures:** The primary data structure used is an array (`nums`) to store the input.  We use integer variables to track the window's boundaries (`l`, `r`), the count of zeros (`c`), and the maximum length (`len`).
* **Algorithms:** The core algorithm is a sliding window approach.  It utilizes a two-pointer technique (with `l` and `r`) and a simple counting mechanism to efficiently manage the window.


## 4. Code Walkthrough

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int c = 0; // Count of zeros in the current window
        int l = 0; // Left boundary of the sliding window
        int r = 0; // Right boundary of the sliding window
        int len = 0; // Length of the longest subarray found so far

        while(r<nums.length) { // Iterate through the array using the right pointer
            if(nums[r] == 0) c++; // If we encounter a zero, increment the zero count

            while(c>k) { // If the number of zeros exceeds k
                if(nums[l] == 0) c--; // Shrink the window from the left, decrementing the zero count if necessary
                l++; // Move the left pointer to the right
            }
            len = Math.max(len , r-l+1); // Update the maximum length of the window
            r++; // Expand the window to the right
        }

        return len; // Return the maximum length
    }
}
```


**Detailed Breakdown:**

1. **Initialization:**  `c`, `l`, `r`, and `len` are initialized to 0.
2. **Outer Loop:** The `while(r < nums.length)` loop iterates through the array using the right pointer `r`.
3. **Zero Count Update:**  `if(nums[r] == 0) c++;` increments the zero count if a zero is encountered.
4. **Inner Loop (Window Shrinkage):** The `while(c > k)` loop shrinks the window from the left if the number of zeros exceeds `k`.  It decrements `c` only if a zero is removed from the left side.
5. **Maximum Length Update:** `len = Math.max(len, r - l + 1);` updates `len` with the maximum length seen so far.
6. **Window Expansion:** `r++;` expands the window to the right.
7. **Return Value:**  The function returns `len`, which represents the length of the longest subarray with at most `k` zeros.


## 5. Time and Space Complexity

* **Time Complexity:** O(N), where N is the length of the input array.  The outer loop iterates through the array at most once. The inner loop also iterates a limited number of times as it only shrinks the window, and the total number of shrink operations is bounded by the size of the array.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space to store variables like `c`, `l`, `r`, and `len`, regardless of the input size.  Therefore, the space complexity is constant.
