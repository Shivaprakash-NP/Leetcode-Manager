class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int cs = 0;
        for(int v : nums)
        {
            cs += v;
            max = Math.max(max , cs);
            if(cs < 0) cs = 0;
        }
        return max;
    }
}