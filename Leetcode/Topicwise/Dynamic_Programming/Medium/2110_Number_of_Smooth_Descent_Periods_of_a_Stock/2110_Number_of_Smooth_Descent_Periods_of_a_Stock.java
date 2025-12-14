class Solution {
    public long getDescentPeriods(int[] prices) {
        long cnt = 1L;
        int n = prices.length;
        long ans = 0L;

        for(int i = 1; i<n; i++) {
            if(prices[i-1]-1 == prices[i]) cnt++;
            else {
                ans += (cnt*(cnt+1))/2L;
                cnt = 1L;
            }
        }

        ans += (cnt*(cnt+1))/2L;

        return ans;
    }
}