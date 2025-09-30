class Solution {
    private int rec(int i, int[] cost, int[] dp) {
        if(i == cost.length) return 0;
        if(dp[i] != -1) return dp[i];
        int a = cost[i]+1+rec(i+1, cost, dp);
        int b = Integer.MAX_VALUE;
        int c = Integer.MAX_VALUE;
        if(i+1 < cost.length) b = cost[i+1]+4+rec(i+2, cost, dp);
        if(i+2 < cost.length) c = cost[i+2]+9+rec(i+3, cost, dp);

        return dp[i] = Math.min(a, Math.min(b, c));
    }

    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return rec(0, costs, dp);
    }
}