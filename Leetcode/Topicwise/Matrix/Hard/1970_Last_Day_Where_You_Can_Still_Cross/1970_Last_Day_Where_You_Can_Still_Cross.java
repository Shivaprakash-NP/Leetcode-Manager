class Solution {
    int[][] map;
    private boolean is(int m, int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[row+1][col+1];

        for(int i = 1; i<=col; i++) {
            if(map[1][i] > m) {
                vis[1][i] = true;
                q.offer(new int[]{1, i});
            }
        }

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();
            if(p[0] == row) return true;

            for(int[] d : dir) {
                int ni = d[0]+p[0];
                int nj = d[1]+p[1];

                if(ni <= row && nj <= col && ni >= 0 && nj >= 0 && !vis[ni][nj] && map[ni][nj] > m) {
                    q.offer(new int[]{ni, nj});
                    vis[ni][nj] = true;
                }
            }
        }

        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        map = new int[row+1][col+1];
        for(int i = 1; i<=row*col; i++) {
            int[] c = cells[i-1];
            int a = c[0];
            int b = c[1];
            map[a][b] = i;
        }
        
        int l = 1;
        int r = row*col;

        int ans = 1;
        while(l <= r) {
            int m = (l+r)/2;
            if(is(m, row, col)) {
                ans = m;
                l = m+1;
            } else r = m-1;
        }

        return ans;
    }
}