class Solution {
    public boolean check(int[] nums) {
        int demo = 0;
        int ii = 0;
        for(int i = 0 ; i<nums.length-1 ; i++)
            if(nums[i]>nums[i+1])
            {
                demo++;
                ii = i;
            } 
        if(demo==0) return true;
        else if(demo>=2) return false;
        for(int i = 0 ; i<=ii ; i++)
            if(nums[i]<nums[nums.length-1]) return false;
        return true;
    }
}