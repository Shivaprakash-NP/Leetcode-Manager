## LeetCode: Merge Intervals - Solution Explanation

**1. Problem Understanding:**

The "Merge Intervals" problem asks us to take a list of non-overlapping intervals, where each interval is represented as a pair of integers `[start, end]`, and merge any overlapping intervals into a single, larger interval.  The output should be a new list of merged, non-overlapping intervals.


**2. Approach / Intuition:**

The solution employs a greedy approach based on sorting.  The core idea is that if we sort the intervals by their starting points, we can efficiently merge overlapping intervals.  We iterate through the sorted intervals, keeping track of the current interval being considered (`cur`). If the current interval overlaps with the next interval (meaning the current interval's end is greater than or equal to the next interval's start), we merge them by updating the `cur` interval's end to be the maximum of the two intervals' ends. If there's no overlap, we add the `cur` interval to the result list and move on to the next interval.  This approach ensures that we only need to consider each interval once.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] intervals`:  A 2D integer array representing the input intervals.
    * `ArrayList<int[]> ans`: A dynamic array (ArrayList) to store the merged intervals. This is used because we don't know the final number of merged intervals beforehand.
* **Algorithms:**
    * **Sorting:** The `Arrays.sort()` method is used to sort the intervals based on their starting points.  This is crucial for the greedy approach to work efficiently.
    * **Iteration:** A linear scan through the sorted intervals is performed to identify and merge overlapping intervals.


**4. Code Walkthrough:**

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort intervals by start time
        int[] cur = intervals[0]; // Initialize 'cur' with the first interval
        ArrayList<int[]> ans = new ArrayList<>(); // Initialize the result list

        for(int i = 1 ; i < intervals.length ; i++) { // Iterate through the sorted intervals
            if(cur[1] >= intervals[i][0]) { // Check for overlap: cur[1] (end of cur) >= intervals[i][0] (start of next)
                cur[1] = Math.max(intervals[i][1] , cur[1]); // Merge: update cur's end to be the max end
            } else { // No overlap
                ans.add(new int[]{cur[0], cur[1]}); // Add 'cur' to result
                cur = intervals[i]; // Set 'cur' to the next interval
            }
        }
        ans.add(new int[]{cur[0], cur[1]}); // Add the last interval (crucial step often missed)
        return ans.toArray(new int[ans.size()][]); // Convert ArrayList to 2D int array and return
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N), where N is the number of intervals.  This is dominated by the sorting step (`Arrays.sort()`), which has a time complexity of O(N log N) for comparison-based sorting algorithms. The iteration through the sorted array takes O(N) time.

* **Space Complexity:** O(N) in the worst case. This is because the `ArrayList ans` can potentially store all the intervals if no merging occurs. The space used for sorting is typically O(log N) (in-place sorting) but it can also be O(N) depending on the specific sorting algorithm used by Java's `Arrays.sort()`. In this case we consider the larger of the two, O(N).  The space for `cur` is constant, O(1).


**In summary,** this solution efficiently merges overlapping intervals using a greedy approach enabled by sorting.  The time complexity is dominated by the sorting, and the space complexity is linear with respect to the number of intervals.  The code is concise and easy to understand, making it an elegant and efficient solution to the Merge Intervals problem.
