class Solution {
    public int minSensors(int n, int m, int k) {
        int cover = 2*k+1;
        int row = (n+2*k)/cover;
        int col = (m+2*k)/cover;
        return row*col;
    }
}