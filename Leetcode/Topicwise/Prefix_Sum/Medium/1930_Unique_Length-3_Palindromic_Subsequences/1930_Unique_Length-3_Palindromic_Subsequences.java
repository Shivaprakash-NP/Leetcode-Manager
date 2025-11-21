class Solution {
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        int n = s.length();
        
        for(char c = 'a'; c<='z'; c++) {
            int i = -1;
            int j = -1;

            for(int k = 0; k<n; k++) {
                if(s.charAt(k) == c) {
                    if(i == -1) i = k;
                    else j = k;
                }
            }

            if(j == -1) continue;

            Set<Character> set = new HashSet<>();
            for(int k = i+1; k<j; k++) {
                set.add(s.charAt(k));
            }

            ans += set.size();
        }

        return ans;
    }
}