class Solution {
    private boolean bfs(ArrayList<int[]>[] adj, boolean[] online, long k, int minw) {
        int n = online.length;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[]{0, 0});
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);

        while(!q.isEmpty()) {
            int[] p = q.poll();
            long dist = (long)p[0];
            int u = p[1];

            if(u == n-1) return true;

            if(dist > k) continue;

            for(int[] nei : adj[u]) {
                int v = nei[0];
                long w = (long) nei[1];
                if(w < minw || !online[v]) continue;

                if(dist+w <= k && dis[v] > dist+w) {
                    dis[v] = dist+w;
                    q.offer(new int[]{(int)dis[v], v});
                }
            }
        }

        return false;
    }
    
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        ArrayList<int[]>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        int maxw = 0;

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            adj[u].add(new int[]{v, w});
            maxw = Math.max(w, maxw);
        }

        int l = 0;
        int r = maxw;
        int ans = -1;
        while(l<=r) {
            int m = (l+r)/2;
            if(bfs(adj, online, k, m)) {
                ans = m;
                l = m+1;
            } else r = m-1;
        }

        return ans;
    }
}