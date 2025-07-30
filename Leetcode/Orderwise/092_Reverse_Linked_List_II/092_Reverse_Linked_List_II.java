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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null) return head;
        ListNode dum = new ListNode(0);
        dum.next = head;
        ListNode pre = dum; 
        for(int i = 1 ; i<left ; i++) pre = pre.next;
        ListNode cur = pre.next;
        ListNode prev = null;
        ListNode nxt = null;
        for(int i = left ; i<=right ; i++)
        {
            nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        pre.next.next = cur;
        pre.next = prev;
        return dum.next;
    }
}