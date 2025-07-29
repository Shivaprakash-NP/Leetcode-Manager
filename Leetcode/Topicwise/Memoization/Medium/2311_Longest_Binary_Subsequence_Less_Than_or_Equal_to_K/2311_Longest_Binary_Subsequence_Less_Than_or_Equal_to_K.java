class Solution {
    public int longestSubsequence(String s, int k) {
        int z = 0;
        int n = s.length();
        for(char c : s.toCharArray()) if(c == '0') z++;

        int pow = 0;
        long val = 0;
        for(int i = n-1 ; i>=0 ; i--) {
            if(s.charAt(i) == '0')z--;
            else val += (1L << pow);
            pow++;
            if(val <= k) continue;
            else return n-i+z-1;
        }
        
        return n;
    }
}