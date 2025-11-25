class Solution {
    final int MOD = 1000000007;  // 10^9 + 7

    private int rec(int[][] grid, int i, int j, int tot, int k, int[][][] dp) {
        if(i == 0 && j == 0) return (grid[0][0]+tot)%k == 0 ? 1 : 0;

        int up = 0;
        int left = 0;

        tot += grid[i][j];
        tot %= k;
        if(dp[i][j][tot] != -1) return dp[i][j][tot];

        if(j > 0) left = rec(grid, i, j-1, tot, k, dp);
        if(i > 0) up = rec(grid, i-1, j, tot, k, dp);

        return dp[i][j][tot] = (left+up)%MOD;
    }

    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][k];

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                for(int x = 0; x<k; x++) {
                    dp[i][j][x] = -1;
                }
            }
        }

        return rec(grid, n-1, m-1, 0, k, dp);    
    }
}