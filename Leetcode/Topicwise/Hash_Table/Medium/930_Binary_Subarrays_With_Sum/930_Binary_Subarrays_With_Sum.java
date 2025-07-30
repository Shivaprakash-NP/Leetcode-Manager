class Solution {
    private int solve(int[] nums, int goal) {
        int l = 0;
        int ans = 0;
        int sum = 0;
        for(int r = 0 ; r<nums.length ; r++) {
            sum+=nums[r];
            while(l<=r && sum > goal) sum-=nums[l++];
            ans+=(r-l+1);
        }
        return ans;
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        return solve(nums,goal)-solve(nums,goal-1);
    }
}