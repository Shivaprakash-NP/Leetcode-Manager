class SegmentTree {
    int[] xs;
    int[] cnt;
    long[] cover;

    public SegmentTree(int[] xs) {
        this.xs = xs;
        int n = xs.length;
        cnt = new int[4*n];
        cover = new long[4*n];
    }

    public long query() {
        return cover[0];
    }

    public void update(int xl, int xr, int l, int r, int op, int pos) {
        if(xr <= xs[l] || xs[r+1] <= xl) return;

        if(xl <= xs[l] && xs[r+1] <= xr) cnt[pos] += op;
        else {
            int mid = (l+r)/2;
            update(xl, xr, l, mid, op, pos*2+1);
            update(xl, xr, mid+1, r, op, pos*2+2);
        }

        if(cnt[pos] > 0) {
            cover[pos] = xs[r+1]-xs[l];
        } else {
            if(l == r) cover[pos] = 0;
            else cover[pos] = cover[pos*2+1]+cover[pos*2+2];
        }
    }
}

class Solution {
    public int rectangleArea(int[][] rectangles) {
        long ans = 0;
        long MOD = (long)1e9 + 7;

        Set<Integer> distX = new HashSet<>();
        List<int[]> events = new ArrayList<>();

        for(int[] rect : rectangles) {
            int xl = rect[0];
            int xr = rect[2];
            int yb = rect[1];
            int yt = rect[3];

            distX.add(xl);
            distX.add(xr);
            events.add(new int[]{yb, xl, xr, 1});
            events.add(new int[]{yt, xl, xr, -1});
        }

        int n = distX.size();
        int[] xs = new int[n];
        int ind = 0;

        for(int v : distX) xs[ind++] = v;

        Arrays.sort(xs);
        Collections.sort(events, (a, b) -> a[0] - b[0]);

        SegmentTree segT = new SegmentTree(xs);

        int py = events.get(0)[0];
        for(int[] event : events) {
            int y = event[0];
            int xl = event[1];
            int xr = event[2];
            int op = event[3];

            long len = (segT.query()%MOD);
            long hei = y-py;

            ans = (ans + (len*hei)%MOD)%MOD;
            segT.update(xl, xr, 0, n-2, op, 0);
            py = y;
        }

        return (int)(ans%MOD);
    }
}