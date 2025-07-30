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
    private ListNode nxt(ListNode head)
    {
        if(head == null || head.next == null) return head;
        ListNode newhead = nxt(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return newhead;
    }
    public ListNode reverseList(ListNode head) {
        return(nxt(head));
    }
}