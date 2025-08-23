## LeetCode: Longest Subarray of 1's After Deleting One Element - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the length of the longest subarray containing only 1s, with the allowance to delete at most one element (a 0) from the array to achieve this.  If the entire array consists of 1s, we must subtract 1 from the length to account for the condition of deleting at most one element.

**2. Approach / Intuition:**

The solution employs a two-pointer sliding window approach enhanced to handle the deletion of a single 0.  Instead of using two explicit pointers, it cleverly uses `cur` to track the current consecutive 1s, and `pre` to store the count of consecutive 1s *before* the last encountered 0.

The algorithm iterates through the array.  If it encounters a 1, it increments `cur`. When it hits a 0, it updates the `ans` (maximum length so far) by considering the current consecutive 1s (`cur`) plus the previous consecutive 1s (`pre`). Then, it moves `pre` to `cur` (preparing for the next possible 0 deletion) and resets `cur` to 0.  Finally, after the loop, it checks one last time to account for the case where the longest subarray ends at the end of the input. The final adjustment subtracts 1 if the entire array is 1s, as required by problem statement.

This approach is chosen because it efficiently handles the single deletion constraint without the overhead of explicitly checking all possible deletion scenarios. It optimizes for finding the maximum length in a single pass.

**3. Data Structures and Algorithms:**

* **Data Structures:** The code primarily uses an array (`nums`) to store the input.  Integer variables (`cur`, `ans`, `pre`) are used for tracking counts.
* **Algorithms:** The core algorithm is a sliding window approach with an optimized handling of a single element deletion. No explicit sorting or complex data structures are needed, making it efficient.

**4. Code Walkthrough:**

* `int cur = 0; int ans = 0; int pre = 0;`:  Initializes variables. `cur` tracks current consecutive 1s, `ans` stores the maximum length found, and `pre` stores the length of consecutive 1s before the last encountered 0.

* `for(int val : nums)`: Iterates through each element in the input array.

* `if(val == 1) cur++;`: If the element is 1, increment `cur`.

* `else { ans = Math.max(ans, cur+pre); pre = cur; cur = 0; }`:  If the element is 0:
    * `ans = Math.max(ans, cur+pre);`: Update `ans` with the maximum between the current `ans` and the sum of `cur` and `pre` (representing the potential longest subarray after deleting this 0).
    * `pre = cur;`:  Move `cur`'s value to `pre`, preparing for the next potential 0 deletion.
    * `cur = 0;`: Reset `cur` as a new sequence of 1s starts after the 0.

* `ans = Math.max(ans, cur+pre);`: After the loop, check if the longest subarray ends at the end of the array.

* `return (ans == nums.length)?ans-1:ans;`:  Returns `ans`, subtracting 1 if `ans` equals the array's length (all 1s case).

**5. Time and Space Complexity:**

* **Time Complexity:** O(n), where n is the length of the input array. The code iterates through the array once.  All operations within the loop are constant time.

* **Space Complexity:** O(1). The code uses only a few integer variables to store intermediate results. The space used is constant regardless of the input array's size.  This makes the solution very memory efficient.
