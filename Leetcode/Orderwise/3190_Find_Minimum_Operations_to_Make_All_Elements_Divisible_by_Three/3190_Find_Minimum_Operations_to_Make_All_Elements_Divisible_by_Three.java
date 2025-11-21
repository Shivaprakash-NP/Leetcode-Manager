class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for(int v : nums) ans += Math.min(3-v%3, v%3);
        return ans;
    }
}