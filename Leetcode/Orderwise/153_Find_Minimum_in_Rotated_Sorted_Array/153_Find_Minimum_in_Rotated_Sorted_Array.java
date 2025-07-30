class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        int ans = Integer.MAX_VALUE;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[l]<=nums[m])
            {
                ans = Math.min(ans , nums[l]);
                l = m+1;
            }
            else
            {
                ans = Math.min(ans , nums[m]);
                r = m-1;
            }
        }
        return ans;
    }
}