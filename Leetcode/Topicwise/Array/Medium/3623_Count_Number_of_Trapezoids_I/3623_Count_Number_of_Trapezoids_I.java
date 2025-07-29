class Solution {
    public int countTrapezoids(int[][] points) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for(int[] p : points) {
            int xp = p[0];
            int y = p[1];
            map.computeIfAbsent(y, x -> new ArrayList<>()).add(xp);
        }
        
        long MOD = 1_000_000_007;
        List<Long> com = new ArrayList<>();
        long ts = 0;
        
        for(int v : map.keySet()) {
            List<Integer> dum = map.get(v);
            int n = dum.size();
            if(n>=2) {
                long tem = (long)n*(n-1)/2;
                com.add(tem);
                ts+=tem;
            }
        }

        int m = com.size();
        long ans = 0;

        for(long val : com) {
            ts = (ts-val)%MOD;
            ans = (ans + (ts*val)%MOD)%MOD;
        }

        return (int)ans;
    }
}