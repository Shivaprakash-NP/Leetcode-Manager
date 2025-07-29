class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] vis = new int[n][m];

        for(int[] inarr : vis) Arrays.fill(inarr, Integer.MAX_VALUE);
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        q.offer(new int[]{0, 0, 0});
        vis[0][0] = 0;

        while(!q.isEmpty()) {
            int[] np = q.poll();
            int cost = np[0];
            int i = np[1];
            int j = np[2];

            for(int[] d : dir) {
                int ni = i+d[0];
                int nj = j+d[1];
                if(ni>=0 && nj>=0 && ni<n && nj<m) {
                    int ncost = Math.max(cost, Math.abs(heights[i][j] - heights[ni][nj]));
                    if(vis[ni][nj] > ncost) {
                        vis[ni][nj] = ncost;
                        q.offer(new int[]{ncost, ni, nj});
                    }
                }
            }
        }

        return vis[n-1][m-1];
    }
}