class Solution {
    public int numSub(String s) {
        int cnt = 0;
        long ans = 0;
        long MOD = 1000000007;

        for(char c : s.toCharArray()) {
            if(c == '1') cnt++;
            else {
                ans = (ans + ((((cnt%MOD)*(cnt+1))%MOD) / 2))%MOD;
                ans %= MOD;
                cnt = 0;
            }
        }

        ans = (ans % MOD + (cnt*(cnt+1)/2))%MOD;
        return (int)ans;
    }
}