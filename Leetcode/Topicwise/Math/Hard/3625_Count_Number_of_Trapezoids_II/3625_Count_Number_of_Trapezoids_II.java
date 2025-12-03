class Solution {
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    } 

    private <T> long compute(ArrayList<T> list) {
        Map<T, Long> cnt = new HashMap<>();
        for(T d : list) cnt.put(d, cnt.getOrDefault(d, 0L)+1L);

        long sum = 0L;
        long sum_sqr = 0L;
        for(long v : cnt.values()) {
            sum += v;
            sum_sqr += v*v;
        }

        return (sum*sum - sum_sqr)/2L;
    }

    public int countTrapezoids(int[][] points) {
        int n = points.length;
        long ans = 0L;

        Map<String, ArrayList<Long>> slopeToInt = new HashMap<>();
        Map<Long, ArrayList<String>> midToSlope = new HashMap<>();

        for(int i = 0; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                int x1 = points[i][0];
                int x2 = points[j][0];
                int y1 = points[i][1];
                int y2 = points[j][1];

                int dx = x2-x1;
                int dy = y2-y1;

                int g = gcd(Math.abs(dx), Math.abs(dy));
                dx /= g;
                dy /= g;

                if (dx < 0 || (dx == 0 && dy < 0)) {
                    dx = -dx;
                    dy = -dy;
                }

                long c = (long)dy * x1 - (long)dx * y1;
                String m = dy+"/"+dx;

                Long mx = (long)(x1+x2);
                Long my = (long)(y1+y2);
                long midKey = mx*100000 + my;

                slopeToInt.computeIfAbsent(m, x -> new ArrayList<>()).add(c);
                midToSlope.computeIfAbsent(midKey, x -> new ArrayList<>()).add(m);
            }
        }

        for(ArrayList<Long> list : slopeToInt.values()) {
            ans += compute(list);
        }

        for(ArrayList<String> list : midToSlope.values()) {
            ans -= compute(list);
        }

        return (int)ans;
    }
}
