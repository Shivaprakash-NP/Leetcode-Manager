```markdown
## Merge Two Sorted Lists Problem Explanation

This document provides a detailed explanation of a Java solution to the LeetCode problem "Merge Two Sorted Lists".

### 1. Problem Understanding

The problem asks us to merge two sorted linked lists into a single sorted linked list. The input consists of the heads of the two sorted lists, `list1` and `list2`. The output should be the head of the merged sorted list, containing all nodes from both input lists in non-decreasing order.

### 2. Approach / Intuition

The core idea is to iteratively compare the current nodes of the two input lists (`list1` and `list2`) and append the smaller value to the merged list.  We use a `while` loop to continue this process until one of the lists becomes empty. After that, we simply append the remaining nodes of the non-empty list to the tail of the merged list.

The key to this approach is maintaining pointers to both input lists. We use a temporary node to keep track of the last node that we modified. We also handle edge cases where either list is initially empty.  We optimize a bit by initially ensuring `l1` is the smaller of the two lists to avoid having to instantiate a new list object.

This approach is efficient because it only requires traversing the lists once, and we're directly manipulating the node pointers to build the merged list.  No extra memory for sorting is required.

### 3. Data Structures and Algorithms

*   **Data Structure:** Singly-Linked List. The problem inherently involves linked lists.
*   **Algorithm:** Iterative traversal and pointer manipulation. We traverse both lists simultaneously and modify the `next` pointers to create the merged list.
    The comparisons use the concept of sorting, but it's closer to a merge operation rather than a full-fledged sorting algorithm.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l1 = list1;
        ListNode l2 = list2;
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val > l2.val)
        {
            ListNode tem = l1;
            l1 = l2;
            l2 = tem;
        }
        ListNode ans = l1;
        while(l1 != null && l2 != null)
        {
            ListNode tem = l1;
            while(l1 != null && l1.val <=l2.val)
            {
                tem = l1;
                l1 = l1.next;
            }

            tem.next = l2;
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;

        }
        return ans;
    }
}
```

*   **`ListNode l1 = list1;` and `ListNode l2 = list2;`**: These lines initialize local pointers `l1` and `l2` to the heads of the input lists.  This avoids modifying the original list head references.
*   **`if(l1 == null) return l2;` and `if(l2 == null) return l1;`**: These are base cases. If either list is empty, return the other list.
*   **`if(l1.val > l2.val) { ... }`**:  This block swaps `l1` and `l2` if the value of `l1`'s head is greater than `l2`'s head. This optimization ensures that `l1` initially points to the list with the smaller head value, which will become the head of the merged list.
*   **`ListNode ans = l1;`**:  `ans` is initialized to `l1`, because after the initial check, we know that `l1` is going to be the initial head of the merged list.
*   **`while(l1 != null && l2 != null) { ... }`**: This loop continues as long as both lists have nodes remaining.
    *   **`ListNode tem = l1;`**: Used to keep track of the last node of list 1 that was appended to our answer.
    *   **`while(l1 != null && l1.val <= l2.val) { ... }`**: This inner loop iterates through `l1` until it finds a node whose value is greater than `l2.val`. It essentially finds the sequence of nodes in `l1` that are smaller than or equal to the current node in `l2`.
        *   **`tem = l1;`**: updates the temporary node, so that after the inner while loop finishes, it points to the last `l1` node whose value is <= `l2`'s value.
        *   **`l1 = l1.next;`**: Moves `l1` to the next node.
    *   **`tem.next = l2;`**: This line appends the current node of `l2` to the end of the sorted portion of the merged list by assigning it to the next pointer of `tem`, which is the tail of the sublist of `l1` we've found to be smaller than `l2`.
    *   **`ListNode temp = l1;`**: `temp` is used to keep track of the remaining nodes in `l1` that are > `l2.val`.
    *   **`l1 = l2;`**: makes `l1` point to the smaller list (the rest of `l2`)
    *   **`l2 = temp;`**: makes `l2` point to the larger list (the rest of `l1`)
*   **`return ans;`**: Returns the head of the merged sorted list.

### 5. Time and Space Complexity

*   **Time Complexity:** O(m + n), where 'm' and 'n' are the lengths of the two input lists. In the worst case, we need to traverse both lists entirely to merge them.
*   **Space Complexity:** O(1).  We are only using a constant amount of extra space for pointers ( `l1`, `l2`, `tem`, `temp`, `ans`).  We modify the existing list nodes in place.
```