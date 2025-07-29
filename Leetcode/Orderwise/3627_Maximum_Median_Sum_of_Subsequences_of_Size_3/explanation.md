## LeetCode: Maximum Median Sum of Subsequences of Size 3

**1. Problem Understanding:**

The problem asks us to find the maximum possible median sum from subsequences of size 3 within a given array of numbers.  In other words, we need to select three numbers from the input array such that their median (the middle value when sorted) is maximized, and then we sum those three numbers.  We then need to find the maximum of all such possible median sums.  The solution implicitly assumes that the input array has at least three elements.


**2. Approach / Intuition:**

The solution leverages the fact that to maximize the median of a subsequence of size 3, we should choose the three largest numbers from the input array. This is because the median of three numbers is always less than or equal to the second largest number among them.  Therefore, selecting the three largest numbers guarantees the maximum possible median, thus maximizing the sum of those three numbers.

The code uses a max-heap (PriorityQueue in Java) to efficiently find the largest numbers. It iteratively removes the largest number (which doesn't contribute to the median of the subsequence) and adds the second largest to the sum, effectively selecting the three largest numbers without explicit sorting of all possible subsequences. This approach avoids unnecessary computations.


**3. Data Structures and Algorithms:**

* **Data Structures:**  A `PriorityQueue` (Max-Heap) is used to store and efficiently retrieve the largest elements in the array.
* **Algorithms:**  The core algorithm is a selection algorithm based on heap properties, which allows for efficient extraction of the largest elements without completely sorting the array.


**4. Code Walkthrough:**

```java
class Solution {
    public long maximumMedianSum(int[] nums) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // Create a max-heap
        for(int v : nums) q.offer(v); // Add all numbers to the max-heap
        int n = nums.length/3; // Determine how many times we need to remove the largest and sum the second largest
        long ans = 0; // Initialize the sum
        while(n-->0) { // Iterate n times to select the three largest numbers implicitly
            q.poll(); // Remove the largest element (we don't need it in the median calculation)
            ans+=q.poll(); // Add the second largest element to the sum
        }
        return ans; // Return the sum of the second and third largest elements (representing the sum of a subsequence with the maximum median)
    }
}
```

* **Line 1-2:** Creates a `PriorityQueue` (max-heap) to store the numbers. The comparator ensures that the largest number is always at the top of the heap.
* **Line 3-4:**  Iterates through the input array `nums` and adds each element to the max-heap.
* **Line 5:** Calculates `n`, which represents the number of times we need to pick the second largest number to create the median sum. Note that because we take the second largest, we implicitly take the three largest numbers.  The division by 3 is to ensure we select the correct number of pairs (second-largest number).
* **Line 6:** Initializes `ans` to store the sum of the relevant second largest numbers.
* **Line 7-8:** The loop iterates `n` times. In each iteration, `q.poll()` removes the largest element, and then `ans += q.poll()` adds the next largest (second largest in the remaining heap) to the `ans`.  This implicitly selects the three largest numbers, and sums the second and third largest.
* **Line 9:** Returns the accumulated sum `ans`.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the length of the input array `nums`. This is dominated by the time to build the heap (O(N log N)) and the time to perform `n` heap operations (O(n log n) which is less than or equal to O(N log N)).
* **Space Complexity:** O(N), primarily due to the space used by the `PriorityQueue` to store the elements of the input array.  In the worst case, the heap will hold all elements of the input array.


**In summary:**  The solution efficiently finds the maximum median sum of subsequences of size 3 by smartly utilizing a max-heap to extract the three largest numbers implicitly, avoiding the need for complete sorting or explicit enumeration of all possible subsequences.  The time complexity is optimal for this type of problem.
