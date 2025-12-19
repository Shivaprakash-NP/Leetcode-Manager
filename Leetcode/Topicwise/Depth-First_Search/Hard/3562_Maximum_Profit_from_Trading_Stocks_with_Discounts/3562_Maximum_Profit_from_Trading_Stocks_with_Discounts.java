class Solution {
    int B;
    int[] pre;
    int[] fur;
    ArrayList<Integer>[] adj;
    int[][][] dp;

    private void dfs(int u) {
        for(int v : adj[u]) dfs(v);

        for(int pb = 0; pb<=1; pb++) {
            int price = (pb == 1) ? pre[u]/2 : pre[u];
            int profit = fur[u]-price;

            int[] skip = new int[B+1];
            for(int v : adj[u]) skip = merge(skip, dp[v][0]);

            int[] take = new int[B+1];
            Arrays.fill(take, Integer.MIN_VALUE);

            if(price <= B) {
                int[] base = new int[B+1];
                for(int  v : adj[u]) base = merge(base, dp[v][1]);

                for(int b  = price; b<=B; b++) take[b] = base[b-price]+profit;
            }

            for(int b = 0; b<=B; b++) dp[u][pb][b] = Math.max(take[b], skip[b]);
        }
    }

    private int[] merge(int[] A, int[] A1) {
        int[] c = new int[B+1];

        for(int i = 0; i<=B; i++) {
            if(A[i] < 0) continue;
            for(int j = 0; i+j<=B; j++) {
                c[i+j] = Math.max(c[i+j], A[i]+A1[j]);
            }
        }

        return c;
    }
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        this.B = budget;
        this.pre = present;
        this.fur = future;
        this.dp = new int[n+1][2][B+1];

        this.adj = new ArrayList[n+1];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] p : hierarchy) {
            adj[p[0]-1].add(p[1]-1);
        }

        dfs(0);

        int ans = 0;
        for(int b = 0; b<=B; b++) ans = Math.max(ans, dp[0][0][b]);

        return ans;
    }
}