class Solution {
    private long canGetKval(long m, int[] a, int[] b) {
        long val = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                int l = 0, r = b.length;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if ((long) a[i] * b[mid] <= m) l = mid + 1;
                    else r = mid;
                }
                val += l;
            } else if (a[i] < 0) {
                int l = 0, r = b.length;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if ((long) a[i] * b[mid] <= m) r = mid;
                    else l = mid + 1;
                }
                val += b.length - l;
            } else { 
                if (m >= 0) val += b.length;
            }
        }
        return val;
    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long l = -1_0000_0000_00L, r = 1_0000_0000_00L;

        while(l<r) {
            long m = l + (r - l) / 2;
            if(canGetKval(m , nums1 , nums2) < k) l = m+1;
            else r = m;
        }

        return l;
    }
}