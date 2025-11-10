class Solution {
    private int rec(int i, int[] nums, int pre, int[][] dp) {
        int n = nums.length;
        if(i >= n) return 0;

        if(dp[i][pre+1] != -1) return dp[i][pre+1];

        int pick = 0;
        int nopick = rec(i+1, nums, pre, dp);
        if(pre == -1 || nums[pre] < nums[i]) pick = 1 + rec(i+1, nums, i, dp);

        return dp[i][pre+1] = Math.max(pick, nopick);
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n][n+1];
        for(int[] d : dp) Arrays.fill(d, -1);

        return rec(0, nums, -1, dp);
    }
}