## LeetCode: Convert Binary Number in a Linked List to Integer - Solution Explanation

**1. Problem Understanding:**

The problem asks us to take a singly linked list where each node represents a bit in a binary number (0 or 1) and convert this binary representation into its decimal equivalent integer value.


**2. Approach / Intuition:**

The solution uses a bit manipulation approach to efficiently convert the binary number.  We iterate through the linked list, processing each bit.  The core idea is to build the decimal value iteratively using left bit shifts (`<<`) and bitwise OR (`|`).  Each time we encounter a new bit, we left-shift the current decimal value by one (effectively multiplying by 2) and then perform a bitwise OR with the new bit. This directly reflects how binary numbers are constructed: each subsequent bit represents a power of 2.

This approach was chosen because it's highly efficient for converting binary to decimal.  Other methods like string conversion or repeated multiplication would be slower and less elegant.


**3. Data Structures and Algorithms:**

* **Data Structures:** Singly Linked List (provided as input).  We implicitly use an integer to store and build the decimal result.
* **Algorithms:**  Iteration (using a `while` loop) and Bit Manipulation (using left shift `<<` and bitwise OR `|`).


**4. Code Walkthrough:**

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
    public int getDecimalValue(ListNode head) {
        int ans = head.val; // Initialize the result with the value of the first node.
        ListNode tem = head.next; // Create a temporary pointer to traverse the list.

        while(tem != null) { // Iterate through the rest of the linked list.
            int v = tem.val; // Get the value of the current node.
            ans = ans<<1;     // Left shift 'ans' by 1 bit (multiply by 2).
            ans = ans|v;      // Perform bitwise OR with the current bit 'v'.
            tem = tem.next;   // Move to the next node.
        }

        return ans; // Return the final decimal value.
    }
}
```

* **`int ans = head.val;`**: This line initializes the `ans` variable with the value of the first node in the linked list. This is our starting point for building the decimal representation.

* **`ListNode tem = head.next;`**: A temporary pointer `tem` is created to traverse the list from the second node onwards.

* **`while(tem != null)`**: This loop iterates through the remaining nodes of the linked list until the end is reached (`tem` becomes `null`).

* **`int v = tem.val;`**:  The value of the current node is assigned to `v`.

* **`ans = ans<<1;`**: This is the core of the binary to decimal conversion.  The left bit shift operator (`<<`) shifts the bits in `ans` one position to the left. This is equivalent to multiplying `ans` by 2.  For example, if `ans` is 101 (binary), after the shift it becomes 1010.

* **`ans = ans|v;`**: The bitwise OR operator (`|`) combines the shifted `ans` with the current bit `v`.  If `v` is 1, the least significant bit of `ans` becomes 1. If `v` is 0, the least significant bit remains unchanged.

* **`tem = tem.next;`**:  The temporary pointer moves to the next node in the list.

* **`return ans;`**:  Finally, the function returns the accumulated decimal value.


**5. Time and Space Complexity:**

* **Time Complexity:** O(N), where N is the number of nodes in the linked list. We iterate through the list once.

* **Space Complexity:** O(1). We only use a constant amount of extra space to store variables like `ans` and `tem`.  The space used does not depend on the input size.
