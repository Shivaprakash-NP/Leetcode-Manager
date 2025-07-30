## Remove Duplicates from Sorted List II - Detailed Explanation

### 1. Problem Understanding:

The problem asks us to remove all nodes from a sorted linked list that have duplicate values, leaving only distinct numbers from the original list.  For example, given the list `1->2->3->3->4->4->5`, the expected output is `1->2->5`.  The list remains sorted after the removal.

### 2. Approach / Intuition:

The core idea is to iterate through the linked list and identify sequences of nodes with the same value. If a duplicate sequence is found, we skip over the entire sequence and adjust the pointers to effectively remove the duplicates from the list.

Here's the intuition behind the chosen approach:

*   **Sorted List Property:** We exploit the fact that the input list is sorted. This means that all duplicates will appear consecutively.
*   **Two Pointers:** We use two pointers, `s` (slow) and `f` (fast), to traverse the list. The `s` pointer points to the current node we are considering. The `f` pointer is used to scan ahead for duplicates.  A `pre` pointer is used to keep track of the node before `s`.
*   **Duplicate Detection and Removal:**  If `s.val` and `f.val` are different, it means we haven't encountered duplicates, so we move the pointers forward.  If `s.val` and `f.val` are the same, we move the `f` pointer forward until it finds a different value or reaches the end of the list. Then, we remove the duplicate sequence by adjusting the `next` pointer of the node before the duplicate sequence (`pre`) or the `head` if the duplicate sequence started from the beginning.
*   **`pre` Pointer:** The `pre` pointer is crucial for updating the `next` pointer of the previous node when duplicates are found. It allows us to "splice" the list by removing the duplicated segment.

This approach is chosen because it efficiently iterates through the list only once, making it an optimal solution in terms of time complexity for this problem.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly-linked list.
*   **Algorithm:** Iteration with two pointers.

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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head; // Base case: Empty or single-node list

        ListNode pre = null; // Pointer to the node before 's' (initially null)
        ListNode s = head;  // Slow pointer, points to the current node
        ListNode f = head.next; // Fast pointer, scans for duplicates

        while(f != null) // Iterate until the fast pointer reaches the end
        {
            if(s.val != f.val) // No duplicates found
            {
                pre = s;      // Move 'pre' to 's'
                s = s.next;     // Move 's' to the next node
                f = f.next;     // Move 'f' to the next node
            }
            else // Duplicates found
            {
                while(f != null && f.val == s.val) f = f.next; // Advance 'f' past all duplicates
                if(pre != null) pre.next = f; // If 'pre' exists, link it to the node after the duplicates
                else head = f; // If 'pre' is null, the duplicates started at the head, so update the head

                s = f; // Move 's' to 'f' (the node after the duplicates)
                if(f!=null) f = f.next; // Move 'f' to the next node (if it exists)
            }
        }
        return head; // Return the updated head
    }
}
```

*   **`if(head == null || head.next == null) return head;`**: Handles the base cases where the list is empty or contains only one node. In these cases, there can be no duplicates, so we simply return the head.
*   **`ListNode pre = null; ListNode s = head; ListNode f = head.next;`**: Initializes the three pointers:
    *   `pre`:  Keeps track of the node *preceding* the current node `s`. Initially set to `null` because the first node has no predecessor.
    *   `s`:  The "slow" pointer, which marks the start of a potential duplicate sequence. Initialized to the head of the list.
    *   `f`: The "fast" pointer, which scans ahead to find duplicates. Initialized to the node after the head.
*   **`while(f != null)`**: This loop iterates through the linked list using the fast pointer `f`.
*   **`if(s.val != f.val)`**:  If the values pointed to by `s` and `f` are different, it means that the current node `s` is not part of a duplicate sequence.  Therefore:
    *   `pre = s;`: The `pre` pointer is updated to point to `s`.
    *   `s = s.next;`:  The `s` pointer is moved to the next node.
    *   `f = f.next;`: The `f` pointer is moved to the next node.
*   **`else`**: This block is executed when `s.val == f.val`, indicating a duplicate sequence.
    *   **`while(f != null && f.val == s.val) f = f.next;`**: This inner loop advances the `f` pointer until it either reaches the end of the list or finds a node with a different value.  After this loop, `f` will point to the first node after the duplicate sequence (or be `null` if the duplicates extended to the end of the list).
    *   **`if(pre != null) pre.next = f; else head = f;`**:  This is the crucial step for removing the duplicate sequence.
        *   If `pre != null`, it means the duplicate sequence was not at the beginning of the list. Therefore, we update the `next` pointer of the node preceding the duplicate sequence (`pre`) to point to the node after the duplicate sequence (`f`).  Effectively, we're splicing the list to remove the duplicates.
        *   If `pre == null`, it means the duplicate sequence started at the beginning of the list (the head). In this case, we update the `head` of the list to point to the node after the duplicate sequence (`f`).
    *   `s = f;`: The slow pointer `s` is moved to point to `f` (which is the node after the duplicate sequence).
    *   `if(f!=null) f = f.next;`: The fast pointer `f` is moved to its next node.
*   **`return head;`**: Finally, the function returns the updated `head` of the linked list.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(n), where n is the number of nodes in the linked list.  We iterate through the list at most twice in the worst-case scenario (one outer loop and one potentially nested inner loop), but the total number of steps is proportional to the number of nodes.
*   **Space Complexity:** O(1). The solution uses a constant amount of extra space for the `pre`, `s`, and `f` pointers, regardless of the size of the input list. Therefore, it has constant space complexity.
