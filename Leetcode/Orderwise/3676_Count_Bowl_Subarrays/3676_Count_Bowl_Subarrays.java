class Solution {
    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        long ans = 0;
        Stack<Integer> st = new Stack<>();

        int[] nge = new int[n];
        int[] pge = new int[n];

        for(int i = 0; i<n; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            pge[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();
        for(int i = n-1; i>=0; i--) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            nge[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        for(int i = 0; i<n; i++) {
            if(pge[i] == -1 || nge[i] == -1) continue;
            if(nge[i]-pge[i]+1 >= 3) ans++;
        }

        return ans;
    }
}