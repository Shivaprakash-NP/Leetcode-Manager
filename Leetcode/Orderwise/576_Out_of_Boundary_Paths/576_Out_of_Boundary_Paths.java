class Solution {
    int mod = (int)1e9 + 7;
    private int dfs(int x, int y, int m, int n, int max, int[][][] dp) {
        if(x < 0 || y < 0 || x >= m || y >= n) return 1;
        if(max == 0) return 0;

        if(dp[x][y][max] != -1) return dp[x][y][max];
        int sum = 0;
        sum = (sum + dfs(x-1, y, m, n, max-1, dp))%mod;
        sum = (sum + dfs(x, y-1, m, n, max-1, dp))%mod;
        sum = (sum + dfs(x+1, y, m, n, max-1, dp))%mod;
        sum = (sum + dfs(x, y+1, m, n, max-1, dp))%mod;

        return dp[x][y][max] = sum%mod;
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove+1];
        for(int[][] d : dp) for(int[] indp : d) Arrays.fill(indp, -1);

        return dfs(startRow, startColumn, m, n, maxMove, dp);
    }
}