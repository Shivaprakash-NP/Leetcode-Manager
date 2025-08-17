class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if(k == 0) return 1.0;
        if(n >= k-1+maxPts) return 1.0;
        
        double[] dp = new double[n+1];
        dp[0] = 1.0;
        double win = 1.0;
        double ans = 0;

        for(int i = 1; i<=n; i++) {
            dp[i] = win/maxPts;

            if(i<k) win+=dp[i];
            else ans+=dp[i];

            if(i-maxPts >= 0) win-=dp[i-maxPts];  
        }

        return ans;
    }
}