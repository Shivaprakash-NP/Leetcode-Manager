class Solution {
    public boolean canMakeEqual(int[] nums, int k) {
        int k1 = 0;
        int k2 = 0;
        int n = nums.length;
        int[] num1 = new int[n];
        num1 = nums.clone();
        for(int i = 0 ; i<n-1 ; i++) {
            if(num1[i] == -1) {
                k1++;
                num1[i]*=-1;
                num1[i+1]*=-1;
            }
        }
        if(num1[n-1] == -1) k1 = Integer.MAX_VALUE;
        num1 = nums.clone();
        for(int i = 0 ; i<n-1 ; i++) {
            if(num1[i] == 1) {
                k2++;
                num1[i]*=-1;
                num1[i+1]*=-1;
            }
        }
        if(num1[n-1] == 1) k2 = Integer.MAX_VALUE;
        return (k1 <= k || k2 <= k);
    }
}