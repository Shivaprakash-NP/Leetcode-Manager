class Solution {
    private int dfs(int[][] team, int i, int pre, int[][] dp) {
        // base case;
        // pick, nopick
        if(i == team.length) return 0;

        if(dp[i][pre+1] != -1) return dp[i][pre+1];

        int pick = 0;
        int nopick = dfs(team, i+1, pre, dp);
        if(pre == -1) {
            pick = dfs(team, i+1, i, dp) + team[i][1];
        } else {
            if(team[pre][0] == team[i][0] || team[pre][1] <= team[i][1]) pick = dfs(team, i+1, i, dp) + team[i][1];
        }

        return dp[i][pre+1] = Math.max(pick, nopick);
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] dp = new int[n+1][n+1];
        for(int[] idp : dp) Arrays.fill(idp, -1);

        int[][] team = new int[n][2];
        for(int i = 0; i<n; i++) {
            team[i][0] = ages[i];
            team[i][1] = scores[i];
        }

        Arrays.sort(team, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        return dfs(team, 0, -1, dp);
    }
}