class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int x = entrance[0];
        int y = entrance[1];

        int[][] dis = new int[m][n];
        for(int[] d : dis) Arrays.fill(d, Integer.MAX_VALUE);

        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
        q.offer(new int[]{entrance[0], entrance[1], 0});
        dis[entrance[0]][entrance[1]] = 0;

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();

            for(int[] d : dir) {
                int i = p[0]+d[0];
                int j = p[1]+d[1];
                if(i<m && j<n && i>=0 && j>=0 && maze[i][j] == '.' && p[2]+1<dis[i][j]) {
                    q.offer(new int[]{i, j, p[2]+1});
                    dis[i][j] = p[2]+1;
                }
            }
        }

        if(x == 0 || x == m-1 || y == 0 || y == n-1) dis[x][y] = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<m; i++) {
            ans = Math.min(ans, Math.min(dis[i][0], dis[i][n-1]));
        }
        for(int i = 0; i<n; i++) {
            ans = Math.min(ans, Math.min(dis[0][i], dis[m-1][i]));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}