class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] map = new int[n][n];
        int INF = 100000000; 

        for(int i = 0; i<n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            map[u][v] = w;
            map[v][u] = w;
        }

        for(int v = 0; v<n; v++) {
            for(int i = 0; i<n; i++) {
                for(int j = 0; j<n; j++) {
                    map[i][j] = Math.min(map[i][v]+map[v][j], map[i][j]);
                }
            }
        }

        int ans = 0;
        int rec = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++) {
            int c = 0;
            for(int j = 0; j<n; j++) {
                if(i!=j && map[i][j] <= distanceThreshold) c++;
            }
            if(c <= rec) {
                rec = c;
                ans = i;
            }
        }
        return ans;
    }
}