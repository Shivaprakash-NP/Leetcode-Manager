class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;

        boolean dp0 = true;
        boolean dp1 = false;
        boolean dp2 = (nums[0] == nums[1]);

        for(int i = 2; i<n; i++) {
            // two equal
            // three equal
            // 3 cons
            boolean cur = false;

            if(nums[i] == nums[i-1]) cur |= dp1;
            if((nums[i] == nums[i-1] && nums[i-1] == nums[i-2]) || (nums[i]-nums[i-1] == 1 && nums[i-1]-nums[i-2] == 1)) cur |= dp0;

            dp0 = dp1;
            dp1 = dp2;
            dp2 = cur;
        }

        return dp2;
    }
}