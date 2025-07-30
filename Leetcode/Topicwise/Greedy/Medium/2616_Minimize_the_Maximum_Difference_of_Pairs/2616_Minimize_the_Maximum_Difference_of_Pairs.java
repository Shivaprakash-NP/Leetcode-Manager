class Solution {
    private boolean can(int m , int[] nums , int p) {
        int c = 0;
        for(int i = 1 ; i<nums.length ; i++) {
            if(nums[i]-nums[i-1]<=m) {
                c++;
                i++;
            }
        }
        return c>=p;
    }

    public int minimizeMax(int[] nums, int p) {
        if(nums.length == 0 || p == 0) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = nums[n-1]-nums[0];
        while(l<r) {
            int m = (l+r)/2;
            if(can(m , nums , p)) {
                r = m;
            } else {
                l = m+1;
            }
        }
        return l;
    }
}