## LeetCode: Maximum Number of Events That Can Be Attended - Solution Explained

**1. Problem Understanding:**

The problem asks us to find the maximum number of non-overlapping events that can be attended.  Each event is defined by a start and end day.  We can only attend one event at a time. The goal is to select the events that maximize the total number of attended events.


**2. Approach / Intuition:**

The solution utilizes a greedy approach combined with a priority queue.  The core idea is to process events day by day. For each day, we consider all events that start on or before that day. We use a priority queue to store the end days of these events, sorted in ascending order.  This allows us to always select the event that ends earliest, ensuring we can attend as many events as possible without overlapping.  This approach is greedy because it always chooses the locally optimal solution (earliest ending event) hoping to lead to the global optimum (maximum number of attended events).  We sort the events by their start days to efficiently iterate through them.


**3. Data Structures and Algorithms:**

* **Data Structures:**
    * `int[][] events`: A 2D array to store the events. Each inner array represents an event with `[start_day, end_day]`.
    * `PriorityQueue<Integer> pq`: A min-heap priority queue to store the end days of events that are currently available.  The min-heap property allows us to efficiently retrieve the event ending earliest.
* **Algorithms:**
    * **Sorting:** `Arrays.sort()` is used to sort the events by their start days in ascending order.  This is crucial for efficient iteration and to ensure we consider events in the correct order.
    * **Greedy Algorithm:** The main logic implements a greedy algorithm by selecting the event ending earliest on each day.
    * **Priority Queue:** The priority queue is used to efficiently manage and select the events that are available to attend on each day.


**4. Code Walkthrough:**

```java
class Solution {
    public int maxEvents(int[][] events) {
        int max = Integer.MIN_VALUE; // Find the maximum end day among all events
        int n = events.length;
        int ans = 0; // Initialize the count of attended events

        for(int[] v : events) max = Math.max(max, v[1]); // Find the maximum end day
        Arrays.sort(events, (a, b) -> a[0] - b[0]); // Sort events by start day

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //Min-heap for event end days
        for(int i = 1, j = 0 ; i<=max ; i++) { // Iterate through each day
            while(j<n && events[j][0] <= i) { // Add events starting on or before current day to the priority queue
                pq.offer(events[j][1]);
                j++;
            }
            while(!pq.isEmpty() && pq.peek() < i) pq.poll(); // Remove events that ended before the current day
            if(!pq.isEmpty()) { // If there are available events
                ans++; // Attend the event
                pq.poll(); // Remove the attended event
            }
        }
        return ans;
    }
}
```

**5. Time and Space Complexity:**

* **Time Complexity:** O(N log N + M log K), where N is the number of events, M is the maximum end day, and K is the maximum number of events overlapping on any single day (usually much smaller than N).  Sorting the events takes O(N log N). Iterating through days takes O(M). Operations on the priority queue (offer and poll) take O(log K) each, and this is done at most N times.

* **Space Complexity:** O(K), where K is the maximum number of events overlapping on any single day.  This is the space used by the priority queue. In the worst-case scenario, all events could overlap, resulting in O(N) space complexity.  However, in many practical cases, K will be significantly smaller than N.


In summary, this solution efficiently solves the "Maximum Number of Events That Can Be Attended" problem by employing a greedy strategy guided by a priority queue, achieving a near-optimal balance between time and space complexity.  The sorting step ensures we consider events in a logical order, while the priority queue optimizes the selection of non-overlapping events.
