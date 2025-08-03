class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if( i == 0 && j == 0) continue;
                int val1 = (i-1 >= 0)?dp[i-1][j]:Integer.MAX_VALUE;
                int val2 = (j-1 >= 0)?dp[i][j-1]:Integer.MAX_VALUE;
                dp[i][j] = Math.min(val1, val2)+grid[i][j];
            }
        }

        return dp[n-1][m-1];
    }
}