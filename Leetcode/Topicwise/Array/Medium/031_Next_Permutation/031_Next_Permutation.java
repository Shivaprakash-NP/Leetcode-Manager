class Solution {
    void swap(int[] nums , int i , int j)
    {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }
    void reverse(int[] nums , int s)
    {
        int i = s;
        int j = nums.length-1;
        while(i<j)
        {
            swap(nums , i , j);
            i++;
            j--;
        }
    }
    public void nextPermutation(int[] nums) {
        int i1 = -1;
        int i2 = -1;
        for(int i = nums.length-2 ; i>=0 ; i--)
        {
            if(nums[i]<nums[i+1])
            {
                i1 = i;
                break;
            }
        }
        if(i1==-1)
            reverse(nums , 0);
        else
        {
            for(int i = nums.length-1 ; i>=0 ; i--)
            {
                if(nums[i]>nums[i1])
                {
                    i2 = i;
                    break;
                }
            }
            swap(nums , i1 , i2);
            reverse(nums , i1+1);
        }
    }
}