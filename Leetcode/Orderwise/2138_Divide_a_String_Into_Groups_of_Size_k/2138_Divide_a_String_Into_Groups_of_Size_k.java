class Solution {
    public String[] divideString(String s, int k, char fill) {
        StringBuilder sb = new StringBuilder(s);
        while(sb.length() % k != 0) sb.append(fill);
        String[] ans = new String[sb.length()/k];
        String ss = sb.toString();
        for(int i = 0 ; i < ss.length() ; i+=k) {
            ans[i/k] = ss.substring(i , i+k);
        }
        return ans;
    }
}