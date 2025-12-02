### Problem Understanding

The problem asks us to find the minimum absolute distance between the indices of two numbers in a given array `nums` that form a "mirror pair". A mirror pair consists of two numbers, `A` and `B`, such that `A` is the reverse of `B`. For example, `123` and `321` form a mirror pair. If no such pair exists in the array, we should return -1. The distance is simply the absolute difference between their indices.

### Approach / Intuition

The core idea is to efficiently find if, for any number `nums[i]`, its reverse has been encountered earlier in the array, or if `nums[i]` itself is the reverse of a number encountered earlier. A hash map is an ideal data structure for this kind of lookup.

Here's the high-level strategy:

1.  **Iterate through the array:** We process each number `nums[i]` from left to right, maintaining a record of previously encountered numbers and their reversed forms.
2.  **Use a HashMap:** The `HashMap` will store `(key, value)` pairs where the `key` is a *reversed* number and the `value` is the *index* of the original number that produced this reverse.
3.  **For each `nums[i]`:**
    *   Calculate its reverse, let's call it `re`.
    *   **Check for a mirror pair:** Before storing `re`, we check if `nums[i]` (the current number) already exists as a `key` in our `map`.
        *   If `map.containsKey(nums[i])` is true, it means we previously encountered a number `nums[j]` at index `j = map.get(nums[i])` such that `reverse(nums[j]) == nums[i]`.
        *   This directly implies that `nums[j]` and `nums[i]` form a mirror pair.
        *   The distance between them is `i - j`. We update our minimum distance found so far.
    *   **Store the reverse:** After checking, we add `(re, i)` to the `map`. This means we are recording that the number `re` was found as the reverse of `nums[i]` at index `i`. This entry will be used if a future number `nums[k]` is equal to `re`.

This approach is chosen because it allows for efficient `O(1)` average-time lookups and insertions, making the overall solution much faster than a brute-force `O(N^2)` comparison of all pairs.

### Data Structures and Algorithms

*   **Data Structure:**
    *   `HashMap<Integer, Integer>`: Used to store `(reversed_number, original_number_index)` pairs. This allows quick lookups to determine if a number's reverse has been seen before.
*   **Algorithms:**
    *   **Linear Scan (Iteration):** The primary algorithm involves iterating through the input array once.
    *   **Integer Reversal:** A helper function `reverse(int n)` is used to reverse an integer by converting it to a string, reversing the string, and converting it back to an integer.
    *   **Hashing:** The underlying mechanism of the `HashMap` for efficient key-value storage and retrieval.

### Code Walkthrough

```java
class Solution {
    // Helper method to reverse an integer
    private int reverse(int n) {
        // 1. Convert the integer to a String
        StringBuilder sb = new StringBuilder(String.valueOf(n));
        // 2. Reverse the StringBuilder
        sb.reverse();
        // 3. Convert the reversed String back to an integer and return
        return Integer.parseInt(sb.toString());
    }
    
    // Main method to find the minimum mirror pair distance
    public int minMirrorPairDistance(int[] nums) {
        // Initialize 'ans' to a very large value. This will store the minimum distance found.
        // If no pair is found, it will remain Integer.MAX_VALUE.
        int ans = Integer.MAX_VALUE;
        // Get the length of the input array for loop bounds
        int n = nums.length;
        
        // Initialize a HashMap to store (reversed_number, index_of_original_number)
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate through the input array 'nums'
        for(int i = 0; i<n; i++) {
            // Calculate the reverse of the current number nums[i]
            int re = reverse(nums[i]);
            
            // Check if the current number nums[i] exists as a key in the map.
            // If it does, it means we previously encountered a number nums[j] (at index j = map.get(nums[i]))
            // such that reverse(nums[j]) == nums[i]. This forms a mirror pair.
            if(map.containsKey(nums[i])) {
                // Calculate the distance between the current index 'i' and the previously stored index 'j'.
                // Update 'ans' with the minimum of current 'ans' and this new distance.
                ans = Math.min(ans, i - map.get(nums[i]));
            }
            
            // Store the reverse of the current number 're' as a key,
            // and its current index 'i' as the value.
            // This prepares for future numbers that might be equal to 're'.
            // If 're' was already in the map, this updates its index to the latest occurrence.
            map.put(re, i);
        }

        // After iterating through all numbers, check if a mirror pair was found.
        // If 'ans' is still Integer.MAX_VALUE, no pair was found, so return -1.
        // Otherwise, return the minimum distance found.
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   The main loop iterates `n` times, where `n` is the length of the `nums` array.
    *   Inside the loop:
        *   The `reverse(int n)` method involves converting an integer to a string, reversing it, and converting it back. The number of digits `d` in an integer is proportional to `log10(n_val)` (where `n_val` is the value of the integer). For standard integer types (like `int` in Java, up to 2 * 10^9), `d` is at most 10. Thus, these string operations are effectively constant time `O(1)` for practical purposes, or `O(d)` / `O(log N_max)` if we consider the number of digits.
        *   `map.containsKey()`, `map.get()`, and `map.put()` operations on a `HashMap` have an average time complexity of `O(1)`. In the worst case (due to hash collisions), they can degrade to `O(N)`, but this is rare with good hash functions.
    *   Combining these, the overall time complexity is **O(N * d)**, where `d` is the average number of digits in the integers. For typical LeetCode constraints where integers fit in `int`, this is practically **O(N)**.

*   **Space Complexity:**
    *   The `HashMap` `map` stores up to `N` entries in the worst case (if all reversed numbers are unique). Each entry stores two integers (key and value).
    *   Therefore, the space complexity is **O(N)**.