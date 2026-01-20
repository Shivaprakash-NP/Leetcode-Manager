/* 
This is just pick not pick with a binary flag for picking for + or -
*/

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n+1][2];

        dp[0][0] = nums[0];
        for(int i = 1; i<n; i++) {            
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + nums[i]);
            dp[i][1] =  Math.max(dp[i-1][1], dp[i-1][0] - nums[i]);
        }

        long max = 0;
        for(int i = 0; i<n; i++) max = Math.max(max, dp[i][0]);

        return max;
    }
}