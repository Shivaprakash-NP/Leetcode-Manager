class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pre_small = new int[n];
        int[] nxt_small = new int[n];
        int max = 0;

        Stack<Integer> st = new Stack<>();

        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            pre_small[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();

        for(int i = n-1 ; i>=0 ; i--) {
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]) st.pop();
            nxt_small[i] = (st.isEmpty())?n:st.peek();
            st.push(i);   
        }

        for(int i = 0 ; i<n ; i++) {
            int h = heights[i];
            int w = nxt_small[i]-pre_small[i]-1;
            max = Math.max(max , h*w);
        }
        return max;
    }
}