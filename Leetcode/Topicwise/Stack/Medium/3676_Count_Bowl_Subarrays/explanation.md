## LeetCode Problem: Count Bowl Subarrays Explanation

Here's a breakdown of the Java code provided, addressing the problem, solution approach, data structures, code walkthrough, and complexity analysis.

### 1. Problem Understanding:

The "Count Bowl Subarrays" problem asks us to find the number of subarrays within a given integer array `nums` that form a "bowl" shape. A "bowl" subarray is defined as a subarray `nums[i...j]` (inclusive) where the element `nums[i]` and `nums[j]` are greater than or equal to any other element `nums[k]` inside the subarray where `i < k < j`. In simpler words, `nums[i]` and `nums[j]` should be the two peak elements and all elements in between them should be less than or equal to these peak elements. Further, the length of the subarray must be greater than or equal to 3.

### 2. Approach / Intuition:

The core idea is to efficiently identify, for each element in the array, its *previous greater element* and its *next greater element*. Once we have this information, we can determine if a subarray centered around a given element `nums[i]` qualifies as a "bowl" subarray.

Here's the breakdown:

1.  **Finding Previous and Next Greater Elements:** The key to solving this problem efficiently is using a stack to find the previous and next greater elements (PGE and NGE) for each element in the array. The stack helps us maintain a monotonically decreasing sequence of elements from left to right(for PGE) and from right to left (for NGE). This allows us to quickly identify the nearest greater elements.

2.  **Checking for Bowl Shape:** Once we have the PGE and NGE for each element `nums[i]`, we check if the subarray formed by `nums[pge[i]] ... nums[nge[i]]` is a valid bowl. For a valid bowl:
    *   Both PGE and NGE must exist (not be -1, meaning they are within the array bounds).
    *   The length of the subarray formed by them should be at least 3 i.e. `nge[i] - pge[i] + 1 >= 3`.  The elements in between pge[i] and nge[i] (exclusive) are not explicitly checked to see if their values are smaller than nums[pge[i]] and nums[nge[i]] as they were used to maintain the monotonically decreasing sequences, so, by definition, they are smaller or equal to nums[i], which is smaller than both of the two peak elements.

3. **Why this approach?**

    *   Using a stack to determine PGE and NGE is efficient (O(n) time) compared to brute-force approaches that would involve nested loops.
    *   By identifying PGE and NGE first, we can then easily verify if the subarray they bound forms a "bowl" by checking that the length of the subarray is >= 3.

### 3. Data Structures and Algorithms:

*   **Stack:**  Used to efficiently find the Previous Greater Element (PGE) and Next Greater Element (NGE) for each element in the array.
*   **Arrays:** `pge` and `nge` arrays store the indices of the PGE and NGE for each element in the input array `nums`.

The core algorithm used is a stack-based approach to find the nearest greater elements.

### 4. Code Walkthrough:

```java
class Solution {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        long ans = 0;
        Stack<Integer> st = new Stack<>();

        int[] nge = new int[n];
        int[] pge = new int[n];

        // Find Previous Greater Element (PGE) for each element
        for(int i = 0; i<n; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop(); // Pop elements from stack that are smaller than current element
            pge[i] = (st.isEmpty())?-1:st.peek(); // If stack is empty, PGE is -1; otherwise, it's the top of the stack
            st.push(i); // Push the current index onto the stack
        }

        st.clear(); // Clear the stack for finding NGE

        // Find Next Greater Element (NGE) for each element
        for(int i = n-1; i>=0; i--) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop(); // Pop elements from stack that are smaller than current element
            nge[i] = (st.isEmpty())?-1:st.peek(); // If stack is empty, NGE is -1; otherwise, it's the top of the stack
            st.push(i); // Push the current index onto the stack
        }

        // Count the number of bowl subarrays
        for(int i = 0; i<n; i++) {
            if(pge[i] == -1 || nge[i] == -1) continue; // If either PGE or NGE doesn't exist, skip
            if(nge[i]-pge[i]+1 >= 3) ans++; // If subarray length is at least 3, increment the count
        }

        return ans;
    }
}
```

*   **Initialization:**
    *   `n`: Stores the length of the input array `nums`.
    *   `ans`:  A `long` variable initialized to 0 to store the final count of bowl subarrays.
    *   `st`: A `Stack<Integer>` is used to store indices.
    *   `nge`: An integer array of size `n` to store the indices of the Next Greater Element for each element.
    *   `pge`: An integer array of size `n` to store the indices of the Previous Greater Element for each element.

*   **Finding Previous Greater Element (PGE):**
    *   The first `for` loop iterates through the array from left to right.
    *   The `while` loop pops elements from the stack if they are smaller than the current element `nums[i]`.  This ensures that the stack maintains a decreasing sequence of elements.
    *   `pge[i]` is set to the index of the top element in the stack (if the stack is not empty) or to -1 (if the stack is empty, meaning there's no previous greater element).
    *   The current index `i` is then pushed onto the stack.

*   **Finding Next Greater Element (NGE):**
    *   The stack is cleared using `st.clear()`.
    *   The second `for` loop iterates through the array from right to left.
    *   The logic is similar to finding the PGE, but it now finds the next greater element to the right.
    *   `nge[i]` is set to the index of the top element in the stack (if the stack is not empty) or to -1 (if the stack is empty, meaning there's no next greater element).
    *   The current index `i` is then pushed onto the stack.

*   **Counting Bowl Subarrays:**
    *   The third `for` loop iterates through the array from left to right.
    *   It checks if both `pge[i]` and `nge[i]` are not -1 (meaning both previous and next greater elements exist).
    *   If the length of the subarray formed by `pge[i]` and `nge[i]` is at least 3 (i.e., `nge[i] - pge[i] + 1 >= 3`), the `ans` counter is incremented.

*   **Return Value:**
    *   The function returns the final count of bowl subarrays, stored in the `ans` variable.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array.  Each element is pushed onto and popped from the stack at most once during both the PGE and NGE calculations.  The final loop to count the bowls also takes O(n) time. Therefore, the overall time complexity is linear.
*   **Space Complexity:** O(n), primarily due to the `pge` and `nge` arrays, which store the previous and next greater element indices for each element in the input array. In the worst-case scenario, the stack could also hold all the elements of the array. Thus the overall space complexity is O(n).
