// subset SUM pattern

class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for(int v : stones) sum += v;
        int tar = sum/2;
        int max = 0;

        //dp[i][k] can we get sum of k, from the first i elements
        boolean[][] dp = new boolean[n+1][sum+1];
        dp[0][0] = true;

        for(int i = 1; i<=n; i++) {
            for(int s = 0; s<=sum; s++) {
                boolean nopick = dp[i-1][s];
                boolean pick = s>=stones[i-1] ? dp[i-1][s-stones[i-1]] : false;
                dp[i][s] = (pick || nopick);
                if(s <= tar && dp[i][s]) max = Math.max(s, max);
            }
        }

        return sum-2*max;
    }
}