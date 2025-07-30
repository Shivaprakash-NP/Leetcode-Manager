class Solution {
    public int beautySum(String s) {
        int sum = 0;
        for(int i = 0 ; i<s.length() ; i++)
        {   
            int[] arr = new int[26];
            for(int j = i ; j<s.length() ; j++)
            {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                arr[s.charAt(j)-97]++;
                for(int v : arr)
                {
                    if(v>0)
                    {
                        min = Math.min(min , v);
                        max = Math.max(max , v);
                    }
                }
                sum+=(max-min);
            }
        }
        return sum;
    }
}