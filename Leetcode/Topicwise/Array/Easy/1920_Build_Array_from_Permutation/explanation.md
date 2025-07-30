```markdown
## LeetCode Problem: Build Array from Permutation - Explanation

### 1. Problem Understanding:

The problem asks us to create a new array `ans` from a given array `nums` such that `ans[i] = nums[nums[i]]` for each index `i`. Essentially, we're using the values in `nums` as indices into `nums` itself to determine the values of `ans`.

### 2. Approach / Intuition:

The approach is straightforward and directly implements the problem's definition. The core idea is to iterate through the input array `nums`, and for each element at index `i`, we use `nums[i]` as an index into `nums` again to get the value to place at `ans[i]`.

This approach is chosen because it directly reflects the problem's requirements, resulting in a concise and efficient solution. There's no need for complex algorithms or data structures since the problem's core requirement is a simple array transformation.

### 3. Data Structures and Algorithms:

*   **Data Structures:** The primary data structure used is an array (`int[]`). We use the input array `nums` and create a new array `ans` to store the result.
*   **Algorithms:** The algorithm is simply an iterative traversal of the input array. We use a `for-each` loop for easy access to the elements of `nums`.

### 4. Code Walkthrough:

```java
class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length]; // Create a new array 'ans' of the same size as 'nums'. This will store the result.
        int i = 0; // Initialize an index 'i' to keep track of the current position in 'ans'.
        for(int v : nums) ans[i++] = nums[v]; // Iterate through the input array 'nums' using a for-each loop.
        return ans; // Return the newly constructed array 'ans'.
    }
}
```

*   **`int[] ans = new int[nums.length];`**: This line creates a new integer array named `ans` with the same length as the input array `nums`. This array will store the transformed values.
*   **`int i = 0;`**: This initializes an integer variable `i` to 0.  This variable serves as the index for the `ans` array as we populate it.
*   **`for(int v : nums) ans[i++] = nums[v];`**: This is the core logic. The `for-each` loop iterates through the `nums` array.
    *   In each iteration, `v` represents the *value* of the element at the current index of `nums`.
    *   `nums[v]` retrieves the value at the index `v` within the `nums` array. This is the transformation step.
    *   `ans[i++] = nums[v];` assigns the retrieved value `nums[v]` to the `i`-th position of the `ans` array. The `i++` post-increment operator then increments `i` for the next element.  This concisely writes to each index of the new array.
*   **`return ans;`**: Finally, the function returns the newly constructed `ans` array, which contains the desired transformation of the original `nums` array.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n)**, where n is the length of the input array `nums`. The code iterates through the `nums` array once. Each operation inside the loop (accessing array elements) takes constant time.
*   **Space Complexity: O(n)**, where n is the length of the input array `nums`.  We create a new array `ans` of the same size as `nums` to store the result.  The space used is directly proportional to the size of the input array.
```