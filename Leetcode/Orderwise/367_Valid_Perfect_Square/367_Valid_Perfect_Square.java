class Solution {
    public boolean isPerfectSquare(int num) {
        int l = 1;
        int r = num;
        while(l<=r) {
            int m = (l+r)/2;
            long sqr = (long) m*m;
            if(sqr == num) return true;
            if(sqr < num) l = m+1;
            else r = m-1;
        }
        return false;
    }
}