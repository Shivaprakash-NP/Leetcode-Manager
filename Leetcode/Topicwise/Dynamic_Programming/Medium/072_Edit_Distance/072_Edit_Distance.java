class Solution {

    private int rec(int i, int j, String s, String t, int[][] dp) {
        if(i == s.length() || j == t.length()) {
            return Math.max(s.length()-i, t.length()-j);
        }

        if(dp[i][j] != -1) return dp[i][j];

        int ans = 0;
        if(s.charAt(i) == t.charAt(j)) {
            ans = rec(i+1, j+1, s, t, dp);
        } else {
            int op1 = rec(i+1, j+1, s, t, dp)+1;
            int op2 = rec(i+1, j, s, t, dp)+1;
            int op3 = rec(i, j+1, s, t, dp)+1;
            ans = Math.min(op1, Math.min(op2, op3));
        }

        return dp[i][j] = ans;
    }
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1+1][l2+1];

        for(int[] d : dp) Arrays.fill(d, -1);
        return rec(0, 0, word1, word2, dp);
    }
}