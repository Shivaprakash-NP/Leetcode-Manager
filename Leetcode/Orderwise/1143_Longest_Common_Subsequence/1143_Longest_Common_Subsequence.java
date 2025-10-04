class Solution {
    private int rec(String s1, String s2, int i1, int i2, int[][] dp) {
        if(i1<0 || i2<0) return 0;
        if(dp[i1][i2] != -1) return dp[i1][i2];

        if(s1.charAt(i1) == s2.charAt(i2)) 
            return dp[i1][i2] = 1+rec(s1, s2, i1-1, i2-1, dp);

        return dp[i1][i2] = Math.max(rec(s1, s2, i1, i2-1, dp), rec(s1, s2, i1-1, i2, dp));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[Math.max(l1, l2)][Math.max(l1, l2)];
        for(int[] d : dp) Arrays.fill(d, -1);
        return rec(text1, text2, l1-1, l2-1, dp);    
    }
}