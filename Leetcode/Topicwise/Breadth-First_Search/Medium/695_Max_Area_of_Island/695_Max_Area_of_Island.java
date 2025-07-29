class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int ans = 0;

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) {
                    Queue<int[]> q = new LinkedList<>();
                    int cursum = 0;
                    q.offer(new int[]{i, j});
                    vis[i][j] = true;
                    while(!q.isEmpty()) {
                        int[] p = q.poll();
                        cursum++;
                        for(int[] d : dir) {
                            int ni = d[0]+p[0];
                            int nj = d[1]+p[1];
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == 1 && !vis[ni][nj]) {
                                q.offer(new int[]{ni, nj});
                                vis[ni][nj] = true;
                            }
                        }
                    }

                    ans = Math.max(ans, cursum);
                }
            }
        }

        return ans;
    }
}