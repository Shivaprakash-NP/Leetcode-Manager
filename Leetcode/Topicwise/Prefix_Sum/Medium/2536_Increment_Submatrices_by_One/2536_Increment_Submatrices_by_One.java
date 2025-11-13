class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] mat = new int[n][n];
        for(int[] q : queries) {
            int x1 = q[0];
            int y1 = q[1];
            int x2 = q[2];
            int y2 = q[3];

            mat[x1][y1]++;
            if(x2 < n-1) mat[x2+1][y1]--;
            if(y2 < n-1) mat[x1][y2+1]--;
            if(x2 < n-1 && y2 < n-1) mat[x2+1][y2+1]++;
        }

        for(int i = 1; i<n; i++) {
            mat[0][i] += mat[0][i-1];
            mat[i][0] += mat[i-1][0];
        }

        for(int i = 1; i<n; i++) {
            for(int j = 1; j<n; j++) {
                mat[i][j] += mat[i-1][j]+mat[i][j-1]-mat[i-1][j-1];
            }
        }

        return mat;
    }
}