class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> val = new HashSet<>();
        int maxc = 0;
        for(int v : nums) val.add(v);
        for(int v : val)
        {
            int cc = 1;
            if(!val.contains(v-1))
            {
                for(int i = v+1 ; ; i++)
                {
                    if(val.contains(i)) cc++;
                    else break;
                }
            } 
            maxc = Math.max(maxc , cc);
        }
        return maxc;
    }
}