class Solution {
    private boolean dfs(int node , int c , int[][] adj , int[] vis) {
        boolean cur = true;

        vis[node] = c;
        for(int v : adj[node]) {
            if(vis[v] != 0) {
                if(vis[v] == c) return false;
            } else {
                cur = cur&&dfs(v, (c==1)?2:1 , adj , vis);
            }
        }

        return cur;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n];

        for(int i = 0 ; i<n ; i++) {
            if(vis[i] == 0) {
                if(!dfs(i, 1, graph, vis)) return false;
            }
        }

        return true;
    }
}