class Solution {
    public int maximumPossibleSize(int[] nums) {
        int c = 1;
        int p = nums[0];
        for(int i = 1 ; i<nums.length ; i++)
        {
            if(p<=nums[i]) 
            {
                p = nums[i];
                c++;
            }
        }
        return c;
    }
}