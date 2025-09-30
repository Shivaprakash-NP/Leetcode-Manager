class Solution {
    public long splitArray(int[] nums) {
        int n = nums.length;
        boolean[] inc = new boolean[n];
        boolean[] dec = new boolean[n];
        long tot = nums[0];
        inc[0] = true;
        dec[n-1] = true;
        for(int i = 1; i<n; i++) {
            inc[i] = (inc[i-1]&&nums[i-1]<nums[i]);
            tot+=nums[i];
        }

        for(int i = n-2; i>=0; i--) dec[i] = (dec[i+1]&&nums[i+1]<nums[i]);

        long sum = 0;
        long ans = Long.MAX_VALUE;
        for(int i = 0; i<n-1; i++) {
            sum+=nums[i];
            if(inc[i]&&dec[i+1]) ans = Math.min(ans, Math.abs(tot-2*sum));
        }

        return (ans == Long.MAX_VALUE)?-1:ans;
    }
}