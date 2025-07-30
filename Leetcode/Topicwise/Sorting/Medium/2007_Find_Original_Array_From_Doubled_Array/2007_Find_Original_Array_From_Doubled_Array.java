class Solution {
    public int[] findOriginalArray(int[] changed) {
        Map<Integer, Integer> val = new TreeMap<>();
        int n = changed.length;
        int x = 0;
        int[] ans = new int[n/2];
        if(n%2 == 1) return new int[]{};
        for(int v : changed) {
            val.put(v , val.getOrDefault(v , 0)+1);
        }
        for(int v : val.keySet()) {
            if(val.get(v) > val.getOrDefault(v*2 , 0)) {
                return new int[]{};
            } 
            for(int i = 0 ; i < val.get(v) ; ++i) {
                ans[x++] = v;
                val.put(v*2 , val.get(v*2)-1);
            }
        }
        return ans;
    }
}