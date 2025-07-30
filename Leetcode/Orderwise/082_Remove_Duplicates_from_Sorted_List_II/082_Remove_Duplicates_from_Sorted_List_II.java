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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode s = head;
        ListNode f = head.next;
        while(f != null)
        {
            if(s.val != f.val) 
            {
                pre = s;
                s = s.next;
                f = f.next;
            }
            else
            {
                while(f != null && f.val == s.val) f = f.next;
                if(pre != null) pre.next = f;
                else head = f;
                s = f;
                if(f!=null) f = f.next;
            }
        }
        return head;
    }
}