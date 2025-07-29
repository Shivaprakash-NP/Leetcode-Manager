class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dis = new int[n][n];
        for(int[] vv : dis) Arrays.fill(vv, Integer.MAX_VALUE);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[]{grid[0][0], 0, 0});
        dis[0][0] = grid[0][0];
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int cost = p[0];
            int i = p[1];
            int j = p[2];

            for(int[] d : dir) {
                int ni = i+d[0];
                int nj = j+d[1];
                if(ni>=0 && nj>=0 && ni<n && nj<n) {
                    int nc = Math.max(cost, grid[ni][nj]);
                    if(nc < dis[ni][nj]) {
                        dis[ni][nj] = nc;
                        q.offer(new int[]{nc, ni, nj});
                    }
                }
            }
        }

        return dis[n-1][n-1];
    }
}