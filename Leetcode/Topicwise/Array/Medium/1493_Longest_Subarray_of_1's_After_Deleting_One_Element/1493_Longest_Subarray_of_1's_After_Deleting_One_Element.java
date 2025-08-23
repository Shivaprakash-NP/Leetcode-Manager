class Solution {
    public int longestSubarray(int[] nums) {
        int cur = 0;
        int ans = 0;
        int pre = 0;

        for(int val : nums) {
            if(val == 1) cur++;
            else {
                ans = Math.max(ans, cur+pre);
                pre = cur;
                cur = 0;
            }
        }

        ans = Math.max(ans, cur+pre);
        return (ans == nums.length)?ans-1:ans;
    }
}