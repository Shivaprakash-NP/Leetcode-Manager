class Solution {
    private boolean chk(double m, int[][] squares, double totArea) {
        double area = 0;
        for(int[] sq : squares) {
            int l = sq[2];
            int y = sq[1];

            if(y < m) {
                area += ((double) l * Math.min(l, m-y));
            }
        }

        return area >= totArea/2;
    }
    
    public double separateSquares(int[][] squares) {
        double totArea = 0;
        int max = 0;

        for(int[] sq : squares) {
            int y = sq[1];
            int l = sq[2];

            totArea += ((double) l * (double) l);
            max = Math.max(max, (y+l));
        }

        double l = 0;
        double r = (double)max;
        double eps = 1e-5;

        while(Math.abs(l-r) > eps) {
            double m = (l+r)/2;
            if(chk(m, squares, totArea)) r = m;
            else l = m;
        }

        return r;
    }
}