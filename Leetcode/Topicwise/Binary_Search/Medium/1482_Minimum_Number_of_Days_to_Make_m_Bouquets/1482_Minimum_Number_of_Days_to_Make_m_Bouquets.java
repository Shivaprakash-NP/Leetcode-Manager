class Solution {
    private int ispossible(int[] val , int m , int k)
    {
        int ans = 0;
        int c = 0;
        for(int v : val)
        {
            if(v<=m) c++;
            else 
            {
                ans += c/k;
                c = 0;
            }
        }
        ans += c / k; 
        return ans;
    }
    public int minDays(int[] bloomDay, int m, int k) {
        if((long) k*m > bloomDay.length) return -1;
        int l = bloomDay[0], r = bloomDay[0];
        for (int i = 1; i < bloomDay.length; i++) 
        {
            if (bloomDay[i] < l) l = bloomDay[i];
            if (bloomDay[i] > r) r = bloomDay[i];
        }
        while(l<=r)
        {
            int mid = l + (r-l)/2;
            if(ispossible(bloomDay , mid , k) < m) l = mid + 1;
            else r = mid - 1;
        }
        return l;
    }
}