## Partition List Problem Explanation

This document provides a detailed explanation of a Java solution for the LeetCode problem "Partition List".

### 1. Problem Understanding:

The "Partition List" problem requires rearranging a given singly linked list such that all nodes with values *less than* a specified value `x` come *before* all nodes with values *greater than or equal to* `x`. Importantly, the *relative order* of nodes within each partition (less than `x` and greater than or equal to `x`) should be preserved.

In essence, we need to split the linked list into two sub-lists (less than `x` and greater than or equal to `x`) and then concatenate them.

### 2. Approach / Intuition:

The core idea is to create *two separate linked lists*: one for nodes with values less than `x`, and another for nodes with values greater than or equal to `x`. We iterate through the original linked list, moving each node to the appropriate sub-list.  Finally, we connect the tail of the "less than x" list to the head of the "greater than or equal to x" list.

This approach is chosen because it directly addresses the problem's requirement of maintaining the relative order within each partition.  Creating two separate lists allows us to append nodes in the order we encounter them, preserving the original ordering.

The use of dummy head nodes for the two sub-lists simplifies the logic, especially when handling the first node of each list.  Without dummy nodes, we would need to check if the `less` or `high` lists were empty before appending, which would make the code more complex.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly Linked List (ListNode)
*   **Algorithm:**  Iterative list traversal, list manipulation (appending nodes), two-pointer technique.

### 4. Code Walkthrough:

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(0);
        ListNode lesst = less;

        ListNode high = new ListNode(0);
        ListNode hight = high;

        while (head != null) {
            if (head.val < x) {
                lesst.next = head;
                lesst = lesst.next;
            } else {
                hight.next = head;
                hight = hight.next;
            }
            head = head.next;
        }

        hight.next = null;  
        lesst.next = high.next;

        return less.next;
    }
}
```

*   **`ListNode less = new ListNode(0);` and `ListNode lesst = less;`**:
    *   Creates a dummy node `less` which will serve as the head of the list containing nodes with values less than `x`.  The `0` is simply a placeholder value, as the actual value of this dummy node is irrelevant.
    *   `lesst` is initialized to `less` and serves as a *tail* pointer for the "less than x" list. We use this to efficiently append new nodes to the end of the `less` list.

*   **`ListNode high = new ListNode(0);` and `ListNode hight = high;`**:
    *   Creates a dummy node `high` which will serve as the head of the list containing nodes with values greater than or equal to `x`.  Similar to `less`, the `0` is a placeholder.
    *   `hight` is initialized to `high` and serves as a *tail* pointer for the "greater than or equal to x" list.

*   **`while (head != null) { ... }`**:
    *   This loop iterates through the original linked list, node by node.

*   **`if (head.val < x) { ... } else { ... }`**:
    *   **`if (head.val < x)`**: If the current node's value (`head.val`) is less than `x`:
        *   `lesst.next = head;`:  Appends the current node (`head`) to the end of the "less than x" list.
        *   `lesst = lesst.next;`:  Moves the `lesst` pointer to the newly appended node, so it's ready to append the next node.
    *   **`else`**: If the current node's value is greater than or equal to `x`:
        *   `hight.next = head;`: Appends the current node (`head`) to the end of the "greater than or equal to x" list.
        *   `hight = hight.next;`: Moves the `hight` pointer to the newly appended node.

*   **`head = head.next;`**:
    *   Advances `head` to the next node in the original linked list.

*   **`hight.next = null;`**:
    *   This is a crucial step. It *terminates* the "greater than or equal to x" list. Without this, the last node in this list might still point to a node that belongs in the "less than x" list, creating a cycle or incorrect result.  Setting `hight.next` to `null` ensures that the "greater than or equal to x" list is properly terminated.

*   **`lesst.next = high.next;`**:
    *   Concatenates the two lists.  It connects the tail of the "less than x" list (`lesst`) to the *actual* head of the "greater than or equal to x" list (`high.next`).  Note that we use `high.next` to skip the dummy head node of the "greater than or equal to x" list.

*   **`return less.next;`**:
    *   Returns the *actual* head of the partitioned list, which is `less.next`.  We skip the dummy head node `less`.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the number of nodes in the linked list.  We iterate through the list once to partition it.  All other operations (appending nodes, updating pointers) are constant time.

*   **Space Complexity:** O(1).  We use a constant amount of extra space, regardless of the size of the input list. We create a few extra `ListNode` variables (the dummy nodes and tail pointers), but the space used doesn't scale with the input size.  The algorithm modifies the original list in-place, so no additional data structures that scale with `n` are created.
