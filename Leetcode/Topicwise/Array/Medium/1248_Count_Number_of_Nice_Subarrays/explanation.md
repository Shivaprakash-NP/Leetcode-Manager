## LeetCode: Count Number of Nice Subarrays - Detailed Explanation

**1. Problem Understanding:**

The problem asks us to count the number of subarrays within a given array `nums` where the number of odd numbers in the subarray is exactly `k`.  A "nice subarray" is defined as a subarray containing exactly `k` odd numbers.

**2. Approach / Intuition:**

The solution cleverly uses the principle of inclusion-exclusion.  Instead of directly counting subarrays with exactly `k` odd numbers, it counts subarrays with *at least* `k` odd numbers and subtracts the count of subarrays with *at least* `k-1` odd numbers.  This leaves us with the precise count of subarrays having exactly `k` odd numbers.

The `solve` function counts the number of subarrays with at least a given number of odd numbers. It uses a sliding window approach. The window expands to the right (`r`) as long as the count of odd numbers (`c`) within the window is less than or equal to the `goal`. When the count exceeds the `goal`, the left side of the window (`l`) is moved to the right until the count of odd numbers within the window is back to the `goal`. This way we efficiently track all subarrays with at least `goal` odd numbers.

**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is the input array `nums` itself. No additional significant data structures are employed.
* **Algorithms:** The core algorithm is a sliding window technique combined with a prefix counting approach.  The underlying idea is similar to a two-pointer approach.

**4. Code Walkthrough:**

* **`solve(int[] nums, int goal)`:** This function is the core logic.
    * `l = 0`: Initializes the left pointer of the sliding window.
    * `ans = 0`: Initializes the count of subarrays with at least `goal` odd numbers.
    * `c = 0`: Initializes the count of odd numbers in the current window.
    * `for(int r = 0 ; r<nums.length ; r++)`: This loop iterates through the array, expanding the window to the right.
    * `c+=((nums[r]%2==1)?1:0)`: Increments `c` if the current element `nums[r]` is odd.
    * `while(l<=r && c > goal)`: If the number of odd numbers in the window (`c`) exceeds the `goal`, this loop shrinks the window from the left until the count is back to at most `goal`.
    * `ans+=(r-l+1)`:  Adds the length of the valid window (number of subarrays ending at `r` with at least `goal` odd numbers) to the total count.
    * `return ans`: Returns the total count of subarrays with at least `goal` odd numbers.


* **`numberOfSubarrays(int[] nums, int k)`:** This function uses the `solve` function to implement inclusion-exclusion.
    * `return solve(nums,k)-solve(nums,k-1)`: Subtracts the count of subarrays with at least `k-1` odd numbers from the count of subarrays with at least `k` odd numbers, giving the exact count of subarrays with exactly `k` odd numbers.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the input array `nums`.  Both calls to `solve` iterate through the array at most once. The while loop inside `solve` only reduces the window and does not increase the overall time complexity beyond linear.

* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space regardless of the input size.  It only uses a few integer variables.


This solution is efficient and elegant because it avoids nested loops, achieving a linear time complexity. The inclusion-exclusion principle is cleverly applied to solve the problem without complex combinatorial calculations.
