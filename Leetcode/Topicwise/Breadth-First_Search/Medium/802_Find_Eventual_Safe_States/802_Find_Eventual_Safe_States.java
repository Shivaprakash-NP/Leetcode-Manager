class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        ArrayList<Integer>[] adj = new ArrayList[n];
        Queue<Integer> q = new LinkedList<>();
        List<Integer> topo = new ArrayList<>();
        int[] ind = new int[n];
        for(int i = 0 ; i<n ; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i<n ; i++) {
            int[] v = graph[i];
            for(int node : v) {
                adj[node].add(i);
                ind[i]++;
            }
        }

        for(int i = 0 ; i < n ; i++) {
            if(ind[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int node = q.poll();

            for(int v : adj[node]) {
                ind[v]--;
                if(ind[v] == 0) q.offer(v);
            }

            topo.add(node);
        }

        Collections.sort(topo);

        return topo;
    }
}