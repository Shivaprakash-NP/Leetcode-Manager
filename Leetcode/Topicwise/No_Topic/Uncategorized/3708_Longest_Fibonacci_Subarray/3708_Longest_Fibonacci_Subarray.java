class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;

        int c = 2;
        for(int i = 2; i<n; i++) {
            if(nums[i-2] + nums[i-1] == nums[i]) c++;
            else {
                max = Math.max(max, c);
                c = 2;
            }
        }

        max = Math.max(max, c);
        return max;
    }
}