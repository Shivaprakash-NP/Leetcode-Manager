class Solution {
    private int dfs(int l, int r, String s, int[][] dp) {
        if(l == r) return 1;
        if(l > r) return 0;

        if(dp[l][r] != -1) return dp[l][r];

        int ans = 0; 
        if(s.charAt(l) == s.charAt(r)) ans = dfs(l+1, r-1, s, dp)+2;
        else {
            int op1 = dfs(l+1, r, s, dp);
            int op2 = dfs(l, r-1, s, dp);
            ans = Math.max(op1, op2);
        }

        return dp[l][r] = ans;
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] indp : dp) Arrays.fill(indp, -1);

        return dfs(0, s.length()-1, s, dp);
    }
}