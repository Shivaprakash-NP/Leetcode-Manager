### Problem Understanding

The problem asks us to find the *minimum length* of a contiguous subarray `nums[l...r]` such that the sum of its *distinct* elements is at least `k`. If no such subarray exists, we should return -1.

For example, if `nums = [1, 2, 1, 3]` and `k = 4`:
*   Subarray `[1, 2]` has distinct elements `{1, 2}`, sum = `1 + 2 = 3`. (Not >= 4)
*   Subarray `[1, 2, 1]` has distinct elements `{1, 2}`, sum = `1 + 2 = 3`. (Not >= 4)
*   Subarray `[1, 2, 1, 3]` has distinct elements `{1, 2, 3}`, sum = `1 + 2 + 3 = 6`. (>= 4). Length = 4.
*   Subarray `[2, 1, 3]` has distinct elements `{2, 1, 3}`, sum = `2 + 1 + 3 = 6`. (>= 4). Length = 3.
The minimum length is 3.

The key challenge is tracking the sum of *distinct* elements efficiently as the subarray (window) changes.

### Approach / Intuition

The problem asks for the "minimum length subarray" satisfying a certain condition. This is a classic indicator for the **Sliding Window** technique.

The core idea is to maintain a window `[l, i]` (from left pointer `l` to right pointer `i`) and adjust its size:
1.  **Expand the window (move `i` to the right):** We add `nums[i]` to our current window. We need to update the sum of distinct elements. If `nums[i]` is a new element (not previously in the window), we add its value to our `distinct_sum`. We also need to keep track of element frequencies to know when an element is truly "distinct" or if it's just another occurrence. A `HashMap` is ideal for this.
2.  **Shrink the window (move `l` to the right):** Once the `distinct_sum` condition (`sum >= k`) is met, we have found a valid subarray. We record its length (`i - l + 1`) as a potential answer. To find the *minimum* length, we try to shrink the window from the left (`l++`) as much as possible *while still maintaining the `sum >= k` condition*. When we remove `nums[l]`:
    *   We decrement its count in our frequency map.
    *   If its count becomes 0 after decrementing (meaning it was the last occurrence of this number in the window), then this number is no longer part of the distinct elements, so we subtract its value from `distinct_sum`.
    *   If its count is still greater than 0, it means other occurrences of `nums[l]` still exist in the window, so it remains a distinct element, and we don't change `distinct_sum`.

This process continues until the right pointer `i` reaches the end of the array.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[] nums`: The input array.
    *   `Map<Integer, Integer> map`: A `HashMap` is used to store the frequency of each number within the current sliding window. The key is the number, and the value is its count. This allows us to efficiently check if an element is present and how many times, which is crucial for managing the distinct sum.
*   **Algorithms:**
    *   **Sliding Window:** The primary algorithm used. It involves two pointers (`l` and `i`) to define a dynamic window over the array.
    *   **Two Pointers:** `l` (left pointer) and `i` (right pointer) are used to manage the window boundaries.

### Code Walkthrough

```java
class Solution {
    public int minLength(int[] nums, int k) {
        int ans = Integer.MAX_VALUE; // Initialize 'ans' to a very large value. We want to find the minimum length, so any valid length will be smaller.
        int n = nums.length;        // Store the length of the input array for convenience.
        
        // HashMap to store the frequency of each number within the current sliding window.
        // Key: number, Value: count of that number in the window.
        Map<Integer, Integer> map = new HashMap<>(); 
        
        int sum = 0; // This variable will store the sum of *distinct* elements in the current window.

        int l = 0; // The left pointer of the sliding window, initialized to the beginning of the array.
        
        // The right pointer 'i' iterates through the array, expanding the window one element at a time.
        for(int i = 0; i<n; i++) {
            // --- Step 1: Expand the window by adding nums[i] ---
            
            // Check if nums[i] is a new distinct element in the current window.
            if(!map.containsKey(nums[i])) { 
                sum += nums[i];             // If it's new, add its value to our distinct sum.
                map.put(nums[i], 1);        // Add it to the map with a count of 1.
            } else {                        // If nums[i] is already in the window (not a new distinct element for sum calculation)
                map.put(nums[i], map.get(nums[i])+1); // Just increment its count in the map.
            }
            
            // --- Step 2: Shrink the window from the left if the distinct sum condition is met ---
            
            // While the sum of distinct elements in the current window is at least 'k',
            // we have a valid subarray. We try to shrink it to find the minimum length.
            while(sum >= k) { 
                ans = Math.min(ans, i-l+1); // Update 'ans' with the minimum of its current value and the current window length (i - l + 1).
                
                int v = nums[l]; // Get the element at the left end of the window, which we are about to remove.
                
                // Check if this element 'v' is the last occurrence of its kind in the window.
                if(map.get(v) == 1) { 
                    sum -= v;         // If it's the last one, it's no longer distinct, so subtract its value from the distinct sum.
                    map.remove(v);    // Remove it from the map.
                } else {              // If there are other occurrences of 'v' in the window (count > 1)
                    map.put(v, map.get(v)-1); // Just decrement its count. It remains a distinct element in the window.
                }
                l++; // Move the left pointer forward, effectively shrinking the window.
            }
        }

        // After the loop finishes, 'ans' will hold the minimum length found.
        // If 'ans' is still Integer.MAX_VALUE, it means no valid subarray was ever found.
        return ans == Integer.MAX_VALUE ? -1 : ans; 
    }
}
```

### Time and Space Complexity

*   **Time Complexity: O(N)**
    *   The right pointer `i` iterates through the array from `0` to `n-1` exactly once. Inside this loop, `HashMap` operations (`containsKey`, `put`, `get`) take amortized O(1) time.
    *   The `while` loop for shrinking the window (moving the left pointer `l`) also processes each element at `nums[l]` at most once across the entire execution (as `l` only moves forward).
    *   Since both pointers traverse the array at most once, the total time complexity is linear with respect to the number of elements in `nums`.

*   **Space Complexity: O(D)**
    *   The `HashMap` `map` stores the frequency of elements within the current sliding window. In the worst case, all elements in the window could be distinct.
    *   The maximum number of entries in the map is bounded by the number of distinct elements present in the entire input array `nums`. Let `D` be the number of distinct elements in `nums`.
    *   Therefore, the space complexity is O(D). In the absolute worst case, if all elements in `nums` are distinct, then `D = N`, making the space complexity O(N).