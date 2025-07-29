class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for(int v : nums) sum+=v;

        int subsum = 0;
        int l = 0;
        int len = -1;
        for(int r = 0 ; r < nums.length ; r++) {
            subsum += nums[r];

            while(l<=r && subsum > sum-x) {
                subsum -= nums[l++];
            }

            if(subsum == sum-x) len = Math.max(len , r-l+1);
        }

        return (len == -1)?-1:nums.length-len;
    }
}