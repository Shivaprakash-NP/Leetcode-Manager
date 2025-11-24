class Solution {
    public int smallestRepunitDivByK(int k) {
        if(k%2 == 0 || k%5 == 0) return -1;

        int step = 1;
        int n = 0;
        while(true) {
            n = n*10 + 1;
            int tem = n%k;
            if(tem == 0) return step;
            step++;
            n = tem;
        }
    }
}