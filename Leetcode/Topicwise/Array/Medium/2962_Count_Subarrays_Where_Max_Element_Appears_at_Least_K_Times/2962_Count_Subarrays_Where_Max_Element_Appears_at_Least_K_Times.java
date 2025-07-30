class Solution {
    public long countSubarrays(int[] nums, int k) {
        long n = nums.length;
        long max = nums[0];
        for(int i = 1 ; i<n ; i++) max = Math.max(max , nums[i]);
        long tot = n*(n+1) / 2;
        long ans = 0;
        long l = 0;
        long c = 0;
        for(long r = 0 ; r < n ; r++) {
            if(nums[(int)r] == max) c++;
            while(c > k-1) {
                if(nums[(int)l] == max) c--;
                l++;
            }
            ans += (r-l+1);
        }
        return tot-ans;
    }
}