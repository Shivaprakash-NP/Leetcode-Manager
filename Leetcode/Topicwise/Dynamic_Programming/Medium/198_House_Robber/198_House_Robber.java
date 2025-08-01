class Solution {
    private int rec(int[] arr, int ind, boolean isprepicked, int[][] dp) {
        if(ind >= arr.length) return 0;

        int flag = (isprepicked)?1:0;
        if(dp[ind][flag] != -1) return dp[ind][flag];

        int val1 = 0;
        if(!isprepicked) val1 = arr[ind] + rec(arr, ind+1, true, dp);
        int val2 = rec(arr, ind+1, false, dp);

        return dp[ind][flag] = Math.max(val1, val2);
    }

    public int rob(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n+1][2];
        for(int[] row : dp) Arrays.fill(row, -1);

        return rec(nums, 0, false, dp);
    }
}