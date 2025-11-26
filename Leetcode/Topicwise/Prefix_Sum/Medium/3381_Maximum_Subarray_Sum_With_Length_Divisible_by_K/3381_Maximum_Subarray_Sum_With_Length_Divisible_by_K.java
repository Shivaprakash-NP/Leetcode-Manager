class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[k];
        Arrays.fill(dp, Long.MAX_VALUE);
        long sum = 0L;
        long ans = Long.MIN_VALUE;
        dp[0] = 0L;

        for(int i = 0; i<n; i++) {
            sum += nums[i];
            int rem = (i+1)%k;

            if(dp[rem]  != Long.MAX_VALUE) {
                ans = Math.max(ans, sum-dp[rem]);
            }

            dp[rem] = Math.min(sum, dp[rem]);
        }

        return ans;
    }
}