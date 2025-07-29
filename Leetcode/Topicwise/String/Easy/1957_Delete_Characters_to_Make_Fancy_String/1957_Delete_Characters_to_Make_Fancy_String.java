class Solution {
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int c = 1;

        for(int i = 0; i<n-1; i++) {
            if(c<3) sb.append(s.charAt(i));
            if(s.charAt(i) == s.charAt(i+1)) c++;
            else c = 1;
        }
        if(c<3) sb.append(s.charAt(n-1));

        return sb.toString();
    }
}