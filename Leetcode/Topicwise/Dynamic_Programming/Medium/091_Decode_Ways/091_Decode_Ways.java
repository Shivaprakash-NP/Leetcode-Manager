class Solution {
    public int numDecodings(String s) {
        if(s.contains("00") || s.charAt(0) == '0') return 0;

        int n = s.length();
        int[] dp = new int[n+1];

        dp[0] = 1;

        for(int i = 1; i<n; i++) {
            int val = (s.charAt(i-1)-'0')*10 + (s.charAt(i)-'0');
            if(s.charAt(i) == '0' && val > 26) return 0; 
            if(s.charAt(i) == '0') {
                if(i >= 2) dp[i] = dp[i-2];
                else dp[i] = 1;
                continue;
            }

            dp[i] = dp[i-1];
            if(s.charAt(i-1) != '0' && val <= 26) dp[i] += (i>=2 ? dp[i-2] : 1);
        }

        return dp[n-1];
    }
}