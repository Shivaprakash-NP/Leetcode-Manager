class Solution {
    public int maximumDifference(int[] nums) {
        int ans = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i<nums.length ; i++) {
            if(nums[i]<=min) min = nums[i];
            else ans = Math.max(ans , nums[i]-min);
        }
        return ans;
    }
}