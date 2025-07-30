class Solution {
    public int[] rearrangeArray(int[] nums) {
        int i = 0;
        int np = 1;
        int pp = 0;
        int[] ans = new int[nums.length];
        while(i<nums.length)
        {
            if(nums[i]<0)
            {
                ans[np] = nums[i];   
                np+=2;
            }
            else
            {
                ans[pp] = nums[i];
                pp+=2;
            }
            i++;
        }
        return ans;
    }
}