class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;

        for(int i = n-1; i>=0; i--) {
            int cur = shelfWidth;
            int maxhei = 0;
            for(int j = i; j<n; j++) {
                if(books[j][0] > cur) break;
                cur -= books[j][0];
                maxhei = Math.max(maxhei, books[j][1]);
                dp[i] = Math.min(dp[i], dp[j+1]+maxhei);
            }
        }

        return dp[0];
    }
}