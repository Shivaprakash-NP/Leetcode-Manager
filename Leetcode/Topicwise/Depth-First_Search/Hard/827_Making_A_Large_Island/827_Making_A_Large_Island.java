class Solution {
    int[] par;
    int[] size;
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        par = new int[n*n];
        size = new int[n*n];
        boolean[][] vis = new boolean[n][n];
        int ans = Integer.MIN_VALUE;

        for(int i =0 ; i<n*n; i++) {
            par[i] = i;
            size[i] = 1;
        }

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) {
                    vis[i][j] = true;
                    for(int[] d : dir) {
                        int ni = i+d[0];
                        int nj = j+d[1];
                        if(ni>=0 && nj>=0 && ni<n && nj<n && grid[ni][nj] == 1 && !vis[ni][nj]) {
                            int nval = ni*n+nj;
                            int val = i*n+j;
                            union(val, nval);
                        }
                    }
                }
            }
        }

        for(int v : size) ans = Math.max(ans, v);
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
                if(grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    int sum = 1;
                    for(int[] d : dir) {
                        int ni = i+d[0];
                        int nj = j+d[1];
                        if(ni>=0 && nj>=0 && ni<n && nj<n && grid[ni][nj] == 1) {   
                            int val = ni*n+nj;                 
                            set.add(find(par[val]));
                        }
                    }
                    for(int v : set) sum+=size[v];
                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans;
    }

    private int find(int a) {
        if(a != par[a]) par[a] = find(par[a]);
        return par[a];
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return;

        if(size[a] > size[b]) {
            par[b] = a;
            size[a]+=size[b];
        } else {
            par[a] = b;
            size[b]+=size[a];
        }
    }
}