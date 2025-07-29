## LeetCode: Maximum Erasure Value - Solution Explained

**1. Problem Understanding:**

The problem "Maximum Erasure Value" asks us to find the maximum sum of a contiguous subarray within a given array `nums` such that all elements in the subarray are unique.  In other words, we need to find the longest subarray with unique elements and calculate its sum.

**2. Approach / Intuition:**

The solution employs a sliding window technique with a hashmap to efficiently track the last seen index of each element.  This approach is chosen because it avoids brute-force checking of all possible subarrays, which would have a significantly higher time complexity (O(n^2) or worse).  The sliding window expands to the right as long as it encounters unique elements. When a duplicate is found, the left boundary of the window is moved to the right until the duplicate is removed from the window.  At each step, the maximum sum is updated.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `HashMap<Integer, Integer> map`:  A hashmap is used to store each element's value as the key and its last seen index as the value. This allows for O(1) lookup of whether an element already exists in the current window and its position.
* **Algorithms:**
    * **Sliding Window:** A sliding window technique is used to iterate through the array efficiently.
    * **Hash Table Lookup:** The hashmap facilitates constant-time lookups to check for duplicates.


**4. Code Walkthrough:**

```java
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(); // Hashmap to store element and its last seen index
        int sum = 0; // Current sum of the window
        int ans = 0; // Maximum sum found so far
        int l = 0; // Left pointer of the sliding window

        for(int r = 0; r<nums.length; r++) { // Iterate through the array with right pointer
            sum+=nums[r]; // Add current element to the window sum

            if(map.containsKey(nums[r])) { // Check if element is already in the window
                while(l<=map.get(nums[r])) { // Move left pointer until duplicate is removed
                    sum-=nums[l++]; // Remove elements from the left until the duplicate is out
                }
            }
            ans = Math.max(ans, sum); // Update maximum sum
            map.put(nums[r], r); // Update element's last seen index
        }

        return ans;
    }
}
```

* **Initialization:** A HashMap `map`, `sum`, `ans`, and `l` (left pointer) are initialized.
* **Iteration:** The code iterates through the array using the right pointer `r`.
* **Adding to Sum:**  `sum += nums[r]` adds the current element to the running sum of the window.
* **Duplicate Check:** `map.containsKey(nums[r])` checks if the current element is already present in the window.
* **Sliding Window Adjustment:** If a duplicate is found (`if` condition), the `while` loop moves the left pointer (`l`) until the duplicate is removed from the window.  Elements are subtracted from `sum` during this process.
* **Max Sum Update:** `ans = Math.max(ans, sum)` updates `ans` to the maximum sum encountered so far.
* **Index Update:** `map.put(nums[r], r)` updates the last seen index of the current element in the map.
* **Return Value:** Finally, the function returns the `ans`, which represents the maximum sum of a subarray with unique elements.


**5. Time and Space Complexity:**

* **Time Complexity:** O(n).  The outer loop iterates through the array once. While the inner `while` loop might seem like nested iteration, in the worst-case scenario (all elements are unique), it executes only once per element.  Therefore, the amortized time complexity remains linear.
* **Space Complexity:** O(n). In the worst case, the hashmap `map` could store all the elements of the input array if they are all unique.  This leads to linear space complexity.
