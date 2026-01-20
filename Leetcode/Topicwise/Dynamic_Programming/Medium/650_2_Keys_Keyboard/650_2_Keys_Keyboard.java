/*

-to fill a val you need to use its highest factor that if for 9 use 3 three times
-so dp[1] = 0;
-then for every number find its highest factor let j be that factor then dp[i] = dp[j]+(i/j) // (i/j) bcoz use that j that much time to get i

 */
class Solution {
    public int minSteps(int n) {
        int[] dp = new int[n+1];

        for(int i = 2; i<=n; i++) {
            for(int j = i-1; j>=1; j--) {
                if(i%j == 0) {
                    dp[i] = dp[j]+(i/j);
                    break;
                }
            }
        }

        return dp[n];
    }
}