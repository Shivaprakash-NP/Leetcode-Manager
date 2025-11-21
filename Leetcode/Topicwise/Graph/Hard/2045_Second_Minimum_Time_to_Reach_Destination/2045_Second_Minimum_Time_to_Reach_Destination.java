class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        ArrayList<Integer>[] adj = new ArrayList[n+1];

        for(int i = 1; i<=n; i++) adj[i] = new ArrayList<>();

        for(int[] p : edges) {
            int u = p[0];
            int v = p[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        int[] d1 = new int[n+1];
        int[] d2 = new int[n+1];
        Arrays.fill(d1, Integer.MAX_VALUE);
        Arrays.fill(d2, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;
        q.offer(1);

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i<size; i++) {
                int u = q.poll();
                for(int v : adj[u]) {
                    if(cnt < d1[v]) {
                        d2[v] = d1[v];
                        d1[v] = cnt;
                        q.offer(v);
                    } else if(cnt > d1[v] && cnt < d2[v]) {
                        d2[v] = cnt;
                        q.offer(v);
                    }
                }
            }

            cnt++;
        }

        int tot_edge = d2[n];
        int t = 0;
        int c = 2*change;

        for(int i = 1; i<=tot_edge; i++) {
            int r = t%c;
            if(r < change) t += time;
            else t += c-r + time;
        }

        return t;
    }
}