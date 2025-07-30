## Swapping Nodes in a Linked List - Detailed Explanation

Here's a breakdown of the provided Java code solution for the "Swapping Nodes in a Linked List" LeetCode problem.

### 1. Problem Understanding:

The problem requires us to swap the values of the k-th node from the beginning and the k-th node from the end of a singly linked list.  Critically, we are swapping *values*, not the nodes themselves.

### 2. Approach / Intuition:

The core idea is to use two pointers: `f` (fast) and `s` (slow).

1.  **Find the k-th node from the beginning:** The `f` pointer is moved `k-1` steps forward. After this loop, `f` points to the k-th node from the beginning.  We store its value in `tem` and also store the node in `tt` for later value swapping.
2.  **Find the k-th node from the end:** After `f` reaches the k-th node from the beginning, we create another node called `tt` to hold the k-th node from the beginning, and advance `f` from `f.next`. Now, we simultaneously advance both `f` and `s` pointers until `f` reaches the end of the list. When `f` reaches the end, `s` will be pointing to the k-th node from the end of the list.
3.  **Swap the values:** Finally, we swap the values of the two identified nodes (the k-th from the beginning and the k-th from the end).
4.  **Return the head:** The head of the linked list remains unchanged, so we return the original `head`.

This approach is chosen because it allows us to find both the k-th node from the beginning and the k-th node from the end using a single traversal (after the initial `k-1` steps). It avoids the need to pre-calculate the length of the linked list, which would require another traversal.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly Linked List.  The code operates directly on the `ListNode` structure.
*   **Algorithms:** Two Pointer Technique.  The core algorithm relies on using two pointers, moving one ahead by `k-1` steps initially and then moving both until the first pointer reaches the end. This enables identifying the k-th nodes from the beginning and end, respectively.

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
    public ListNode swapNodes(ListNode head, int k) {
        if(head == null || head.next ==null) return head;
        ListNode s = head; // Slow pointer, will eventually point to k-th node from the end
        ListNode f = head; // Fast pointer, will initially point to k-th node from the beginning
        ListNode tt = null; // Temporary pointer to store the k-th node from the beginning

        for(int i = 1; i<k ; i++) f = f.next; // Move 'f' to the k-th node from the beginning

        int tem = f.val; // Store the value of the k-th node from the beginning
        tt = f; // store the k-th node from the beginning
        f = f.next; // Move `f` to the next node to prepare for the second loop

        while(f != null) // Move 'f' to the end, moving 's' simultaneously. When 'f' reaches the end, 's' will point to the k-th node from the end.
        {
            s = s.next;
            f = f.next;
        }

        tt.val = s.val; // Swap the values of the k-th node from the beginning and the k-th node from the end
        s.val = tem;
        return head; // Return the head of the linked list
    }
}
```

*   **`if(head == null || head.next ==null) return head;`**: Handles the base cases where the list is empty or has only one node. In these cases, there's nothing to swap, so the original `head` is returned.

*   **`ListNode s = head; ListNode f = head; ListNode tt = null;`**: Initializes the slow (`s`) and fast (`f`) pointers to the head of the list.  `tt` is initialized as null, and will later store the k-th node from the beginning.

*   **`for(int i = 1; i<k ; i++) f = f.next;`**: This loop advances the fast pointer `f` to the k-th node from the beginning of the list. The loop iterates `k-1` times.

*   **`int tem = f.val; tt = f; f = f.next;`**: Stores the value of the k-th node from the beginning in the `tem` variable. `tt` points to the k-th node from the beginning. `f` advances so we can proceed to finding the k-th node from the end.

*   **`while(f != null){ s = s.next; f = f.next; }`**:  This `while` loop moves both the `f` and `s` pointers simultaneously.  `f` continues to move towards the end of the list. When `f` reaches the end (becomes `null`), `s` will be pointing to the k-th node from the *end* of the list.

*   **`tt.val = s.val; s.val = tem;`**:  Finally, this section swaps the values of the k-th node from the beginning (stored in `tt` node) and the k-th node from the end (pointed to by `s`). It utilizes the temporary variable `tem` to store the value of the k-th node from the beginning before overwriting it.

*   **`return head;`**: The function returns the `head` of the linked list.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the number of nodes in the linked list. The code iterates through the list at most once in the `while` loop after the `for` loop. The initial `for` loop takes O(k) time, but since `k <= N`, this is still within O(N).
*   **Space Complexity:** O(1). The solution uses a constant amount of extra space for the pointers (`s`, `f`, `tt`) and the temporary variable (`tem`). It doesn't use any auxiliary data structures that scale with the input size.
