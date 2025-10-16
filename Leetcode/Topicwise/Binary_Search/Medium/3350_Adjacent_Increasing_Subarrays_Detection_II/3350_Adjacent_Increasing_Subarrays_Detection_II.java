class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = 1;
        suf[n-1] = 1;
        for(int i = 1; i<n; i++) {
            if(nums.get(i-1) < nums.get(i)) pre[i] = pre[i-1] + 1;
            else pre[i] = 1;
        }

        for(int i = n-2; i>=0; i--) {
            if(nums.get(i+1) > nums.get(i)) suf[i] = suf[i+1] + 1;
            else suf[i] = 1;
        }

        int ans = 0;
        for(int i = 0; i<n-1; i++) {
            ans = Math.max(ans, Math.min(pre[i], suf[i+1]));
        }

        return ans;
    }
}