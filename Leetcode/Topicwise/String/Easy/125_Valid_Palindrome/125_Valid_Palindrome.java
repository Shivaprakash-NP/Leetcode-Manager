class Solution {
    public static boolean isPalindrome(String s) {
        String ss = s.replaceAll("[^a-zA-Z0-9]" , "").toLowerCase();
        StringBuilder val = new StringBuilder(ss);
        String ans = val.reverse().toString();
        return ss.equals(ans);
    }
}