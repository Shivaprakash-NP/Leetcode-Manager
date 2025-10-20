class Solution {
    String ans;
    private void solve(int i, String s, String t, int[] map) {
        int n = t.length();
        if(n == i) {
            if(s.compareTo(t) > 0) {
                if(ans == null || ans.compareTo(s) > 0) ans = s;
            }

            return;
        }

        if(map[t.charAt(i)-'a'] > 0) {
            map[t.charAt(i)-'a']--;
            solve(i+1, s+t.charAt(i), t, map);
            map[t.charAt(i)-'a']++;
        }

        for(int j = t.charAt(i)-'a'+1; j<26; j++) {
            if(map[j] > 0) {
                StringBuilder sb = new StringBuilder(s);
                sb.append((char)('a'+j));
                int[] temp = map.clone();
                temp[j]--;
                for(int k = 0; k<26; k++) {
                    while(temp[k]-- > 0) sb.append((char)('a'+k));
                }

                String d = sb.toString();
                if(ans == null || ans.compareTo(d) > 0) ans = d;
                return;
            }
        }
    }

    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] map = new int[26];

        ans = null;
        for(char c : s.toCharArray()) map[c-'a']++;

        solve(0, "", target, map);

        return ans==null?"":ans;
    }
}