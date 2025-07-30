class Solution {
    private int solve(int[] nums, int goal) {
        int l = 0;
        int ans = 0;
        int c = 0;
        for(int r = 0 ; r<nums.length ; r++) {
            c+=((nums[r]%2==1)?1:0);
            while(l<=r && c > goal) c-=((nums[l++]%2==1)?1:0);
            ans+=(r-l+1);
        }
        return ans;
    }
    public int numberOfSubarrays(int[] nums, int k) {
        return solve(nums,k)-solve(nums,k-1);
    }
}