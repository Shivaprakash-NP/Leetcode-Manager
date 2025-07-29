class Solution {
    public int maxFrequency(int[] nums, int k) {
        int l = 0;
        int ans = 0;
        long sum = 0;
        
        Arrays.sort(nums);

        for(int r = 0; r<nums.length; r++) {
            sum+=nums[r];

            while((long)(r-l+1)*nums[r]-sum > k) {
                sum-=nums[l];
                l++;
            }

            ans = Math.max(ans, r-l+1);
        }

        return ans;
    }
}