class Solution {
    final int MOD = 1000000007;  // 10^9 + 7
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length+1;
        ArrayList<int[]>[] adj = new ArrayList[n];
        for(int i = 0; i<n; i++) adj[i] = new ArrayList<>();
        boolean[] vis = new boolean[n];

        for(int[] p : conversions) {
            int u = p[0];
            int v = p[1];
            int w = p[2];

            adj[u].add(new int[]{v,w});
        }

        int[] ans = new int[n];
        Queue<long[]> q = new LinkedList<>();

        q.add(new long[]{0, 1});

        while(!q.isEmpty()) {
            long[] p = q.poll();
            long u = p[0];
            long pro = p[1]%MOD;

            vis[(int)u] = true;
            ans[(int)u] = (int)(pro%MOD);
            for(int[] nei : adj[(int)u]) {
                long v = nei[0];
                long w = nei[1]%MOD;

                if(!vis[(int)v]) q.offer(new long[]{v, (w*pro)%MOD}); 
            }
        }

        return ans;
    }
}