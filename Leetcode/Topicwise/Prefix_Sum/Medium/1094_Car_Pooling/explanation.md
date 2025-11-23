### Problem Understanding

The problem "Car Pooling" asks us to determine if a car with a given `capacity` can accommodate a series of `trips`. Each trip is specified by an array `[numPassengers, startLocation, endLocation]`, indicating that `numPassengers` need to be picked up at `startLocation` and dropped off at `endLocation`. The car's passenger count must never exceed its `capacity` at any point in time during its journey. We need to return `true` if all trips can be completed successfully, and `false` otherwise.

### Approach / Intuition

The core idea behind solving this problem is to simulate the car's journey and keep track of the number of passengers currently in the car. Since events (pickups and drop-offs) happen at specific locations, a "sweep-line" or "event-based" approach is highly suitable.

1.  **Chronological Processing:** We need to process events in the order they occur along the car's route. The most natural way to do this is to sort all pickup locations. This ensures that when we arrive at a certain location, we've already accounted for all pickups that happened before or at this location.

2.  **Managing Passengers On Board:** When the car arrives at a `startLocation` for a new trip, two things might happen:
    *   **Drop-offs:** Some passengers already in the car might have reached their `endLocation` at or before the current `startLocation`. These passengers need to be removed from the car.
    *   **Pickups:** The new `numPassengers` for the current trip are added to the car.

3.  **Efficient Drop-off Management:** To efficiently handle drop-offs, we need a way to quickly identify which passengers should leave the car. A **min-priority queue** (min-heap) is perfect for this. We can store all trips currently on board in the priority queue, ordered by their `endLocation`. This way, the trip at the top of the priority queue will always be the one whose passengers need to be dropped off earliest.

The overall strategy is:
*   Sort all trips by their `startLocation` in ascending order. This makes sure we process pickups chronologically.
*   Initialize a variable `currentPassengers` to 0.
*   Initialize a min-priority queue to store trips currently in the car, ordered by `endLocation`.
*   Iterate through the sorted trips:
    *   For each trip `[p, start, end]`:
        *   While the priority queue is not empty and the earliest drop-off location (from `pq.peek()`) is less than or equal to the current `startLocation`:
            *   Remove those passengers from `currentPassengers` and remove the trip from the priority queue.
        *   Add the `p` passengers for the current trip to `currentPassengers`.
        *   Add the current trip `[p, start, end]` to the priority queue.
        *   Check if `currentPassengers` exceeds `capacity`. If it does, return `false`.
*   If we successfully process all trips, return `true`.

### Data Structures and Algorithms

*   **Data Structures:**
    *   `int[][] trips`: The input array, which is sorted in-place.
    *   `PriorityQueue<int[]> pq`: A min-priority queue that stores `int[]` representing trips. It is custom-ordered to prioritize trips with earlier `endLocation`s.
*   **Algorithms:**
    *   **Sorting:** `Arrays.sort()` is used to sort the input `trips` array based on `startLocation`, then `endLocation`, then `numPassengers`. This is a comparison sort, typically Timsort or Quicksort in Java, resulting in `O(N log N)` time complexity.
    *   **Sweep-Line Algorithm:** The main loop iterating through sorted trips and managing passenger counts with a priority queue is an application of the sweep-line paradigm.
    *   **Min-Heap (Priority Queue):** The `PriorityQueue` acts as a min-heap, allowing `offer` (insertion) and `poll` (extraction of minimum) operations in logarithmic time.

### Code Walkthrough

```java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // 1. Sort the trips array.
        // The custom comparator sorts trips primarily by their 'startLocation' (index 1).
        // If startLocations are identical, it then sorts by 'endLocation' (index 2).
        // If both start and end locations are identical, it sorts by 'numPassengers' (index 0).
        // Sorting by startLocation is crucial to process pickup events in chronological order.
        // The secondary and tertiary sort keys ensure deterministic behavior, though the primary sort is the most critical for correctness.
        Arrays.sort(trips, (a,b) -> {
            if(a[1] != b[1]) return a[1]-b[1]; // Sort by startLocation (ascending)
            else if(a[2] != b[2]) return a[2]-b[2]; // Then by endLocation (ascending)
            return a[0]-b[0]; // Then by numPassengers (ascending)
        });

        // 'size' variable keeps track of the current number of passengers in the car.
        int size = 0;

        // The following line is for debugging purposes and prints the sorted trips.
        // It should typically be removed in a final submission.
        // for(int[] v : trips) System.out.println(v[0]+" "+v[1]+" "+v[2]);

        // 2. Initialize a Min-Priority Queue.
        // This priority queue will store trips that are currently "on board" the car.
        // It is ordered by the 'endLocation' (index 2) of the trips in ascending order.
        // This allows us to efficiently retrieve and remove passengers who need to be dropped off earliest.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));

        // 3. Iterate through the sorted trips, simulating the car's journey.
        // Each trip 'p' represents a pickup event at p[1].
        for(int[] p : trips) {
            // Step A: Handle drop-offs.
            // Before picking up new passengers, check if any existing passengers need to be dropped off.
            // The 'while' loop continues as long as:
            //   - The priority queue is not empty (there are passengers on board).
            //   - The trip at the top of the PQ (earliest drop-off) has an 'endLocation' (pq.peek()[2])
            //     less than or equal to the 'startLocation' of the current trip (p[1]).
            // This means these passengers have either already reached their destination or are reaching it
            // at the current pickup stop.
            while(!pq.isEmpty() && pq.peek()[2] <= p[1]) {
                // Remove these passengers from the car's current count.
                size -= pq.poll()[0]; // pq.poll() removes the trip with the smallest endLocation.
            }

            // Step B: Handle the current pickup.
            // Add the passengers for the current trip to the car's current count