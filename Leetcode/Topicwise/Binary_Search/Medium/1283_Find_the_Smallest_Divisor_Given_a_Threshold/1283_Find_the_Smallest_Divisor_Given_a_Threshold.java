class Solution {
    private int is(int[] val , int m)
    {
        int ans = 0;
        for(int v : val) ans += (v+m-1)/m;
        return ans;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = Arrays.stream(nums).max().getAsInt();
        while(l<=r)
        {
            int m = (l+r)/2;
            if(is(nums , m) <= threshold) r = m - 1;
            else l = m + 1;
        }
        return l;
    }
}