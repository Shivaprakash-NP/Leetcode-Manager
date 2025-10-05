class Solution {
    int ans = Integer.MIN_VALUE;
    private void rec(int[] nums, int i, int v, int l, int[] dp) {
        if(i<0) return;
        int v1 = v^nums[i];
        int pick = 0;
        int nopick = 0;
        if(dp[i] != -1) {
            ans = Math.max(ans, dp[i]);
            return;
        }
        
        rec(nums, i-1, v1, l+1, dp);
        rec(nums, i-1, v, l, dp);

        dp[i] = 0;
        if(v1 != 0) {
            ans = Math.max(ans, l+1);
            dp[i] = Math.max(dp[i], l+1);
        }
        
        if(v != 0) {
            ans = Math.max(ans, l);
            dp[i] = Math.max(dp[i], l);
        }
    }
    
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        // long xor = 0;
        int z = 0;
        for(int v : nums) if(v == 0) z++;
        if(z == n) return 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp ,-1);
        rec(nums, n-1, 0, 0, dp);
        return ans;
        // for(int v : nums) xor^=v;
        // if(xor != 0) return n;
        // return n==1 ? 0 : n-1;
    }
}