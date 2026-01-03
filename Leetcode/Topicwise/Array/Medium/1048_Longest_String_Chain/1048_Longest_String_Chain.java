class Solution {
    private boolean is(String s, String t) {
        boolean can = true;
        if(Math.abs(s.length() - t.length()) != 1) return false;

        int l = 0;
        int r = 0;

        while(l < s.length() && r < t.length()) {
            if(s.charAt(l) != t.charAt(r)) {
                if(!can) return false;
                can = false;
                l++; 
            } else {
                l++;
                r++;
            }
        }

        return true;
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int n = words.length;

        int[] dp = new int[n+1];
        Arrays.fill(dp, 1);

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<i; j++) {
                if(is(words[i], words[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 0;
        for(int v : dp) ans = Math.max(ans, v);

        return ans;
    }
}