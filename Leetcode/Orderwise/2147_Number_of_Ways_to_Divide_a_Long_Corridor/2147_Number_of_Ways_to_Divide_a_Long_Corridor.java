class Solution {
    final int MOD = 1000000007;  // 10^9 + 7
    public int numberOfWays(String corridor) {
        int n = corridor.length();

        long Scnt = 0;
        long plants = 0;
        long ans = 1;

        for(char c : corridor.toCharArray()) {
            if(c == 'S') {
                Scnt++;
                if(Scnt >= 2 && Scnt%2 == 1) {
                    ans = (ans*(plants+1))%MOD;
                    plants = 0;
                }
            } else if(Scnt >= 2 && Scnt%2 == 0) plants++;
        }

        return (Scnt >= 2 && Scnt%2 == 0) ? (int)ans : 0;
    }
}