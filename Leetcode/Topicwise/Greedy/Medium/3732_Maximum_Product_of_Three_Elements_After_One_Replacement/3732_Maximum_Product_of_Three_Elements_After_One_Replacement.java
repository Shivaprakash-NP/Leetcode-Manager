class Solution {
    public long maxProduct(int[] nums) {
        int n = nums.length;

        for(int i = 0; i<n; i++) nums[i] = Math.abs(nums[i]);

        Arrays.sort(nums);
        long ans = (long)nums[n-1]*(long)nums[n-2];
        ans = ans*(long)10e4;

        return ans;
    }
}