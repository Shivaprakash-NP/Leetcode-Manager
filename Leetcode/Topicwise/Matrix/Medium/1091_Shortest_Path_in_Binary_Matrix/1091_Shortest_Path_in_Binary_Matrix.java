class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = -1;
        int s = 0;
        boolean f = false;

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        while(!q.isEmpty() && !f) {
            int size = q.size();
            for(int i = 0; i<size ; i++) {
                int[] p = q.poll();
                if(p[0] == n-1 && p[1] == n-1) f = true;
                for(int[] d : dir) {
                    int ni = p[0]+d[0];
                    int nj = p[1]+d[1];
                    if(ni>=0 && nj>=0 && ni<n && nj<n && grid[ni][nj] == 0) {
                        int[] add = new int[]{ni, nj};
                        q.offer(add);
                        grid[ni][nj] = -1;
                    }
                }
            }
            s++;
        }

        return (f)?s:-1;
    }
}