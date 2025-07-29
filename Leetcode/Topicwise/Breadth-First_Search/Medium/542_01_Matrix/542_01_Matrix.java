class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int c = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];

        for(int i = 0 ; i<n ; i++) {
            for(int j = 0 ; j<m ; j++) {
                if(mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                    vis[i][j] = true;
                }
            }
        }

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while(!q.isEmpty()) {
            int[] p = q.poll();
            for(int[] d : dir) {
                int ni = d[0]+p[0];
                int nj = d[1]+p[1];
                if(ni>=0 && nj>=0 && ni<n && nj<m && !vis[ni][nj]) {
                    mat[ni][nj] = mat[p[0]][p[1]] + 1;
                    q.offer(new int[]{ni, nj});
                    vis[ni][nj] = true;
                }
            }
        }

        return mat;
    }
}