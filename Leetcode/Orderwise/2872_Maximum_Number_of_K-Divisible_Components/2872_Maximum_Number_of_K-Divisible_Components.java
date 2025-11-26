class Solution {
    int cnt = 0;
    private long dfs(int u, ArrayList<Integer>[] adj, int[] values, boolean[] vis, int k) {

        long sum = values[u];
        for(int v : adj[u]) {
            if(!vis[v]) {
                vis[v] = true;
                sum += dfs(v, adj, values, vis, k);
            }
        }

        if(sum%k == 0) {
            cnt++;
            return 0;
        }

        return sum;
    }

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] p : edges) {
            int u = p[0];
            int v = p[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        boolean[] vis = new boolean[n];
        vis[0] = true;
        long val = dfs(0, adj, values, vis, k);

        return cnt;
    }
}