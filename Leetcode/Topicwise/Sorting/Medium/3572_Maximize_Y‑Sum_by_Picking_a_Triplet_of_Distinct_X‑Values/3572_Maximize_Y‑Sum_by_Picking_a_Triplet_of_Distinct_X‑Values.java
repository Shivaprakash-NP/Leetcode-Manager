class Solution {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        Map<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            int xx = x[i];
            if(map.containsKey(xx)) map.put(xx , Math.max(y[i] , map.get(xx)));
            else map.put(xx , y[i]);
        }
        if(map.size() < 3) return -1;
        int f = 0 , m = 0 , l = 0;
        for(int v : map.values()) {
            if(v>f) {
                l = m;
                m = f;
                f = v;
            } else if(v>m) {
                l = m;
                m = v;
            } else if(v>l) {
                l = v;
            }
        }
        return f+m+l;
    }
}