class Solution {
    private boolean is(String s , int l , int r) {
        while(l<r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else return false;
        }
        return true;
    }
    
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        boolean alr = false;
        while(l<=r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return is(s , l+1 , r) || is(s , l , r-1);
            }
        }
        return true;
    }
}