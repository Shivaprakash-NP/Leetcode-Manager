class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int pp = 1;
        int sp = 1;
        for(int i = 0 ; i<n ; i++)
        {
            pp*=nums[i];
            sp*=nums[n-1-i];
            max = Math.max(max , Math.max(pp , sp));
            if(pp==0) pp=1;
            if(sp==0) sp=1;
        }
        return max;
    }
}