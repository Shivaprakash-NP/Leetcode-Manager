class Solution {
    public int findMaxFish(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dir = {{1,0}, {-1,0},{ 0,1}, {0,-1}};
        int ans = 0;

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] > 0) {
                    int sum = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    sum+=grid[i][j];
                    grid[i][j] = -1;

                    while(!q.isEmpty()) {
                        int[] p = q.poll();
                        for(int[] d : dir) {
                            int ni = p[0]+d[0];
                            int nj = p[1]+d[1];
                            if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj] >0) {
                                sum+=grid[ni][nj];
                                grid[ni][nj] = -1;
                                q.offer(new int[]{ni, nj});
                            }
                        }
                    }
                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans;
    }
}