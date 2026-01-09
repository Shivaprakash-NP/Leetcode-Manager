class Solution {
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for(int[] a : cuboids) Arrays.sort(a);
        
        Arrays.sort(cuboids, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            else if(a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });

        int[] dp = new int[n];
        for(int i = 0; i<n; i++) dp[i] = cuboids[i][2];

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<i; j++) {
                if(cuboids[j][0] <= cuboids[i][0] && 
                   cuboids[j][1] <= cuboids[i][1] && 
                   cuboids[j][2] <= cuboids[i][2] ) {
                    dp[i] = Math.max(dp[i], cuboids[i][2]+dp[j]);
                }
            }
        }

        int ans = 0;
        for(int v : dp) ans = Math.max(ans, v);

        return ans;
    }
}