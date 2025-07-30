class Solution {
    public int pivotInteger(int n) {
        int sum = n*(n+1) / 2;
        int s = 0;
        for(int i = n ; i>0 ; i--) {
            if(s+i == sum-s) return i;
            s += i;
        }
        return -1;
    }
}