class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]>[] adj = new ArrayList[n];
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] f : flights) {
            int u = f[0];
            int v = f[1];
            int c = f[2];
            adj[u].add(new int[]{v, c});
        }

        q.offer(new int[]{0, src, 0});
        dis[src] = 0;

        while(!q.isEmpty()) {
            int[] fp = q.poll();
            int stop = fp[0];
            int u = fp[1];
            int dist = fp[2];

            if(stop>k) continue;

            for(int[] nei : adj[u]) {
                int v = nei[0];
                int c = nei[1];
                if(dist+c < dis[v]) {
                    dis[v] = dist+c;
                    q.offer(new int[]{stop+1, v, dis[v]});
                }
            }
        }

        return (dis[dst]==Integer.MAX_VALUE)?-1:dis[dst];
    }
}