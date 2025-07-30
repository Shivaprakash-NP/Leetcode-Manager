## Odd Even Linked List Problem Explanation

Here's a detailed breakdown of the "Odd Even Linked List" LeetCode problem and the provided Java solution.

### 1. Problem Understanding:

The problem asks us to rearrange a given singly linked list such that all nodes with odd indices (1st, 3rd, 5th, etc.) appear first, followed by all nodes with even indices (2nd, 4th, 6th, etc.). The relative order within both the odd and even groups should remain the same as in the original list.  The node numbering starts from 1 (i.e., the head is the first node, so it's odd).

For example:

Input: `1 -> 2 -> 3 -> 4 -> 5 -> NULL`
Output: `1 -> 3 -> 5 -> 2 -> 4 -> NULL`

Input: `2 -> 1 -> 3 -> 5 -> 6 -> 4 -> 7 -> NULL`
Output: `2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4 -> NULL`

### 2. Approach / Intuition:

The core idea is to maintain two separate linked lists: one for odd-indexed nodes and another for even-indexed nodes. We iterate through the original list, moving nodes to their respective odd or even lists.  After processing all nodes, we simply connect the tail of the odd list to the head of the even list.

The approach leverages the concept of *pointer manipulation*. We need to carefully update the `next` pointers of the nodes to build the odd and even lists correctly and then join them.  Using temporary pointers simplifies the process.

Why this approach? It's an *in-place* algorithm (modifies the existing list instead of creating new ones) and achieves the desired rearrangement with linear time complexity.  It's relatively simple to understand and implement, once the pointer manipulation logic is clear.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly Linked List
*   **Algorithm:** Iteration and Pointer Manipulation.  The algorithm performs a linear traversal of the linked list, modifying pointers to rearrange the node order.

### 4. Code Walkthrough:

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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode se = head.next; // 'se' points to the second node (first even node)
        ListNode sehead = se; // 'sehead' stores the head of the even list

        ListNode tem = head; // 'tem' points to the first node (first odd node)

        while(tem.next != null && tem.next.next != null)
        {
            ListNode t = tem.next.next; // 't' is the next odd node to connect to the current odd node
            se.next = se.next.next; // connect even to next even
            tem.next = t; // connect odd to next odd
            tem = tem.next; // move to the next odd node
            se = se.next; // move to the next even node
        }
        tem.next = sehead; // connect tail of odd list to the head of even list
        return head;
    }
}
```

1.  **`if(head == null || head.next == null) return head;`**: Handles the base cases where the list is empty or contains only one node. In these cases, no rearrangement is needed, so the original list is returned.

2.  **`ListNode se = head.next;`**:  `se` stands for "second" and is initialized to the second node in the list. This node will become the head of the even-indexed list.

3.  **`ListNode sehead = se;`**: `sehead` stores the initial head of the even list, which we'll need later to connect it to the odd list.

4.  **`ListNode tem = head;`**: `tem` is used to iterate the list and connect odds.

5.  **`while(tem.next != null && tem.next.next != null)`**:  This loop is the heart of the algorithm. It iterates as long as there are at least two nodes remaining after the current `tem` node. The two-node check is crucial to prevent `NullPointerExceptions`. `tem.next` ensures we have an even node and `tem.next.next` ensures we have an odd node after the even node to connect with.

6.  **`ListNode t = tem.next.next;`**: `t` is a temporary variable that points to the next odd node after the current even node pointed to by `tem.next`.  This is crucial to preserve the link to the next odd node *before* we modify `tem.next`.

7.  **`se.next = se.next.next;`**: This line connects the current even node (`se`) to the *next* even node.  Effectively, it removes the next odd node from the even list and adds it to the odd list.

8.  **`tem.next = t;`**: This line connects the current odd node (`tem`) to the *next* odd node (`t`).

9.  **`tem = tem.next;`**: Moves the `tem` pointer to the next odd node.

10. **`se = se.next;`**: Moves the `se` pointer to the next even node.

11. **`tem.next = sehead;`**: After the `while` loop finishes, the `tem` pointer will be at the last odd node. This line connects the tail of the odd list to the head of the even list (`sehead`).

12. **`return head;`**: Returns the head of the modified linked list (which is the same as the original head).

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the number of nodes in the linked list.  We iterate through the list once.

*   **Space Complexity:** O(1).  The solution uses a constant amount of extra space, regardless of the size of the input list.  We only use a few pointers (`se`, `sehead`, `tem`, `t`). It is an in-place algorithm.
