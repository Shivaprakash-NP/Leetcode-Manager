class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dis = new int[n];
        ArrayList<int[]>[] adj = new ArrayList[n];
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for(int[] e : times) adj[e[0]-1].add(new int[]{e[1]-1, e[2]});
        
        q.offer(new int[]{0, k-1});
        dis[k-1] = 0;

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int tim = p[0];
            int u = p[1];

            for(int[] nei : adj[u]) {
                int v = nei[0];
                int w = nei[1];

                if(tim+w < dis[v]) {
                    dis[v] = tim+w;
                    q.offer(new int[]{dis[v], v});
                }
            }
        }

        for(int val : dis) {
            if(val == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, val);
        }

        return ans;
    }
}