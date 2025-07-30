```markdown
## Count Complete Subarrays in an Array

### 1. Problem Understanding:

The problem asks us to find the number of subarrays within a given array `nums` that are "complete". A subarray is considered "complete" if it contains all the distinct values present in the original array `nums`.  In essence, we need to count how many subarrays have all the unique numbers from the original array represented within them.

### 2. Approach / Intuition:

The core idea is to use a sliding window technique. We expand the window (increase `j`) until the subarray within the window contains all the distinct values from the original array. Once we find such a complete subarray, we start shrinking the window from the left (increase `i`) while maintaining the completeness condition. For each complete subarray ending at index `j`, the number of subarrays ending at `j` that are complete is simply `nums.length - j`.

Why this approach? The sliding window provides an efficient way to explore all possible subarrays. Using a hash map to track the frequency of elements within the current window helps to quickly determine if the window contains all distinct values.

### 3. Data Structures and Algorithms:

*   **HashSet:** Used to store the distinct values present in the input array `nums`. This is used to find the total number of distinct elements, which is a crucial criteria for complete subarrays.
*   **HashMap:** Used to store the frequency of each element within the current sliding window.  This allows for constant-time lookups and updates to determine if all distinct elements are present in the window.
*   **Sliding Window:** The main algorithmic technique used to explore all possible subarrays of the given array.  Two pointers, `i` (start) and `j` (end), define the window's boundaries.
*   **Frequency Counting:** Implicitly used through the HashMap.

### 4. Code Walkthrough:

```java
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> val = new HashSet<>();
        for(int v : nums) val.add(v);
        int n = val.size();
        int c = 0;
        int i = 0;
        int j = 0;
        HashMap<Integer , Integer> v = new HashMap<>();
        while(j < nums.length)
        {
            v.put(nums[j] , v.getOrDefault(nums[j] , 0)+1);
            while(v.size() == n)
            {
                c += nums.length-j;
                v.put(nums[i] , v.get(nums[i])-1);
                if(v.get(nums[i]) == 0) v.remove(nums[i]);
                i++;
            }
            j++;
        }
        return c;
    }
}
```

1.  **`HashSet<Integer> val = new HashSet<>();`**:  Creates a `HashSet` called `val` to store the distinct values in the input array `nums`.

2.  **`for(int v : nums) val.add(v);`**: Iterates through the `nums` array and adds each element to the `val` HashSet.  Since sets only store unique values, `val` will contain only the distinct values from `nums`.

3.  **`int n = val.size();`**: Stores the number of distinct elements (the size of the `val` HashSet) in the variable `n`.  This represents the number of unique elements that a "complete" subarray must contain.

4.  **`int c = 0;`**:  Initializes a counter `c` to 0. This counter will store the total number of complete subarrays found.

5.  **`int i = 0;`**: Initializes the left pointer `i` of the sliding window to 0.

6.  **`int j = 0;`**: Initializes the right pointer `j` of the sliding window to 0.

7.  **`HashMap<Integer , Integer> v = new HashMap<>();`**: Creates a `HashMap` called `v` to store the frequency of each element within the current sliding window defined by `i` and `j`.

8.  **`while(j < nums.length)`**: The main loop that iterates through the `nums` array, expanding the sliding window one element at a time.

9.  **`v.put(nums[j] , v.getOrDefault(nums[j] , 0)+1);`**: Adds the element at index `j` to the frequency map `v`.  `v.getOrDefault(nums[j] , 0)` retrieves the current count of `nums[j]` in the map, or 0 if it's not present.  Then, it increments the count by 1 and puts it back into the map.

10. **`while(v.size() == n)`**:  This inner loop checks if the current window contains all the distinct elements from the original array.  `v.size()` gives the number of distinct elements in the current window, and `n` is the total number of distinct elements in the entire array. If `v.size()` equals `n`, it means the window is a complete subarray.

11. **`c += nums.length-j;`**: If the window is complete, increment the counter `c` by `nums.length - j`. This is because any subarray ending at `j` and starting from `i` to `j` will contain all distinct elements. Thus, any subarray ending at `j` and after `i` will also be a complete array. Number of such subarrays is `nums.length - j`.

12. **`v.put(nums[i] , v.get(nums[i])-1);`**: Shrinks the window from the left.  Decrements the frequency count of the element at index `i` in the `v` map.

13. **`if(v.get(nums[i]) == 0) v.remove(nums[i]);`**: If the frequency count of the element at index `i` becomes 0 after decrementing, it means that the element is no longer present in the window, so it's removed from the `v` map.

14. **`i++;`**: Moves the left pointer `i` to the right, effectively shrinking the window.

15. **`j++;`**: Moves the right pointer `j` to the right, expanding the window.

16. **`return c;`**: After iterating through the entire array, returns the final count `c` of complete subarrays.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the length of the input array `nums`. The outer `while` loop iterates through the array once. The inner `while` loop might seem like it could lead to O(N^2) complexity, but the `i` pointer only moves forward, so the amortized complexity of the inner loop is also O(N).  The `HashSet` initialization and HashMap operations (`put`, `getOrDefault`, `remove`) take constant time on average. Therefore, the overall time complexity is O(N).

*   **Space Complexity:** O(M), where M is the number of distinct elements in the input array `nums`.  The `HashSet val` stores distinct elements.  The `HashMap v` in the worst case (when all elements in `nums` are distinct) can also store at most M elements.  Therefore, the space complexity is O(M).  In the worst case where all elements in the original array are distinct, M = N, making the space complexity O(N).
