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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode se = head.next;
        ListNode sehead = se;
        ListNode tem = head;
        while(tem.next != null && tem.next.next != null)
        {
            ListNode t = tem.next.next;
            se.next = se.next.next;
            tem.next = t;
            tem = tem.next;
            se = se.next;
        }
        tem.next = sehead;
        return head;
    }
}