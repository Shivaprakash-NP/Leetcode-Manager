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
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();

        for(int v : nums) set.add(v);

        ListNode tem = new ListNode();
        tem.next = head;

        ListNode ptr = tem;

        while(ptr.next != null) {
            if(set.contains(ptr.next.val)) ptr.next = ptr.next.next;
            else ptr = ptr.next;
        }

        return tem.next;
    }
}