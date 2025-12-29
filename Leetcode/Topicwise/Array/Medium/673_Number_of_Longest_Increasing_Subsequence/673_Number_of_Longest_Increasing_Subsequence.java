class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        int[] cnt = new int[n+1];
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    if(dp[j]+1 > dp[i]) cnt[i] = cnt[j];
                    if(dp[j]+1 == dp[i]) cnt[i] += cnt[j];
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int v : dp) max = Math.max(max, v);

        int ans = 0;
        for(int i = 0; i<n; i++) {
            if(dp[i] == max) ans += cnt[i];
        }

        return ans;
    }
}