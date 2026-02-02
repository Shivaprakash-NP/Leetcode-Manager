### Problem Understanding

The problem asks us to design a `RideSharingSystem` class that simulates a basic ride-sharing platform. We need to manage both riders seeking rides and drivers available to give rides. The system should support adding new riders and drivers, matching an available driver with an available rider, and allowing riders to cancel their requests if they haven't been matched yet. The matching strategy implies a "first-come, first-served" approach for both riders and drivers. A key challenge is efficiently handling rider cancellations, as a cancelled rider should not be matched.

### Approach / Intuition

The core idea behind this solution is to use queues to maintain the "first-come, first-served" order for both riders and drivers. However, rider cancellations introduce a complication: if a rider cancels, they might still be in the middle of the rider queue. Removing an element from the middle of a standard `LinkedList` (which typically implements `Queue`) can be inefficient.

To address this, the solution employs a "lazy deletion" strategy for riders:

1.  **Queues for Order:** Two `Queue<Integer>` objects, `rid` for riders and `dri` for drivers, are used. When a rider or driver is added, their ID is simply enqueued. This naturally preserves the order of requests/availability.
2.  **Status Tracking for Riders:** An auxiliary array, `int[] isrid`, is used to keep track of the *active status* of each rider. `isrid[riderId] = 1` means the rider is currently active and seeking a ride, while `isrid[riderId] = 0` means they are inactive (either cancelled or already matched). This array acts like a boolean flag for each rider ID.
3.  **Cancellation:** When `cancelRider(riderId)` is called, we simply update `isrid[riderId]` to `0`. The rider's ID remains in the `rid` queue, but their status is marked as inactive. This is an O(1) operation.
4.  **Matching with Lazy Deletion:** In `matchDriverWithRider()`, before attempting to match, we first "clean up" the front of the `rid` queue. We repeatedly check the rider at `rid.peek()`. If `isrid[rid.peek()] == 0` (meaning this rider is cancelled or already matched), we `poll()` them from the queue and discard them. This continues until we find an active rider at the front, or the queue becomes empty. Once an active rider is found, they are matched with the first available driver, and both are dequeued. The matched rider's status is also updated to `0` in `isrid`.

This approach ensures that `cancelRider` is very fast, and `matchDriverWithRider` only processes active riders. The overhead of skipping cancelled riders is amortized over the lifetime of all rider requests.

### Data Structures and Algorithms

*   **`Queue<Integer> rid` (implemented by `LinkedList`):** Stores rider IDs in FIFO order. Used for `addRider` and `matchDriverWithRider`.
*   **`Queue<Integer> dri` (implemented by `LinkedList`):** Stores driver IDs in FIFO order. Used for `addDriver` and `matchDriverWithRider`.
*   **`int[] isrid` (Array):** An array used as a hash map or boolean array to store the active status of riders. The index is the `riderId`, and the value (0 or 1) indicates status. Its fixed size `1001` implies rider IDs are expected to be within `[0, 1000]`.
*   **`Set<Integer> rem` (HashSet):** This data structure is declared but **not used** in the provided code. It appears to be a leftover from an alternative thought process or an incomplete feature.

*   **Algorithms:**
    *   **FIFO (First-In, First-Out):** The primary algorithm for managing both riders and drivers using queues.
    *   **Lazy Deletion / Marking:** The technique used to handle rider cancellations. Instead of physically removing cancelled riders from the queue immediately, they are marked as inactive, and their removal is deferred until they reach the front of the queue during a matching attempt.

### Code Walkthrough

```java
class RideSharingSystem {

    Queue<Integer> rid; // Stores rider IDs waiting for a ride, in order of request.
    Queue<Integer> dri; // Stores driver IDs available for a ride, in order of availability.
    Set<Integer> rem;   // Declared but unused in the provided implementation.
    int[] isrid;        // An array to track the active status of riders.
                        // isrid[riderId] = 1 means active, 0 means cancelled or matched.

    public RideSharingSystem() {
        // Constructor: Initializes the data structures.
        rid = new LinkedList<>(); // Rider queue initialized.
        dri = new LinkedList<>(); // Driver queue initialized.
        rem = new HashSet<>();    // Unused Set initialized.
        isrid = new int[1001];    // Status array initialized. Default values are 0 (inactive).
                                  // Assumes rider IDs are between 0 and 1000.
    }
    
    public void addRider(int riderId) {
        // Adds a new rider to the system.
        rid.offer(riderId);      // Add the rider ID to the back of the rider queue.
        isrid[riderId] = 1;      // Mark this rider as active (1).
    }
    
    public void addDriver(int driverId) {
        // Adds a new driver to the system.
        dri.offer(driverId);     // Add the driver ID to the back of the driver queue.
    }
    
    public int[] matchDriverWithRider() {
        // Attempts to match an available driver with an active rider.

        // Step 1: Clean up the front of the rider queue.
        // Remove any riders from the front of the queue who have been cancelled or already matched.
        while(!rid.isEmpty() && isrid[rid.peek()] == 0) {
            rid.poll(); // If the rider at the front is inactive, remove them.
        }
        
        // Step 2: Check if a match is possible after cleanup.
        // A match requires both an active rider and an available driver.
        if(rid.isEmpty() || dri.isEmpty()) {
            return new int[]{-1, -1}; // If either queue is empty, no match can be made.
        }
        
        