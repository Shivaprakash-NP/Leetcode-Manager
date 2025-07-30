class Solution {
    public int characterReplacement(String s, int k) {
        int len = 0;
        int l = 0 , r = 0;
        int c = 0;
        int[] map = new int[26];
        int maxf = 0;
        while(r < s.length()) {
            map[s.charAt(r)-'A']++;
            maxf = Math.max(maxf , map[s.charAt(r)-'A']);
            if(r-l+1-maxf > k) {
                map[s.charAt(l++)-'A']--;
            }
            len = Math.max(len , r-l+1);
            r++;
        }
        return len;
    }
}