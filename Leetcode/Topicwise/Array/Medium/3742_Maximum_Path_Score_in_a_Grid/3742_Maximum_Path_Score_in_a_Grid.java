class Solution {
    private int rec(int i, int j, int[][] grid, int k, int[][][] dp) {
        if(i == 0 && j == 0) return 0;

        if(grid[i][j] != 0) k--;
        if(k < 0) return -1;
        if(dp[i][j][k] != -2) return dp[i][j][k];
        
        int left = -1;
        int up = -1;
        
        if(j>0) left = rec(i, j-1, grid, k, dp);
        if(i>0) up = rec(i-1, j, grid, k, dp);

        if(up == left && up == -1) return dp[i][j][k] = -1;
        dp[i][j][k] = Math.max(up, left)+grid[i][j];
        return dp[i][j][k];
    }
    
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][][] dp = new int[m+1][n+1][k+1];
        for(int[][] dd : dp) for(int[] d : dd) Arrays.fill(d, -2);

        int ans =  rec(m-1, n-1, grid, k, dp);
        return ans;
    }
}