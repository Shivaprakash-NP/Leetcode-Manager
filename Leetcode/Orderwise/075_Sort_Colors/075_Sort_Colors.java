class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int l = 0;
        int m = 0;
        int h = n-1;
        while(m<=h) {
            if(nums[m] == 0) {
                int tem = nums[l];
                nums[l] = nums[m];
                nums[m] = tem;
                l++;
                m++;
            } else if(nums[m] == 2) {
                int tem = nums[h];
                nums[h] = nums[m];
                nums[m] = tem;
                h--;
            } else m++;
        }
    }
}