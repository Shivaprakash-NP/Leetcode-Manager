class Solution {
    public int countIslands(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        
        boolean[][] vis = new boolean[n][m];
        int[][] dir = {{1,0} , {-1,0} , {0,1} , {0,-1}};
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] != 0 && !vis[i][j]) {
                    Queue<int[]> q = new LinkedList<>();
                    int sum = 0;
                    q.offer(new int[]{i, j});
                    vis[i][j] = true;
                    sum+=grid[i][j];

                    while(!q.isEmpty()) {
                        int[] p = q.poll();
                        for(int[] d : dir) {
                            int ni = d[0]+p[0];
                            int nj = d[1]+p[1];
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] != 0 && !vis[ni][nj]) {
                                sum+=grid[ni][nj];
                                vis[ni][nj] = true;
                                q.offer(new int[]{ni, nj});
                            }
                        }
                    }

                    if(sum%k == 0) ans++;
                }
            }
        }

        return ans;
    }
}