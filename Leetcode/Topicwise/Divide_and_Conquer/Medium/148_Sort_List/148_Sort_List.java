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
    private ListNode findmiddle(ListNode head)
    {
        ListNode s = head;
        ListNode f = head;
        while(f.next != null && f.next.next != null)
        {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    private ListNode merge(ListNode lhead , ListNode rhead)
    {
        ListNode dum = new ListNode(-1);
        ListNode tem = dum;
        while(lhead != null && rhead != null)
        {
            if(lhead.val < rhead.val) 
            {
                tem.next = lhead;
                lhead = lhead.next;
            }
            else
            {
                tem.next = rhead;
                rhead = rhead.next;
            }
            tem = tem.next;
        }
        if(lhead == null) tem.next = rhead;
        else tem.next = lhead;
        return dum.next;
    }
    private ListNode ms(ListNode head)
    {
        if(head == null || head.next == null) return head;
        ListNode m = findmiddle(head);
        ListNode lhead = head;
        ListNode rhead = m.next;
        m.next = null;
        lhead = ms(lhead);
        rhead = ms(rhead);
        return merge(lhead , rhead);
    }
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        return ms(head);
    }
}