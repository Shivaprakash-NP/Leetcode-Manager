/* REC + MEMO
   To change to iterative just follow what u did in rec
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+2][2];

        for(int i = n-1; i>=0; i--) {
            int Buy = dp[i+1][0] - prices[i];
            int noBuy = dp[i+1][1];
            int Sell = dp[i+2][1]+prices[i];
            int noSell = dp[i+1][0];

            dp[i][0] = Math.max(Sell, noSell);
            dp[i][1] = Math.max(Buy, noBuy);
        }

        return dp[0][1];
    }
}