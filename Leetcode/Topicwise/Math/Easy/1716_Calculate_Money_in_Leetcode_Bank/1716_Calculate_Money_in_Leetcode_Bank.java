class Solution {
    public int totalMoney(int n) {
        int t = n/7;
        int r = n%7;

        return ((7*t)*(7+t))/2 + r*t + ((r+1)*r)/2;
    }
}