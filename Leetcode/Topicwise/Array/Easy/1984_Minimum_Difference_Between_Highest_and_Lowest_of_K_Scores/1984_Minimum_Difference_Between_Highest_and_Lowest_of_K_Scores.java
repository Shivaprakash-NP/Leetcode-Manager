class Solution {
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        for(int i = k-1; i < n; i++) {
            min = Math.min(min, nums[i]-nums[i-k+1]);
        }

        return min;
    }
}