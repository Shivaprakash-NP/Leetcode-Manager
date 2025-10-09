class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n+1];

        for(int i = 0; i<n; i++) {
            if(i<k) dp[i] = energy[i];
            else dp[i] = Math.max(energy[i], dp[i-k]+energy[i]);
        }

        int max = Integer.MIN_VALUE;
        for(int i = n-1; n-i-1 < k && i>=0; i--) max = Math.max(max, dp[i]);

        return max;
    }
}