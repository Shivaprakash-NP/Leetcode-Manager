class Solution {
    public int minimumObstacles(int[][] grid) {
        int INF = Integer.MAX_VALUE;
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}}; 
        for(int[] v : vis) Arrays.fill(v, INF);

        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{0, 0, 0});
        while(!q.isEmpty()) {
            int[] p = q.removeFirst();
            int dis = p[0];
            int i = p[1];
            int j = p[2];

            for(int[] d : dir) {
                int ni = i+d[0];
                int nj = j+d[1];
                if(ni>=0 && nj>=0 && ni<n && nj<m) {
                    int nc = dis+grid[ni][nj];
                    if(nc < vis[ni][nj]) {
                        if(nc == dis) q.addFirst(new int[]{nc, ni, nj});
                        else q.addLast(new int[]{nc, ni, nj});
                        vis[ni][nj] = nc;
                    }
                }
            }
        }

        return vis[n-1][m-1];
    }
}