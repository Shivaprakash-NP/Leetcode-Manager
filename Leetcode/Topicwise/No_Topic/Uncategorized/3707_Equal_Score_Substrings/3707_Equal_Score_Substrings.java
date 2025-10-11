class Solution {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int sum = 0;
        for(char c : s.toCharArray()) sum += c-'a'+1;
        int su = 0;
        for(char c : s.toCharArray()) {
            su += c-'a'+1;
            if(su*2 == sum) return true;
        }

        return false;
    }
}