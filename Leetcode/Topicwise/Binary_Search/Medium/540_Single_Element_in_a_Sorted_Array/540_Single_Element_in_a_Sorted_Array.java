class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        int ans = 0;
        if(r==0) return nums[0];
        if(nums[0]!=nums[1]) return nums[0];
        if(nums[r]!=nums[r-1]) return nums[r];
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]!=nums[m-1] && nums[m]!=nums[m+1]) return nums[m];
            if((m+1)%2==0)
            {
                if(nums[m-1]==nums[m]) l = m+1;
                else r = m-1;
            }
            else
            {
                if(nums[m+1]==nums[m]) l = m+1;
                else r = m-1;
            }
        }
        return nums[l];
    }
}