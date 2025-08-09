class Solution {
    private double calc(int a, int b, double[][] dp) {
        if(a<=0 && b<=0) return 0.5;
        if(a<=0) return 1.0;
        if(b<=0) return 0.0;
        if(dp[a][b] != -1.0) return dp[a][b];

        double ans = 0.0;
        ans+=0.25*calc(a-4, b, dp);
        ans+=0.25*calc(a-3, b-1, dp);
        ans+=0.25*calc(a-2, b-2, dp);
        ans+=0.25*calc(a-1, b-3, dp);

        return dp[a][b] = ans;
    }
    public double soupServings(int n) {
        if(n>=4800) return 1.0;
        n = (int)Math.ceil(n/25.0);

        double[][] dp = new double[n+1][n+1];
        for(int i = 0; i<=n; i++) Arrays.fill(dp[i], -1.0);
        return calc(n, n, dp);
    }
}