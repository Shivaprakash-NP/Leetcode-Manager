class Solution {
    private int maxrect(int[] hei) {
        int n = hei.length;
        int[] pse = new int[n];
        int[] nse = new int[n];
        Stack<Integer> st = new Stack<>();

        for(int i = 0 ; i < n ; i++) {
            while(!st.isEmpty() && hei[st.peek()] >= hei[i]) st.pop();
            pse[i] = (st.isEmpty())?-1:st.peek();
            st.push(i);
        }

        st.clear();

        for(int i = n-1 ; i >= 0 ; i--) {
            while(!st.isEmpty() && hei[st.peek()] >= hei[i]) st.pop();
            nse[i] = (st.isEmpty())?n:st.peek();
            st.push(i);
        }

        int max = 0;
        for(int i = 0 ; i < n ; i++) {
            int h = hei[i];
            int w = (nse[i]-pse[i]-1);
            max = Math.max(max , h*w);
        }

        return max;
    }
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] mat = new int[n][m];
        for(int i = 0 ; i<m ; i++) mat[0][i] = (matrix[0][i]=='0')?0:1;
        for(int i = 0 ; i<m ; i++) {
            for(int j = 1 ; j<n ; j++) {
                int v = (matrix[j][i]=='0')?0:1;
                mat[j][i] = (mat[j-1][i]+v)*v;
            }
        }

        int max = 0;

        for(int[] hei : mat) {
            max = Math.max(max , maxrect(hei));
        }

        return max;
    }
}