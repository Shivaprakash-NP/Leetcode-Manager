### Problem Understanding

The problem "Make Sum Divisible by P" asks us to find the length of the *smallest contiguous subarray* that, if removed from the original array `nums`, makes the sum of the *remaining* elements divisible by a given integer `p`. If the total sum of `nums` is already divisible by `p`, we don't need to remove any subarray, so the answer is 0. If it's impossible to achieve the goal by removing a subarray (i.e., the only way is to remove the entire array, or no such subarray exists), we should return -1.

### Approach / Intuition

The core idea revolves around modulo arithmetic and prefix sums.

1.  **Target Remainder:** Let `S` be the total sum of all elements in `nums`. We want to remove a subarray with sum `S_sub` such that `(S - S_sub)` is divisible by `p`. This can be expressed using modulo: `(S - S_sub) % p == 0`.
    This condition is equivalent to `S % p == S_sub % p`.
    Let `target_remainder = S % p`.
    If `target_remainder` is 0, it means the original sum `S` is already divisible by `p`, so we don't need to remove anything. The answer is 0.

2.  **Finding the Subarray:** If `target_remainder` is not 0, we need to find the shortest subarray `nums[j...i]` whose sum `S_sub` satisfies `S_sub % p == target_remainder`.

3.  **Prefix Sums Modulo p:** To efficiently find subarray sums, we can use prefix sums. Let `prefix_sum[k]` be the sum of `nums[0...k-1]`. The sum of a subarray `nums[j...i]` is `prefix_sum[i+1] - prefix_sum[j]`. We are interested in these sums modulo `p`.
    Let `cur_prefix_sum_mod_p[k]` be `(nums[0] + ... + nums[k-1]) % p`.
    The sum of `nums[j...i]` modulo `p` is `(cur_prefix_sum_mod_p[i+1] - cur_prefix_sum_mod_p[j] + p) % p`. (Adding `p` before the final modulo ensures a non-negative result).

4.  **Hash Map for Efficient Lookup:**
    We iterate through the array, calculating the `current_prefix_sum_mod_p` at each index `i`. Let this be `cur_rem`.
    We are looking for a *previous* `prefix_sum_mod_p` (let's call it `prev_rem` at index `j-1`) such that the subarray sum `nums[j...i]` modulo `p` equals `target_remainder`.
    So, `(cur_rem - prev_rem + p) % p == target_remainder`.
    Rearranging this equation to find `prev_rem`:
    `prev_rem == (cur_rem - target_remainder + p) % p`.
    We can use a hash map to store `(remainder, index)` pairs. For each `cur_rem` at index `i`, we calculate the `need`ed `prev_rem`. If `need` is present in our hash map, it means we've found a subarray `nums[map.get(need)+1 ... i]` whose sum modulo `p` is `target_remainder`. The length of this subarray is `i - map.get(need)`. We keep track of the minimum such length found.

5.  **Handling Edge Cases:**
    *   The map is initialized with `map.put(0L, -1)`. This is crucial. If a subarray starting from index 0 (`nums[0...i]`) has the required remainder, its sum is `cur_prefix_sum_mod_p[i+1]`. If `cur_prefix_sum_mod_p[i+1] % p == target_remainder`, it means `prev_rem` must be 0 (corresponding to an empty prefix before index 0). By putting `0L` at index `-1`, the length calculation `i - map.get(0L)` correctly gives `i - (-1) = i + 1`.
    *   If after checking all subarrays, the `min` length is still `n` (the length of the entire array), it means the only way to achieve the target remainder is by removing the entire array. In this specific problem context, removing the entire array is typically not considered a valid "removal" unless a shorter subarray doesn't exist. Thus, if `min` remains `n`, we return -1.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `HashMap<Long, Integer>`: Used to store `(prefix_sum_modulo_p, index)` pairs. This allows for O(1) average-time lookups and insertions.
*   **Algorithms:**
    *   **Prefix Sums:** Used to efficiently calculate the sum of any contiguous subarray.
    *   **Modulo Arithmetic:** Crucial for handling large sums and focusing on remainders with respect to `p`.
    *   **Hash Table Lookup:** Enables quick checks for required previous prefix sums.

### Code Walkthrough

```java
class Solution {
    public int minSubarray(int[] nums, int p) {
        // 1. Calculate the total sum of all elements in nums.
        long sum = 0;
        for(int v : nums) sum += v;

        // 2. Determine the target remainder. This is the remainder the removed subarray's sum
        //    must have for the remaining sum to be divisible by p.
        long target = sum % p;
        int n = nums.length;

        // 3. If the total sum is already divisible by p (target remainder is 0),
        //    no subarray needs to be removed. Return 0.
        if(target == 0) return 0;

        // 4. Initialize a HashMap to store (prefix_sum_modulo_p, index).
        //    map.put(0L, -1) is a crucial initialization. It represents an "empty prefix"
        //    before index 0, whose sum is 0. This allows us to correctly find subarrays
        //    that start from index 0. For example, if nums[0...i] is the desired subarray,
        //    its sum modulo p is (current_prefix_sum_mod_p - 0) % p.
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);  

        // 5. 'cur' will store the current prefix sum modulo p as we iterate.
        //    'min' will store the minimum length of a valid subarray found so far.
        //    Initialize 'min' to 'n' (length of the entire array), as this is the maximum possible length.
        long cur = 0;
        int min = n;

        // 6. Iterate through the array to calculate prefix sums modulo p.
        for(int i = 0; i < n; i++) {
            // Update the current prefix sum modulo p.
            // (cur + nums[i]) % p ensures 'cur' always stays within [0, p-1].
            cur = (cur + nums[i]) % p;

            // Calculate the 'needed' previous prefix sum modulo p.
            // We want (cur_prefix_sum_mod_p - prev_prefix_sum_mod_p + p) % p == target.
            // Rearranging for prev_prefix_sum_mod_p:
            // prev_prefix_sum_mod_p = (cur_prefix_sum_mod_p - target + p) % p.
            // Adding 'p' before the modulo ensures the result is always non-negative.
            long need = (cur - target + p) % p;

            // 7. Check if the 'needed' remainder has been encountered before.
            if(map.containsKey(need)) {
                // If yes, we found a subarray whose sum modulo p is 'target'.
                // The subarray starts at map.get(need) + 1 and ends at 'i'.
                // Its length is i - map.get(need).
                // Update 'min' with the smallest length found so far.
                min = Math.min(min, i - map.get(need));
            }

            // 8. Store the current prefix sum modulo p and its corresponding index 'i' in the map.
            //    If '