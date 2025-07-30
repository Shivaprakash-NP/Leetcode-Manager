class Solution {
    private int bs(int[] arr , int tar)
    {
        int l = 0;
        int r = arr.length-1;
        while(l<=r)
        {
            int m = (l+r)/2;
            if(arr[m]==tar) return m;
            if(arr[m]<tar) l = m+1;
            else r = m-1;
        }
        return -1;
    }
    public int[] twoSum(int[] numbers, int target) {
        for(int i = 0 ; i < numbers.length ; i++)
        {
            int val = target - numbers[i];
            int ind = bs(numbers , val);
            if(ind != -1)
            {
                if(ind != i) return new int[]{i+1 , ind+1};
                else if(i+1 < numbers.length && numbers[i]==numbers[i+1]) return new int[]{i+1 , i+2};
            }
        }
        return new int[]{};
    }
}