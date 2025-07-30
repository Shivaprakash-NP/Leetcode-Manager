## Sum of Subarray Minimums Explained

Here's a detailed explanation of the Java code provided for the "Sum of Subarray Minimums" LeetCode problem.

### 1. Problem Understanding:

The problem asks us to find the sum of the minimum elements of all possible subarrays of a given array `arr`. Since the sum can be very large, we need to return the answer modulo 10^9 + 7 (1_000_000_007).

For example, if `arr = [3, 1, 2, 4]`, the subarrays are:

*   [3] - min = 3
*   [3, 1] - min = 1
*   [3, 1, 2] - min = 1
*   [3, 1, 2, 4] - min = 1
*   [1] - min = 1
*   [1, 2] - min = 1
*   [1, 2, 4] - min = 1
*   [2] - min = 2
*   [2, 4] - min = 2
*   [4] - min = 4

Sum of minimums = 3 + 1 + 1 + 1 + 1 + 1 + 1 + 2 + 2 + 4 = 17

### 2. Approach / Intuition:

The brute-force approach of iterating through all possible subarrays and finding the minimum in each would result in a time complexity of O(n^2) or O(n^3), which is not efficient enough for larger input arrays.

The key idea is to find, for each element `arr[i]`, the number of subarrays in which `arr[i]` is the minimum element. Then, we can simply multiply `arr[i]` by the number of such subarrays and add it to the total sum.

To efficiently determine the number of subarrays where `arr[i]` is the minimum, we need to find:

*   **`prev[i]`:** The index of the first element to the left of `arr[i]` that is *strictly less* than `arr[i]`.  This tells us the left boundary of subarrays where `arr[i]` *could* be the minimum.

*   **`next[i]`:** The index of the first element to the right of `arr[i]` that is *less than or equal to* `arr[i]`. This tells us the right boundary of subarrays where `arr[i]` *could* be the minimum.  It's important to use `<=` here. Otherwise, you could double count.

Once we have `prev[i]` and `next[i]`, the number of subarrays where `arr[i]` is the minimum is `(i - prev[i]) * (next[i] - i)`. We can then calculate the contribution of `arr[i]` to the total sum by multiplying it with this count.

The monotonic stack data structure is used to efficiently determine `prev[i]` and `next[i]` in O(n) time.

### 3. Data Structures and Algorithms:

*   **Data Structures:**
    *   `int[] next`: Stores the index of the next smaller or equal element to the right for each element in `arr`.
    *   `int[] prev`: Stores the index of the previous smaller element to the left for each element in `arr`.
    *   `Deque<Integer> stack`: A stack (implemented using `ArrayDeque`) to maintain a monotonically increasing stack of indices.

*   **Algorithms:**
    *   **Monotonic Stack:** This is the core algorithm.  We use a monotonic stack to efficiently find the next and previous smaller elements.

### 4. Code Walkthrough:

```java
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] next = new int[n];
        int[] prev = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Find previous smaller element for each element
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Find next smaller or equal element for each element
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            next[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long mod = 1_000_000_007;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long left = i - prev[i];
            long right = next[i] - i;
            ans = (ans + (arr[i] * left % mod * right % mod)) % mod;
        }

        return (int) ans;
    }
}
```

1.  **Initialization:**

    *   `n = arr.length`: Stores the length of the input array.
    *   `next = new int[n]`: An array to store the index of the next smaller or equal element to the right.
    *   `prev = new int[n]`: An array to store the index of the previous smaller element to the left.
    *   `Deque<Integer> stack = new ArrayDeque<>()`: A stack to maintain a monotonically increasing sequence of indices.

2.  **Finding Previous Smaller Element (`prev`):**

    *   The first loop iterates through the array from left to right.
    *   `while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();`: This loop maintains the monotonically increasing property of the stack.  If the current element `arr[i]` is smaller than the element at the top of the stack (`arr[stack.peek()]`), it means the element at the top of the stack is not the previous smaller element for any future elements. So, we pop it.  This continues until the stack is empty or the element at the top of the stack is smaller than or equal to `arr[i]`.
    *   `prev[i] = stack.isEmpty() ? -1 : stack.peek();`: If the stack is empty after the `while` loop, it means there's no smaller element to the left of `arr[i]`, so we set `prev[i]` to -1. Otherwise, the element at the top of the stack is the index of the previous smaller element, so we set `prev[i]` to `stack.peek()`.
    *   `stack.push(i);`: We push the index `i` onto the stack.

3.  **Finding Next Smaller or Equal Element (`next`):**

    *   `stack.clear()`: Clears the stack for reuse.
    *   The second loop iterates through the array from right to left.
    *   `while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();`: This loop maintains the monotonically increasing property of the stack, but with the crucial difference that it uses `>=` instead of `>`. This is to ensure that we are only counting subarrays where arr[i] is strictly the minimum.
    *   `next[i] = stack.isEmpty() ? n : stack.peek();`: If the stack is empty after the `while` loop, it means there's no smaller or equal element to the right of `arr[i]`, so we set `next[i]` to `n` (which is beyond the bounds of the array). Otherwise, the element at the top of the stack is the index of the next smaller or equal element, so we set `next[i]` to `stack.peek()`.
    *   `stack.push(i);`: We push the index `i` onto the stack.

4.  **Calculating the Sum:**

    *   `long mod = 1_000_000_007;`: Defines the modulo value.
    *   `long ans = 0;`: Initializes the answer to 0.
    *   The third loop iterates through the array from left to right.
    *   `long left = i - prev[i];`: Calculates the length of the subarray to the left of `arr[i]` where `arr[i]` is the minimum.
    *   `long right = next[i] - i;`: Calculates the length of the subarray to the right of `arr[i]` where `arr[i]` is the minimum.
    *   `ans = (ans + (arr[i] * left % mod * right % mod)) % mod;`:  Calculates the contribution of `arr[i]` to the total sum and adds it to `ans`. We use the modulo operator `% mod` at each step to prevent integer overflow.

5.  **Return Value:**

    *   `return (int) ans;`: Returns the final sum (modulo 10^9 + 7) as an integer.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the length of the input array `arr`.  Each element is pushed onto and popped from the stack at most once in each of the two loops for finding `prev` and `next`. The final loop also takes O(n) time.

*   **Space Complexity:** O(n), where n is the length of the input array `arr`. We use three arrays (`next`, `prev`, and `stack`) each of size n.  The `ArrayDeque` stack can potentially hold all indices in the worst-case scenario (monotonically increasing array), so its space complexity is also O(n).
