## LeetCode: Find All K-Distant Indices in an Array - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find all indices in a given integer array `nums` that are within a distance `k` from any index containing the value `key`.  In simpler terms, we need to identify all indices that are at most `k` positions away from an index holding the target value `key`.

**2. Approach / Intuition:**

The solution employs a straightforward linear scan approach. It iterates through the input array `nums`.  Whenever it encounters an element equal to `key`, it identifies the range of indices within a distance `k` from the current index. This range is then added to a set (`ans`) to avoid duplicates.  A `TreeSet` is specifically used to ensure the indices are stored in sorted order, making the final result easier to manage and potentially faster to return as a list.

This approach was chosen because it's simple to implement and understand.  While potentially not the most optimized for extremely large arrays, its readability and ease of implementation outweigh the potential performance gains from more complex algorithms for this problem.

**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[] nums`: The input array of integers.
    * `Set<Integer> ans`: A `TreeSet` to store the indices, ensuring uniqueness and sorted order. `TreeSet` provides O(log n) insertion time.
    * `ArrayList<Integer>`: Used to convert the `TreeSet` into a `List<Integer>` for returning the result, as required by LeetCode.
* **Algorithms:**
    * **Linear Scan:** The algorithm iterates through the input array linearly.
    * **Range Calculation:** For each occurrence of `key`, a range of indices is calculated using `Math.max` and `Math.min` to handle boundary conditions.

**4. Code Walkthrough:**

```java
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        Set<Integer> ans = new TreeSet<>(); // Initialize a TreeSet to store indices (sorted and unique)

        for(int i = 0 ; i < nums.length ; i++) { // Iterate through the array
            if(nums[i] == key) { // Check if the current element is equal to the key
                int s = Math.max(0 , i-k); // Calculate the start index of the range, ensuring it's within bounds (>=0)
                int e = Math.min(nums.length-1 , i+k); // Calculate the end index of the range, ensuring it's within bounds (<=nums.length-1)
                for(int j = s ; j<=e ; j++) ans.add(j); // Add all indices within the range to the set
            }           
        }
        
        return new ArrayList<>(ans); // Convert the TreeSet to an ArrayList and return
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(n*k), where n is the length of the input array `nums`.  In the worst-case scenario (many occurrences of `key` spread throughout the array), the inner loop might iterate up to `k` times for each occurrence of `key`. However,  if `k` is relatively small compared to `n`, it approximates to O(n).  The `TreeSet` insertion has O(log n) complexity, which is dominated by the linear scan. The conversion to `ArrayList` takes O(n) in the worst case.

* **Space Complexity:** O(n) in the worst case.  The `TreeSet ans` can potentially store all indices in the array if `k` is large enough to encompass almost the entire array. The `ArrayList` will have a maximum size of `n`.


In summary, this solution provides a clear, concise, and reasonably efficient way to solve the "Find All K-Distant Indices in an Array" problem.  While a more sophisticated algorithm might exist for extreme cases, this approach offers a good balance of readability and performance for most practical scenarios.
