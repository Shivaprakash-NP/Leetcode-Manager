## LeetCode: Binary Subarrays With Sum - Solution Explained

**1. Problem Understanding:**

The problem asks us to count the number of contiguous subarrays within a given integer array `nums` whose elements sum up to a specified target value `goal`.

**2. Approach / Intuition:**

The solution cleverly uses a technique based on prefix sums and a two-pointer sliding window approach.  Instead of directly counting subarrays with sum `goal`, it leverages the principle of inclusion-exclusion.  It first counts the number of subarrays with a sum *greater than or equal to* `goal` and then subtracts the number of subarrays with a sum *greater than or equal to* `goal - 1`.  The difference precisely represents the number of subarrays with a sum exactly equal to `goal`. This approach is efficient because it avoids redundant computations.  The `solve` function efficiently finds the count of subarrays with sum greater than or equal to a given value using a sliding window.


**3. Data Structures and Algorithms:**

* **Data Structures:** The primary data structure used is an integer array (`nums`) to store the input.  No other significant data structures are employed.
* **Algorithms:** The core algorithm is a two-pointer sliding window approach combined with the principle of inclusion-exclusion.


**4. Code Walkthrough:**

* **`solve(int[] nums, int goal)`:** This helper function counts the number of subarrays with a sum greater than or equal to `goal`.
    * `l = 0`:  This initializes the left pointer of the sliding window.
    * `ans = 0`: This variable accumulates the count of subarrays satisfying the condition.
    * `sum = 0`: This variable keeps track of the current sum of the sliding window.
    * `for(int r = 0 ; r < nums.length ; r++)`: This loop iterates through the array using the right pointer `r`.
    * `sum += nums[r]`: The current element is added to the window's sum.
    * `while(l <= r && sum > goal)`: This inner loop shrinks the window from the left if the current sum exceeds `goal`.  It subtracts elements from the left until the sum becomes less than or equal to `goal`.
    * `ans += (r - l + 1)`: This line is crucial.  Once the window's sum is less than or equal to `goal`, it means all subarrays starting from `l` up to `r` have a sum greater than or equal to `goal`.  Therefore, `(r - l + 1)` represents the number of such subarrays, which are added to `ans`.
    * `return ans`: The function returns the total count of subarrays.

* **`numSubarraysWithSum(int[] nums, int goal)`:** This is the main function.
    * `return solve(nums, goal) - solve(nums, goal - 1)`:  This line implements the inclusion-exclusion principle. It calculates the difference between the count of subarrays with sum >= `goal` and the count of subarrays with sum >= `goal - 1`, which gives the exact count of subarrays with sum equal to `goal`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the input array `nums`.  The `solve` function iterates through the array at most twice (the outer loop and the inner loop, which has amortized O(N) time complexity). The `numSubarraysWithSum` function calls `solve` twice, leading to an overall linear time complexity.

* **Space Complexity:** O(1). The solution uses only a constant amount of extra space to store variables like `l`, `r`, `sum`, and `ans`.  It does not use any auxiliary data structures that scale with the input size.
