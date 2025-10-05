class Solution {
    public String removeSubstring(String s, int k) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbb = new StringBuilder();
        
        for(int i = 0; i<k; i++) sbb.append("(");
        for(int i = 0; i<k; i++) sbb.append(")");
        
        String sub = sbb.toString();
        
        for(char c : s.toCharArray()) {
            sb.append(c);
            if(sb.length() >= 2*k && sb.substring(sb.length()-2*k).equals(sub)) {
                sb.setLength(sb.length()-2*k);
            }
        }

        return sb.toString();
    }
}