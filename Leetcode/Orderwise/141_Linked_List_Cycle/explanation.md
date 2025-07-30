```markdown
## Linked List Cycle Problem Explanation

This document provides a detailed explanation of the provided Java code that detects if a cycle exists within a singly linked list.

### 1. Problem Understanding:

The "Linked List Cycle" problem asks us to determine whether a given singly linked list contains a cycle. A cycle exists if, starting from the head of the list, we can traverse the list and eventually revisit a node that we've already encountered. This means the linked list doesn't end with a `null` pointer but loops back on itself.  We need to return `true` if a cycle exists and `false` otherwise.

### 2. Approach / Intuition:

The provided solution uses the **Floyd's Cycle-Finding Algorithm**, also known as the "tortoise and hare" algorithm.  The intuition behind this algorithm is based on the following analogy:

Imagine two runners on a circular track. One runner (the "hare") is faster than the other (the "tortoise"). If the track is truly circular (i.e., a cycle exists), the faster runner will eventually catch up to and pass the slower runner.  If the track is linear, the faster runner will reach the end of the track without ever catching the slower runner.

Applying this to a linked list, we use two pointers:

*   **Slow pointer (tortoise):** Moves one node at a time.
*   **Fast pointer (hare):** Moves two nodes at a time.

If a cycle exists, the fast pointer will eventually catch up to the slow pointer because it's traversing the list at a faster rate.  If no cycle exists, the fast pointer will reach the end of the linked list (i.e., encounter a `null` node) before the slow pointer.

This approach is chosen because it's both efficient (linear time complexity) and uses a constant amount of extra space.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly Linked List. The code assumes a `ListNode` class is defined with a `val` (integer value) and `next` (pointer to the next node) field.
*   **Algorithm:** Floyd's Cycle-Finding Algorithm (Tortoise and Hare Algorithm).

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
    public boolean hasCycle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while(f != null && f.next != null)
        {
            s = s.next;
            f = f.next.next;
            if(s == f) return true;
        }
        return false;
    }
}
```

1.  **`public boolean hasCycle(ListNode head)`:** This is the method that takes the `head` of the linked list as input and returns `true` if a cycle exists, and `false` otherwise.

2.  **`ListNode s = head;`**: Initializes the slow pointer `s` to the `head` of the linked list.

3.  **`ListNode f = head;`**: Initializes the fast pointer `f` to the `head` of the linked list.

4.  **`while(f != null && f.next != null)`:** This loop continues as long as the fast pointer `f` and its `next` pointer are not `null`.  This condition is crucial to avoid a `NullPointerException` when the fast pointer reaches the end of the list (i.e., when there's no cycle).

5.  **`s = s.next;`**: Moves the slow pointer `s` one node forward in each iteration.

6.  **`f = f.next.next;`**: Moves the fast pointer `f` two nodes forward in each iteration.

7.  **`if(s == f) return true;`**: Checks if the slow pointer `s` and the fast pointer `f` are pointing to the same node. If they are, it means the fast pointer has caught up to the slow pointer, indicating the presence of a cycle.  In this case, the method immediately returns `true`.

8.  **`return false;`**: If the `while` loop terminates without the slow and fast pointers meeting, it means the fast pointer has reached the end of the list, and no cycle exists. Therefore, the method returns `false`.

### 5. Time and Space Complexity:

*   **Time Complexity: O(n)**, where `n` is the number of nodes in the linked list.

    *   In the worst case (no cycle), the fast pointer traverses the entire list once.
    *   If a cycle exists, the fast pointer might traverse the cycle multiple times before catching up to the slow pointer. However, the number of traversals is still proportional to the length of the list.

*   **Space Complexity: O(1)**, which means constant space complexity. The algorithm uses only two pointers ( `s` and `f` ), regardless of the size of the linked list. Therefore, the space used by the algorithm doesn't scale with the input size. This is a very memory-efficient solution.
