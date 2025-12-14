class Solution {
    public int absDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int min = 0;
        int max = 0;

        for(int i = 0; i<k; i++) min += nums[i];

        int ind = nums.length-1;
        for(int i = 0; i<k; i++) max += nums[ind--];

        return Math.abs(min-max);
    }
}