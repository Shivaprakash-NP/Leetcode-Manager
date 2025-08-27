class Solution {
    int[][] dis = {{1,1}, {1,-1}, {-1,-1}, {-1,1}};

    private int find(int i, int j, int d, boolean b, int t, int[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        int ni = i+dis[d][0];
        int nj = j+dis[d][1];
        if(ni<0 || nj<0 || ni>=n || nj>=m || grid[ni][nj] != t) return 0;

        int noturn = find(ni, nj, d, b, 2-t, grid);
        int turn = 0;
        if(b) {
            turn = find(ni, nj, (d+1)%4, !b, 2-t, grid);
        }

        return Math.max(turn, noturn)+1;
    }

    public int lenOfVDiagonal(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] == 1) {
                    for(int d = 0; d<4; d++) {
                        ans = Math.max(ans, 1+find(i, j, d, true, 2, grid));
                    }
                }
            }
        }

        return ans;
    }
}