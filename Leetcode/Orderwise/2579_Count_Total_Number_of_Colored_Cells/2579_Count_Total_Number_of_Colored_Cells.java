class Solution {
    public long coloredCells(int n) {
        return (1+((long)n * (long)(n-1) * (long) 2));
    }
}