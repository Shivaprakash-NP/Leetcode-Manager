class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for(int v : nums) max = Math.max(max , v);

        int c = 0;
        int ans = 0;

        for(int v : nums) {
            if(v == max) c++;
            else {
                ans = Math.max(ans, c);
                c = 0;
            }
        }
        
        ans = Math.max(ans, c);

        return ans;
    }
}