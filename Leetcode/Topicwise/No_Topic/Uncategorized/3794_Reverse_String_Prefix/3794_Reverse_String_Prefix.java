class Solution {
    public String reversePrefix(String s, int k) {
        int l = 0;
        int r = k-1;
        StringBuilder sb = new StringBuilder(s);
        while(l < r) {
            char t = sb.charAt(l);
            sb.setCharAt(l, sb.charAt(r));
            sb.setCharAt(r, t);
            l++;
            r--;
        }

        return sb.toString();
    }
}