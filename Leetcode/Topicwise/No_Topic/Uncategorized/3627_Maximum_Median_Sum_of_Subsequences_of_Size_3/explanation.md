# Maximum Median Sum of Subsequences of Size 3

## 1. Problem Understanding

The problem asks us to find the maximum possible median sum from all subsequences of size 3 within a given array `nums`.  The median of a subsequence of size 3 is simply the middle element when the subsequence is sorted.  The problem, therefore, boils down to selecting the largest possible sum of the `nums.length/3` largest elements after removing the largest element from consideration.

## 2. Approach / Intuition

The most efficient approach leverages the properties of a sorted array (or a data structure that behaves like one).  Since we only need the top `nums.length / 3` elements (after discarding the largest elements which would be added to the median if it were included), we can use a priority queue (max-heap in this case).

This approach is chosen because:

* **Efficiency:**  A max-heap provides O(log n) insertion and removal of the largest element, making it far more efficient than sorting the entire array (O(n log n)) repeatedly.
* **Simplicity:** The code directly reflects the logic: add all elements to the heap, iteratively remove the largest element (which is not considered in the median), and then add the next largest.  This process is repeated `nums.length / 3` times.

## 3. Data Structures and Algorithms

* **Priority Queue (Max-Heap):**  A `PriorityQueue` in Java is used to efficiently maintain the largest elements. We use a custom comparator to make it a max-heap (largest element at the top).
* **Iteration:** A `while` loop iterates to extract the necessary elements for the calculation.


## 4. Code Walkthrough

```java
class Solution {
    public long maximumMedianSum(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // Creates a max-heap priority queue
        for(int v : nums) q.offer(v); // Adds all elements from nums to the priority queue
        int n = nums.length/3; // Determines how many elements we need for our median sum
        long ans = 0; // Variable to accumulate the sum of the medians
        while(n-->0) { //Iterates nums.length/3 times
            q.poll(); //Removes the largest element (not part of the median)
            ans+=q.poll(); //Adds the next largest element (which will be part of the median)
        }
        return ans; //Returns the total median sum
    }
}
```

The code is concise and directly implements the algorithm.  The priority queue efficiently manages the largest elements, enabling the straightforward computation of the maximum median sum.

## 5. Time and Space Complexity

* **Time Complexity:** O(N log N), where N is the length of the `nums` array. This is dominated by the cost of adding N elements to the priority queue (O(N log N)). The `while` loop runs for N/3 iterations, with each iteration taking O(log N) time for `poll()` operation.

* **Space Complexity:** O(N) to store the priority queue.  The space used is proportional to the size of the input array.  The other variables use constant space.

**In summary:** The solution uses a priority queue to efficiently solve the "Maximum Median Sum of Subsequences of Size 3" problem, achieving a time complexity of O(N log N) and a space complexity of O(N). The code is clear and directly reflects the algorithmic approach.
