class Solution {
    int MOD = (int)1e9 + 7;
    public int countGoodStrings(int low, int high, int zero, int one) {
        long[] dp = new long[high+1];

        dp[0] = 1L;

        for(int i = 0; i<=high; i++) {
            if(i >= zero) {
                dp[i] = (dp[i] + dp[i-zero])%MOD;
            }
            if(i >= one) {
                dp[i] = (dp[i] + dp[i-one])%MOD;
            }
        }

        long ans = 0L;
        for(int i = low; i<=high; i++) {
            ans = (ans + dp[i])%MOD;
        }

        return (int)(ans%MOD);
    }
}