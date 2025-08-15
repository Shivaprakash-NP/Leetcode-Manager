class Solution {
    private boolean rec(int n) {
        if(n == 1) return true;
        if(n == 0 || n%4 != 0) return false;

        return rec(n/4);
    }

    public boolean isPowerOfFour(int n) {
        return rec(n);    
    }
}