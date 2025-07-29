class Solution {
    int[] parent;
    int[] size;
    public int makeConnected(int n, int[][] connections) {
        if(n-1 > connections.length) return -1;

        parent = new int[n];
        size = new int[n];

        for(int i = 0; i<n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int ans = n;

        for(int[] c : connections) {
            int u = c[0];
            int v = c[1];
            if(union(u, v)) ans--;
        }

        return ans-1;
    }

    public int find(int u) {
        if(u != parent[u]) parent[u] = find(parent[u]);
        return parent[u];
    }

    private boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;
        if(size[a] < size[b]) {
            parent[a] = b;
            size[b]+=size[a];
        } else {
            parent[b] = a;
            size[a]+=size[b];
        }

        return true;
    }
}