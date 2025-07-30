class Solution {
    public int longestOnes(int[] nums, int k) {
        int c = 0;
        int l = 0;
        int r = 0;
        int len = 0;

        while(r<nums.length) {
            if(nums[r] == 0) c++;
            while(c>k) {
                if(nums[l] == 0) c--;
                l++;
            }
            len = Math.max(len , r-l+1);
            r++;
        }
        
        return len;
    }
}