## Largest Number At Least Twice of Others - Solution Explanation

Here's a detailed explanation of the provided Java code solution for the LeetCode problem "Largest Number At Least Twice of Others":

### 1. Problem Understanding:

The problem asks us to find the index of the *largest* number in an array of integers, given that this largest element is at least twice as large as every other number in the array. If no such element exists, we should return -1.

### 2. Approach / Intuition:

The core idea is to efficiently find the two largest numbers in the array.  Once we have those, we can simply check if the largest number is at least twice the second largest.  If it is, then we've found our solution.  If not, then no element satisfies the problem conditions.

The approach chosen is efficient because it involves only a single pass through the array. It avoids sorting or repeated searches, making it relatively fast.

### 3. Data Structures and Algorithms:

*   **Data Structures:** Primarily, the code uses an integer array `nums` provided as input.  It also uses integer variables `m1`, `m2`, and `ans` to store the largest number, second largest number, and the index of the largest number respectively.
*   **Algorithms:** The algorithm used is a modified linear search. It's designed to identify the largest and second-largest elements concurrently during a single iteration through the input array. This optimization avoids the need for a full sort or separate searches for the largest and second-largest numbers.

### 4. Code Walkthrough:

```java
class Solution {
    public int dominantIndex(int[] nums) {
        int m1 = 0; // Initialize the largest number to 0
        int m2 = 0; // Initialize the second largest number to 0
        int ans = -1; // Initialize the index of the largest number to -1 (default)

        for(int i = 0 ; i<nums.length ; i++) {
            if(nums[i] > m1) { // If the current number is greater than the current largest
                m2 = m1;      // The current largest becomes the second largest
                m1 = nums[i];  // The current number becomes the new largest
                ans = i;      // Store the index of the new largest
            }
            if(nums[i] > m2 && nums[i]!=m1) m2 = nums[i]; // if nums[i] > m2 and nums[i]!= m1, then nums[i] becomes the new m2
        }

        return (m1 >= m2*2)?ans:-1; // Check if the largest is at least twice the second largest.  Return the index if true, otherwise -1.
    }
}
```

*   **Initialization:**
    *   `m1 = 0`: `m1` stores the largest number found so far. Initialized to 0. *Important Note:* This initialization assumes the input array will always contain non-negative numbers. If the array could contain negative numbers, initializing `m1` to `Integer.MIN_VALUE` would be more robust.
    *   `m2 = 0`: `m2` stores the second largest number found so far. Initialized to 0.  Similar to `m1`, initializing to `Integer.MIN_VALUE` for more general inputs would be beneficial.
    *   `ans = -1`: `ans` stores the index of the largest number. Initialized to -1, which is the return value if no dominant element is found.

*   **Looping through the array:**
    *   `for(int i = 0 ; i < nums.length ; i++)`: The loop iterates through each element `nums[i]` of the input array `nums`.

*   **Finding the largest and second largest:**
    *   `if(nums[i] > m1)`: If the current element `nums[i]` is greater than the current largest number `m1`:
        *   `m2 = m1`: The old largest number `m1` becomes the new second-largest number `m2`.
        *   `m1 = nums[i]`: The current element `nums[i]` becomes the new largest number `m1`.
        *   `ans = i`: The index `i` of the current element is stored as the index of the largest number.
    *   `if(nums[i] > m2 && nums[i] != m1)`: If the current element `nums[i]` is greater than the current second-largest number `m2`, but it's not the same as the largest number `m1` (to avoid updating the second largest with the largest itself when the array has duplicate largest numbers), the current element becomes the new second-largest number `m2`.

*   **Returning the result:**
    *   `return (m1 >= m2*2)?ans:-1;`: After the loop, the code checks if the largest number `m1` is at least twice as large as the second largest number `m2`. If it is, it returns the index `ans` of the largest number. Otherwise, it returns -1, indicating that no such dominant element exists.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array `nums`. The code iterates through the array only once. Each comparison and assignment within the loop takes constant time.
*   **Space Complexity:** O(1). The code uses a fixed number of integer variables (`m1`, `m2`, `ans`, `i`) regardless of the size of the input array. Therefore, the space complexity is constant.
