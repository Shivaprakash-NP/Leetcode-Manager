class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];
        for(int i = 2*n-1 ; i>=0 ; i--) {
            int cur = nums[i%n];
            while(!st.isEmpty() && cur>=st.peek()) st.pop();
            if(i<n) ans[i] = (st.isEmpty())?-1:st.peek();
            st.push(cur);
        }
        return ans;
    }
}