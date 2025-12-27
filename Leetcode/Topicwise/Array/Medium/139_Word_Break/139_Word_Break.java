class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        boolean[] dp = new boolean[n+1];
        for(int i = 0; i<n; i++) {
            if(i > 0 && !dp[i-1]) continue;
            for(String dic : wordDict) {
                int len = dic.length();
                if(dic.equals(s.substring(i, Math.min(n, i+len)))) dp[i+len-1] = true;
            }
        }

        return dp[n-1];
    }   
}