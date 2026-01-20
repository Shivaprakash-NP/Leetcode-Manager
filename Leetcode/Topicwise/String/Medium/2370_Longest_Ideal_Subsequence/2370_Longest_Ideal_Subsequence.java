/* 
String + subsequence = always pick + nopick only
*/

class Solution {
    private int dfs(int i, int p, String s, int k, int[][] dp) {
        if(i == s.length()) return 0;

        if(dp[i][p+1] != -1) return dp[i][p+1];

        int pick = 0;
        int nopick = dfs(i+1, p, s, k, dp);

        if(p == -1 || Math.abs((s.charAt(i)-'a')-p) <= k) pick = dfs(i+1, s.charAt(i)-'a', s, k, dp) + 1;

        return dp[i][p+1] = Math.max(pick, nopick);
    }

    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][27];
        for(int[] dpp : dp) Arrays.fill(dpp, -1);

        return dfs(0, -1, s, k, dp);
    }
}