class Solution {
    private int dfs(int l, int r, String s, int[][] dp) {
        if(l >= r) return 0;

        if(dp[l][r] != -1) return dp[l][r];

        int subans = 0;
        if(s.charAt(l) == s.charAt(r)) {
            subans =  dfs(l+1, r-1, s, dp);
        } else {
            int op1 = dfs(l+1, r, s, dp)+1;
            int op2 = dfs(l, r-1, s, dp)+1;
            subans = Math.min(op1, op2);
        }

        return dp[l][r] = subans;
    }

    public int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int[] indp : dp) Arrays.fill(indp, -1);

        return dfs(0, s.length()-1, s, dp);
    }
}