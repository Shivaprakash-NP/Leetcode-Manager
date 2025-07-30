class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length-1;
        long ans1 = 0;
        while(l<r)
        {
            if(nums[l]+nums[r]<=upper) 
            {
                ans1+=r-l;
                l++;
            }
            else r--;
        }
        long ans2 = 0;
        l = 0;
        r = nums.length - 1;
        while(l<r)
        {
            if(nums[l]+nums[r]<=lower - 1) 
            {
                ans2+=r-l;
                l++;
            }
            else r--;
        }
        return (ans1-ans2);
    }
}