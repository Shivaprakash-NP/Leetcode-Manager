class Solution {
    final int MOD = 1000000007;  // 10^9 + 7
    public int numOfWays(int n) {
        long aba = 6;
        long abc = 6;

        for(int i = 1; i<n; i++) {
            long nxt_aba = (3*aba + 2*abc)%MOD;
            long nxt_abc = (2*aba + 2*abc)%MOD;

            aba = nxt_aba;
            abc = nxt_abc;
        }

        return (int)(aba+abc)%MOD;
    }
}