class Solution {
    public int partitionString(String s) {
        boolean[] seen = new boolean[26];
        int ans = 1;
        for (int i = 0 ;  i<s.length() ; i++) {
            char c = s.charAt(i);
            if (seen[c - 'a']) {
                ans++;
                seen = new boolean[26];
            }
            seen[c - 'a'] = true;
        }
        return ans;
    }
}
