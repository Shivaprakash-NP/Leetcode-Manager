class Solution {
    boolean cyc = false;
    private void dfs(int node, ArrayList<Integer>[] adj, Stack<Integer> st, boolean[] vis, boolean[] path) {
        if(cyc) return;
        
        vis[node] = true;
        path[node] = true;

        for(int v : adj[node]) {
            if(!vis[v]) {
                dfs(v, adj, st, vis, path);
            } else if(path[v]) {
                cyc = true;
                return;
            }
        }

        st.push(node);
        path[node] = false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        boolean[] vis = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];

        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        for(int i = 0; i<numCourses ; i++) adj[i] = new ArrayList<>();

        for(int[] c : prerequisites) {
            adj[c[1]].add(c[0]);
        }

        Stack<Integer> st = new Stack<>();
        for(int i = 0; i<numCourses; i++) {
            if(!vis[i]) dfs(i, adj, st, vis, path);
        }

        if(cyc) return new int[]{};
        int[] ans = new int[st.size()];

        int i = 0;
        while(!st.isEmpty()) ans[i++] = st.pop();
        return ans;
    }
}