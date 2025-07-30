class Solution {
    public int majorityElement(int[] nums) {
        int value = nums[0];
        int c = 1;
        for(int i = 1 ; i<nums.length ; i++)
        {
            if(nums[i]==value) c++;
            else c--;
            if(c==0)
            {
                value = nums[i];
                c++;
            }
        }
        return value;
    }
}