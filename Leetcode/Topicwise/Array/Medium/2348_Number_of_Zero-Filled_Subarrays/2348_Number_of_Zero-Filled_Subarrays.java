class Solution {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        long c = 0;
        for(int v : nums) {
            if(v == 0) c++;
            else {
                ans += (c*(c+1))/2;
                c = 0;
            }
        }
        ans += (c*(c+1))/2;
        return ans;
    }
}