class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int s1 = Integer.MAX_VALUE;
        int s2 = Integer.MAX_VALUE;
        for(int i = 0 ; i<n ; i++) {
            if(nums[i] <= s1) s1 = nums[i];
            else if(nums[i] <= s2) s2 = nums[i];
            else return true;
        }
        return false;
    }
}