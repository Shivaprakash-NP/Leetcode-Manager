class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for(int c : coins) {
            for(int a = 1; a<=amount; a++) {
                if(a>=c) dp[a] += dp[a-c];
            }
        }

        return dp[amount];
    }
}