class SegmentTree {
    int[] xs;
    int[] cnt;
    long[] cover;

    public SegmentTree(int[] xs) {
        int n = xs.length;
        this.xs = xs;
        cnt = new int[4*n];
        cover = new long[4*n];
    }

    public long query() {
        return cover[0];
    }

    public void update(int xl, int xr, int l, int r, int op, int pos) {
        if (xs[r + 1] <= xl || xs[l] >= xr) return;

        if(xs[l] >= xl && xs[r+1] <= xr) {
            cnt[pos] += op;
        } else {
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
    public double separateSquares(int[][] squares) {
        Set<Integer> distX = new HashSet<>();
        List<int[]> events = new ArrayList<>();

        for(int[] sq : squares) {
            int x = sq[0];
            int y = sq[1];
            int l = sq[2];
            distX.add(x);
            distX.add(x+l);
            events.add(new int[]{y, x, x+l, 1});
            events.add(new int[]{y+l, x, x+l, -1});
        }

        int[] xs = distX.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(xs);
        int n = xs.length;

        SegmentTree segT = new SegmentTree(xs);

        Collections.sort(events, (a, b) -> a[0]-b[0]);

        double totArea = 0;
        int preY = events.get(0)[0];

        for(int[] event : events) {
            int y = event[0];
            int xl = event[1];
            int xr = event[2];
            int op = event[3];
            long len = segT.query();
            int hei = y-preY;
            totArea += (double) (len*hei);
            segT.update(xl, xr, 0, n-2, op, 0);
            preY = y;
        }

        double accArea = 0;
        preY = events.get(0)[0];

        for(int[] event : events) {
            int y = event[0];
            int xl = event[1];
            int xr = event[2];
            int op = event[3];
            long len = segT.query();
            int hei = y-preY;
            double curArea = (double) (len*hei);

            if(accArea + curArea >= totArea / 2.0) {
                return (double)preY + (totArea/2.0 - accArea)/(double)len;
            }
            
            accArea += curArea;
            segT.update(xl, xr, 0, n-2, op, 0);
            preY = y;
        }

        return 0.0;
    }
}