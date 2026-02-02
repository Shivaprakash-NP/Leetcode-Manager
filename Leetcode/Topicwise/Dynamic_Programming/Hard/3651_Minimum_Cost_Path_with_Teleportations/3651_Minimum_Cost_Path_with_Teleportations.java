class Solution {
    private int bs(List<int[]> list, int t) {
        int l = 0;
        int r = list.size()-1;
        int ans = -1;

        while(l <= r) {
            int m = l + (r-l)/2;
            if(list.get(m)[0] >= t) {
                ans = m;
                r = m-1;
            } else l = m+1;
        }

        return ans;
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[k+1][m][n];
        for(int[][] layer : dp) for(int[] row : layer) Arrays.fill(row, Integer.MAX_VALUE / 2);

        for(int i = 0; i<=k; i++) dp[i][0][0] = 0;

        // list -> {grid[i][j], precost};
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i<=k; i++) {
            int len = list.size();
            int[] sufMin = new int[len];
            Collections.sort(list, (a, b) -> a[0] - b[0]);
            if(!list.isEmpty()) sufMin[len-1] = list.get(len-1)[1];
            for(int y = len-2; y>=0; y--) sufMin[y] = Math.min(sufMin[y+1], list.get(y)[1]);

            List<int[]> local = new ArrayList<>();

            for(int a = 0; a<m; a++) {
                for(int b = 0; b<n; b++) {
                    int f_left = (b>0 ? dp[i][a][b-1] : Integer.MAX_VALUE / 2);
                    int f_top = (a>0 ? dp[i][a-1][b] : Integer.MAX_VALUE / 2);
                    dp[i][a][b] = Math.min(dp[i][a][b], Math.min(f_left, f_top) + grid[a][b]);

                    int ind = bs(list, grid[a][b]);
                    if(ind != -1) dp[i][a][b] = Math.min(dp[i][a][b], sufMin[ind]);
                    local.add(new int[]{grid[a][b], dp[i][a][b]});
                }
            }

            list = local;
        }

        return dp[k][m-1][n-1];
    }
}