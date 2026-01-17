class Solution {
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[][] dp = new long[m][n];
        for(int i = 0; i<n; i++) {
            dp[0][i] = points[0][i];
        }

        for(int i = 1; i<m; i++) {
            long[] lmax = new long[n];
            long[] rmax = new long[n];
            lmax[0] = dp[i-1][0];
            rmax[n-1] = dp[i-1][n-1];
            for(int k = 1; k<n; k++) lmax[k] = Math.max(lmax[k-1]-1, dp[i-1][k]);
            for(int k = n-2; k>=0; k--) rmax[k] = Math.max(rmax[k+1]-1, dp[i-1][k]);
            for(int j = 0; j<n; j++) {
                dp[i][j] = Math.max(lmax[j], rmax[j])+points[i][j];
            }
        }

        long max = 0;
        for(long v : dp[m-1]) max = Math.max(max, v);

        return max;
    }
}