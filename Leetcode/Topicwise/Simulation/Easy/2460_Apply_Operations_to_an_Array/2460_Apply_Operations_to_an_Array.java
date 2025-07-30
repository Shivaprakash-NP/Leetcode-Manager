class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length; 
        for(int i = 0 ; i<n-1 ; i++)
        {
            if(nums[i]==nums[i+1])
            {
                nums[i]*=2;
                nums[i+1]=0;
            }
        }
        int ind = 0;
        for(int v : nums) if(v!=0) nums[ind++] = v;
        while(ind<n) nums[ind++] = 0;
        return nums;
    }
}