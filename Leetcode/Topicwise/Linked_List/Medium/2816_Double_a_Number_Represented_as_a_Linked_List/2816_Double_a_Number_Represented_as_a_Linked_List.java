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