class Solution {
    public int countPaths(int n, int[][] roads) {
        long MOD = 1000000007L; 
        ArrayList<long[]>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] e : roads) {
            long u = e[0];
            long v = e[1];
            long w = e[2];
            adj[(int)u].add(new long[]{v, w});
            adj[(int)v].add(new long[]{u, w});
        }

        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);

        long[] way = new long[n];
        Arrays.fill(way, 0);

        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        q.offer(new long[]{0, 0});
        dis[0] = 0;
        way[0] = 1;
        
        while(!q.isEmpty()) {
            long[] p = q.poll();
            long u = p[1];
            long d = p[0];

            for(long[] nei : adj[(int)u]) {
                long v = nei[0];
                long dd = nei[1];
                if(d+dd == dis[(int)v]) way[(int)v]=(way[(int)v]+way[(int)u])%MOD;
                if(d+dd < dis[(int)v]) {
                    dis[(int)v] = d+dd;
                    q.offer(new long[]{d+dd, v});
                    way[(int)v] = way[(int)u];
                }
            }
        }

        return (int)way[n-1];
    }
}