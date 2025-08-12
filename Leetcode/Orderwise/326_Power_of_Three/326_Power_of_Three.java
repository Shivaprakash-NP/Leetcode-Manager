class Solution {
    public boolean rec(int n) {
        if(n == 1) return true;
        if(n%3 != 0) return false;
        return rec(n/3);
    }

    public boolean isPowerOfThree(int n) {
        if(n == 0) return false;
        return rec(n);
    }
}