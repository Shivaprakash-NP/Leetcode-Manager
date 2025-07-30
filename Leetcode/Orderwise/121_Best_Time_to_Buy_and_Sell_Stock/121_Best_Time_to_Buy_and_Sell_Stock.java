class Solution {
    public int maxProfit(int[] prices) {
        int dp = prices[0];
        int max = 0;
        for(int i = 1 ; i<prices.length ; i++)
        {
            if(prices[i]>=dp) max = Math.max(max , prices[i] - dp);
            dp = Math.min(dp , prices[i]);
        }
        return max;
    }
}