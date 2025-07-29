class Solution {
    int max = 0;
    int ans = 0;

    private void dfs(int[] nums, int i, int orval) {
        if(i == nums.length) {
            if(max == orval) ans++;
            return;
        }

        dfs(nums, i+1, orval | nums[i]);
        dfs(nums, i+1, orval);
    }

    public int countMaxOrSubsets(int[] nums) {
        for(int v : nums) max |= v;

        dfs(nums, 0, 0);
        
        return ans;
    }
}