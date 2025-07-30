class Solution {
    public boolean checkValidString(String s) {
        int l = 0 , h = 0;

        for(char c : s.toCharArray()) {
            if(c == '(') {l++;h++;}
            if(c == ')') {l = Math.max(0 , l-1) ; h--;}
            if(c == '*') {l = Math.max(0 , l-1) ; h++;}
            if(h<0) return false;
        }

        return l==0;
    }
}