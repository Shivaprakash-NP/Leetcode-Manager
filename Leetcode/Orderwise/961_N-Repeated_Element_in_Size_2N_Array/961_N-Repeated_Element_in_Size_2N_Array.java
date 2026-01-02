class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;

        for(int i = 0; i<n-1; i++) {
            if(nums[i] == nums[i+1]) return nums[i];
        }

        for(int i = 0; i<n-2; i++) {
            if(nums[i] == nums[i+2]) return nums[i];
        }

        return nums[0];
    }
}