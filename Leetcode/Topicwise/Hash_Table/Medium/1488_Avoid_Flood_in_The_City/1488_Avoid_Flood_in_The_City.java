class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;

        int[] ans = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> dry = new ArrayList<>();

        for(int i = 0; i<n; i++) {
            if(rains[i] == 0) dry.add(i);
            else {
                ans[i] = -1;
                if(map.containsKey(rains[i])) {
                    int j = map.get(rains[i]);
                    int ind = Collections.binarySearch(dry, j);
                    if(ind < 0) ind = -ind-1;
                    if(ind == dry.size()) return new int[]{};
                    int dryInd = dry.get(ind);
                    ans[dryInd] = rains[i];
                    dry.remove(ind);
                    map.put(rains[i], i);
                }
                map.put(rains[i], i);
            }
        }

        for(int i = 0; i<n; i++) if(ans[i] == 0) ans[i] = 1;
        
        return ans;
    }
}