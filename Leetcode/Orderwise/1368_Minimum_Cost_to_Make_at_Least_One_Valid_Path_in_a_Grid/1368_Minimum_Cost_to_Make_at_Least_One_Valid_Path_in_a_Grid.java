class Solution {
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] map = {{0,0}, {0,1}, {0,-1}, {1,0}, {-1,0}};

        int[][] distance = new int[n][m];
        for(int[] arr : distance) Arrays.fill(arr, Integer.MAX_VALUE);

        Deque<int[]> q = new ArrayDeque<>();
        q.addFirst(new int[]{0, 0, 0});      
        distance[0][0] = 0;

        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[1];
            int j = p[2];
            int dis = p[0];
            int[] cdir = map[grid[i][j]];

            for(int[] d : dir) {
                int ni = i+d[0];
                int nj = j+d[1];
            
                if(ni>=0 && nj>=0 && ni<n && nj<m) {
                    int c = 1;
                    if(d[0] == cdir[0] && d[1] == cdir[1]) c = 0;
                    if(distance[ni][nj] > dis+c) {
                        if(c == 0) q.addFirst(new int[]{dis, ni, nj});
                        else q.addLast(new int[]{dis+1, ni, nj});
                        distance[ni][nj] = dis+c;
                    }
                }
            }
        }

        return distance[n-1][m-1];
    }
}