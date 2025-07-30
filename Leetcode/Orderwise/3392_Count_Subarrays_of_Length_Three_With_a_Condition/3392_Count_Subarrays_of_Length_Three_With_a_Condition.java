class Solution {
    public int countSubarrays(int[] nums) {
        int i = 0;
        int j = 1;
        int k = 2;
        int c = 0;
        while(k<nums.length)
        {
            if(2*(nums[i]+nums[k]) == nums[j]) c++;
            i++;
            j++;
            k++;
        }
        return c;
    }
}