## LeetCode: Subarrays with K Different Integers - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the total number of subarrays within a given array `nums` that contain exactly `k` different integers.  A subarray is a contiguous portion of the array.

**2. Approach / Intuition:**

The solution cleverly uses the principle of inclusion-exclusion.  It first calculates the number of subarrays with *at most* `k` distinct integers (`solve(nums, k)`). Then, it subtracts the number of subarrays with *at most* `k-1` distinct integers (`solve(nums, k-1)`). The difference gives the exact number of subarrays with precisely `k` distinct integers.

The `solve(nums, k)` function employs a sliding window technique with a hash map to efficiently count subarrays with at most `k` distinct integers.  This approach is chosen because it avoids brute-force enumeration of all possible subarrays, which would be significantly slower for large input arrays.

**3. Data Structures and Algorithms:**

* **Data Structures:** `HashMap` (or `Map` in other languages) is used to store the frequency of each integer within the current sliding window. This allows for efficient tracking of the number of distinct integers.
* **Algorithms:** The core algorithm is a sliding window approach, combined with a frequency count using a hash map.  The overall strategy is an application of the inclusion-exclusion principle.

**4. Code Walkthrough:**

* **`solve(int[] nums, int k)`:** This function counts the number of subarrays with at most `k` distinct integers.
    * `c = 0`: Initializes a counter for the total number of subarrays satisfying the condition.
    * `l = 0`:  `l` is the left pointer of the sliding window.
    * `HashMap<Integer, Integer> set = new HashMap<>()`: A hash map to store the frequency of each integer in the current window.
    * `for(int r = 0; r < nums.length; r++)`: The outer loop iterates through the array using the right pointer `r`.
        * `set.put(nums[r], set.getOrDefault(nums[r], 0) + 1)`: Adds the current element `nums[r]` to the hash map, updating its frequency.
        * `while(set.size() > k)`: This is the core of the sliding window. If the number of distinct integers in the window (`set.size()`) exceeds `k`, the left pointer `l` is moved to shrink the window.
            * `set.put(nums[l], set.get(nums[l]) - 1)`: Decrements the frequency of the element at the left end of the window.
            * `if(set.get(nums[l]) == 0) set.remove(nums[l])`: If the frequency becomes 0, the element is removed from the hash map.
            * `l++`: Moves the left pointer one step to the right.
        * `c += (r - l + 1)`: Adds the length of the valid subarray (window size) to the counter `c`.
    * `return c`: Returns the total count of subarrays with at most `k` distinct integers.

* **`subarraysWithKDistinct(int[] nums, int k)`:** This function leverages `solve()` to get the final answer.
    * `return solve(nums, k) - solve(nums, k - 1)`:  Calculates the number of subarrays with exactly `k` distinct integers using the inclusion-exclusion principle.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the length of the input array `nums`.  The `solve()` function iterates through the array at most twice (once for each pointer movement).  The operations within the loop (hash map access, updates) take constant time on average.

* **Space Complexity:** O(N) in the worst case. The hash map `set` can store up to all the unique elements in the input array.  In the best case, if the array has a small number of unique elements, the space complexity would be smaller.

In summary, this solution efficiently solves the "Subarrays with K Different Integers" problem using a sliding window and hash map, leveraging the inclusion-exclusion principle for an optimal time complexity of O(N).
