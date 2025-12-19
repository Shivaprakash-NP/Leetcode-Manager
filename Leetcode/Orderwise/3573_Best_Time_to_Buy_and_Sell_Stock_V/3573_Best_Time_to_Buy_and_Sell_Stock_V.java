/*
State = 0, 1, 2
0 - this came from simple state
1 - came from after making normal tranc
2 - came from after making short tranc

dp[index][k][state] 
*/

class Solution {
    long[][][] dp;
    
    private long rec(int i, int k, int state, int[] prices) {
        if(i >= prices.length || k == 0) {
            return (state == 0) ? 0 : Integer.MIN_VALUE;
        }

        if(dp[i][k][state] != -1) return dp[i][k][state];
        long ans = 0;

        /*
        1 do nothing
        2 start normal
        3 start short
        */
        
        if(state == 0) {
            long dontg = rec(i+1, k, 0, prices);
            long nom = -prices[i]+rec(i+1, k-1, 1, prices);
            long shot = prices[i]+rec(i+1, k-1, 2, prices);

            ans = Math.max(dontg, Math.max(nom, shot));
        }

        /*
        1 do nothing
        2 sell
        */

        if(state == 1) {
            long dontg = rec(i+1, k, 1, prices);
            long sell = prices[i]+rec(i+1, k-1, 0, prices);

            ans = Math.max(dontg, sell);
        }

        /*
        1 do nothing
        2 buy
        */

        if(state == 2) {
            long dontg = rec(i+1, k, 2, prices);
            long buy = -prices[i]+rec(i+1, k-1, 0, prices);

            ans = Math.max(dontg, buy);
        }

        return dp[i][k][state] = ans;
    }
    
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        dp = new long[n][2*k+1][3];

        for(long[][] col : dp) {
            for(long[] d : col) {
                Arrays.fill(d, -1);
            }
        }

        return rec(0, 2*k, 0, prices);
    }
}