class Solution {
    int MOD = 1000000007;

    private long rec(int rem, int n, int x, long[][] dp) {
        if(rem == 0) return 1;
        if(rem < 0) return 0;
        long p = (long) Math.pow(n, x);
        if((int)p > rem) return 0;
        if(dp[rem][n] != -1) return dp[rem][n];

        long take = rec(rem-(int)p, n+1, x, dp);
        long skip = rec(rem, n+1, x, dp);

        return dp[rem][n] = (take + skip)%MOD;
    }

    public int numberOfWays(int n, int x) {
        long[][] dp = new long[n+1][n+1];
        for(int i = 0; i<=n; i++) Arrays.fill(dp[i], -1L);
        return (int)rec(n, 1, x, dp) % MOD;
    }
}