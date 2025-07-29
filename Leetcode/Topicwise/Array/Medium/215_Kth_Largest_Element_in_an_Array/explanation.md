## LeetCode: Kth Largest Element in an Array - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the kth largest element in an unsorted integer array.  For example, if the array is `[3,2,1,5,6,4]` and `k` is 2, the second largest element is 5.

**2. Approach / Intuition:**

This solution employs a `PriorityQueue` (min-heap by default in Java) to efficiently find the kth largest element.  We leverage the property of a min-heap: the smallest element is always at the root.  By using a *max-heap* (achieved via `Collections.reverseOrder()`), the largest element is always at the root.  We add all elements to the max-heap. Then, we repeatedly remove the largest element (`pq.poll()`) until only the kth largest element remains at the top of the heap.  This approach is chosen because it provides a good balance between simplicity and efficiency, especially for larger arrays where sorting the entire array would be less efficient.


**3. Data Structures and Algorithms:**

* **Data Structure:** `PriorityQueue` (specifically, a max-heap implemented using a min-heap with a reverse comparator).
* **Algorithm:**  Heap-based selection.  We use the heap to maintain a sorted order (in reverse) of the largest k elements seen so far, discarding smaller elements that are irrelevant to finding the kth largest.

**4. Code Walkthrough:**

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // Creates a max-heap
        for(int v : nums) pq.add(v); // Adds all elements to the max-heap.  O(n log k) overall due to heap operations.
        while(k-- > 1) pq.poll(); //Removes the largest k-1 elements. O((k-1)log k)
        return pq.poll(); //The remaining element is the kth largest. O(log k)
    }
}
```

* **Line 2:** A `PriorityQueue` named `pq` is created. `Collections.reverseOrder()` ensures it's a max-heap, ordering elements from largest to smallest.
* **Line 3:** A for-each loop iterates through the input array `nums`. Each element `v` is added to the `pq`. The `add()` operation maintains the heap property (max-heap in this case).
* **Line 4:** A `while` loop runs `k-1` times.  In each iteration, `pq.poll()` removes the largest element (root of the max-heap). This leaves the kth largest element at the root.
* **Line 5:** `pq.poll()` retrieves and returns the kth largest element (which is now at the root).


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log k), where N is the length of the input array `nums`.  Adding N elements to the heap takes O(N log k) time (because the heap size is at most k).  Removing k-1 elements takes O((k-1) log k) time.  The dominant factor is adding the elements to the heap. If k is significantly smaller than N (which is often the case in this problem), this is much faster than sorting the entire array (O(N log N)).

* **Space Complexity:** O(k). The `PriorityQueue` stores at most k elements at any given time.  The space used is proportional to k.


**Improvement Note:**  While this solution is correct and relatively efficient, for very large N and small k, other approaches like QuickSelect (average case O(N), worst case O(N^2)) could be more efficient.  However, QuickSelect is more complex to implement.  This heap-based solution offers a good balance between efficiency and code simplicity.
