class Solution {
    public int minCost(int n, int[][] edges) {
        int[] vis = new int[n];
        Arrays.fill(vis, Integer.MAX_VALUE);
        
        ArrayList<int[]>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, 2*w});
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
            );
        q.offer(new int[]{0, 0});
        vis[0] = 0;

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int u = p[1];
            int w = p[0];

            for(int[] nei : adj[u]) {
                int v = nei[0];
                int cost = nei[1];

                if(cost+w < vis[v]) {
                    vis[v] = cost+w;
                    q.offer(new int[]{vis[v], v});
                }
            }
        }

        return vis[n-1] == Integer.MAX_VALUE?-1:vis[n-1];
    }
}