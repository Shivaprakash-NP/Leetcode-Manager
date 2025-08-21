class Solution {
    public int minimumArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int top = n;
        int bot = 0;
        int l = m;
        int r = 0;

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] == 1) {
                    top = Math.min(top, i);
                    bot = Math.max(bot, i);
                    l = Math.min(l, j);
                    r = Math.max(r, j);
                }
            }
        }

        return (bot-top+1)*(r-l+1);
    }
}