/*  
subset sum pattern
HOW?
    - you need to get target, you have sum, you need to make - sign for some ele with tot value val from the array to get the target
    - so making - sign for one ele reduce sum by sum-2*ele_val
    - so the eqn is sum-2*val = target
    - here the val is the sum of ele that we are gonig to put neg
    - val = (sum+target)/2
    - so find the no of ways to form val then that is the answer
*/

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;

        for(int v : nums) sum += v;
        if(sum < target) return 0;

        int val = sum - target;
        if(val%2 == 1) return 0;
        val/=2;

        int[][] dp = new int[n+1][val+1];
        dp[0][0] = 1;

        for(int i = 1; i<=n; i++) {
            for(int s = 0; s<=val; s++) {
                dp[i][s] = dp[i-1][s]+(s >= nums[i-1] ? dp[i-1][s-nums[i-1]] : 0);
            }
        }

        return dp[n][val];
    }
}