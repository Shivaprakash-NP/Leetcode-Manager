## LeetCode: Sliding Window Maximum - Detailed Solution Explanation

**1. Problem Understanding:**

The "Sliding Window Maximum" problem asks us to find the maximum value within a sliding window of size `k` as it moves from left to right across an input array `nums`.  The output should be an array containing these maximum values for each window position.


**2. Approach / Intuition:**

A brute-force approach would iterate through each window, finding the maximum in each, resulting in O(n*k) time complexity.  This is inefficient for large arrays.  Instead, we use a deque (double-ended queue) to maintain a monotonically decreasing sequence of indices. This deque stores indices of elements within the current window, ensuring the index of the largest element is always at the front.

This approach is chosen because a deque allows for efficient addition and removal of elements from both ends, making it ideal for tracking the maximum element within the sliding window.  The monotonically decreasing property of the deque means we don't need to search the entire window to find the maximum; it's always at the front.


**3. Data Structures and Algorithms:**

* **Deque (ArrayDeque):** A double-ended queue is used to store indices of elements.  This allows for O(1) time complexity for adding and removing elements from both ends.
* **Sliding Window Technique:** This algorithm paradigm is fundamental to solving the problem efficiently.
* **Monotonic Queue:**  The deque is maintained as a monotonic decreasing queue, significantly optimizing the search for the maximum.


**4. Code Walkthrough:**

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>(); // Deque to store indices
        int n = nums.length;
        int[] ans = new int[n-k+1]; // Result array
        int j = 0; // Index for the result array

        for(int i = 0 ; i < n ; i++) {
            // Remove indices outside the current window
            if(!dq.isEmpty() && dq.peekFirst() <= i-k) dq.pollFirst();

            // Remove indices of smaller elements from the rear
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();

            // Add the current index to the deque
            dq.addLast(i);

            // Add the maximum to the result array if the window is formed
            if(i >= k-1) ans[j++] = nums[dq.peekFirst()];
        }
        return ans;
    }
}
```

* **`Deque<Integer> dq = new ArrayDeque<>();`**: Initializes an empty deque to store indices.
* **`int n = nums.length;`**: Gets the length of the input array.
* **`int[] ans = new int[n-k+1];`**: Creates the result array to store the maximum values for each window.
* **`int j = 0;`**: Index for the result array.
* **`for(int i = 0 ; i < n ; i++)`**: Iterates through the input array.
* **`if(!dq.isEmpty() && dq.peekFirst() <= i-k) dq.pollFirst();`**: Removes indices that are outside the current window from the front of the deque.
* **`while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();`**: Removes indices of smaller elements from the rear of the deque, maintaining the monotonically decreasing property.
* **`dq.addLast(i);`**: Adds the current index to the rear of the deque.
* **`if(i >= k-1) ans[j++] = nums[dq.peekFirst()];`**: Once the window is fully formed (`i >= k-1`), adds the maximum element (index at the front of the deque) to the result array.
* **`return ans;`**: Returns the result array.

**5. Time and Space Complexity:**

* **Time Complexity: O(n)** - Each element is added and removed from the deque at most once. The outer loop iterates through the array once.  The operations inside the loop are O(1) on average.

* **Space Complexity: O(k)** - In the worst case, the deque can hold at most `k` elements (the size of the window).  The result array is O(n-k+1), but since n-k+1 <= n this is also dominated by the O(n) space used by the input array.  Therefore, we consider the space complexity to be O(k).


This solution efficiently solves the Sliding Window Maximum problem using a deque to maintain a monotonically decreasing sequence of indices, resulting in a linear time complexity.  It's a classic example of how clever data structure usage can dramatically improve algorithm efficiency.
