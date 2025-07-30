class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0 ; i<n-1 ; i++)
        {
            for(int j = i+1 ; j<n ; j++)
            {
                int tem = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tem;
            }
        }
        for(int i = 0 ; i<n ; i++)
        {
            int l = 0;
            int r = n-1;
            while(l<r)
            {
                int tem = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = tem;
                l++;
                r--;
            }
        }
    }
}