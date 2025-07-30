## Find the Duplicate Number - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to find the duplicate number in a given array `nums` of `n + 1` integers where each integer is in the range `[1, n]` (inclusive). It is guaranteed that at least one number is repeated and there is only one duplicate number. We must find this duplicate number without modifying the array `nums` and using only constant extra space.

### 2. Approach / Intuition:

The solution cleverly uses the concept of cycle detection, treating the array as a linked list. The core idea is to model the array indices as nodes in a linked list, and the values at those indices as the 'next' pointers.  Because there is a duplicate number, this creates a cycle in the linked list.

Specifically:

*   **Array as Linked List:**  Think of `nums[i]` as a pointer from index `i` to index `nums[i]`. For example, if `nums = [1,3,4,2,2]`, then from index 0 we go to index 1 (because `nums[0] = 1`), from index 1 we go to index 3 (because `nums[1] = 3`), and so on.  Because the numbers are in the range `[1,n]`, all array indices are valid "pointers".

*   **Duplicate Creates Cycle:** Since there is a duplicate number, say `x`, there must be two different indices, `i` and `j`, that both have the value `x`. Therefore, both index `i` and `j` would point to index `x` (meaning `nums[i] = x` and `nums[j] = x`). This creates a cycle in the linked list.

*   **Floyd's Cycle Detection (Tortoise and Hare):** We use Floyd's cycle detection algorithm (also known as the "tortoise and hare" algorithm) to find the entry point of the cycle. This algorithm uses two pointers, `slow` (tortoise) and `fast` (hare). The `slow` pointer moves one step at a time, while the `fast` pointer moves two steps at a time. They will eventually meet at some point within the cycle.

*   **Finding the Entry Point:** Once the `slow` and `fast` pointers meet, we reset the `fast` pointer to the starting point (index 0) and move both pointers one step at a time. The point where they meet again is the entry point of the cycle, which corresponds to the duplicate number.

The reason this approach is chosen is that it efficiently finds the duplicate number without modifying the array or using extra space beyond a couple of variables.

### 3. Data Structures and Algorithms:

*   **Data Structures:**  We are primarily operating on the input array `nums`. Although we treat it as a linked list, no explicit linked list data structure is used.
*   **Algorithms:**
    *   Floyd's Cycle Detection Algorithm (Tortoise and Hare)

### 4. Code Walkthrough:

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int s = nums[0]; // Slow pointer (tortoise)
        int f = nums[0]; // Fast pointer (hare)

        // Phase 1: Find the intersection point of the two pointers.
        do {
            s = nums[s];       // Slow pointer moves one step
            f = nums[nums[f]]; // Fast pointer moves two steps
        } while (s != f);      // Continue until they meet

        // Phase 2: Find the entry point of the cycle.
        f = nums[0];       // Reset fast pointer to the start
        while (s != f) {
            s = nums[s];       // Move both pointers one step
            f = nums[f];       // Move both pointers one step
        }

        return s;          // The meeting point is the duplicate number
    }
}
```

**Explanation:**

1.  **Initialization:**
    *   `s = nums[0]`: Initialize the `slow` pointer to the first element's index (index 0).
    *   `f = nums[0]`: Initialize the `fast` pointer to the first element's index (index 0).

2.  **Cycle Detection (Phase 1):**
    *   `do { ... } while (s != f);`: This loop continues until the `slow` and `fast` pointers meet.
    *   `s = nums[s]`:  The `slow` pointer moves one step forward in the "linked list".  It goes from the current index `s` to the index given by the value at `nums[s]`.
    *   `f = nums[nums[f]]`: The `fast` pointer moves two steps forward in the "linked list". It goes from the current index `f` to the index given by the value at `nums[f]`, and then from that index to the index given by the value at `nums[nums[f]]`.

3.  **Finding the Entry Point (Phase 2):**
    *   `f = nums[0]`:  Reset the `fast` pointer back to the starting point (index 0).
    *   `while (s != f) { ... }`: This loop continues until the `slow` and `fast` pointers meet again.
    *   `s = nums[s]`: Move the `slow` pointer one step.
    *   `f = nums[f]`: Move the `fast` pointer one step.  Now, *both* pointers move at the same pace.
    *   The point where `s` and `f` meet is the entry point of the cycle, which is the duplicate number.

4.  **Return Value:**
    *   `return s`:  Return the value of `s` (or `f`, as they are equal at this point), which represents the duplicate number.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n)
    *   The first loop (cycle detection) can iterate at most `n` times since the slow pointer moves one step at a time, and the length of the cycle and path to the cycle combined is at most `n`.
    *   The second loop (finding the entry point) can also iterate at most `n` times because both `slow` and `fast` pointers move one step at a time.
    *   Therefore, the overall time complexity is O(n).

*   **Space Complexity:** O(1)
    *   The algorithm uses only a constant amount of extra space for the `slow` and `fast` pointers, regardless of the size of the input array. It doesn't modify the input array itself. Therefore, the space complexity is O(1).
