class Solution {
    public int minAllOneMultiple(int k) {
        if(k%2 == 0 || k%5 == 0) return -1;

        int cnt = 1;
        long n = 1L;

        while(true) {
            if(n%k == 0) return cnt;
            n *= 10L;
            n = n+1L;
            n = n%k;
            cnt++;
        }
    }
}