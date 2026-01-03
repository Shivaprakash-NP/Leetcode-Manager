class Solution {
    int MOD = (int)1e9 + 7;
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] pre = new long[n+1];
        for(int i = 0; i<n; i++) pre[i+1] = pre[i]+(long)nums[i];

        int[] nse = new int[n];
        int[] pse = new int[n];

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i<n; i++) {
            while(!st.isEmpty() && nums[i] <= nums[st.peek()]) st.pop();
            pse[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        st.clear();
        for(int i = n-1; i>=0; i--) {
            while(!st.isEmpty() && nums[i] <= nums[st.peek()]) st.pop();
            nse[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        long max = 0;
        for(int i = 0; i<n; i++) {
            int l = pse[i];
            int r = nse[i];
            long sum = pre[r]-pre[l+1];
            max = Math.max(max, sum*(long)nums[i]);
        }

        return (int)(max%MOD);
    }
}