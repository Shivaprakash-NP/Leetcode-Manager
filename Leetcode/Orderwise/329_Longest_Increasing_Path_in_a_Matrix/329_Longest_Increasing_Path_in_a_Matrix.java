/*
- Just DFS + Memo
- use global DP, while solving last time you used local dp for every call which is not needed 
*/ 

class Solution {
    private int dfs(int i, int j, int[][] mat, int[][] dp) {
        if(dp[i][j] != -1) return dp[i][j];

        int max = 0;
        //left
        if(j > 0 && mat[i][j] < mat[i][j-1]) max = Math.max(max, dfs(i, j-1, mat, dp)+1);
        //up 
        if(i > 0 && mat[i][j] < mat[i-1][j]) max = Math.max(max, dfs(i-1, j, mat, dp)+1);

        //down
        if(i < mat.length-1 && mat[i][j] < mat[i+1][j]) max= Math.max(max, dfs(i+1, j, mat, dp)+1);
        //right
        if(j < mat[0].length-1 && mat[i][j] < mat[i][j+1]) max = Math.max(max, dfs(i, j+1, mat, dp)+1);

        return dp[i][j] = max;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        for(int[] indp : dp) Arrays.fill(indp, -1);

        for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {
                max = Math.max(max, dfs(i, j, matrix, dp)+1);
            }
        }

        return max;
    }
}