class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        HashMap<Integer , Integer> map = new HashMap<>();
        for(int i = nums2.length-1 ; i>=0 ; i--) {
            while(!st.isEmpty() && nums2[i]>=st.peek()) st.pop();
            if(st.isEmpty()) map.put(nums2[i] , -1);
            else map.put(nums2[i] , st.peek());
            st.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        int i = 0;
        for(int v : nums1) {
            ans[i++] = map.get(v);
        }
        return ans;
    }
}