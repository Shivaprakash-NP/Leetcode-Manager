class Solution {
    public int[] findErrorNums(int[] nums) {
        long n = nums.length;
        long a_sum = n*(n+1)/2;
        long a_srsum = n*(n+1)*(2*n+1) / 6;
        long sum = 0;
        long sr_sum = 0;
        for(int v : nums) {
            sum += (long)v;
            sr_sum += (long)(v*v);
        }
        long x_minus_y = sum-a_sum;
        long x_minus_y2 = sr_sum - a_srsum;
        long x_plus_y = x_minus_y2 / x_minus_y;
        long x = ( x_plus_y + x_minus_y ) / 2;
        long y = x_plus_y - x;
        return new int[]{(int)x,(int)y};
    }
}