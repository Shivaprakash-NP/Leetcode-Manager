class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int fo = -1;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]==target) fo = m;
            if(nums[m]>=target) r = m-1;
            else l = m+1;
        }
        l = 0;
        r = nums.length-1;
        int lo = -1;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(nums[m]==target) lo = m;
            if(nums[m]<=target) l = m+1;
            else r = m-1;
        }
        return new int[]{fo , lo};
    }
}