class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] presat = new long[n+1];
        long[] preprice = new long[n+1];

        for(int i = 0; i<n; i++) {
            presat[i+1] = presat[i]+(long)(prices[i]*strategy[i]);
            preprice[i+1] = prices[i]+preprice[i];
        }

        long max = presat[n];
        
        for(int i = 0; i<=n-k; i++) {
            long tem = presat[i]+(preprice[i+k]-preprice[i+k/2])+presat[n]-presat[i+k];
            max = Math.max(max, tem);
        }

        return max;
    }
}