class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        int[][] vis = new int[m][n];
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for(int i = 0; i<m; i++) {
            q.offer(new int[]{heightMap[i][0], i, 0});
            q.offer(new int[]{heightMap[i][n-1], i, n-1});
            vis[i][0] = 1;
            vis[i][n-1] = 1;
        }

        for(int i = 1; i<n-1; i++) {
            q.offer(new int[]{heightMap[0][i], 0, i});
            q.offer(new int[]{heightMap[m-1][i], m-1, i});
            vis[0][i] = 1;
            vis[m-1][i] = 1;
        }

        int ans = 0;
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0,-1}};

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int ch = p[0];

            for(int[] d : dir) {
                int i = d[0]+p[1];
                int j = d[1]+p[2];
                if(i<m && j<n && i>=0 && j>=0 && vis[i][j] != 1) {
                    ans += Math.max(0, ch-heightMap[i][j]);
                    q.offer(new int[]{Math.max(ch, heightMap[i][j]), i, j});
                    vis[i][j] = 1;
                }
            }
        }

        return ans;
    }
}