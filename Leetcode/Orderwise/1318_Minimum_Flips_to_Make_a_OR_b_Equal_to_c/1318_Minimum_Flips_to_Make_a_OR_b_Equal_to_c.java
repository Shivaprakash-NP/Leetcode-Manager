class Solution {
    public int minFlips(int a, int b, int c) {
        int ans = 0;
        while(a!=0 || b!=0 || c!=0) {
            int abit = a&1;
            int bbit = b&1;
            int cbit = c&1;
            if (cbit == 0) {
                if (abit == 1) ans++;
                if (bbit == 1) ans++;
            } else {
                if (abit == 0 && bbit == 0) ans++;
            }
            c = c>>1;
            a = a>>1;
            b = b>>1;
        }
        return ans;
    }
}