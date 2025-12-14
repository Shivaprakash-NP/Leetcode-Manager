class Solution {
    private int cnt(String s) {
        int cnt = 0;
        for(char c : s.toCharArray()) {
            if(c == 'a' || c=='e' || c == 'i' || c == 'o' || c == 'u') cnt++;
        }

        return cnt;
    }

    private String rev(String s) {
        int l = 0;
        int r = s.length()-1;

        StringBuilder sb = new StringBuilder(s);
        while(l<r) {
            sb.setCharAt(l, s.charAt(r));
            sb.setCharAt(r, s.charAt(l));
            l++;
            r--;
        }

        return sb.toString();
    } 
    public String reverseWords(String s) {
        String[] sa = s.split(" ");
        StringBuilder sb = new StringBuilder();

        int fcnt = cnt(sa[0]);

        sb.append(sa[0]);

        for(int i = 1; i<sa.length; i++) {
            sb.append(" ");
            if(cnt(sa[i]) == fcnt) sb.append(rev(sa[i]));
            else sb.append(sa[i]);
        }

        return sb.toString();
    }
}