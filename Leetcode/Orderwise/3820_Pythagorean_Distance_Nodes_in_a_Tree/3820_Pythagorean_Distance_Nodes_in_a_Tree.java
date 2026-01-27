class Solution {
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        int[][] dp = new int[n][3];
        
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];

            adj[v].add(u);
            adj[u].add(v);
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, -1, 0});

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int node = p[0];
            int par = p[1];
            int dis = p[2];
            dp[node][0] = dis;
            for(int nei : adj[node]) {
                if(nei == par) continue;
                q.offer(new int[]{nei, node, dis+1});
            }
        }

        q.offer(new int[]{y, -1, 0});

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int node = p[0];
            int par = p[1];
            int dis = p[2];
            dp[node][1] = dis;
            for(int nei : adj[node]) {
                if(nei == par) continue;
                q.offer(new int[]{nei, node, dis+1});
            }
        }

        q.offer(new int[]{z, -1, 0});

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int node = p[0];
            int par = p[1];
            int dis = p[2];
            dp[node][2] = dis;
            for(int nei : adj[node]) {
                if(nei == par) continue;
                q.offer(new int[]{nei, node, dis+1});
            }
        }

        int ans = 0;
        for(int[] d: dp) {
            Arrays.sort(d);
            if(d[2]*d[2] == d[1]*d[1]+d[0]*d[0]) ans++;
        }

        return ans;
    }
}