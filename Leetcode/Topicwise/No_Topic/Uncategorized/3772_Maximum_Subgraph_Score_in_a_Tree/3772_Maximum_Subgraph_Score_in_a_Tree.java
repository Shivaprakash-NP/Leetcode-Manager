class Solution {
    private void dfs(int u, ArrayList<Integer>[] adj, int p, int[] dp, int[] good) {
        int sum = (good[u] == 1)?1:-1;

        for(int v : adj[u]) {
            if(v != p) {
                dfs(v, adj, u, dp, good);
                if(dp[v] > 0) sum += dp[v]; 
            }
        }

        dp[u] = sum;
    }

    private void dfs1(int u, ArrayList<Integer>[] adj, int p, int[] dp, int[] ans) {
        ans[u] = dp[u];

        for(int v : adj[u]) {
            if(v == p) continue;
            int valu = dp[u];
            int valv = dp[v];

            if(dp[v] > 0) dp[u] -= dp[v];
            if(dp[u] > 0) dp[v] += dp[u];

            dfs1(v, adj, u, dp, ans);

            dp[v] = valv;
            dp[u] = valu;
        }
    }
    
    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        int[] dp = new int[n];
        int[] ans = new int[n];
        
        dfs(0, adj, -1, dp, good);
        dfs1(0, adj, -1, dp, ans);

        return ans;
    }
}