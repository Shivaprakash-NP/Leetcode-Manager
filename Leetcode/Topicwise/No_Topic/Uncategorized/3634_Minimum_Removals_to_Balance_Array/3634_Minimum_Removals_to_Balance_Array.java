class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        int len = 0;
        int l = 0;
        Arrays.sort(nums);

        for(int r = 0; r<n ; r++) {
            while((long)nums[r] > (long)nums[l]*k) l++;
            len = Math.max(len, r-l+1);
        }

        return n-len;
    }
}