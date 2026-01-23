class Solution {
    int mod = (int)1e9 + 7;

    private int dfs(int i, int cur, ArrayList<Integer>[] map, int n, int[][] dp) {
        if(i == n-1) return 1;

        if(dp[i][cur] != -1) return dp[i][cur];

        int ans = 0;
        for(int v : map[cur]) {
            ans = (ans + dfs(i+1, v, map, n, dp))%mod;
        }

        return dp[i][cur] = ans;
    }

    public int countVowelPermutation(int n) {
        ArrayList<Integer>[] map = new ArrayList[5];
        for(int i = 0; i<5; i++) map[i] = new ArrayList<>();

        map[0].addAll(Arrays.asList(1));
        map[1].addAll(Arrays.asList(0, 2));
        map[2].addAll(Arrays.asList(1, 3, 4, 0));
        map[3].addAll(Arrays.asList(2, 4));
        map[4].addAll(Arrays.asList(0));

        int ans = 0;
        int[][] dp = new int[n][5];
        for(int[] indp : dp) Arrays.fill(indp, -1);

        for(int i = 0; i<5; i++) ans = (ans + dfs(0, i, map, n, dp))%mod;

        return ans;
    }
}