class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long xor = 0;
        int MOD = 1000000007;
        int n = nums.length;
        
        long[] num = new long[n];
        for(int i = 0; i<n; i++) num[i] = nums[i];
        
        for(int[] q : queries) {
            for(int i = q[0]; i<=q[1]; i+=q[2]) {
                num[i] = num[i]%MOD;
                num[i] = (num[i]*q[3])%MOD;
            }
        }

        for(long v : num) xor^=v;

        return (int)xor;
    }
}