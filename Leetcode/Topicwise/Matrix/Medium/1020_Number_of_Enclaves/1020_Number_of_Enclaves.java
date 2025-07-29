class Solution {
    private void dfs(int i, int j, int[][] mat) {
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length || mat[i][j] != 1) return;
        mat[i][j] = -1; 
        dfs(i+1, j, mat);
        dfs(i-1, j, mat);
        dfs(i, j+1, mat);
        dfs(i, j-1, mat);
    }

    public int numEnclaves(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[0][i] == 1) dfs(0, i, grid);
            if (grid[n-1][i] == 1) dfs(n-1, i, grid);
        }

        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(i, 0, grid);
            if (grid[i][m-1] == 1) dfs(i, m-1, grid);
        }

        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 1) count++;

        return count;
    }
}
