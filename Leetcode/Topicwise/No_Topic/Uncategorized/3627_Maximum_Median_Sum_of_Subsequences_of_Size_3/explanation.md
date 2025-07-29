## Maximum Median Sum of Subsequences of Size 3 - LeetCode Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the maximum possible median sum of subsequences of size 3 from a given array `nums`.  In simpler terms, we need to select three numbers from the array such that their median (the middle number when sorted) is maximized.  We then sum these three numbers to get the maximum median sum.  The algorithm is optimized to only calculate this median sum; it doesn't actually find all possible subsequences of size 3.

**2. Approach / Intuition:**

The solution cleverly leverages the fact that to maximize the median of a subsequence of size 3, we should select the largest possible three numbers. However, we only need the two largest numbers *after* removing the largest number.  This is because the median of three numbers will always be the second largest if the three are sorted.

The algorithm uses a max-heap (PriorityQueue in Java) to efficiently find the largest numbers. We repeatedly remove the largest element, then add the next largest element to the sum. This directly calculates the sum of the two largest numbers after removing the largest, effectively computing the sum that maximizes the median.


**3. Data Structures and Algorithms:**

* **PriorityQueue (Max-Heap):**  A priority queue is used as a max-heap to efficiently store and retrieve the largest elements from the input array.  The `PriorityQueue<Integer>(a,b)->Integer.compare(b,a))` constructor creates a max-heap, ordering elements in descending order.
* **Iteration:** A `while` loop iterates to remove the largest element (`q.poll()`) and then add the second largest (`ans += q.poll()`),  repeating until the required number of medians (nums.length/3) is processed.


**4. Code Walkthrough:**

* `PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a));`: This line creates a max-heap priority queue to store the input numbers. The comparator ensures that larger numbers have higher priority.
* `for(int v : nums) q.offer(v);`: This loop adds all the numbers from the input array `nums` into the priority queue.
* `int n = nums.length/3;`: This line calculates the number of times we need to find the sum of the two largest numbers (after removing the largest); essentially this determines the number of subsequences of size 3 considered. We only need to consider `nums.length/3` medians because we are interested in the maximum median sum.
* `long ans = 0;`: Initializes a variable `ans` to store the sum of the second largest numbers (after repeatedly removing the largest).
* `while(n-->0) { q.poll(); ans+=q.poll(); }`: This loop is the core of the algorithm. In each iteration:
    * `q.poll();`: The largest element is removed from the queue.
    * `ans+=q.poll();`: The next largest element (the second largest initially) is added to `ans`. This effectively accumulates the sum of the second largest element across the iterations.
* `return ans;`: The function returns the final sum `ans`.

**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the length of the input array `nums`. This is dominated by the time taken to build the priority queue (O(N log N)) and the `while` loop which iterates N/3 times, each iteration taking O(log N) time for `poll()` operation.
* **Space Complexity:** O(N), as the priority queue can store up to N elements in the worst case (all unique numbers).


In summary, this solution provides an efficient way to find the maximum median sum of subsequences of size 3 by cleverly using a max-heap to efficiently find and process the required largest elements. The time complexity is optimal for this approach, as sorting would also take O(N log N) time.
