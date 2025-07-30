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
    public ListNode swapNodes(ListNode head, int k) {
        if(head == null || head.next ==null) return head;
        ListNode s = head;
        ListNode f = head;
        ListNode tt = null;
        for(int i = 1; i<k ; i++) f = f.next;
        int tem = f.val;
        tt = f;
        f = f.next;
        while(f != null)
        {
            s = s.next;
            f = f.next;
        }
        tt.val = s.val;
        s.val = tem;
        return head;
    }
}