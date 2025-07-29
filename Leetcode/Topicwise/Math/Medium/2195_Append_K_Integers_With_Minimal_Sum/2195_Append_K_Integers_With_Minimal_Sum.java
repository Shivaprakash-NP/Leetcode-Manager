class Solution {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        int p = 0;

        for(int v : nums) {
            if(v == p) continue;
            if(v>p+1) {
                long gap = v-p-1;
                long t = Math.min(k , gap);
                long a = p+1;
                long b = p+t;
                sum += (a+b)*t/2;
                k-=t;
            }
            if(k==0) break;
            p = v;
        }
        
        if(k>0) {
            long a = p+1;
            long b = p+k;
            sum += (a+b)*k/2;
        }
        return sum;
    }
}