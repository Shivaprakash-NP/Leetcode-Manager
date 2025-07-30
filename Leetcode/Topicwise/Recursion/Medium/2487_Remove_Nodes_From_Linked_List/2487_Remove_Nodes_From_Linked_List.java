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
    public ListNode removeNodes(ListNode head) {
        head = reverse(head);
        ListNode s = head;
        while(s != null)
        {
            ListNode newnode = s.next;
            while(newnode != null && newnode.val < s.val) newnode = newnode.next;
            s.next = newnode;
            s = s.next;
        }
        return reverse(head);
    }
}