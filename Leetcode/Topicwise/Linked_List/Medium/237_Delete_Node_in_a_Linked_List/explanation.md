```markdown
## Delete Node in a Linked List - Detailed Explanation

### 1. Problem Understanding:

The "Delete Node in a Linked List" problem presents a unique challenge. We are given a node within a singly linked list that we want to delete. Crucially, we are *not* given the head of the list.  Instead, we are provided direct access to the node that must be deleted.  The task is to remove the specified node from the list. It's guaranteed that the node to be deleted is *not* the tail node.

### 2. Approach / Intuition:

The standard approach to deleting a node in a linked list involves finding the *previous* node and updating its `next` pointer to skip the node being deleted. However, since we only have access to the node we want to delete and *not* the head of the list, we can't traverse from the beginning to find the previous node.

The clever solution here exploits the fact that we know the node to be deleted *isn't* the tail.  This allows us to overwrite the node's value with the value of the *next* node.  Then, we update the `next` pointer of the node being deleted to point to the node *after* the next node, effectively skipping over the next node and deleting it from the logical structure of the list.  In essence, we are copying the data from the next node into the current node and then deleting the next node.

The approach is chosen because it allows us to "delete" the node without needing access to the head of the list or the previous node. It's a space-efficient in-place modification.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly Linked List (represented by the `ListNode` class).
*   **Algorithm:** In-place node value replacement and pointer manipulation.  No traditional search or sorting algorithms are employed.

### 4. Code Walkthrough:

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val; // Step 1: Copy the value from the next node
        node.next = node.next.next; // Step 2: Update the next pointer to skip the next node
    }
}
```

*   **`ListNode node`:**  This is the input parameter. It represents the node in the linked list that we want to delete.
*   **`node.val = node.next.val;`:** This line is the core of the solution.  It copies the `val` (data) from the *next* node (`node.next`) into the `val` of the current node (`node`). Effectively, the current node now holds the data that was originally in the *next* node.
*   **`node.next = node.next.next;`:** This line updates the `next` pointer of the current node.  It's set to point to the node *after* the next node (`node.next.next`). This effectively skips over the *next* node, removing it from the linked list's logical structure. Because the *next* node is no longer pointed to by any other node in the list, it is effectively "deleted".

**Example:**

Consider the linked list:  4 -> 5 -> 1 -> 9, and we want to delete node 5.

1.  `node` points to the node with value 5.
2.  `node.val = node.next.val;` copies the value 1 from the next node to the current node. The list conceptually becomes: 4 -> 1 -> 1 -> 9
3.  `node.next = node.next.next;` makes the node with value 1 (originally node 5) point to node 9. The list now appears as: 4 -> 1 -> 9. The node that originally held the value '1' is now effectively removed.

### 5. Time and Space Complexity:

*   **Time Complexity: O(1)** - The solution involves only a constant number of operations (value assignment and pointer update) regardless of the size of the linked list. We are not traversing the list in any way.
*   **Space Complexity: O(1)** -  The solution operates in place. We are not using any extra data structures that scale with the size of the input list.  Only a constant amount of extra memory is used.
