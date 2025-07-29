class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        int[][] ans = new int[n][m];

        for(int i = 0; i<n ; i++) {
            for(int j = 0; j<m ; j++) {
                if(isWater[i][j] == 1) {
                    vis[i][j] = true;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while(!q.isEmpty()) {
            int[] node = q.poll();
            int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for(int[] d : dir) {
                int ni = node[0]+d[0];
                int nj = node[1]+d[1];
                if(ni>=0 && nj>=0 && ni<n && nj<m && !vis[ni][nj]) {
                    vis[ni][nj] = true;
                    ans[ni][nj] = ans[node[0]][node[1]] + 1;
                    q.offer(new int[]{ni, nj});
                }
            }
        }
        return ans;
    }
}