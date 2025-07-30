```markdown
## Swap Nodes in Pairs: Detailed Explanation

This document provides a comprehensive explanation of the Java code solution for the LeetCode problem "Swap Nodes in Pairs".

### 1. Problem Understanding

The problem asks us to swap every two adjacent nodes in a singly linked list.  For example, given the linked list `1 -> 2 -> 3 -> 4`, we should return `2 -> 1 -> 4 -> 3`. The swapping should be done in-place, meaning we shouldn't create new nodes; we should only modify the `next` pointers of the existing nodes. If the list has an odd number of nodes, the last node should remain as is.

### 2. Approach / Intuition

The core idea is to iterate through the linked list, taking two nodes at a time and swapping their positions. We can achieve this by:

1.  Using a `pre` pointer to keep track of the node *before* the pair we're swapping.  This is crucial because we need to update the `next` pointer of this `pre` node to point to the swapped pair.
2.  For each pair of nodes (let's call them `f` for "first" and `s` for "second"), we perform the following steps:
    *   Temporarily store `s.next` because we'll need it later.
    *   Update `f.next` to point to `s.next` (the node after the pair).
    *   Update `s.next` to point to `f` (completing the swap within the pair).
    *   Update `pre.next` to point to `s` (connecting the `pre` node to the swapped pair).
    *   Advance `pre` to `f` so that it is ready for the next pair.

A dummy node is used at the beginning of the list to handle the case where the head of the list needs to be swapped. This avoids special-case handling for the first pair of nodes.  Without a dummy node, the code would need to update the `head` pointer directly in the first swap, making the logic more complex.

This iterative approach is preferred over a recursive approach in this specific problem because of potential stack overflow issues with extremely large lists.

### 3. Data Structures and Algorithms

*   **Data Structure:** Singly Linked List (`ListNode`)
*   **Algorithm:** Iterative pointer manipulation. The core operation is rearranging the `next` pointers of the nodes in the linked list to achieve the desired swapping.

### 4. Code Walkthrough

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head; // Base case: empty or single-node list
        ListNode dum = new ListNode(0); // Dummy node
        dum.next = head; // Dummy node points to the original head
        ListNode pre = dum; // 'pre' node points to the dummy node. This is where we begin
        while(pre.next != null && pre.next.next != null) // Main loop: iterate as long as there are at least two nodes to swap
        {
            ListNode f = pre.next; // 'f' is the first node of the pair
            ListNode s = f.next; // 's' is the second node of the pair

            f.next = s.next; // f's next now points to what s points to. s's next node
            s.next = f; // s's next points to f, swapping the pair
            pre.next = s; // pre's next points to s, connecting the previous list with the swapped pair

            pre = f; // pre moves to f, so it's ready to swap the next pair
        }
        return dum.next; // Return the modified list (starting from the original head, which might have been swapped)
    }
}
```

*   **`if(head == null || head.next == null) return head;`**: This is the base case. If the list is empty or has only one node, there's nothing to swap, so we simply return the original head.
*   **`ListNode dum = new ListNode(0);`**: We create a dummy node.  The `val` doesn't matter in this case, since we only use it as a placeholder to simplify the swapping logic at the beginning of the list.
*   **`dum.next = head;`**: The dummy node's `next` pointer points to the original head of the linked list.
*   **`ListNode pre = dum;`**: We initialize a `pre` pointer to the dummy node. This pointer will always point to the node *before* the pair we're currently swapping.
*   **`while(pre.next != null && pre.next.next != null)`**: This is the main loop that iterates through the list. It continues as long as there are at least two nodes (`pre.next` and `pre.next.next`) available to swap.
*   **`ListNode f = pre.next;`**:  `f` points to the first node of the pair to be swapped.
*   **`ListNode s = f.next;`**: `s` points to the second node of the pair to be swapped.
*   **`f.next = s.next;`**: The crucial step.  We make the `next` pointer of the first node (`f`) point to the node that *follows* the second node (`s`). This effectively detaches `f` from `s` and connects it to the rest of the list.
*   **`s.next = f;`**: We make the `next` pointer of the second node (`s`) point to the first node (`f`).  This completes the swap within the pair. `s` now points to `f`.
*   **`pre.next = s;`**: We make the `next` pointer of the node *before* the pair (`pre`) point to the second node (`s`). This connects the part of the list before the pair to the newly swapped pair.
*   **`pre = f;`**:  We move the `pre` pointer forward to `f`.  `pre` is now ready to be the "previous" node for the *next* pair to be swapped.
*   **`return dum.next;`**: Finally, we return `dum.next`.  This is the head of the modified list. Remember that the original head might have been swapped, so we need to return the node that the dummy node is pointing to.

### 5. Time and Space Complexity

*   **Time Complexity:** O(n), where n is the number of nodes in the linked list. We iterate through the list once, processing each pair of nodes.
*   **Space Complexity:** O(1). We only use a few constant extra space variables (dummy node, `pre`, `f`, `s`). The swapping is done in-place, without creating any new nodes proportional to the input size.
