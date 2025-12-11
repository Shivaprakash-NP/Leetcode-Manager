class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        int[] xmin = new int[n+1];
        int[] xmax = new int[n+1];
        int[] ymin = new int[n+1];
        int[] ymax = new int[n+1];

        Arrays.fill(xmin, Integer.MAX_VALUE);
        Arrays.fill(xmax, Integer.MIN_VALUE);
        Arrays.fill(ymin, Integer.MAX_VALUE);
        Arrays.fill(ymax, Integer.MIN_VALUE);

        for(int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            xmin[y] = Math.min(xmin[y], x);
            xmax[y] = Math.max(xmax[y], x);
            ymin[x] = Math.min(ymin[x], y);
            ymax[x] = Math.max(ymax[x], y);       
        }

        int cnt = 0;
        for(int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            boolean b1 = (x < xmax[y] && x > xmin[y]);
            boolean b2 = (y < ymax[x] && y > ymin[x]);

            if(b1&&b2) cnt++;
        }

        return cnt;
    }
}