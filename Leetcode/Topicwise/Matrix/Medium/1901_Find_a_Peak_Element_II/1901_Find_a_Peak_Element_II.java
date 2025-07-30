class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int l = 0;
        int r = mat[0].length-1;
        int[] ans = {-1,-1};
        while(l<=r)
        {
            int m = (l+r)/2;
            int row = 0;
            for(int i = 1 ; i<mat.length ; i++) if(mat[row][m]<mat[i][m]) row = i;
            int le = (m>0)?mat[row][m-1]:-1;
            int ri = (m<mat[0].length-1)?mat[row][m+1]:-1;
            if(mat[row][m]>le && mat[row][m]>ri)
            {
                ans[0] = row;
                ans[1] = m;
                break;
            }
            else if(mat[row][m] < le) r = m-1;
            else l = m+1;
        }
        return ans;
    }
}