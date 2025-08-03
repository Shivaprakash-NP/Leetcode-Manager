class Solution {
    private int find(int[] nums) {
        int n = nums.length;
        int pre1 = nums[0];
        int pre2 = 0;

        for(int i = 1; i<n; i++) {
            int take = pre2 + nums[i];
            int notake = pre1;

            int cur = Math.max(take, notake);
            pre2 = pre1;
            pre1 = cur;
        }

        return pre1;
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        return Math.max(find(Arrays.copyOfRange(nums, 0, n-1)), find(Arrays.copyOfRange(nums, 1, n)));    
    }
}