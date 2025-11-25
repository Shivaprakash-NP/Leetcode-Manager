class Solution {
    int[] parent;
    int[] size;

    private void init(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i = 0; i<n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int find(int a) {
        if(parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    private boolean union(int a, int b, int[][] restrictions) {
        a = find(a);
        b = find(b);

        if(a == b) return true;
        for(int[] r : restrictions) {
            int u = find(r[0]);
            int v = find(r[1]);

            if((a == u && b == v) || (a == v && b == u)) return false;
        }

        if(size[a] > size[b]) {
            parent[b] = a;
            size[a] += size[b];
        } else {
            parent[a] = b;
            size[b] += size[a];
        }

        return true;
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        init(n);
        int len = requests.length;
        boolean[] ans = new boolean[len];

        for(int i = 0; i<len; i++) {
            int a = requests[i][0];
            int b = requests[i][1];

            ans[i] = union(a, b, restrictions);
        }

        return ans;
    }
}