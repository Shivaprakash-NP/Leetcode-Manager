class Solution {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        int[] pre_small = new int[n];
        int[] nxt_small = new int[n];
        int[] pre_gre = new int[n];
        int[] nxt_gre = new int[n];
        Stack<Integer> st = new Stack<>();

        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && nums[st.peek()] > nums[i]) st.pop();
            pre_small[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();

        for(int i = n-1 ; i >= 0 ; i--) {
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();
            nxt_small[i] = (st.isEmpty())?n:st.peek();
            st.push(i);
        }

        st.clear();

        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            pre_gre[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();

        for(int i = n-1 ; i >= 0 ; i--) {
            while(!st.isEmpty() && nums[st.peek()] <= nums[i]) st.pop();
            nxt_gre[i] = (st.isEmpty())?n:st.peek();
            st.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long l1 = i - pre_small[i];
            long r1 = nxt_small[i] - i;
            long l2 = i - pre_gre[i];
            long r2 = nxt_gre[i] - i;

            ans += l2 * r2 * nums[i]; 
            ans -= l1 * r1 * nums[i];
        }
        return ans;
    }
}