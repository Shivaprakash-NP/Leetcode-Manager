class DSU {
    int[] par;
    int[] size;
    int c;

    public DSU(int n) {
        par = new int[n];
        size = new int[n];
        c = n;
        for(int i = 0; i<n; i++) {
            par[i] = i;
            size[i] = 1;
        }
    }

    public int find(int a) {
        if(par[a] != a) par[a] = find(par[a]);
        return par[a];
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;
        c--;
        if(size[a] > size[b]) {
            par[b] = a;
            size[a] += size[b];
        } else {
            par[a] = b;
            size[b] += size[a];
        }
    }
}

class Solution {
    private boolean ispossible(int m, int[][] edges, int k, int n) {
        DSU f = new DSU(n);

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];

            if(t <= m) continue;
            f.union(u, v);
        }

        return f.c >= k;
    }

    public int minTime(int n, int[][] edges, int k) {
        int l = 0;
        int r = (int)1e9;
        int ans = -1;

        Arrays.sort(edges, (a, b) -> b[2] - a[2]);
        while(l <= r) {
            int m = l+(r-l)/2;

            if(ispossible(m, edges, k, n)) {
                ans = m;
                r = m-1;
            } else l = m+1;
        }

        return ans;
    }
}