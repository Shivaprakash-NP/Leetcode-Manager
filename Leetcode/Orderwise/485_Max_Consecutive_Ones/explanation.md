## Max Consecutive Ones - Detailed Explanation

### 1. Problem Understanding:

The problem "Max Consecutive Ones" asks us to find the length of the longest contiguous subarray consisting only of 1s within a given binary array (an array containing only 0s and 1s). For example, given the array `[1,1,0,1,1,1]`, the longest contiguous subarray of 1s has length 3 (the last three elements).

### 2. Approach / Intuition:

The core idea is to iterate through the array, keeping track of the current count of consecutive 1s. We maintain two variables: `c` to store the current consecutive 1s count, and `m` to store the maximum consecutive 1s count encountered so far.

As we iterate, if we encounter a '1', we increment `c`. If we encounter a '0', it means the current sequence of 1s has ended.  We then update `m` (the maximum) with the larger value between `m` and `c`, and reset `c` to 0 to start counting a new potential sequence of 1s.

After the loop finishes, it's crucial to update `m` one last time. This is because the last sequence of 1s might extend until the end of the array, and the loop would not have triggered the update within its body.

This approach is straightforward and efficient because it involves a single pass through the array, making it suitable for large input sizes.

### 3. Data Structures and Algorithms:

*   **Data Structures:** We primarily use an integer array as input. The problem inherently deals with sequential data.
*   **Algorithms:** The solution employs a simple iterative approach, essentially a **linear scan** of the array. It doesn't use any advanced algorithms or data structures like dynamic programming or trees.

### 4. Code Walkthrough:

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int c = 0;  // Current consecutive ones count
        int m = 0;  // Maximum consecutive ones count found so far

        for(int v : nums) // Iterate through each value 'v' in the array 'nums'
        {
            if(v==1) c++;  // If the current value is 1, increment the current count
            else // If the current value is 0
            {
                m = Math.max(m,c); // Update the maximum count with the larger value between the current maximum and the current count
                c = 0; // Reset the current count to 0, as the consecutive sequence is broken
            }
        }
        m = Math.max(m , c); // Important: Update the maximum count one last time after the loop finishes.  Handles cases where the array ends with a sequence of 1s.
        return m; // Return the maximum consecutive ones count
    }
}
```

*   **Initialization:**
    *   `int c = 0;`: Initializes `c` to 0. This variable will keep track of the length of the current consecutive sequence of 1s.
    *   `int m = 0;`: Initializes `m` to 0. This variable will store the maximum length of consecutive 1s found so far.

*   **Looping:**
    *   `for(int v : nums)`: This enhanced for loop iterates through each element `v` in the input array `nums`.

*   **Conditional Check:**
    *   `if(v==1) c++;`:  If the current element `v` is equal to 1, it means the current consecutive sequence of 1s is extended. So, we increment `c`.
    *   `else { ... }`: If the current element `v` is not equal to 1 (i.e., it's 0), the current consecutive sequence of 1s is broken.  The code inside the `else` block does the following:
        *   `m = Math.max(m,c);`:  It updates `m` with the maximum value between the current maximum `m` and the length of the just-ended consecutive sequence `c`. This ensures that `m` always holds the largest consecutive sequence encountered so far.
        *   `c = 0;`: It resets `c` to 0 to start counting a new potential consecutive sequence of 1s.

*   **Final Update and Return:**
    *   `m = Math.max(m , c);`: After the loop completes, this line is crucial.  It updates `m` one last time. This is because the loop only updates `m` when it encounters a '0'. If the array ends with a sequence of 1s, the loop wouldn't have updated `m` for that last sequence.
    *   `return m;`:  Finally, the function returns the value of `m`, which represents the maximum length of consecutive 1s found in the array.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n)** - The code iterates through the input array `nums` only once.  The number of operations performed is directly proportional to the size of the input array `n`.
*   **Space Complexity: O(1)** - The code uses only a fixed number of extra variables (`c` and `m`) regardless of the input array size. Therefore, the space complexity is constant.
