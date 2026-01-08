class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        long[][] dp = new long[n+1][m+1];
        long ans = Long.MIN_VALUE;

        for(int i = n-1; i>=0; i--) {
            for(int j = m-1; j>=0; j--) {
                dp[i][j] = Math.max(dp[i+1][j], Math.max(nums1[i]*nums2[j]+dp[i+1][j+1], dp[i][j+1]));
                ans = Math.max(ans, nums1[i]*nums2[j]);
            }
        }

        if(ans <= 0) return (int)ans;

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return (int)ans;
    }
}