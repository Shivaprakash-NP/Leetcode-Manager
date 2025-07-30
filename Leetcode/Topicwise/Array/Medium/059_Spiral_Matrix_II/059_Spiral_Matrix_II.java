class Solution {
    public int[][] generateMatrix(int n) {
        int l = 0;
        int r = n-1;
        int t = 0;
        int b = n-1;
        int val = 1;
        int[][] matrix = new int[n][n];
        while(l<=r && t<=b)
        {
            for(int i = l ; i<=r ; i++ , val++)
                matrix[t][i] = val;
            t++;
            for(int i = t ; i<=b ; i++ , val++)
                matrix[i][r] = val;
            r--;
            if(t<=b)
            {
                for(int i = r ; i>=l ; i-- , val++)
                    matrix[b][i] = val;
                b--;
            }
            if(l<=r)
            {
                for(int i = b ; i>=t ; i-- , val++)
                    matrix[i][l] = val;
                l++;
            }
        }
        return matrix;
    }
}