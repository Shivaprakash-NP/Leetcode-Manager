class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int cnt = 0;

        for(int i = n-1; i>=0; i--) {
            while(!st.isEmpty() && st.peek()>nums[i]) st.pop();
            int nxt_min = (st.isEmpty())?-1:st.peek();
            st.push(nums[i]);

            if(nums[i] != 0 && nums[i] != nxt_min) cnt++;
        }

        return cnt;
    }
}