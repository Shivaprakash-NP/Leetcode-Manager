## Linked List Cycle II: Detailed Explanation

### 1. Problem Understanding:

The problem asks us to detect if a given singly linked list has a cycle. If a cycle exists, we need to find the node where the cycle begins (the "entry point" or "tail connecting node"). If no cycle exists, we should return `null`.

### 2. Approach / Intuition:

The solution uses the "Floyd's Cycle-Finding Algorithm" (also known as the "tortoise and hare" algorithm).  Here's the breakdown of the intuition:

1.  **Detecting a Cycle:** We use two pointers, `slow` (or "tortoise") and `fast` (or "hare"). The `slow` pointer moves one step at a time, while the `fast` pointer moves two steps at a time. If a cycle exists, the `fast` pointer will eventually catch up to the `slow` pointer. If there is no cycle, the `fast` pointer will reach the end of the list (become `null`) before the two pointers meet.

2.  **Finding the Cycle Entry Point:** Once we detect a cycle (the `slow` and `fast` pointers meet), we reset the `fast` pointer to the head of the list. Then, we move both the `slow` and `fast` pointers one step at a time. The point where they meet again is the entry point of the cycle.

**Why this approach works:**

Let's say the distance from the head of the list to the cycle entry point is `a`. Let the distance from the entry point to where the `slow` and `fast` pointers meet is `b`. Let the remaining distance in the cycle (from the meeting point back to the entry point) be `c`. Then the length of the cycle is `b + c`.

When the pointers meet:
*   The `slow` pointer has traveled `a + b` steps.
*   The `fast` pointer has traveled `a + b + n(b + c)` steps, where `n` is some integer representing the number of times the fast pointer has fully traversed the cycle.

Since the fast pointer travels twice as fast as the slow pointer:
`2(a + b) = a + b + n(b + c)`
`2a + 2b = a + b + nb + nc`
`a = nb + nc - b`
`a = (n - 1)(b + c) + c`

If `n = 1`, then `a = c`. This means the distance from the head to the entry point is equal to the distance from the meeting point to the entry point. If `n > 1`, this implies that the distance 'a' is equal to complete cycle traversals plus the remaining distance 'c'.

When we reset the `fast` pointer to the head and move both `slow` and `fast` at the same speed, they will both traverse the distance 'a'. The slow pointer effectively traverses 'c' distance to reach entry point plus some number of full cycle traversals if 'n > 1'.

Therefore, they will meet at the cycle entry point.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly linked list.
*   **Algorithm:** Floyd's Cycle-Finding Algorithm ("tortoise and hare").

### 4. Code Walkthrough:

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode s = head; // Slow pointer, initialized to the head
        ListNode f = head; // Fast pointer, initialized to the head

        // Phase 1: Detect the cycle
        while(f != null && f.next != null)
        {
            s = s.next;        // Move slow pointer one step
            f = f.next.next;   // Move fast pointer two steps
            if(s == f) break;  // If they meet, break out of the loop
        }

        // If there's no cycle, return null
        if(f == null || f.next == null) return null;

        // Phase 2: Find the cycle entry point
        f = head;              // Reset fast pointer to the head
        while(s != f)
        {
            s = s.next;        // Move slow pointer one step
            f = f.next;        // Move fast pointer one step
        }

        return s;              // Return the cycle entry point
    }
}
```

**Line-by-line explanation:**

1.  `ListNode s = head;`:  Initializes a slow pointer `s` to the head of the linked list.
2.  `ListNode f = head;`: Initializes a fast pointer `f` to the head of the linked list.
3.  `while(f != null && f.next != null)`: This loop continues as long as the fast pointer and its next node are not null.  This condition ensures that we don't try to access `f.next.next` when `f` is at the end of the list (which would throw a `NullPointerException`). This part is for detecting whether there is a cycle.
4.  `s = s.next;`: Moves the slow pointer one step forward.
5.  `f = f.next.next;`: Moves the fast pointer two steps forward.
6.  `if(s == f) break;`: If the slow and fast pointers meet (they point to the same node), it means there's a cycle.  The loop is terminated using `break`.
7.  `if(f == null || f.next == null) return null;`: If the fast pointer reaches the end of the list (either `f` or `f.next` is `null`), it means there's no cycle, so the function returns `null`. This check is only performed after the first while loop.
8.  `f = head;`: Resets the fast pointer to the head of the linked list.
9.  `while(s != f)`: This loop continues as long as the slow and fast pointers are not pointing to the same node.
10. `s = s.next;`: Moves the slow pointer one step forward.
11. `f = f.next;`: Moves the fast pointer one step forward.
12. `return s;`: When the slow and fast pointers meet, they are at the cycle entry point. The function returns the slow pointer (which is now at the entry point).

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the number of nodes in the linked list. In the worst case, the slow pointer might traverse the entire list once. Even though the fast pointer moves twice as fast, the overall number of steps is still proportional to N.
*   **Space Complexity:** O(1), because we are only using a constant amount of extra space for the slow and fast pointers.  The algorithm operates in place without requiring any additional data structures whose size scales with the input.
