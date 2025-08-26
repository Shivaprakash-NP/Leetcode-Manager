class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;
        int diag = 0;
        int area = 0;
        for(int i = 0; i<n; i++) {
            int l = dimensions[i][0];
            int w = dimensions[i][1];
            int curdiag = l*l + w*w;
            if(diag == curdiag) area = Math.max(area, l*w);
            if(curdiag > diag) {
                diag = curdiag;
                area = l*w;
            }
        }

        return area;
    }
}