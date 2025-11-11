class Solution {
    private int rec(String[] str, int ind, int m, int n, int maxm, int maxn, int[][][] dp) {
        if(ind < 0) return 0;
        if(m > maxm || n > maxn) return 0;

        if(dp[ind][m][n] != -1) return dp[ind][m][n];

        int ones = 0;
        int len = str[ind].length();
        for(char c : str[ind].toCharArray()) ones += c-'0';
        int zero = len-ones;

        int pick = 0;
        int nopick = rec(str, ind-1, m, n, maxm, maxn, dp);
        if(m+zero <= maxm && n+ones <= maxn) pick = rec(str, ind-1, m+zero, ones+n, maxm, maxn, dp) + 1;

        return dp[ind][m][n] = Math.max(pick, nopick);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len+1][m+1][n+1];

        for(int i = 0; i<=len; i++) {
            for(int j = 0; j<=m; j++) {
                for(int k = 0; k<=n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return rec(strs, len-1, 0, 0, m, n, dp);
    }
}