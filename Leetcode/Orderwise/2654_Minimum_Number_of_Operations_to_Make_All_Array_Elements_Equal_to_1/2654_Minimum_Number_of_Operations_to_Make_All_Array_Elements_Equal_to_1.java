class Solution {
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public int minOperations(int[] nums) {
        int o = 0;
        int n = nums.length;

        for(int v : nums) if(v == 1) o++;
        if(o>0) return n-o;

        for(int i = 0 ; i<nums.length-1 ; i++) {
            if(gcd(nums[i] , nums[i+1]) == 1) return nums.length;
        }

        int minlen = Integer.MAX_VALUE;
        for(int i = 0 ; i <n ; i++) {
            int g = nums[i];
            for(int j = i+1 ; j<n ; j++) {
                g = gcd(g , nums[j]);
                if(g == 1) {
                    minlen = Math.min(minlen , j-i+1);
                    break;
                }
            }
        }
        return (minlen == Integer.MAX_VALUE)?-1:(minlen-1+n-1);
    }
}