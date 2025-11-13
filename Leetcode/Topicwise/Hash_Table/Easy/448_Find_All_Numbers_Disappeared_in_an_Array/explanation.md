### Problem Understanding

The problem asks us to find all numbers that are missing from a given array `nums`. We are told that the input array `nums` contains `n` integers, where each `nums[i]` is between `1` and `n` (inclusive). Our goal is to return a list of all integers from `1` to `n` that do not appear in the input array `nums`.

For example, if `nums = [4,3,2,7,8,2,3,1]`, then `n = 8`. The numbers we expect to see are `1, 2, 3, 4, 5, 6, 7, 8`. The numbers present in `nums` are `1, 2, 3, 4, 7, 8`. Therefore, the disappeared numbers are `5` and `6`.

### Approach / Intuition

The core idea is to efficiently determine which numbers from the expected range `[1, n]` are *not* present in the input array `nums`.

A straightforward way to check for the presence or absence of elements is to use a hash set (like Java's `HashSet`). Hash sets provide average `O(1)` time complexity for adding elements and checking if an element exists.

The strategy involves two main steps:

1.  **Record existing numbers:** Iterate through the input array `nums` and add every number encountered into a `HashSet`. This set will act as a quick lookup table for all numbers that *are* present in `nums`.
2.  **Identify missing numbers:** Iterate through the range of numbers from `1` to `n` (where `n` is `nums.length`). For each number `i` in this range, check if it exists in our `HashSet`. If `i` is *not* found in the set, it means `i` is a disappeared number, and we add it to our result list.

This approach is chosen because it's simple, intuitive, and offers good performance characteristics due to the efficiency of hash set operations.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `List<Integer> ans`: An `ArrayList` is used to store the final list of disappeared numbers. It's dynamic and allows efficient addition of elements.
    *   `Set<Integer> set`: A `HashSet` is used to store all unique numbers present in the input array `nums`. Its primary benefit is `O(1)` average time complexity for `add` and `contains` operations, which is crucial for efficient lookups.

*   **Algorithms:**
    *   **Iteration:** Standard `for-each` loop to populate the `HashSet` and a standard `for` loop to iterate through the expected range `[1, n]`.
    *   **Hashing:** The underlying mechanism of `HashSet` relies on hashing to provide fast average-case performance for its operations.

### Code Walkthrough

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 1. Initialize the result list
        List<Integer> ans = new ArrayList<>();
        
        // 2. Initialize a HashSet to store numbers present in 'nums'
        Set<Integer> set = new HashSet<>();

        // 3. Populate the HashSet with numbers from the input array
        // This loop iterates through each number 'v' in 'nums'.
        // For each 'v', it adds it to the 'set'.
        // After this loop, 'set' will contain all unique numbers from 'nums'.
        for(int v : nums) {
            set.add(v);
        }

        // 4. Iterate through the expected range [1, n] and find missing numbers
        // 'nums.length' represents 'n'.
        // This loop checks every integer from 1 up to 'n'.
        for(int i = 1; i <= nums.length; i++) {
            // Check if the current number 'i' is NOT present in our 'set'.
            // If 'set.contains(i)' returns false, it means 'i' was not in the input array 'nums'.
            if(!set.contains(i)) {
                // If 'i' is not in the set, it's a disappeared number, so add it to our result list.
                ans.add(i);
            }
        }

        // 5. Return the list of disappeared numbers
        return ans;
    }
}
```

### Time and Space Complexity

*   **Time Complexity:** `O(n)`
    *   The first loop `for(int v : nums)` iterates `n` times (where `n` is `nums.length`). Each `set.add(v)` operation takes `O(1)` time on average. So, this part is `O(n)`.
    *   The second loop `for(int i = 1; i <= nums.length; i++)` also iterates `n` times. Inside the loop, `set.contains(i)` takes `O(1)` time on average, and `ans.add(i)` takes `O(1)` amortized time. So, this part is also `O(n)`.
    *   Combining these, the total time complexity is `O(n) + O(n) = O(n)`.

*   **Space Complexity:** `O(n)`
    *   The `HashSet set` stores up to `n` unique elements from the input array `nums` in the worst case (if all numbers in `nums` are unique). This contributes `O(n)` space.
    *   The `ArrayList ans` stores the disappeared numbers. In the worst case (e.g., if `nums` contains only duplicates of a single number, like `[1,1,1,1]` for `n=4`), it could store up to `n-1` numbers (`2,3,4` in this example). This also contributes `O(n)` space.
    *   Combining these, the total space complexity is `O(n) + O(n) = O(n)`.