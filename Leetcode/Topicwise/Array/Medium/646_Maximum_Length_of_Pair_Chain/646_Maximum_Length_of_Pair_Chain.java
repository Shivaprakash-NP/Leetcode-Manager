class Solution {
    private int bs(int[] dp, int v) {
        int l = 0;
        int r = dp.length;

        while(l<r) {
            int m = l + (r-l)/2;
            if(dp[m] < v) l = m+1;
            else r = m;
        }

        return l;
    } 

    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;

        Arrays.sort(pairs, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        for(int[] p : pairs) {
            int ind = bs(dp, p[0]);
            dp[ind] = Math.min(dp[ind], p[1]);
        }

        int ans = 0;
        for(int v = 0; v<=n; v++) {
            if(dp[v] != Integer.MAX_VALUE) {
                ans = v;
            }
        }

        return ans; 
    }
}