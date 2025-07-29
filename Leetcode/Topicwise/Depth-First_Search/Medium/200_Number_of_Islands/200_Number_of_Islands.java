class Solution {
    public int numIslands(char[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        boolean[][] vis = new boolean[n][m];

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] == '1' && !vis[i][j]) {
                    ans++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    vis[i][j] = true;
                    while(!q.isEmpty()) {
                        int[] p = q.poll();

                        for(int[] d : dir) {
                            int ni = p[0]+d[0];
                            int nj = p[1]+d[1];
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] == '1' && !vis[ni][nj]) {
                                q.offer(new int[]{ni, nj});
                                vis[ni][nj] = true;
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}