class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();

        long[][] dist = new long[26][26];
        for(long[] row : dist) Arrays.fill(row, (long)1e8);
        for(int i = 0; i<26; i++) dist[i][i] = 0L;

        for(int i = 0; i<original.length; i++) {
            int u = original[i]-'a';
            int v = changed[i]-'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        for(int k = 0; k<26; k++) {
            for(int i = 0; i<26; i++) {
                for(int j = 0; j<26; j++) {
                    if(dist[i][k] != (long)1e8 && dist[k][j] != (long)1e8) dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }

        long c = 0;
        for(int i = 0; i<n; i++) {
            int u = source.charAt(i)-'a';
            int v = target.charAt(i)-'a';

            if(dist[u][v] >= (long)1e8) return -1L;
            c += dist[u][v];
        }

        return c;
    }
}