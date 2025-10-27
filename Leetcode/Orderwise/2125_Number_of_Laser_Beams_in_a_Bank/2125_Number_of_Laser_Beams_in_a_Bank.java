class Solution {
    private int count(String s) {
        int cnt = 0;
        for(char c : s.toCharArray()) if(c == '1') cnt++;
        return cnt;
    } 

    public int numberOfBeams(String[] bank) {
        int pre = 0;
        int ans = 0;
        
        for(String s : bank) {
            int cur = count(s);
            if(cur == 0) continue;
            else {
                ans += cur*pre;
                pre = cur;
            }
        }

        return ans;
    }
}