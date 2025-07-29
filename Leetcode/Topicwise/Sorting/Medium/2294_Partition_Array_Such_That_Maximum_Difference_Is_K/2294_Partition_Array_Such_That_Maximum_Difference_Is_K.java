class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0;
        int r = 1;
        int c = 1;
        while(r < nums.length) {
            if(nums[r]-nums[l] > k) {
                c++;
                l = r;
                r = l+1;
            } else r++;
        }
        return c;
    }
}