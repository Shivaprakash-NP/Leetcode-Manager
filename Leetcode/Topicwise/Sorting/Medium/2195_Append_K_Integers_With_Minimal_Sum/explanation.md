## LeetCode: Append K Integers With Minimal Sum - Solution Explanation

**1. Problem Understanding:**

The problem asks us to find the minimum sum achievable by appending `k` distinct positive integers to a given array `nums`.  These appended integers must be positive and *not* already present in `nums`.  The goal is to find the smallest possible sum of these `k` appended numbers.


**2. Approach / Intuition:**

The solution employs a greedy approach. The core idea is to append the smallest possible positive integers that are not already in `nums`.  We sort `nums` to efficiently identify gaps where we can insert these missing integers. We iteratively fill these gaps with as many integers as possible until we've added `k` integers.  If we exhaust all gaps before adding `k` integers, we continue appending consecutive integers starting from the largest number in `nums` plus 1.

This approach is chosen because it directly addresses the problem's requirement of minimizing the sum.  By filling gaps first, we ensure we're always using the smallest possible available numbers.


**3. Data Structures and Algorithms:**

* **Data Structures:**  The primary data structure used is an array (`nums`).  No other sophisticated data structure is needed.
* **Algorithms:** The core algorithm used is a greedy algorithm combined with sorting (`Arrays.sort`).  The sum calculation utilizes the formula for the sum of an arithmetic series.


**4. Code Walkthrough:**

```java
class Solution {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums); // Sort the input array to easily identify gaps
        long sum = 0; // Initialize the sum of appended integers
        int p = 0; // Tracks the last processed number in nums

        for(int v : nums) { // Iterate through the sorted nums array
            if(v == p) continue; // Skip consecutive numbers
            if(v>p+1) { // If there's a gap
                long gap = v-p-1; // Calculate the size of the gap
                long t = Math.min(k , gap); // Determine how many integers can be added from the gap
                long a = p+1; // Starting integer for this gap
                long b = p+t; // Ending integer for this gap
                sum += (a+b)*t/2; // Add the sum of integers in this gap using the arithmetic series formula
                k-=t; // Reduce k accordingly
            }
            if(k==0) break; // If we've added k integers, stop
            p = v; // Update the last processed number
        }

        if(k>0) { // If k integers haven't been added yet
            long a = p+1; // Starting integer
            long b = p+k; // Ending integer
            sum += (a+b)*k/2; // Add remaining integers
        }
        return sum;
    }
}
```


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), dominated by the sorting of the `nums` array.  The rest of the code iterates through the array once, which is O(N).
* **Space Complexity:** O(1). The algorithm uses a constant amount of extra space, regardless of the input size.  The sorting algorithm might use extra space depending on its implementation (e.g., merge sort might use O(N) extra space in some implementations, but in-place sorting algorithms could achieve O(1) space complexity).  However, in the context of the overall problem, the space complexity is considered O(1) because it's independent of the input size, even if a sorting algorithm with O(N) space is used.


In summary, this solution efficiently solves the "Append K Integers With Minimal Sum" problem using a greedy approach and a clear, concise implementation.  The time complexity is optimal given the need for sorting and the space complexity is very efficient.
