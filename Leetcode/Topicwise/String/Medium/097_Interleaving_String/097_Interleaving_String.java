/* 
- Just rec that carries i and j that is currect index of the s1 and s2
- no need to index of s3 because index of s3 is i+j
- so this helps in reducing the dp state from 3 to 2
- for iterative just do what you done in rec one by one
    - identify base case
    - indentify the flow of rec and find the loop direction 
    - then do what you do in rec
*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length() != s3.length()) return false;

        int n = s3.length();
        if(n == 0) return true;

        boolean[][] dp = new boolean[n+1][n+1];

        for(int i = 0; i<s2.length(); i++) dp[s1.length()][i] = s3.substring(s1.length()+i).equals(s2.substring(i));

        for(int i = 0; i<s1.length(); i++) dp[i][s2.length()] = s3.substring(s2.length()+i).equals(s1.substring(i));

        for(int i = s1.length()-1; i>=0; i--) {
            for(int j = s2.length()-1; j>=0; j--) {
                boolean can = false;
                if(s1.charAt(i) == s3.charAt(i+j)) {
                    can |= dp[i+1][j];
                }
                if(s2.charAt(j) == s3.charAt(i+j)) {
                    can |= dp[i][j+1];
                }

                dp[i][j] = can;     
            }       
        }

        return dp[0][0];
    }
}