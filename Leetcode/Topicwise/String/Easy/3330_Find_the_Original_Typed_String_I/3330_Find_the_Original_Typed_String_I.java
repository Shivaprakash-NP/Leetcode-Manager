class Solution {
    public int possibleStringCount(String word) {
        int ans = 1;
        char cur = '\0';
        int cnt = 1;
        for(char c : word.toCharArray()) {
            if(cur == c) cnt++;
            else {
                ans+=cnt-1;
                cnt = 1;
                cur = c;
            }
        }
        if(cnt > 0) ans+=cnt-1;
        return ans;
    }
}