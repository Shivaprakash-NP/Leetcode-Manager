class Solution {
    private int is(int[] val , int m)
    {
        int ans = 1;
        int c = 0;
        for(int v : val)
        {
            if(c+v <= m) c+=v;
            else 
            {
                ans++;
                c = v;
            }
        }
        return ans;
    }
    public int splitArray(int[] nums, int k) {
        int l = nums[0];
        int r = nums[0];
        int bst = -1;
        int n = nums.length;
        for(int i = 1 ; i<n ; i++)
        {
            if(l<nums[i]) l = nums[i];
            r+=nums[i];
        }
        while(l<=r)
        {
            int m = l + (r-l)/2;
            int ans = is(nums , m);
            if(ans <= k)
            {
                bst = m;
                r = m-1;
            }
            else l = m+1;
        }
        return bst;
    }
}