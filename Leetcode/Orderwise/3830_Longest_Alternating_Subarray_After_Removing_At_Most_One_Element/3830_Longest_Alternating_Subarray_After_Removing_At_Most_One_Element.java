class Solution {
    public int longestAlternating(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];

        for(int i = 0; i<n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }
        
        int[][] dp1 = new int[n][2];

        for(int i = 0; i<n; i++) {
            dp1[i][0] = 1;
            dp1[i][1] = 1;
        }
        
        for(int i = 1; i<n; i++) {
            if(nums[i-1] < nums[i]) {
                dp[i][0] += dp[i-1][1];
            } else if(nums[i-1] > nums[i]) {
                dp[i][1] += dp[i-1][0];
            }
        }

        for(int i = n-2; i>=1; i--) {
            if(nums[i] < nums[i+1]) {
                dp1[i][0] += dp1[i+1][1];
            } else if(nums[i] > nums[i+1]) {
                dp1[i][1] += dp1[i+1][0];
            }
        }
        
        int max = 0;
        for(int i = 1; i<n-1; i++) {
            if(nums[i-1] < nums[i+1]) {
                max = Math.max(dp[i-1][1]+dp1[i+1][1], max);
            } else if(nums[i-1] > nums[i+1]) {
                max = Math.max(dp[i-1][0]+dp1[i+1][0], max);
            }
        }

        for(int i = 0; i<n; i++) {
            max = Math.max(Math.max(dp[i][0], dp[i][1]), max);
            max = Math.max(Math.max(dp1[i][0], dp1[i][1]), max);
        }
        
        return max;
    }
}