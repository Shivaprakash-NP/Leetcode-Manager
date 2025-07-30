## Subarray Sum Equals K - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to find the number of contiguous subarrays within a given array `nums` whose elements sum up to a specific target value `k`.

### 2. Approach / Intuition:

The core idea behind this solution is to leverage the concept of prefix sums and a hash map to efficiently count the number of subarrays that sum to `k`.

Here's the intuition:

*   **Prefix Sum:** The sum of a subarray `nums[i...j]` can be calculated as `prefixSum[j] - prefixSum[i-1]`, where `prefixSum[i]` is the sum of elements from `nums[0]` to `nums[i]`.

*   **Finding Subarrays with Sum K:** We want to find subarrays where `prefixSum[j] - prefixSum[i-1] == k`.  This can be rearranged to `prefixSum[i-1] == prefixSum[j] - k`.

*   **Hash Map for Efficiency:**  We iterate through the array, maintaining a running sum (`sum`). For each element, we check if there's a previous prefix sum (`sum - k`) already stored in the hash map. If it exists, it means we've found a subarray ending at the current index with a sum equal to `k`. We increment the count by the number of times `sum - k` has appeared previously (because there might be multiple such subarrays). We then update the hash map to store the current running sum.

The hash map acts as a counter for the number of times we've encountered a particular prefix sum.  It allows us to quickly determine how many subarrays ending at a given point satisfy the condition without iterating through all previous subarrays.

The approach is chosen for its optimal time complexity.  Brute-force approaches of iterating through all possible subarrays would result in O(n^2) or even O(n^3) time complexity. The hash map allows us to achieve O(n) time complexity.

### 3. Data Structures and Algorithms:

*   **Data Structure:**
    *   `HashMap<Integer, Integer>`: Stores the prefix sums as keys and their frequencies (number of occurrences) as values.

*   **Algorithm:**
    *   Prefix Sum Calculation (implicitly done while iterating).
    *   Hashing for efficient lookup and counting.

### 4. Code Walkthrough:

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int c = 0, sum = 0; // c: count of subarrays with sum k, sum: running prefix sum
        Map<Integer, Integer> val = new HashMap<>(); // val: HashMap to store prefix sums and their counts

        val.put(0, 1); // Initialize: A prefix sum of 0 exists before the array (empty subarray)

        for (int num : nums) // Iterate through each element in the input array
        {
            sum += num; // Update the running prefix sum
            c += val.containsKey(sum-k) ? val.get(sum-k) : 0; // Check if sum-k exists in the HashMap
            // If sum-k exists, it means there's a previous subarray ending before the current element whose sum is sum-k.
            // Therefore, the subarray from that point to the current element sums to k.
            // Add the count of sum-k to the total count c.
            // If sum-k doesn't exist, add 0 to the count.

            val.put(sum, val.containsKey(sum) ? val.get(sum) + 1 : 1); // Update the HashMap with the current prefix sum
            // If the sum already exists in the HashMap, increment its count.
            // Otherwise, add the sum to the HashMap with a count of 1.
        }
        return c; // Return the total count of subarrays with sum k
    }
}
```

**Explanation of each line:**

1.  `int c = 0, sum = 0;`: Initializes `c` (count of subarrays with sum `k`) and `sum` (running prefix sum) to 0.
2.  `Map<Integer, Integer> val = new HashMap<>();`: Creates a HashMap called `val` to store prefix sums (as keys) and their frequencies (as values).
3.  `val.put(0, 1);`:  This is crucial.  It initializes the HashMap with a prefix sum of 0 occurring once. This handles the case where a subarray starting from the beginning of the array has a sum equal to `k`.  Imagine `k` itself is one of the initial numbers in the `nums` array.  Without this initialization, we would miss that single-element subarray.
4.  `for (int num : nums)`: Iterates through each number `num` in the input array `nums`.
5.  `sum += num;`: Updates the running prefix sum `sum` by adding the current number.
6.  `c += val.containsKey(sum-k) ? val.get(sum-k) : 0;`: This is the core logic.  It checks if the HashMap contains the key `sum - k`.
    *   If `val.containsKey(sum-k)` is true (i.e., `sum - k` exists as a prefix sum encountered earlier), it means there's a subarray ending at the current index whose sum is equal to `k`.  We retrieve the frequency (count) of `sum - k` from the HashMap using `val.get(sum-k)` and add it to the `c` (count).  This is because if `sum - k` occurred `n` times, there are `n` subarrays ending at the current index with sum `k`.
    *   If `val.containsKey(sum-k)` is false (i.e., `sum - k` doesn't exist), it means we haven't encountered a prefix sum that, when subtracted from the current prefix sum, results in `k`.  So, we add 0 to the count `c`.
7.  `val.put(sum, val.containsKey(sum) ? val.get(sum) + 1 : 1);`:  This line updates the HashMap with the current prefix sum `sum`.
    *   If `sum` already exists as a key in the HashMap (`val.containsKey(sum)` is true), we increment its frequency by 1 using `val.get(sum) + 1`.
    *   If `sum` doesn't exist in the HashMap (`val.containsKey(sum)` is false), we add it to the HashMap with a frequency of 1.
8.  `return c;`: Finally, the function returns the total count `c` of subarrays that sum up to `k`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array `nums`. This is because we iterate through the array once.  HashMap operations (containsKey, get, put) have an average time complexity of O(1).
*   **Space Complexity:** O(n) in the worst case, where n is the length of the input array `nums`. This is because the HashMap `val` might store up to n distinct prefix sums. For example, if the array contains distinct numbers, the HashMap will store each of the prefix sums from index 0 to n-1.
