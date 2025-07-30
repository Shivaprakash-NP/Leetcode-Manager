class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer , Integer> val = new HashMap<>();
        int ans = 0;
        for(int v : answers)
            val.put(v , val.getOrDefault(v , 0)+1);
        for(int v : val.keySet())
        {
            if(v==0) ans+=val.get(v);
            else ans += (int) Math.ceil((double) val.get(v) / (v + 1)) * (v + 1);
        }
        return ans;
    }
}