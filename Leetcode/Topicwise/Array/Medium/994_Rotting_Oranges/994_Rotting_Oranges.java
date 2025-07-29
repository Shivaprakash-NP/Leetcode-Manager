class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int fre = 0;
        int n = grid.length;
        int m = grid[0].length;

        for(int i = 0 ; i<n ; i++) {
            for(int j = 0 ; j<m ; j++) {
                if(grid[i][j] == 2) q.add(new int[]{i, j});
                else if(grid[i][j] == 1) fre++;
            }
        }

        if(fre == 0) return 0;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        int t = -1;
        while(!q.isEmpty()) {
            t++;
            int size = q.size();
            for(int i = 0 ; i<size ; i++) {
                int[] p = q.poll();
                for(int[] d : dir) {
                    int ni = d[0]+p[0];
                    int nj = d[1]+p[1];
                    if(ni>=0 && nj>=0 && ni<n && nj<m && grid[ni][nj]==1) {
                        grid[ni][nj] = 2;
                        q.add(new int[]{ni, nj});
                        fre--;
                    }
                }
            }
        }
        return (fre==0)?t:-1;
    }
}