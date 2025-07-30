class Solution {
    public int partitionDisjoint(int[] nums) {
        int lmax = nums[0];
        int omax = nums[0];
        int ans = 0;
        for(int i = 1 ; i < nums.length ; ++i) {
            omax = Math.max(omax , nums[i]);
            if(nums[i] < lmax) {
                ans = i;
                lmax = omax;
            }
        }
        return ans+1;
    }
}