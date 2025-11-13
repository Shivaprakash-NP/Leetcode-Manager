class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int one = 0;
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '0') {
                while(i<n-1 && s.charAt(i) == s.charAt(i+1)) i++;
                cnt += one;
            } else one++;
        }

        return cnt;
    }
}