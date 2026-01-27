/*
This is just rec so if u see ? or same char you need to match and go to i+1, j+1
else if u see * then you can either match a empty substring do to i, j+! or just match 1 char in s and keep that * alive that is i+1, j

this keeping * alive is due to avoid TLE bcoz if u run a loop if length s.length() everytime u see a * then that will give TLE
*/

class Solution {
    String s;
    String p;
    private boolean dfs(int i, int j, int[][] dp) {
        if(i >= s.length()) {
            for(int k = j; k<p.length(); k++) {
                if(p.charAt(k) != '*') return false;
            }
            return true;
        }
        
        if(i >= s.length() || j >= p.length()) return false;
        if(dp[i][j] != -1) return dp[i][j] == 1;

        boolean is = false;
        
        if(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
            is = dfs(i+1, j+1, dp);
        } else if(p.charAt(j) == '*') {
            is = dfs(i, j+1, dp) || dfs(i+1, j, dp);
        } else return false;

        dp[i][j] = is ? 1 : 0;

        return is;
    }

    public boolean isMatch(String a, String b) {
        s = a;
        p = b;
        int n = s.length();
        int m = p.length();
        int[][] dp = new int[n][m];

        for(int[] indp : dp) Arrays.fill(indp, -1);

        return dfs(0, 0, dp);
    }
}