class Solution {
    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> val = new HashMap<>();
        int ans = 0;
        for(int i = 1 ; i  <= n ; i++)
        {
            int v = i;
            int sum = 0;
            while(v > 0)
            {
                sum+=v%10;
                v/=10;
            }
            val.put(sum , val.getOrDefault(sum , 0)+1);
        }
        int max = 0;
        for(int v : val.keySet())
        {
            max = Math.max(max , val.get(v));
        }
        for(int v : val.keySet())
        {
            if(val.get(v) == max) ans++;
        }
        return ans;
    }
}