class Solution {
    private void merge(int[] arr , int l , int m , int r)
    {
        ArrayList<Integer> val = new ArrayList<>();
        int le = l;
        int ri = m+1;
        while(le<=m && ri<=r)
        {
            if(arr[le]<arr[ri]) 
                val.add(arr[le++]);
            else
                val.add(arr[ri++]);
        }
        while(le<=m) val.add(arr[le++]);
        while(ri<=r) val.add(arr[ri++]);
        for(int i = 0 ; i<val.size() ; i++)
            arr[i+l] = val.get(i);
    }
    private void mergesort(int[] arr , int l , int r)
    {
        if(l>=r)return;
        int m = (l+r)/2;
        mergesort(arr , l , m);
        mergesort(arr , m+1 , r);
        merge(arr , l , m , r);
    }
    public int maximumGap(int[] nums) {
        mergesort(nums , 0 , nums.length-1);
        int max = 0;
        for(int i = 0 ; i<nums.length-1 ; i++)
        {
            max = Math.max(max , nums[i+1]-nums[i]);
        }
        return max;
    }
}