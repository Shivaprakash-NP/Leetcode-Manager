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
    public boolean isPalindrome(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while(f.next != null && f.next.next != null)
        {
            s = s.next;
            f = f.next.next;
        }
        ListNode pre = null;
        ListNode cur = s.next;
        while(cur != null)
        {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        ListNode st = head;
        ListNode nxt = pre;
        while(st != null && nxt != null)
        {
            if(st.val != nxt.val) return false;
            st = st.next;
            nxt = nxt.next;
        }
        return true;
    }
}