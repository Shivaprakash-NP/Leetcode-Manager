class Solution {
    int cnt;
    private long dfs(int u, ArrayList<Integer>[] adj, int[] values, boolean[] vis, int k) {

        long sum = values[u];
        for(int v : adj[u]) {
            if(!vis[v]) {
                vis[v] = true;
                sum += dfs(v, adj, values, vis, k);
            }
        }

        if(sum == k) {
            cnt++;
            return 0;
        }

        return sum;
    }

    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];

            adj[v].add(u);
            adj[u].add(v);
        }

        int max = 0;
        int sum = 0;
        int ans = 0;
        for(int v : nums) {
            max = Math.max(max, v);
            sum += v;
        }

        for(int i = 1; i*i <= sum; i++) {
            if(sum%i != 0) continue;
            int d1 = i;
            int d2 = sum/i;
            if(d1 >= max) {
                boolean[] vis = new boolean[n];
                vis[0] = true;
                cnt = 0;
                long v = dfs(0, adj, nums, vis, i);
                if(v == 0) ans = Math.max(ans, cnt-1);
            }

            if(d1 != d2 && d2 >= max) {
                boolean[] vis = new boolean[n];
                vis[0] = true;
                cnt = 0;
                long v = dfs(0, adj, nums, vis, sum/i);
                if(v == 0) ans = Math.max(ans, cnt-1);
            }
        }

        return ans;
    }
}