class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(0);
        ListNode lesst = less;

        ListNode high = new ListNode(0);
        ListNode hight = high;

        while (head != null) {
            if (head.val < x) {
                lesst.next = head;
                lesst = lesst.next;
            } else {
                hight.next = head;
                hight = hight.next;
            }
            head = head.next;
        }

        hight.next = null;  
        lesst.next = high.next;

        return less.next;
    }
}
