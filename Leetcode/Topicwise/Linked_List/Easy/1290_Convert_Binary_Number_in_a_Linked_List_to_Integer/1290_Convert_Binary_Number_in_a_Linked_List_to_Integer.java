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
    public int getDecimalValue(ListNode head) {
        int ans = head.val;
        ListNode tem = head.next;

        while(tem != null) {
            int v = tem.val;
            ans = ans<<1;
            ans = ans|v;
            tem = tem.next;
        }

        return ans;
    }
}