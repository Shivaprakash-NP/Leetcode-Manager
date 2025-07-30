class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0;
        int r = matrix.length-1;
        int ind = -1;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(matrix[m][0]<=target && matrix[m][matrix[0].length-1]>=target) 
            {
                ind = m;
                break;
            } 
            if(matrix[m][0]<=target) l = m+1;
            else r = m-1;
        }
        l = 0;
        r = matrix[0].length-1;
        if(ind == -1) return false;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(matrix[ind][m]==target) return true;
            if(matrix[ind][m]<target) l = m+1;
            else r = m-1;
        }
        return false;
    }
}