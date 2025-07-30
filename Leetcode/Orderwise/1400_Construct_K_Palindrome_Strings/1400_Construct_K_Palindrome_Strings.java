class Solution {
    public boolean canConstruct(String s, int k) {
        if(s.length() < k) return false;
        int[] val = new int[26];
        for(char c : s.toCharArray()) val[c - 'a']++;
        int count = 0;
        for(int v : val) if((v&1) == 1) count++;
        if(count > k) return false;
        return true;
    }
}