### Problem Understanding

The problem asks us to modify a given singly-linked list. We are provided with the head of this linked list and an integer array `nums`. Our task is to delete all nodes from the linked list whose `val` (value) is present in the `nums` array. After performing all necessary deletions, we must return the head of the modified linked list.

For example, if `nums = [1, 3]` and the linked list is `1 -> 2 -> 3 -> 4 -> 5`, the nodes with values 1 and 3 should be deleted, resulting in `2 -> 4 -> 5`.

### Approach / Intuition

The core challenge is to efficiently determine if a linked list node's value should be deleted. A naive approach would be to iterate through the `nums` array for each linked list node, which would be inefficient if `nums` is large.

Here's the intuition behind the chosen solution:

1.  **Efficient Lookup for Deletion Candidates:** To quickly check if a node's value is in the `nums` array, we can pre-process `nums` into a data structure that offers fast lookups. A `HashSet` is ideal for this, as `add` and `contains` operations take O(1) time on average. We'll populate a `HashSet` with all elements from `nums`.

2.  **Deleting Nodes in a Linked List:** When deleting a node in a singly-linked list, we need access to the *predecessor* node. If we want to delete `current_node`, we modify `predecessor_node.next` to point to `current_node.next`, effectively bypassing `current_node`.

3.  **Handling Head Deletion (Edge Case):** A common pitfall in linked list problems is handling the deletion of the head node. If the original head needs to be deleted, the new head of the list changes. To simplify this, we can use a "dummy" or "sentinel" node. This dummy node sits *before* the actual head of the list. By always operating on `dummy.next`, we ensure that every node (even the original head) has a preceding node, making the deletion logic uniform. The final result will be `dummy.next`.

Combining these ideas:
*   First, put all elements from `nums` into a `HashSet`.
*   Create a dummy node that points to the original head of the linked list.
*   Iterate through the linked list using a pointer, say `ptr`, which always points to the *predecessor* of the node currently being examined.
*   At each step, check `ptr.next` (the current node being examined). If its value is in the `HashSet`, delete it by setting `ptr.next = ptr.next.next`. Importantly, `ptr` *does not advance* in this case, because the *new* `ptr.next` (which was `ptr.next.next`) also needs to be checked against the set.
*   If `ptr.next`'s value is *not* in the `HashSet`, it means we keep this node. So, we advance `ptr` to `ptr.next` to move to the next node in the list.
*   Finally, return `dummy.next` as the head of the modified list.

### Data Structures and Algorithms

1.  **Data Structures:**
    *   `HashSet<Integer>`: Used to store the values from the `nums` array for O(1) average-time lookups.
    *   `ListNode`: The fundamental building block of the singly-linked list.

2.  **Algorithms:**
    *   **Hashing:** The underlying principle of `HashSet` for efficient storage and retrieval.
    *   **Linked List Traversal with Dummy Node:** A standard pattern for modifying linked lists, especially when deletions at the head are possible, using a single pointer (`ptr`) that acts as a "predecessor" or "current-before-next" pointer.

### Code Walkthrough

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
    public ListNode modifiedList(int[] nums, ListNode head) {
        // 1. Initialize a HashSet. This set will store all the values from the 'nums' array
        // that we need to delete from the linked list.
        Set<Integer> set = new HashSet<>();

        // 2. Populate the HashSet. Iterate through the 'nums' array and add each value to the set.
        // This allows for O(1) average-time lookups later to check if a node's value should be deleted.
        for(int v : nums) set.add(v);

        // 3. Create a dummy node (often called a sentinel node).
        // This node simplifies the logic for deleting nodes, especially if the original head itself
        // needs to be deleted. The dummy node's 'next' pointer is initialized to point to the
        // original head of the linked list.
        ListNode tem = new ListNode();
        tem.next = head;

        // 4. Initialize a pointer 'ptr' to the dummy node.
        // 'ptr' will always represent the node *before* the current node we are examining for deletion.
        ListNode ptr = tem;

        // 5. Traverse the linked list. The loop continues as long as 'ptr' has a 'next' node
        // (i.e., we haven't reached the end of the list).
        while(ptr.next != null) {
            // 6. Check if the value of the node *after* 'ptr' (i.e., 'ptr.next', the current node being examined)
            // is present in our 'set' of values to be deleted.
            if(set.contains(ptr.next.val)) {
                // 7. If the value is in the set, it means 'ptr.next' should be deleted.
                // To delete 'ptr.next', we bypass it by making 'ptr' point to the node *after* 'ptr.next'.
                // So, 'ptr.next' effectively gets removed from the list.
                // IMPORTANT: 'ptr' itself does NOT advance here. The new 'ptr.next' (which was 'ptr.next.next')
                // also needs to be checked against the set, as it might also contain a value to be deleted.
                ptr.next = ptr.next.next;
            } else {
                // 8. If the value of 'ptr.next' is NOT in the set, it means this node should be kept.
                // In this case, we advance 'ptr' to the next node ('ptr.next') to continue the traversal.
                ptr = ptr.next;
            }
        }

        // 9. After the loop finishes, 'tem.next' will point to the head of the modified linked list.
        // If all nodes were deleted, 'tem.next' will be null.
        return tem.next;
    }
}
```

### Time and Space Complexity

*   **Time Complexity:**
    *   **Populating the `HashSet`:** The `for` loop iterates `N` times, where `N` is the number of elements in the `nums` array. Each `add` operation to a `HashSet` takes O(1) time on average. Therefore, this step takes **O(N)** time.
    *   **Traversing the Linked List:** The `while` loop iterates through the linked list. Let `L` be the number of nodes in the linked list. In each iteration:
        *   `set.contains()` takes O(1) time on average.
        *   Pointer assignments take O(1) time.
        Each node in the linked list is visited at most once. Therefore, this step takes **O(L)** time.
    *   **Total Time Complexity:** Combining these steps, the overall time complexity is **O(N + L)**.

*   **Space Complexity:**
    *   **`HashSet`:** The `HashSet` stores unique elements from the `nums` array. In the worst case, all `N` elements in `nums` are unique, requiring space proportional to `N`. Therefore, this takes **O(N)** space.
    *   **Dummy Node:** The `tem` node is a single `ListNode` object, which consumes constant space, **O(1)**.
    *   **Total Space Complexity:** The dominant factor is the `HashSet`, so the overall space complexity is **O(N)**.