class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long s1 = 0;
        long s2 = 0;
        long z1 = 0;
        long z2 = 0;
        for(int v : nums1) {
            s1 += (long)v;
            if(v == 0) {
                z1++;
                s1++;
            }
        }
        for(int v : nums2) {
            s2 += (long)v;
            if(v == 0) {
                z2++;
                s2++;
            }
        }
        if((s1 > s2 && z2 == 0) || (s2 > s1 && z1 == 0)) return -1;
        return Math.max(s1,s2);
    }
}