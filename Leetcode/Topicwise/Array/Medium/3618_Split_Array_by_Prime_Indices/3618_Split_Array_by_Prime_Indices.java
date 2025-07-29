class Solution {
    private boolean is(int n) {
        if(n == 0 || n == 1) return false;
        for(int i = 2; i*i <= n ; i++) {
            if(n%i == 0) return false;
        }
        return true;
    }
    public long splitArray(int[] nums) {
        long A = 0;
        long B = 0;

        for(int i = 0; i<nums.length ; i++) {
            if(is(i)) A+=nums[i];
            else B+=nums[i];
        }

        return Math.abs(A-B);
    }
}