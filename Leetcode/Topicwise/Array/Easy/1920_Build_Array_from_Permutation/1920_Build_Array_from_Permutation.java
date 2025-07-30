class Solution {
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        int i = 0;
        for(int v : nums) ans[i++] = nums[v];
        return ans;
    }
}