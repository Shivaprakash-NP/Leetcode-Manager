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
    public int pairSum(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        ListNode p = null;
        while(f != null)
        {
            p = s;
            s = s.next;
            f = f.next.next;
        }
        p.next = null;
        ListNode pre = null;
        while(s != null)
        {
            ListNode nxt = s.next;
            s.next = pre;
            pre = s;
            s = nxt;
        }
        int sum = Integer.MIN_VALUE;
        while(head!=null)
        {
            sum = Math.max(sum , head.val + pre.val);
            head = head.next;
            pre = pre.next;
        }
        return sum;
    }
}