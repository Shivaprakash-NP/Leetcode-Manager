## LeetCode: Non-overlapping Intervals

**1. Problem Understanding:**

The problem asks us to find the minimum number of intervals we need to remove from a given set of intervals to make the remaining intervals non-overlapping.  Non-overlapping means that no two intervals share a common point.  In other words, for any two intervals `[a, b]` and `[c, d]`, either `b <= c` or `d <= a` must be true.


**2. Approach / Intuition:**

The solution uses a greedy approach.  The core idea is to sort the intervals by their end times.  Then, we iterate through the sorted intervals, keeping track of the interval with the smallest end time encountered so far (`last`). For each subsequent interval, if its start time is less than the end time of the `last` interval, it overlaps. We increment a counter (`ans`) to track the number of overlaps and skip this overlapping interval. Otherwise, we update `last` to the current interval because it doesn't overlap with the previous ones.  By sorting by end time, we prioritize selecting intervals that leave the most remaining space for subsequent non-overlapping intervals. This ensures we find the minimum number of intervals to remove.


**3. Data Structures and Algorithms:**

* **Data Structure:** We use a 2D array (`int[][] intervals`) to represent the intervals.  
* **Algorithm:** The core algorithm is a greedy approach combined with sorting.  We use `Arrays.sort()` to sort the intervals by their end times. Then, a linear scan is performed to count the number of overlapping intervals.

**4. Code Walkthrough:**

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0; // Initialize the count of overlapping intervals
        if(intervals.length == 0) return ans; // Handle empty input
        Arrays.sort(intervals , (a,b) -> Integer.compare(a[1] , b[1])); // Sort by end times
        int[] last = intervals[0]; // Initialize 'last' to the first interval
        for(int i = 1 ; i<intervals.length ; i++) {
            if(intervals[i][0] < last[1]) { // Check for overlap
                ans++; // Increment overlap count if overlap exists
            } else last = intervals[i]; // Update 'last' if no overlap
        }
        return ans; // Return the count of overlapping intervals
    }
}
```

* **Line 4:** Initializes `ans` to 0. This variable counts the number of intervals that need to be removed.
* **Line 5:** Handles the base case of an empty input array.
* **Line 6:** Sorts the `intervals` array based on the end times of the intervals using a lambda expression for the comparator.  This is crucial for the greedy approach to work correctly.
* **Line 7:** Sets `last` to the first interval after sorting, which has the earliest end time.
* **Line 8-11:** Iterates through the sorted intervals, starting from the second interval.
    * **Line 9:** Checks if the current interval's start time is less than the `last` interval's end time. If true, there's an overlap.
    * **Line 10:** Increments `ans` because we need to remove one of the overlapping intervals.
    * **Line 11:** Updates `last` to the current interval if there's no overlap.
* **Line 12:** Returns `ans`, which represents the minimum number of intervals to remove.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), dominated by the sorting step. The iteration through the sorted array takes O(N) time.
* **Space Complexity:** O(log N) in the best and average cases for sorting (due to the space used by the sorting algorithm), and O(N) in the worst case (if the sorting algorithm has O(N) worst-case space complexity).  The extra space used by the `last` variable is constant, O(1).  Therefore, the overall space complexity is dominated by the sorting algorithm used.  For many implementations of `Arrays.sort()`, the space complexity is O(log N), but it can be O(N) in some cases.
