class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int MOD = (int)1e9 + 7;
        List<Long> h = new ArrayList<>();
        List<Long> v = new ArrayList<>();

        h.add(1L);
        h.add((long)m);
        v.add(1L);
        v.add((long)n);
        for(long val : hFences) h.add(val);
        for(long val : vFences) v.add(val);

        Set<Long> hdif = new HashSet<>();
        Set<Long> vdif = new HashSet<>();

        for(int i = 0; i<h.size(); i++) {
            for(int j = i+1; j<h.size(); j++) {
                hdif.add(Math.abs(h.get(i)-h.get(j)));
            }
        }

        for(int i = 0; i<v.size(); i++) {
            for(int j = i+1; j<v.size(); j++) {
                vdif.add(Math.abs(v.get(i)-v.get(j)));
            }
        }

        long max = -1;
        for(long val : hdif) {
            if(vdif.contains(val)) max = Math.max(max, val);
        }

        return max == -1 ? -1 : (int)((max*max)%MOD);
    }
}