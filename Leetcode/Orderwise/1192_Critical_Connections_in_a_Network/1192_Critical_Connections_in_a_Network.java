class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    int[] time;
    int[] low;
    int t = 0;
    ArrayList<Integer>[] adj;

    private void dfs(int par, int node) {
        time[node] = t;
        low[node] = t;
        t++;

        for(int v : adj[node]) {
            if(v == par) continue;
            if(time[v] == -1) {
                dfs(node, v);
                low[node] = Math.min(low[v], low[node]);

                if(low[v] > time[node]) {
                    List<Integer> tem = new ArrayList<>();
                    tem.add(node);
                    tem.add(v);
                    ans.add(tem);
                }                
            } else low[node] = Math.min(low[node], time[v]);
        }
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        time = new int[n];
        low = new int[n];

        Arrays.fill(time, -1);
        Arrays.fill(low, Integer.MAX_VALUE);

        adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i<connections.size(); i++) {
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(-1, 0);

        return ans;
    }
}