```markdown
## Doubling a Number Represented as a Linked List

### 1. Problem Understanding:

The problem requires us to double a non-negative number represented as a singly-linked list. Each node in the linked list represents a digit of the number. We need to return the head of the linked list representing the doubled number.  For example, if the input linked list represents the number 123, the output linked list should represent the number 246.

### 2. Approach / Intuition:

The straightforward approach of converting the linked list to an integer, doubling it, and then converting it back to a linked list can lead to overflow issues when dealing with large numbers. Therefore, we need to perform the doubling digit by digit, simulating manual multiplication.

The chosen approach involves the following steps:

1.  **Reverse the linked list:**  Processing from the least significant digit (tail) to the most significant digit (head) simplifies the carry propagation.  This avoids the complexity of needing to know the lengths beforehand or using recursion.
2.  **Iterate and Double:**  Iterate through the reversed linked list, doubling each digit and adding the carry from the previous digit. Calculate the new digit value and update the carry.
3.  **Handle the final carry:** After processing all digits, if there's a remaining carry, create a new node to store it at the end (tail) of the reversed list.
4.  **Reverse the linked list again:**  Reverse the linked list to restore the original order, resulting in the doubled number.

This approach avoids integer overflows and performs the doubling operation directly on the linked list, simulating the manual multiplication process.

### 3. Data Structures and Algorithms:

*   **Data Structure:** Singly-linked list (`ListNode`).
*   **Algorithm:**
    *   **Reversal:** Iterative linked list reversal.
    *   **Iteration:**  Iterative traversal of the linked list.
    *   **Modular Arithmetic:** Used for calculating the digit value and carry.

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
    private ListNode reverse(ListNode head)
    {
        ListNode pre = null;
        while(head != null)
        {
            ListNode nxt = head.next;
            head.next = pre;
            pre = head;
            head = nxt;
        }
        return pre;
    }
    public ListNode doubleIt(ListNode head) {
        head = reverse(head);
        ListNode tem = head;
        int c = 0;
        while(tem != null)
        {
            int sum = tem.val+tem.val+c;
            c = sum/10;
            tem.val = sum%10;
            if(tem.next == null) break;
            tem = tem.next;
        }
        if(c!=0) tem.next = new ListNode(c);
        return reverse(head);
    }
}
```

*   **`reverse(ListNode head)`:**
    *   This function reverses a singly-linked list.
    *   `pre`:  Keeps track of the previous node. Initialized to `null`.
    *   `head`:  The current node being processed.
    *   `nxt`: Stores the next node to avoid losing it when reversing.
    *   The `while` loop iterates through the list. In each iteration:
        *   `nxt = head.next`: Stores the next node.
        *   `head.next = pre`: Reverses the link of the current node, pointing it to the previous node.
        *   `pre = head`:  Moves `pre` to the current node.
        *   `head = nxt`:  Moves `head` to the next node.
    *   Finally, it returns `pre`, which is the new head of the reversed list.

*   **`doubleIt(ListNode head)`:**
    *   This is the main function that doubles the number represented by the linked list.
    *   `head = reverse(head)`: Reverses the input linked list using the `reverse` function.
    *   `ListNode tem = head`: `tem` is a temporary pointer to traverse the reversed linked list.
    *   `int c = 0`: `c` stores the carry-over value from the previous digit. Initialized to 0.
    *   The `while` loop iterates through the reversed linked list:
        *   `int sum = tem.val+tem.val+c`: Calculates the sum of the doubled digit value and the carry.
        *   `c = sum/10`: Calculates the new carry (the tens digit of the sum).
        *   `tem.val = sum%10`: Updates the digit value to the units digit of the sum.
        *   `if(tem.next == null) break;`: Optimization to exit early if there's no next node.
        *   `tem = tem.next`: Moves to the next node in the list.
    *   `if(c!=0) tem.next = new ListNode(c)`: If there's a non-zero carry after processing all digits, a new node is created with the carry value and appended to the end of the list.
    *   `return reverse(head)`: Reverses the linked list again to restore the original order, and returns the head of the resulting linked list.

### 5. Time and Space Complexity:

*   **Time Complexity:** O(N), where N is the number of nodes in the linked list.  The linked list is traversed at most three times (once for each reverse and once for the doubling operation).
*   **Space Complexity:** O(1). The `reverse` function operates in place, and only a few extra variables are used.  The additional node for the final carry contributes a constant amount of extra space. The space complexity does not depend on the input size N.
