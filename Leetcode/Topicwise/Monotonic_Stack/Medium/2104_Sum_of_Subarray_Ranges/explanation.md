```markdown
## Sum of Subarray Ranges Problem Explanation

### 1. Problem Understanding:

The problem asks us to calculate the sum of the ranges of all possible subarrays within a given integer array `nums`. The range of a subarray is defined as the difference between its maximum and minimum element. We need to find the sum of these ranges across all possible subarrays.

### 2. Approach / Intuition:

The brute-force approach of iterating through all possible subarrays (O(n^2)) and finding the min/max for each (O(n)) would result in an O(n^3) solution, which is not efficient for larger arrays.

A more efficient approach is to consider each element `nums[i]` individually.  For each `nums[i]`, we want to determine how many subarrays it's the *minimum* element in and how many subarrays it's the *maximum* element in.  Then, we can multiply `nums[i]` by the number of times it's a maximum, and subtract the number of times it's a minimum to get its contribution to the final sum.

The core idea is to precompute four arrays: `pre_small`, `nxt_small`, `pre_gre`, and `nxt_gre`.

*   `pre_small[i]` stores the index of the nearest smaller element to the left of `nums[i]`.  If no such element exists, it stores -1.
*   `nxt_small[i]` stores the index of the nearest smaller or equal element to the right of `nums[i]`. If no such element exists, it stores `n`.
*   `pre_gre[i]` stores the index of the nearest greater element to the left of `nums[i]`. If no such element exists, it stores -1.
*   `nxt_gre[i]` stores the index of the nearest greater or equal element to the right of `nums[i]`. If no such element exists, it stores `n`.

These four arrays help determine the extent of subarrays where `nums[i]` acts as the minimum or maximum. The number of subarrays where `nums[i]` is the minimum can be calculated as `(i - pre_small[i]) * (nxt_small[i] - i)`.  Similarly, the number of subarrays where `nums[i]` is the maximum is `(i - pre_gre[i]) * (nxt_gre[i] - i)`.

By subtracting the sum of the minimum contributions from the sum of the maximum contributions, we efficiently calculate the total sum of subarray ranges.

This approach avoids recomputing min/max for each subarray, achieving a better time complexity.

### 3. Data Structures and Algorithms:

*   **Arrays:** `nums`, `pre_small`, `nxt_small`, `pre_gre`, `nxt_gre`.  Used to store the input array and precomputed information.
*   **Stack:** Used to efficiently find the nearest smaller and greater elements to the left and right. The stack helps to maintain a decreasing or increasing sequence, which allows finding the desired elements in O(n) total time across all elements.
*   **Algorithm:** Stack-based monotonic stack to find nearest smaller and greater elements.  Iteration to calculate the final answer.

### 4. Code Walkthrough:

```java
class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        int[] pre_small = new int[n];
        int[] nxt_small = new int[n];
        int[] pre_gre = new int[n];
        int[] nxt_gre = new int[n];
        Stack<Integer> st = new Stack<>();

        // Find nearest smaller element to the left (pre_small)
        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && nums[st.peek()] > nums[i]) st.pop();
            pre_small[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();

        // Find nearest smaller or equal element to the right (nxt_small)
        for(int i = n-1 ; i >= 0 ; i--) {
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();
            nxt_small[i] = (st.isEmpty())?n:st.peek();
            st.push(i);
        }

        st.clear();

        // Find nearest greater element to the left (pre_gre)
        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            pre_gre[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();

        // Find nearest greater or equal element to the right (nxt_gre)
        for(int i = n-1 ; i >= 0 ; i--) {
            while(!st.isEmpty() && nums[st.peek()] <= nums[i]) st.pop();
            nxt_gre[i] = (st.isEmpty())?n:st.peek();
            st.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long l1 = i - pre_small[i];
            long r1 = nxt_small[i] - i;
            long l2 = i - pre_gre[i];
            long r2 = nxt_gre[i] - i;

            ans += l2 * r2 * nums[i]; //Contribution as maximum
            ans -= l1 * r1 * nums[i]; //Contribution as minimum
        }
        return ans;
    }
}
```

**Explanation:**

1.  **Initialization:**
    *   `n`: Stores the length of the input array `nums`.
    *   `pre_small`, `nxt_small`, `pre_gre`, `nxt_gre`: Arrays to store the indices of the nearest smaller/greater elements.
    *   `st`: A stack to help find the nearest smaller/greater elements efficiently.

2.  **Finding `pre_small`:**
    *   Iterate through the `nums` array from left to right.
    *   For each element `nums[i]`, pop elements from the `st` stack as long as the element at the top of the stack (`nums[st.peek()]`) is greater than `nums[i]`. This ensures that the stack maintains a decreasing sequence from bottom to top.
    *   After popping, if the stack is empty, it means there's no smaller element to the left of `nums[i]`, so `pre_small[i]` is set to -1. Otherwise, the element at the top of the stack is the nearest smaller element to the left, so `pre_small[i]` is set to `st.peek()`.
    *   Push the current index `i` onto the stack.

3.  **Finding `nxt_small`:**
    *   Clear the stack.
    *   Iterate through the `nums` array from right to left.
    *   For each element `nums[i]`, pop elements from the `st` stack as long as the element at the top of the stack (`nums[st.peek()]`) is greater than or equal to `nums[i]`. This ensures that the stack maintains a decreasing or equal sequence from bottom to top.
    *   After popping, if the stack is empty, it means there's no smaller or equal element to the right of `nums[i]`, so `nxt_small[i]` is set to `n`. Otherwise, the element at the top of the stack is the nearest smaller or equal element to the right, so `nxt_small[i]` is set to `st.peek()`.
    *   Push the current index `i` onto the stack.

4.  **Finding `pre_gre`:**
    *   Clear the stack.
    *   Iterate through the `nums` array from left to right.
    *   For each element `nums[i]`, pop elements from the `st` stack as long as the element at the top of the stack (`nums[st.peek()]`) is smaller than `nums[i]`. This ensures that the stack maintains an increasing sequence from bottom to top.
    *   After popping, if the stack is empty, it means there's no greater element to the left of `nums[i]`, so `pre_gre[i]` is set to -1. Otherwise, the element at the top of the stack is the nearest greater element to the left, so `pre_gre[i]` is set to `st.peek()`.
    *   Push the current index `i` onto the stack.

5.  **Finding `nxt_gre`:**
    *   Clear the stack.
    *   Iterate through the `nums` array from right to left.
    *   For each element `nums[i]`, pop elements from the `st` stack as long as the element at the top of the stack (`nums[st.peek()]`) is smaller than or equal to `nums[i]`. This ensures that the stack maintains an increasing or equal sequence from bottom to top.
    *   After popping, if the stack is empty, it means there's no greater or equal element to the right of `nums[i]`, so `nxt_gre[i]` is set to `n`. Otherwise, the element at the top of the stack is the nearest greater or equal element to the right, so `nxt_gre[i]` is set to `st.peek()`.
    *   Push the current index `i` onto the stack.

6.  **Calculating the Sum:**
    *   Iterate through the `nums` array from left to right.
    *   For each element `nums[i]`, calculate the number of subarrays where `nums[i]` is the minimum: `l1 * r1`, where `l1 = i - pre_small[i]` and `r1 = nxt_small[i] - i`.
    *   Calculate the number of subarrays where `nums[i]` is the maximum: `l2 * r2`, where `l2 = i - pre_gre[i]` and `r2 = nxt_gre[i] - i`.
    *   Add `l2 * r2 * nums[i]` to `ans` (contribution as maximum).
    *   Subtract `l1 * r1 * nums[i]` from `ans` (contribution as minimum).

7.  **Return:**
    *   Return the final sum `ans`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n)
    *   Each of the four loops to find `pre_small`, `nxt_small`, `pre_gre`, and `nxt_gre` iterates through the array once and each element is pushed and popped from the stack at most once, resulting in O(n) time complexity for each loop.
    *   The final loop to calculate the sum also iterates through the array once, contributing O(n).
    *   Therefore, the overall time complexity is O(n).

*   **Space Complexity:** O(n)
    *   The `pre_small`, `nxt_small`, `pre_gre`, and `nxt_gre` arrays each take O(n) space.
    *   The stack `st` can hold at most `n` elements in the worst case, requiring O(n) space.
    *   Therefore, the overall space complexity is O(n).
