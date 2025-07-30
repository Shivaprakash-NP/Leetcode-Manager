class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        Arrays.fill(dp, 0);    
        for(int i = n-1 ; i>=0 ; i--)
        {
            long sk = (i+1<n)?dp[i+1]:0;

            int nxind = i+1+questions[i][1];
            long sol = questions[i][0] + ((nxind<n)?dp[nxind]:0);

            dp[i] = Math.max(sk , sol);
        }
        return dp[0];
    }
}