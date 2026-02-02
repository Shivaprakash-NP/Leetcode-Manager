class Solution {
    private int dfs(String s, int n, int tight, int count, int[][][] dp) {
        if(n == 0) return count;

        if(dp[n][count][tight] != -1) return dp[n][count][tight];

        int ans = 0;
        int ub = (tight == 1) ? s.charAt(s.length()-n)-'0' : 9;
        for(int i = 0; i<=ub; i++) {
            ans += dfs(s, n-1, (tight == 1 && i == ub) ? 1 : 0, i == 1 ? count+1 : count, dp);
        }

        return dp[n][count][tight] = ans;
    }

    public int countDigitOne(int n) {
        String s = n+"";

        int[][][] dp = new int[s.length()+1][s.length()+1][2];
        for(int[][] lay : dp) for(int[] row : lay) Arrays.fill(row, -1);

        return dfs(s, s.length(), 1, 0, dp);
    }
}