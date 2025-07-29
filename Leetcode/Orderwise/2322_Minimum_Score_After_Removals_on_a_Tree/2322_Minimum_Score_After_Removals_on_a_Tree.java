class Solution {
    int[] xor;
    ArrayList<Integer>[] adj;
    Set<Integer>[] set;

    private void dfs(int par, int chi, int[] nums) {
        xor[chi] = nums[chi];
        set[chi].add(chi);

        for(int nei : adj[chi]) {
            if(nei == par) continue;
            dfs(chi, nei, nums);
            xor[chi]^=xor[nei];
            set[chi].addAll(set[nei]);
        }
    }

    private int calc(int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        return max-min;
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        xor = new int[n];
        adj = new ArrayList[n];
        set = new HashSet[n];

        for(int i = 0; i<n; i++) {
            adj[i] = new ArrayList<>();
            set[i] = new HashSet<>();
        }

        for(int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        dfs(-1, 0, nums);
        int res = Integer.MAX_VALUE;

        for(int i = 1; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                int a = i, b = j;
                int x1=0,x2=0,x3=0;

                if(set[a].contains(b)) {
                    x1 = xor[b];
                    x2 = xor[a]^x1;
                    x3 = xor[0]^xor[a];                
                } else if(set[b].contains(a)) {
                    x1 = xor[a];
                    x2 = xor[b]^x1;
                    x3 = xor[0]^xor[b];
                } else {
                    x1 = xor[a];
                    x2 = xor[b];
                    x3 = xor[0]^x2^x1;
                }

                res = Math.min(res, calc(x1, x2, x3));
            }
        }

        return res;
    }
}